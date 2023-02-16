package de.bp.pacman;


/**
 * Direction of a movement
 */
public enum Direction {
    /**
     * Movement upwards
     */
    UP(0, -1),
    /**
     * Movement downwards
     */
    DOWN(0, 1),
    /**
     * Movement to the right
     */
    RIGHT(1, 0),
    /**
     * Movement to the left
     */
    LEFT(-1, 0),
    /**
     * Stay in this position
     */
    NONE(0, 0);

    private final int x;
    private final int y;

    Direction(int deltaX, int deltaY) {
        x = deltaX;
        y = deltaY;
    }

    /**
     * @return delta x of a movement in that direction
     */
    public int getX() {
        return x;
    }

    /**
     * @return delta y of a movement in that direction
     */
    public int getY() {
        return y;
    }

}
