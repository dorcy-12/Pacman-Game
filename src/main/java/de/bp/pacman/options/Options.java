package de.bp.pacman.options;

import de.bp.pacman.PlayerMovement;
import de.bp.pacman.Sound;

import java.util.List;
import java.util.Objects;

/**
 * Options class that controls the game options
 */
public class Options {
    private static final int GAMESPEED_DEFAULT = 5;
    private static final int REFRESHRATE_DEFAULT = 60;
    private static final int LIVES_DEFAULT = 3;


    private static final List<PlayerMovement> PLAYER_MOVEMENTS_DEFAULT = PlayerMovement.getAllPlayer(3);
    private int gameSpeed;
    private int refreshRate;
    private List<PlayerMovement> pm;

    private boolean sound;

    private boolean easymove;

    private Sound music = Sound.getInstance();

    private int playerlives;

    /**
     * Defines the options of a game
     *
     * @param gameSpeed speed of the game
     * @param refreshRate frame rat
     * @param playerlives number of player lives
     * @param pm player movement
     * @param easymove easymove
     * @param sound sound
     */
    public Options(int gameSpeed, int refreshRate, int playerlives, List<PlayerMovement> pm, boolean easymove, boolean sound) {
        if (gameSpeed >= 1) {
            this.gameSpeed = gameSpeed;
        } else {
            this.gameSpeed = GAMESPEED_DEFAULT;
        }
        if (refreshRate >= 1) {
            this.refreshRate = refreshRate;
        } else {
            this.refreshRate = REFRESHRATE_DEFAULT;
        }
        if (playerlives >=1){
            this.playerlives = playerlives;
        }
        else{
            this.playerlives = LIVES_DEFAULT;
        }
        this.easymove = easymove;
        this.pm = Objects.requireNonNullElse(pm, PLAYER_MOVEMENTS_DEFAULT);

        this.sound = sound;
    }

    /**
     *
     * @return the number of players lives
     */
    public int getPlayerlives() {return playerlives;}

    /**
     *
     * @return the speed of the game
     */
    public int getGameSpeed() {return gameSpeed;}
    /**
     *
     * @return the refresh rate of the game.
     */
    public int getRefreshRate() {
        return refreshRate;
    }

    /**
     *
     * @return easymove option
     */
    public boolean easymove() {
        return easymove;
    }

    /**
     * @return a list of the object player movement
     */
    public List<PlayerMovement> getPlayerMovement() {
        return pm;
    }

    /**
     *
     * @return sound file with the mute option set
     */
    public Sound getSound() {
        music.setsoundon(sound);
        return music;
    }
}

