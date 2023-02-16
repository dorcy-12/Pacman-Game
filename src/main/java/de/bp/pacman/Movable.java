package de.bp.pacman;

import javafx.scene.Node;

/**
 * an interface for movable objects with a position
 */
public interface Movable {
    /**
     * Moves into that direction
     * @param direction new direction
     */
    void move(Direction direction);

    /**
     * Actual position of the movable
     * @return x
     */
    int getPositionX();

    /**
     * Actual position of the movable
     * @return y
     */
    int getPositionY();

    /**
     * If the movable would move to that direction it would be placed here
     * @param direction possible Direction
     * @return x
     */
    int getNextPositionX(Direction direction);

    /**
     * If the movable would move to that direction it would be placed here
     * @param direction possible Direction
     * @return y
     */
    int getNextPositionY(Direction direction);

    /**
     * Returns a node with the image of that movable
     * @param tick modus of the movable
     * @return image of the ghost
     */
    Node getImage(int tick);

}
