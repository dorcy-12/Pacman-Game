package de.bp.pacman.ui.fieldtype;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Like the empty in the gamelogic but magenta for a found letter
 */
public class ColoredEmpty extends Empty {
    private final double width;
    private final double height;
    /**
     * Field without something eatable but is red (because a letter is found)
     *
     * @param width  width
     * @param height height
     */
    public ColoredEmpty(double width, double height) {
        super(width, height);
        this.width = width;
        this.height = height;
        draw();
    }

    private void draw() {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.setFill(Color.PALEVIOLETRED);
        gc.fillRect(0, 0, width, height);
    }
}
