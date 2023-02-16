package de.bp.pacman.ui;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Class that displays the sum of all players' points
 */
public class DisplaySummarizedPoints extends HBox {
    private final Text show;

    /**
     * Displays cummulative points
     */
    public DisplaySummarizedPoints() {
        show = new Text("All player together: " + 0);
        getChildren().add(show);
    }

    /**
     * Update the display of the points and the lifes
     * @param points summarized points to display
     */
    public void update(int points) {
        show.setText("All player together: " + points);
    }
}
