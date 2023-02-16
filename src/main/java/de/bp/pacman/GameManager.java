package de.bp.pacman;

import de.bp.pacman.options.Options;
import de.bp.pacman.ui.GamePane;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

import static de.bp.pacman.Direction.NONE;

/**
 * Gamanager manages the pacman-game
 */
public class GameManager implements Gameboard {
    private final Player[] players;
    private Ghost[] ghosts;

    //Richtung des Players nach dem naechsten Schritt
    private final Direction[] nextDirection;

    //2dimensionales Array mit Inhalt des Felds
    private Field field;

    //Zugang zur Grafik/UI
    private final GamePane pane;
    private Timeline clock;

    private final Options options;

    private static GameManager instance = null;

    private static final boolean controllOptionKeepKeyPressed = false;

    private static int controllMouthMoving = 10;

    private boolean closeGame = false;


    private GameManager(GamePane pane, int playerNumber, Options options, int level) {
        this.options = options;
        players = new Player[playerNumber];
        initializePlayers(playerNumber, level, controllMouthMoving);
        nextDirection = new Direction[playerNumber];
        for (int i = 0; i < playerNumber; i++) {
            nextDirection[i] = NONE;
        }
        initializeGhosts(level);
        this.pane = pane;
        for (Player player : players) {
            player.setFindWord(FindWord.getInstance(pane, level));
        }

    }

