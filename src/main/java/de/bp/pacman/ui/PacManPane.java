package de.bp.pacman.ui;

import de.bp.pacman.Direction;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;

import static de.bp.pacman.Direction.*;

/**
 * PacmanPane is a pane painting the yellow pacman arc
 */
public class PacManPane extends Pane {

    private final Arc pacman;

    private final double width;
    private final double height;

    private final int mouthMoving;

    /**
     * Creates a pacmanField
     *
     * @param width  width
     * @param height height
     * @param color  of the player
     * @param mouthMoving mouth animation behavior
     */
    public PacManPane(double width, double height, Color color, int mouthMoving) {
        this.width = width;
        this.height = height;
        pacman = new Arc();
        pacman.setCenterX(width / 2);
        pacman.setCenterY(height / 2);
        pacman.setRadiusX(width / 2);
        pacman.setRadiusY(height / 2);
        pacman.setStartAngle(0);
        pacman.setLength(360);
        pacman.setFill(color);
        pacman.setType(ArcType.ROUND);
        getChildren().add(pacman);
        this.mouthMoving = mouthMoving;
    }

    private int calculateMouthMovingTick(int tick) {
        return switch (this.mouthMoving) {
            case 0 -> 5;
            case 2 -> (tick > 5) ? 5 : 0;
            case 10 -> tick;
            default -> tick % 10;
        };
    }

    private void changeMouth(Direction direction, int tick) {
        if (tick > 5) {
            tick = 10 - tick;
        }
        switch (direction) {
            case NONE -> {
                pacman.setStartAngle(0);
                pacman.setLength(360);
            }
            case RIGHT -> {
                pacman.setStartAngle(9 * tick);
                pacman.setLength(360 - 18 * tick);
            }
            case UP -> {
                pacman.setStartAngle(90 + 9 * tick);
                pacman.setLength(360 - 18 * tick);
            }
            case LEFT -> {
                pacman.setStartAngle(180 + 9 * tick);
                pacman.setLength(360 - 18 * tick);
            }
            case DOWN -> {
                pacman.setStartAngle(270 + 9 * tick);
                pacman.setLength(360 - 18 * tick);
            }
            default -> {
            }
        }
        pacman.toFront();
    }

    /**
     * Moves the arc to the next position and adds a border if the player is invincible
     *
     * @param direction the player is moving
     * @param tick      (between 0 and 9) phase during one step
     * @param invincible whether or how long the player is invincible
     */
    public void setSettings(Direction direction, int tick, int invincible) {
        double newCenterX = width / 2;
        double newCenterY = height / 2;
        changeMouth(direction, calculateMouthMovingTick(tick));
        if (direction.equals(LEFT)) {
            newCenterX = 1.5 * width - tick * width / 10;
        } else if (direction.equals(RIGHT)) {
            newCenterX = 0.5 * width + tick * width / 10;
        } else if (direction.equals(UP)) {
            newCenterY = 1.5 * height - tick * height / 10;
        } else if (direction.equals(DOWN)) {
            newCenterY = 0.5 * height + tick * height / 10;
        }
        pacman.setCenterX(newCenterX);
        pacman.setCenterY(newCenterY);
        if (invincible > 0) {
            pacman.setStroke(Color.WHITE);
        } else {
            pacman.setStroke(null);
        }
    }
}
