package de.bp.pacman;

import de.bp.pacman.ui.MovingGhost;
import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Moving Ghost enemy
 */
public class Ghost implements Movable {
    private static int positionXRespawn = 10;
    private static int positionYRespawn = 11;
    private int positionX;
    private int positionY;
    private Direction direction;
    private int fieldwidth;
    private int fieldheight;

    private final MovingGhost image;

    private int timeToRespawn;

    /**
     * creates a ghost
     *
     * @param startX x
     * @param startY y
     * @param color  color of the ghost (blue, green, yellow, red, pink)
     * @param respawnX x coordinate of the respawn position
     * @param respawnY y coordinate of the respawn position
     * @param fieldwidth width of the field
     * @param fieldheight height of the field
     */
    public Ghost(int startX, int startY, Color color, int respawnX, int respawnY, int fieldwidth, int fieldheight) {
        positionXRespawn = respawnX;
        positionYRespawn = respawnY;
        this.positionX = startX;
        this.positionY = startY;
        this.image = MovingGhost.getGhost(color, 20, 20);
        this.timeToRespawn = -1;
        this.fieldwidth = fieldwidth;
        this.fieldheight = fieldheight;
        direction = Direction.NONE;
    }

    /**
     * moves Ghost in given direction
     *
     * @param direction new direction
     */
    @Override
    public void move(Direction direction) {
        if (timeToRespawn > 0) {
            positionX = 5;
            positionY = 5;
            timeToRespawn--;
        } else if (timeToRespawn == 0) {
            positionX = positionXRespawn;
            positionY = positionYRespawn;
            timeToRespawn--;
        } else {
            positionX = Math.floorMod((positionX + direction.getX()), fieldwidth);
            positionY = Math.floorMod((positionY + direction.getY()), fieldheight);
            this.direction = direction;
        }
    }

    /**
     * moves ghost according to settings
     */

    public void move() {
        List<Direction> possibilitys = getPossibleDirections();
        int random = new Random().nextInt(possibilitys.size());
        if (direction.equals(Direction.NONE) || moveInBlock(direction) || possibilitys.size() > 2) {
            move(possibilitys.get(random));
        } else {
            move(direction);
        }
    }


    @Override
    public int getPositionX() {
        return positionX;
    }

    @Override
    public int getPositionY() {
        return positionY;
    }

    public int getNextPositionX(Direction random) {
        return positionX + random.getX();
    }

    public int getNextPositionY(Direction random) {
        return positionY + random.getY();
    }

    public Node getImage(int tick) {
        return image;
    }

    /**
     * lets the ghost die (until the time to respawn)
     */
    public void die() {
        timeToRespawn = 50;
    }

    /**
     * Returns if the ghost is dead
     *
     * @return if the ghost is dead
     */
    public boolean isDead() {
        return timeToRespawn >= 0;
    }

    /**
     * @return List of possible directions. List with only None in error case
     */
    private List<Direction> getPossibleDirections() {
        List<Direction> res = new ArrayList<>();
        for (Direction dir : Direction.values()) {
            if (!moveInBlock(dir)) {
                res.add(dir);
            }
        }
        if (res.size() > 1) {
            res.remove(Direction.NONE);
        }
        return res;
    }

    /**
     * @param direction the direction to check for a wall in
     * @return wheather there is a wall in given direction
     */
    private boolean moveInBlock(Direction direction) {
        return GameManager.getInstance().getField().getType(getNextPositionX(direction), getNextPositionY(direction)).equals(FieldType.BLOCK);
    }

}
