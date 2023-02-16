package de.bp.pacman.ui.fieldtype;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A block is an unpassable wall.
 */
public class Block extends Canvas {
    private final double with;
    private final double height;

    /**
     * Creates an image of a block
     *
     * @param width  width
     * @param height height
     */
    public Block(double width, double height) {
        super(width, height);
        this.height = height;
        this.with = width;
        draw();
    }

    private void draw() {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(4);
        gc.fillRect(0,0, with, height);
        gc.strokeRoundRect(0,0,with,height,15,15);
    }
}
