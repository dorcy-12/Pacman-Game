package de.bp.pacman.ui;

import de.bp.pacman.Field;
import javafx.scene.layout.BorderPane;

/**
 * GamePane
 */
public class GamePane extends BorderPane {
    private final PacmanField pacmanField;
    private final DisplayAll display;

    /**
     * Creates a gamepane
     *
     * @param field        field
     * @param numberPlayer number of player in the game
     */
    public GamePane(Field field, int numberPlayer) {
        pacmanField = new PacmanField(field);
        display = new DisplayAll(numberPlayer);
        setCenter(pacmanField);
        setTop(display);
    }

    /**
     * updates the ghosts
     *
     * @param tick phase during one step (between 0 and 9)
     */
    public void repaintGhost(int tick) {
        pacmanField.repaintGhost(tick);
    }

    /**
     * updates the display and the pacman
     *
     * @param tick phase during one step (between 0 and 9)
     */
    public void repaintPacman(int tick) {
        display.update();
        pacmanField.repaintPacman(tick);
    }

    /**
     * Refreshs the displayed fieldtype in the position
     * @param x the x position to refresh
     * @param y the y position to refresh
     */
    public void refreshType(int x, int y) {
        pacmanField.refreshType(x, y);
    }
}
