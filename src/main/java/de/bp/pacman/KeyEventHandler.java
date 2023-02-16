package de.bp.pacman;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.List;

/**
 * Handles key events (up, down, left and right)
 */
public class KeyEventHandler implements EventHandler<KeyEvent> {
    private final List<PlayerMovement> playerMovements;
    private final Gameboard board;

    /**
     * Creats a keyeventhandler with reaction to keys the playermovement defines
     *
     * @param playerMovements defines the possible direction-keys for each player
     * @param gameboard       board of the game
     */
    public KeyEventHandler(List<PlayerMovement> playerMovements, Gameboard gameboard) {
        this.board = gameboard;
        this.playerMovements = playerMovements;
    }

    @Override
    public void handle(KeyEvent event) {
        KeyCode fromEvent = event.getCode();
        Direction direction = Direction.NONE;
        int playerNumber = -1;
        for (PlayerMovement playerMovement : playerMovements) {
            if (playerMovement.getUp().equals(fromEvent)) {
                playerNumber = playerMovement.getNumber();
                direction = Direction.UP;
            }
            if (playerMovement.getDown().equals(fromEvent)) {
                playerNumber = playerMovement.getNumber();
                direction = Direction.DOWN;
            }
            if (playerMovement.getLeft().equals(fromEvent)) {
                playerNumber = playerMovement.getNumber();
                direction = Direction.LEFT;
            }
            if (playerMovement.getRight().equals(fromEvent)) {
                playerNumber = playerMovement.getNumber();
                direction = Direction.RIGHT;
            }
        }
        if(event.getEventType()==KeyEvent.KEY_PRESSED){
            if (!direction.equals(Direction.NONE)) {
                board.nextDirection(playerNumber, direction);
            }
        }else if(event.getEventType()==KeyEvent.KEY_RELEASED&&!GameManager.getInstance().getOptions().easymove()){
            if(board.getNextDirection(playerNumber).equals(direction)){
                board.nextDirection(playerNumber, Direction.NONE);
            }
        }

    }
}
