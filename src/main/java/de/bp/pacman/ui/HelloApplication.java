package de.bp.pacman.ui;

import de.bp.pacman.*;
import de.bp.pacman.options.Options;
import de.bp.pacman.ui.controllers.helloApplicationController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.HashMap;

/**
 * Pretty pacman-game
 */
public class HelloApplication extends Application {
    private int playerNumber = 1;
    private int levelNumber = 1;
    private Options options = new Options(-1, -1, 3,null, false,true);

    private Sound sound = Sound.getInstance();

    private HashMap<String,Integer> allOptions = null;

    /**
     * starts an application
     *
     * @param stage stage
     */
    @Override
    public void start(Stage stage) {
        Field field = new Field(levelNumber);
        GamePane pane = new GamePane(field, playerNumber);
        GameManager.setControllMouthMoving(allOptions.get("mouthanim"));
        GameManager.createInstance(pane, playerNumber, options, levelNumber).startGame(field);
        helloApplicationController hello = new helloApplicationController();
        hello.gameOverUpdate(GameManager.getInstance(), stage,levelNumber);
        hello.setOptions(allOptions);
        sound.getMusic().setMute(true);
        Scene scene = new Scene(pane);
        KeyEventHandler handler= new KeyEventHandler(PlayerMovement.getAllPlayer(playerNumber), GameManager.getInstance());
        scene.setOnKeyPressed(handler);
        scene.setOnKeyReleased(handler);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Starts a pacman-game
     * @param args not used
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Sets the number of players for the Ingame GUI.
     *
     * @param players number of players
     */
    public void setPlayerNumber(int players) {
        this.playerNumber = players;
    }

    /**
     * Sets the Options for the Ingame GUI.
     *
     * @param options Options Object
     */
    public void setOptions(Options options) {
        this.options = options;
    }

    /**
     * Sets the level of the map of the game
     *
     * @param levelNumber level of the game
     */
    public void setLevelNumber(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    /**
     * Sets the all the options that are not defined in the Options Class
     *
     * @param allOptions all the options of the game.
     */
    public void setAllOptions(HashMap<String, Integer> allOptions) {
        this.allOptions = allOptions;
    }
}