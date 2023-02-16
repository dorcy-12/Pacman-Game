package de.bp.pacman;

import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Important for the keyhandler
 *
 * @param up     upwards
 * @param down   downwards
 * @param left   to the left
 * @param right  to the right
 * @param number number of the player
 */
public record PlayerMovement(KeyCode up, KeyCode down,
                             KeyCode left, KeyCode right, int number) {

    /**
     * creats a playermovement for the player with the given number (up to 3)
     *
     * @param number of the player
     * @return keys to move that player
     */
    public static List<PlayerMovement> getAllPlayer(int number) {
        List<PlayerMovement> playerMovementList = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            playerMovementList.add(getOnePlayer(i));
        }
        return playerMovementList;
    }

    private static PlayerMovement getOnePlayer(int number) {
        return switch (number) {
            case 1 -> new PlayerMovement(KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT, number);
            case 2 -> new PlayerMovement(KeyCode.W, KeyCode.S, KeyCode.A, KeyCode.D, number);
            case 3 -> new PlayerMovement(KeyCode.NUMPAD8, KeyCode.NUMPAD2, KeyCode.NUMPAD4, KeyCode.NUMPAD6, number);
            default -> null;
        };
    }

    /**
     *
     * @return keycode up
     */
    public KeyCode getUp() {
        return up;
    }

    /**
     *
     * @return keycode down
     */
    public KeyCode getDown() {
        return down;
    }

    /**
     *
     * @return keycode to the right
     */
    public KeyCode getRight() {
        return right;
    }

    /**
     * Left keycode
     * @return to the left
     */
    public KeyCode getLeft() {
        return left;
    }

    /**
     * @return number of the movement
     */
    public int getNumber() {
        return number;
    }
}
