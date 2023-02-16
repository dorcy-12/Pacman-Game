package de.bp.pacman.ui;

import javafx.scene.layout.VBox;

/**
 * Displays for each player the points and the lifes
 */
public class DisplayAll extends VBox {
    private final Display[] displays;
    private final DisplaySummarizedPoints sum;

    /**
     * Displays for each player the points and the lifes
     *
     * @param playerNumber number of players
     */
    public DisplayAll(int playerNumber) {
        displays = new Display[playerNumber];
        sum = new DisplaySummarizedPoints();
        instantiateDisplays();
    }

    private void instantiateDisplays() {
        for (int i = 0; i < displays.length; i++) {
            displays[i] = new Display(i);
            getChildren().add(displays[i]);
        }
        getChildren().add(sum);
    }

    /**
     * updates the points and the lifes of all players
     */
    protected void update() {
        int summarizePoints = 0;
        for (Display display : displays) {
            display.update();
            summarizePoints += display.getPoints();
        }
        sum.update(summarizePoints);
    }
}
