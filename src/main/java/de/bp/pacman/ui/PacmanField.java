package de.bp.pacman.ui;

import de.bp.pacman.*;
import de.bp.pacman.ui.fieldtype.*;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import static de.bp.pacman.Direction.DOWN;
import static de.bp.pacman.Direction.RIGHT;

/**
 * Pacmanfield is the graphical unit of the field
 */
public class PacmanField extends GridPane {
    private static final int HEIGHT = 20, WIDTH = 20;


    private Node getNode(FieldType type) {
        switch (type) {
            case EMPTY -> {
                return new Empty(WIDTH, HEIGHT);
            }
            case POINT -> {
                return new Point(WIDTH, HEIGHT);
            }
            case BLOCK -> {
                return new Block(WIDTH, HEIGHT);
            }
            case POWER_UP -> {
                return new Power_Up(WIDTH, HEIGHT);
            }
            case COLORED_EMPTY -> {
                return new ColoredEmpty(WIDTH, HEIGHT);
            }
        }
        return null;
    }

    /**
     * creates the visible copy of the field
     *
     * @param field field
     */
    public PacmanField(Field field) {
        super();
        for (int i = 0; i < field.WIDTH; i++) {
            for (int j = 0; j < field.HEIGHT; j++) {
                add(getNode(field.getType(i, j)), i * WIDTH / 2, j * HEIGHT / 2, WIDTH / 2, HEIGHT / 2);
            }
        }
    }

    /**
     * Refreshs the display of the position
     * @param x position of the node
     * @param y position of the node
     */
    public void refreshType(int x, int y) {
        this.getChildren().remove(getNodeFromGridPane(x, y));
        add(getNode(GameManager.getInstance().getField().getType(x,y)), x * WIDTH / 2, y * HEIGHT / 2, WIDTH / 2, HEIGHT / 2);
    }

    private Node getNodeFromGridPane(int col, int row) {
        for (Node node : getChildren()) {
            if (GridPane.getColumnIndex(node) == col * 10 && GridPane.getRowIndex(node) == row * 10) {
                return node;
            }
        }
        return null;
    }

    /**
     * moves all ghosts
     *
     * @param tick (between 0 and 9)
     */
    public void repaintGhost(int tick) {
        for (Ghost ghost : GameManager.getInstance().getGhosts()) {
            getChildren().remove(ghost.getImage(tick));
            if (!ghost.isDead()) {
                add(ghost.getImage(tick), ghost.getPositionX() * WIDTH / 2, ghost.getPositionY() * HEIGHT / 2, WIDTH / 2, HEIGHT / 2);
            }
        }
    }

    /**
     * moves the pacman
     *
     * @param tick of the movement
     */
    public void repaintPacman(int tick) {
        Player[] players = GameManager.getInstance().getPlayer();
        for (Player player : players) {
            getChildren().remove(getNodeFromGridPane(player.getLastPositionX(), player.getLastPositionY()));
            add(getNode(GameManager.getInstance().getField().getType(player.getLastPositionX(), player.getLastPositionY())), player.getLastPositionX() * WIDTH / 2, player.getLastPositionY() * HEIGHT / 2, WIDTH / 2, HEIGHT / 2);
            getChildren().remove(player.getImage(tick));
            if (player.getPositionY() == 0 && player.getDirection().equals(DOWN)) {
                return;
            } else if (player.getPositionX() == 0 && player.getDirection().equals(RIGHT)) {
                return;
            }
            if (player.isAlive()) {
                switch (player.getDirection()) {
                    case UP, LEFT, NONE ->
                            add(player.getImage(tick), player.getPositionX() * WIDTH / 2, player.getPositionY() * HEIGHT / 2, WIDTH / 2, HEIGHT / 2);
                    case DOWN ->
                            add(player.getImage(tick), player.getPositionX() * WIDTH / 2, (player.getPositionY() - 1) * HEIGHT / 2, WIDTH / 2, HEIGHT / 2);
                    case RIGHT ->
                            add(player.getImage(tick), (player.getPositionX() - 1) * WIDTH / 2, player.getPositionY() * HEIGHT / 2, WIDTH / 2, HEIGHT / 2);
                }
            }
        }
    }
}
