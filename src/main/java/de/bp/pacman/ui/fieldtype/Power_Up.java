package de.bp.pacman.ui.fieldtype;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Power_up is a canvas that represents an eatable power-up
 */
public class Power_Up extends Canvas {
    private final double with;
    private final double height;

    /**
     * Creates a power-up
     * @param width width
     * @param height height
     */
    public Power_Up(double width, double height) {
        super(width, height);
        this.height = height;
        this.with = width;
        draw();
    }

    private void draw() {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, with, height);
        gc.setFill(Color.GOLD);
        gc.fillOval(with / 2.0, height / 2.0, with / 2.5, height / 2.5);
    }
}
