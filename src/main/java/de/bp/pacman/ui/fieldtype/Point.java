package de.bp.pacman.ui.fieldtype;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Point is a canvas representing an eatable point
 */
public class Point extends Canvas {
    private final double with;
    private final double height;

    /**
     * Creates a point
     * @param width width
     * @param height height
     */
    public Point(double width, double height) {
        super(width, height);
        this.height = height;
        this.with = width;
        draw();
    }

    private void draw() {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.BLUE);
        gc.fillRect(0,0,with,height);
        gc.setFill(Color.GOLD);
        gc.fillOval(with / 2.0, height / 2.0, with / 5, height / 5);
    }
}
