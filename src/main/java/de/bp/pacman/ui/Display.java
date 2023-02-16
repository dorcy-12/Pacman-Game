package de.bp.pacman.ui;

import de.bp.pacman.GameManager;
import de.bp.pacman.Player;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Display containing lifes and points of one player
 */
public class Display extends HBox {
    private final Text textLifes;
    private final Text textPoints;

    private final int playerNumber;
    private int points;

    /**
     * Displays the points and the lifes of the player
     *
     * @param playerNumber number of players in the game
     */
    public Display(int playerNumber) {
        this.playerNumber = playerNumber;
        textLifes = new Text("Lifes of player " + (playerNumber + 1) + ": " + 1);
        textPoints = new Text("Points of player " + (playerNumber + 1) + ": " + 0);
        this.getChildren().add(textLifes);
        this.getChildren().add(textPoints);
        this.setSpacing(50.0);
    }

    /**
     * Update the display of the points and the lifes
     */
    public void update() {
        Player player = GameManager.getInstance().getPlayer()[playerNumber];
        textLifes.setText("Lifes of player " + (playerNumber + 1) + ": " + player.getLifes());
        textPoints.setText("Points of player " + (playerNumber + 1) + ": " + player.getPoints());
        this.points = player.getPoints();
    }

    /**
     *
     * @return the amount of points stored
     */
    public int getPoints() {
        return points;
    }
}
