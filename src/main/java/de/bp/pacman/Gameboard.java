package de.bp.pacman;

/**
 * The Gameboard interface that functions as a base for the {@link GameManager} class
 */
public interface Gameboard {
    /**
     * the direction after the next step
     * @param playerNumber number of one special player that plays
     * @param direction next direction
     */
    void nextDirection(int playerNumber, Direction direction);

    /**
     * returns moving direction of a Player
     *
     * @param playerNumber the id of the player to get the next direction from
     * @return the direction a player is moving in
     */
    Direction getNextDirection(int playerNumber);

    /**
     * Movement of the game
     * @param tickrate amount of frames before the next tick
     */
    void step(int tickrate);
}
