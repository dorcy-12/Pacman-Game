package de.bp.pacman;

import de.bp.pacman.ui.GamePane;

/**
 * Shows when the player has found a letter for easter eggs
 */
public class FindWord {
    private final FindLetter[] foundLetters;

    private FindWord(char[] cs, GamePane pane) {
        foundLetters = new FindLetter[cs.length];
        for (int i = 0; i < foundLetters.length; i++) {
            foundLetters[i] = FindLetter.getInstance(cs[i], pane);
        }
    }

    /**
     * Registers the movement of the player
     * @param x the x position of the player
     * @param y the y position of the player
     */
    public void moveTo(int x, int y) {
        for (FindLetter fl : foundLetters) {
            fl.moveTo(x, y);
            if (fl.foundLetter()) {
                fl.makeVisible();
            }
        }
    }

    /**
     * Get the instance of a FindWord what looks for LOVE
     * @param pane the pane to make the found letter visible
     * @return the instance of FindWord looking for LOVE
     */
    public static FindWord getInstanceLOVE(GamePane pane) {
        char[] cs = new char[]{'l', 'o', 'v', 'e'};
        return new FindWord(cs, pane);
    }
    /**
     * Get the instance of a FindWord what looks for FUN
     * @param pane the pane to make the found letter visible
     * @return the instance of FindWord looking for FUN
     */
    public static FindWord getInstanceFUN(GamePane pane) {
        char[] cs = new char[]{'f', 'u', 'n'};
        return new FindWord(cs, pane);
    }
    /**
     * Get an empty FindWord instance for levels without easter egg
     * @param pane the pane to make the found letter visible
     * @return an empty instance of FindWord
     */
    public static FindWord getEmptyInstance(GamePane pane) {
        return new FindWord(new char[]{}, pane);
    }
    /**
     * Get the instance of a FindWord for the current level
     * @param pane the pane to make the found letter visible
     * @param level the current level
     * @return the instance of FindWord looking for a word depending on the level
     */
    public static FindWord getInstance(GamePane pane, int level) {
        switch (level) {
            case 1 : {
                return getInstanceLOVE(pane);
            } case 2 : {
                return getInstanceFUN(pane);
            } default: return getEmptyInstance(pane);
        }
    }
}