    /**
     * returns an existing gamemanager. should exist cause otherwise is pane null
     *
     * @return gamemanager
     */
    public static GameManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException();
        }
        return instance;
    }

    private boolean isOver = false;

    /**
     * @return the field of the game
     */
    public Field getField() {
        return field;
    }

    /**
     * @return game options
     */
    public Options getOptions() {
        return options;
    }

    /**
     * sets mouth Moving option
     *
     * @param mouthMoving amount of steps the mouth moving animation has
     */
    public static void setControllMouthMoving(int mouthMoving) {
        controllMouthMoving = mouthMoving;
    }

    /**
     * spawnes all ghosts for given Level
     *
     * @param levelNumber current level
     */
    private void initializeGhosts(int levelNumber) {
        int respawnx = 2;
        int respawny = 2;

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputStream levelStream = this.getClass().getClassLoader().getResourceAsStream("Levels.xml");
            Document doc = db.parse(levelStream);

            if (doc.getChildNodes().item(0).hasChildNodes()) {
                NodeList levels = doc.getChildNodes().item(0).getChildNodes();
                for (int i = 0; i < levels.getLength(); i++) {
                    Node level = levels.item(i);
                    if (level.getNodeType() == Node.ELEMENT_NODE && level.getAttributes().getNamedItem("nr").getNodeValue().equals(String.valueOf(levelNumber))) {
                        int fieldwidth = Integer.parseInt(level.getAttributes().getNamedItem("width").getNodeValue());
                        int fieldheight = Integer.parseInt(level.getAttributes().getNamedItem("height").getNodeValue());
                        if (level.hasChildNodes()) {
                            NodeList children = level.getChildNodes();
                            for (int j = 0; j < children.getLength(); j++) {
                                Node ghostsNode = children.item(j);
                                if (ghostsNode.getNodeType() == Node.ELEMENT_NODE &&
                                        ghostsNode.getNodeName().equals("fixedpoint")) {
                                    respawnx = Integer.parseInt(ghostsNode.getAttributes().getNamedItem("x").getNodeValue());
                                    respawny = Integer.parseInt(ghostsNode.getAttributes().getNamedItem("y").getNodeValue());
                                }
                                if (ghostsNode.getNodeType() == Node.ELEMENT_NODE &&
                                        ghostsNode.getNodeName().equals("ghosts")) {
                                    ghosts = new Ghost[Integer.parseInt(ghostsNode.getAttributes().getNamedItem("number").getNodeValue())];
                                    if (ghostsNode.hasChildNodes()) {
                                        NodeList ghostNodes = ghostsNode.getChildNodes();
                                        int g = 0;
                                        for (int k = 0; k < ghostNodes.getLength(); k++) {
                                            Node ghost = ghostNodes.item(k);
                                            if (ghost.getNodeType() == Node.ELEMENT_NODE) {
                                                ghosts[g] = new Ghost(Integer.parseInt(ghost.getAttributes().getNamedItem("spawnx").getNodeValue()),
                                                        Integer.parseInt(ghost.getAttributes().getNamedItem("spawny").getNodeValue()),
                                                        Color.valueOf(ghost.getAttributes().getNamedItem("color").getNodeValue()),
                                                        respawnx, respawny, fieldwidth, fieldheight);
                                                g++;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * initializes the game and starts
     *
     * @param field parcours to win
     */
    public void startGame(Field field) {
        this.field = field;
        gameLoop();
    }

    /**
     * sets moving direction of a Player
     *
     * @param playerNumber number of one special player that plays
     * @param direction    next direction
     */
    @Override
    public void nextDirection(int playerNumber, Direction direction) {
        this.nextDirection[playerNumber - 1] = direction;
    }

    /**
     * returns moving direction of a Player
     *
     * @param playerNumber the id of the player to get the next direction from
     * @return the direction a player is moving in
     */
    @Override
    public Direction getNextDirection(int playerNumber) {
        return nextDirection[playerNumber - 1];
    }

    /**
     * Returns pacman
     *
     * @return movable pacman
     */
    public Player[] getPlayer() {
        return players;
    }

    /**
     * Returns the enemies of pacman
     *
     * @return movable ghosts
     */
    public Ghost[] getGhosts() {
        return ghosts;
    }

    /**
     * Returns an existing gamemanager if there is one, otherwise creates a new.
     *
     * @param pane           graphical unit
     * @param numberOfPlayer number of players
     * @param options        options instance containing the current game options
     * @param level          the selected level
     * @return a new GameManager instance
     */
    public static GameManager createInstance(GamePane pane, int numberOfPlayer, Options options, int level) {
        instance = new GameManager(pane, numberOfPlayer, options, level);
        return instance;
    }

    private long i = 0;

    /**
     * tacts the unit steps of the game
     */
    public void gameLoop()  {
        int framesPerSecond = options.getRefreshRate();
        Duration frameDuration = Duration.millis(1000d / framesPerSecond);
        int framesUntilNextTick = framesPerSecond / options.getGameSpeed();
        clock = new Timeline(framesPerSecond, new KeyFrame(frameDuration, e -> step(framesUntilNextTick)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    /**
     * advances the game state by one frame
     *
     * @param framesUntilNextTick the amount of frames until the next tick happens
     */
    @Override
    public void step(int framesUntilNextTick) {
        if (i % (framesUntilNextTick * 1.25) == 0) {
            moveGhost();
            react();
        }
        pane.repaintGhost(0);

        if (i % framesUntilNextTick == 0) {
            movePlayer();
            react();
            winSurvivingPoints();
        }
        pane.repaintPacman((int) (((i % (framesUntilNextTick)) / (double) framesUntilNextTick) * 10));

        i = (i + 1) % framesUntilNextTick;
    }

    /**
     * moves all ghosts one step
     */
    private void moveGhost() {
        for (Ghost ghost : ghosts) {
            ghost.move();
        }
    }

    /**
     * moves the player one step
     */
    private void movePlayer() {
        for (int i = 0; i < players.length; i++) {

            if (!field.getType(players[i].getNextPositionX(nextDirection[i]), players[i].getNextPositionY(nextDirection[i])).equals(FieldType.BLOCK)) {
                players[i].move(nextDirection[i]);
            }
            /* player keeps moving in latest direction*/
            else if (options.easymove() &&
                    !field.getType(players[i].getNextPositionX(players[i].getDirection()), players[i].getNextPositionY((players[i].getDirection()))).equals(FieldType.BLOCK)) {
                players[i].move(players[i].getDirection());
            } else {
                players[i].move(NONE);
            }


        }
    }

    private void winSurvivingPoints() {
        for (Player p : players) {
            if (p.isAlive()) {
                p.addPoints(5);
            }
        }
    }

    private boolean powerUpSoundPlaying = false;
    private void react() {
        for (Player player : players) {
            switch (field.getType(player.getPositionX(), player.getPositionY())) {
                case POINT -> {
                    player.addPoints(50);
                    field.set(player.getPositionX(), player.getPositionY(), FieldType.EMPTY);
                    //should not play while power up song plays
                    if (!powerUpSoundPlaying){
                        options.getSound().SetFile(1);
                        options.getSound().chomp();
                    }

                }/*
                case SPECIAL -> {
                    player.addPoints(100);
                    field.set(player.getPositionX(), player.getPositionY(), FieldType.EMPTY);
                }*/
                case POWER_UP -> {
                    player.addPoints(50);
                    field.set(player.getPositionX(), player.getPositionY(), FieldType.EMPTY);
                    player.setInvincible();
                    if(powerUpSoundPlaying){
                        options.getSound().getClip().seek(Duration.ZERO); // restart the power up sound
                        options.getSound().play();
                        options.getSound().getClip().setOnEndOfMedia(()-> {
                            powerUpSoundPlaying = false;
                        });
                    }
                    else{
                        powerUpSoundPlaying = true;
                        options.getSound().SetFile(6);
                        options.getSound().play();
                        options.getSound().getClip().setOnEndOfMedia(()-> {
                            powerUpSoundPlaying = false;
                        });
                    }
                }
            }
            looseLifes();
            if (isWon() && !isOver) {
                setIsOver();
            }
            else if (isLost() && !isOver) {
                setIsOver();
                options.getSound().SetFile(2);
                options.getSound().play();
            }
        }
    }

    private void setIsOver() {
        isOver = true;
        clock.stop();
        clock.setCycleCount(100);
        clock.play();
        clock.setOnFinished(event -> {closeGame = true;});
    }

    /**
     * This method helps to close the game after the game is over.
     * This allows the game to run the game a little longer after it
     * is over.
     *
     * @return a boolean to close the game.
     */
    public boolean isCloseGame() {
        return closeGame;
    }

    private boolean isWon() {
        return field.isWithoutPointsAndPowerUps();
    }

    private void looseLifes() {
        for (Ghost ghost : ghosts) {
            for (Player player : players) {
                if (player.getPositionX() == ghost.getPositionX() && player.getPositionY() == ghost.getPositionY()) {
                    if (player.isInvincible()) {
                        ghost.die();
                        player.addPoints(200);
                        //should not play while power up song plays
                        if(!powerUpSoundPlaying){
                            options.getSound().SetFile(4);
                            options.getSound().play();
                        }
                    } else {
                        player.looseLife();
                        if (player.getLifes() == 0) {
                            player.die();
                        }
                    }
                }
            }
        }
    }

    private boolean isLost() {
        for (Player player : players) {
            if (player.getLifes() > 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * Initializes Players with spawnpoint given in XML
     *
     * @param numberOfPlayers number of players
     * @param levelNumber     Level Map of the game
     * @param mouthmoving     Pacman Mouth Animation
     */
    private void initializePlayers(int numberOfPlayers, int levelNumber, int mouthmoving) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputStream levelStream = this.getClass().getClassLoader().getResourceAsStream("Levels.xml");
            Document doc = db.parse(levelStream);

            if (doc.getChildNodes().item(0).hasChildNodes()) {
                NodeList levels = doc.getChildNodes().item(0).getChildNodes();
                for (int i = 0; i < levels.getLength(); i++) {
                    Node level = levels.item(i);
                    if (level.getNodeType() == Node.ELEMENT_NODE && level.getAttributes().getNamedItem("nr").getNodeValue().equals(String.valueOf(levelNumber))) {
                        int fieldwidth = Integer.parseInt(level.getAttributes().getNamedItem("width").getNodeValue());
                        int fieldheight = Integer.parseInt(level.getAttributes().getNamedItem("height").getNodeValue());
                        if (level.hasChildNodes()) {
                            NodeList children = level.getChildNodes();
                            for (int j = 0; j < children.getLength(); j++) {
                                if (children.item(j).getNodeType() == Node.ELEMENT_NODE &&
                                        children.item(j).getNodeName().equals("spawnpoint")) {
                                    for (int k = 0; k < numberOfPlayers; k++) {
                                        players[k] = new Player(new Color(Math.random(), Math.random(), Math.random(), 1.0), k + 1,
                                                Integer.parseInt(children.item(j).getAttributes().getNamedItem("x").getNodeValue()),
                                                Integer.parseInt(children.item(j).getAttributes().getNamedItem("y").getNodeValue()),
                                                fieldwidth, fieldheight,
                                                mouthmoving, options.getPlayerlives());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
