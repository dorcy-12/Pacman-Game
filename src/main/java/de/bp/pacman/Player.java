package de.bp.pacman;

import de.bp.pacman.ui.PacManPane;
import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 * Player with position, movement, image, points and lifes
 */
public class Player implements Movable {
    private FindWord findWord;

    private static final int invincibleTime = 50;

    private boolean isDead;

    private int positionX;
    private int positionY;

    private int points;
    private int lifes;

    private int invincible;

    private final int id;

    private Direction direction = Direction.NONE;

    private final PacManPane image;

    private final int fieldwidth;
    private final int fieldheight;

    /**
     * creats a player
     *
     * @param color          color of the player
     * @param id             id of the current player
     * @param startPositionX x coordinate of the spawnpoint
     * @param startPositionY y coordinate of the spawnpoint
     * @param fieldwidth     width of the field
     * @param fieldheight    height of the field
     * @param mouthMoving    mouth animation behavior
     * @param lifes          amount of lives
     */
    public Player(Color color, int id, int startPositionX, int startPositionY, int fieldwidth, int fieldheight, int mouthMoving, int lifes) {
        this.invincible = 0;
        this.id = id;
        this.points = 0;
        this.lifes = lifes;
        this.positionX = startPositionX;
        this.positionY = startPositionY;
        this.fieldwidth = fieldwidth;
        this.fieldheight = fieldheight;
        image = new PacManPane(20, 20, color, mouthMoving);
    }

    /**
     * Registers a findWord to show when the player has found the word
     *
     * @param findWord which registers the path of the player
     */
    public void setFindWord(FindWord findWord) {
        this.findWord = findWord;
    }

    /**
     * moves player in given direction
     *
     * @param direction new direction
     */
    @Override
    public void move(Direction direction) {
        if (!isDead) {
            if (this.invincible > 0) {
                invincible--;
            }
            this.direction = direction;
            positionX = Math.floorMod((positionX + direction.getX()), fieldwidth);
            positionY = Math.floorMod((positionY + direction.getY()), fieldheight);
            findWord.moveTo(positionX, positionY);
        }
    }

    /**
     * @return actual direction
     */
    public Direction getDirection() {
        return direction;
    }

    @Override
    public Node getImage(int tick) {
        image.setSettings(direction, tick, invincible);
        return image;
    }

    @Override
    public int getNextPositionX(Direction direction) {
        return positionX + direction.getX();
    }

    @Override
    public int getNextPositionY(Direction direction) {
        return positionY + direction.getY();
    }

    /**
     * Adds the point to the pointcounter
     *
     * @param points to add
     */
    public void addPoints(int points) {
        this.points += points;
    }

    @Override
    public int getPositionX() {
        return positionX;
    }

    @Override
    public int getPositionY() {
        return positionY;
    }

    /**
     * position before the last movement
     *
     * @return x
     */
    public int getLastPositionX() {
        return Math.floorMod(positionX - direction.getX(), fieldwidth);
    }

    /**
     * position before the last movement
     *
     * @return y
     */
    public int getLastPositionY() {
        return Math.floorMod(positionY - direction.getY(), fieldheight);
    }

    /**
     * Lifes of the player
     *
     * @return lifes of the player
     */
    public int getLifes() {
        return lifes;
    }

    /**
     * Points of the player
     *
     * @return points of the player
     */
    public int getPoints() {
        return points;
    }

    /**
     * Lets the player loose one life
     */
    public void looseLife() {
        lifes -= 1;
        invincible = 5;
    }

    /**
     * @return if the player is now invincible
     */
    public boolean isInvincible() {
        return invincible > 0;
    }

    /**
     * sets the player invincible for 20 steps
     */
    public void setInvincible() {
        this.invincible = invincibleTime;
    }

    /**
     * kills player
     */
    public void die() {
        this.isDead = true;
        this.positionX = 0;
        this.positionY = 0;
        this.direction = Direction.NONE;
    }

    /**
     * @return whether the player is alive
     */
    public boolean isAlive() {
        return !isDead;
    }

    /**
     * @return the player id
     */
    public int getId() {
        return id;
    }

}
