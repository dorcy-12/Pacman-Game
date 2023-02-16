package de.bp.pacman.ui;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.Objects;

import static javafx.scene.paint.Color.*;

/**
 * Ghost
 */
public class MovingGhost extends Parent {
    private MovingGhost(Image image) {
        ImageView view = new ImageView(image);
        this.getChildren().add(view);
    }

    /**
     * Creates a ghost with the color, width and height given
     * @param color c
     * @param width with
     * @param height height
     * @return ghost
     */
    public static MovingGhost getGhost(Color color, double width, double height) {
        String imageName;
        if (YELLOW.equals(color)) {
            imageName = "/gelbGhost.png";
        } else if (RED.equals(color)) {
            imageName = "/redGhost.png";
        } else if (BLUE.equals(color)) {
            imageName = "/blueGhost.png";
        } else if (PINK.equals(color)) {
            imageName = "/pinkGhost.png";
        } else if (GREEN.equals(color)) {
            imageName = "/greenGhost.png";
        } else {
            throw new IllegalStateException("Unexpected value: " + color);
        }
        Image image = new Image(Objects.requireNonNull(MovingGhost.class.getResource(imageName)).toString(), width, height, false, true);
        return new MovingGhost(image);
    }
}
