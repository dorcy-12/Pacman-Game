package de.bp.pacman.ui.fieldtype;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Empty field without something eatable
 */
public class Empty extends Canvas {
    private final double width;
    private final double height;

    /**
     * Field without something eatable
     * @param width width
     * @param height height
     */
    public Empty(double width, double height) {
        super(width, height);
        this.height = height;
        this.width = width;
        draw();
    }

    private void draw() {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);
    }
}
