package de.bp.pacman;

import de.bp.pacman.ui.GamePane;

/**
 * Looks for a letter and paints them in another color if it is found
 */
public class FindLetter {
    private final int[] pathXs;
    private final int[] pathYs;

    private final GamePane pane;

    private int counter;

    private FindLetter(int[] xs, int[] ys, GamePane pane) {
        this.pathXs = xs;
        this.pathYs = ys;
        this.counter = 0;
        this.pane = pane;
        assert (pathYs.length == pathXs.length);
    }

    /**
     * Returns the instance of a letter (l, o, v, e)
     * @param c the letter to look for
     * @param pane the paint to color the found letter
     * @return the instance of FindLetter
     */
    public static FindLetter getInstance(char c, GamePane pane) {
        switch (c) {
            case 'l' -> {
                int[] xs = new int[12 + 7];
                int[] ys = new int[12 + 7];
                for (int i = 0; i < 12; i++) {
                    xs[i] = 4;
                    ys[i] = i + 2;
                }
                for (int i = 0; i < 7; i++) {
                    xs[i + 12] = 4 + i + 1;
                    ys[i + 12] = 13;
                }
                return new FindLetter(xs, ys, pane);
            }
            case 'o' -> {
                int[] xs = new int[2 * 4 + 2];
                int[] ys = new int[2 * 4 + 2];
                xs[0] = 6;
                xs[1] = 6;
                xs[2] = 6;
                xs[3] = 6;
                xs[4] = 7;
                xs[5] = 8;
                xs[6] = 8;
                xs[7] = 8;
                xs[8] = 8;
                xs[9] = 7;
                ys[0] = 18;
                ys[1] = 17;
                ys[2] = 16;
                ys[3] = 15;
                ys[4] = 15;
                ys[5] = 15;
                ys[6] = 16;
                ys[7] = 17;
                ys[8] = 18;
                ys[9] = 18;
                return new FindLetter(xs, ys, pane);
            }
            case 'e' -> {
                int[] xs = new int[16];
                int[] ys = new int[16];
                xs[0] = 9;
                xs[1] = 8;
                xs[2] = 7;
                xs[3] = 6;
                xs[4] = 6;
                xs[5] = 6;
                xs[6] = 6;
                xs[7] = 6;
                xs[8] = 7;
                xs[9] = 8;
                xs[10] = 9;
                xs[11] = 9;
                xs[12] = 9;
                xs[13] = 8;
                xs[14] = 7;
                xs[15] = 6;
                ys[0] = 7;
                ys[1] = 7;
                ys[2] = 7;
                ys[3] = 7;
                ys[4] = 6;
                ys[5] = 5;
                ys[6] = 4;
                ys[7] = 3;
                ys[8] = 3;
                ys[9] = 3;
                ys[10] = 3;
                ys[11] = 4;
                ys[12] = 5;
                ys[13] = 5;
                ys[14] = 5;
                ys[15] = 5;
                return new FindLetter(xs, ys, pane);
            }
            case 'v' -> {
                int[] xs = new int[9];
                int[] ys = new int[9];
                xs[0] = 11;
                xs[1] = 11;
                xs[2] = 11;
                xs[3] = 11;
                xs[4] = 12;
                xs[5] = 13;
                xs[6] = 13;
                xs[7] = 13;
                xs[8] = 13;
                ys[0] = 2;
                ys[1] = 3;
                ys[2] = 4;
                ys[3] = 5;
                ys[4] = 5;
                ys[5] = 5;
                ys[6] = 4;
                ys[7] = 3;
                ys[8] = 2;
                return new FindLetter(xs, ys, pane);
            }
            case 'f' -> {
                int[] xs = new int[15];
                int[] ys = new int[15];
                xs[0] = 1;
                xs[1] = 1;
                xs[2] = 1;
                xs[3] = 1;
                xs[4] = 1;
                xs[5] = 1;
                xs[6] = 2;
                xs[7] = 3;
                xs[8] = 2;
                xs[9] = 1;
                xs[10] = 1;
                xs[11] = 1;
                xs[12] = 1;
                xs[13] = 2;
                xs[14] = 3;
                ys[0] = 6;
                ys[1] = 5;
                ys[2] = 4;
                ys[3] = 3;
                ys[4] = 2;
                ys[5] = 1;
                ys[6] = 1;
                ys[7] = 1;
                ys[8] = 1;
                ys[9] = 1;
                ys[10] = 2;
                ys[11] = 3;
                ys[12] = 4;
                ys[13] = 4;
                ys[14] = 4;
                return new FindLetter(xs, ys, pane);
            }
            case 'u' -> {
                int[] xs = new int[7];
                int[] ys = new int[7];
                xs[0] = 8;
                xs[1] = 8;
                xs[2] = 8;
                xs[3] = 9;
                xs[4] = 10;
                xs[5] = 10;
                xs[6] = 10;
                ys[0] = 6;
                ys[1] = 7;
                ys[2] = 8;
                ys[3] = 8;
                ys[4] = 8;
                ys[5] = 7;
                ys[6] = 6;
                return new FindLetter(xs, ys, pane);
            }
            case 'n' -> {
                int[] xs = new int[7];
                int[] ys = new int[7];
                xs[0] = 12;
                xs[1] = 12;
                xs[2] = 12;
                xs[3] = 13;
                xs[4] = 14;
                xs[5] = 14;
                xs[6] = 14;
                ys[0] = 6;
                ys[1] = 5;
                ys[2] = 4;
                ys[3] = 4;
                ys[4] = 4;
                ys[5] = 5;
                ys[6] = 6;
                return new FindLetter(xs, ys, pane);
            }

        }
        return new FindLetter(null, null, pane);
    }

    /**
     * Registers the movement of a player and looks if he is on the path
     * @param x the x position of the player
     * @param y the y position of the player
     */
    public void moveTo(int x, int y) {
        if (counter < pathYs.length) {
            if (pathYs[counter] == y && pathXs[counter] == x) {
                counter++;
            } else {
                counter = 0;
            }
        }
    }

    /**
     * If the player has already found the letter
     * @return true if the player has already found the letter
     */
    public boolean foundLetter() {
        return counter == pathYs.length;
    }

    /**
     * Draws the letter in the color.
     */
    public void makeVisible() {
        for (int i = 0; i < pathYs.length; i++) {
            GameManager.getInstance().getField().set(pathXs[i], pathYs[i], FieldType.COLORED_EMPTY);
            pane.refreshType(pathXs[i], pathYs[i]);
        }
    }
}
