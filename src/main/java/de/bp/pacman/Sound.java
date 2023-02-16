package de.bp.pacman;

import javafx.animation.Animation;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import java.net.URL;

/**
 * Sound Class
 */
public class Sound {

    private MediaView clip;

    private MediaView mainsound;

    private boolean alternate = true;

    private boolean soundon = false;

    private boolean musicplaying = true;

    URL[] soundURL = new URL[10];

    private static Sound instance = null;
    /**
     * Loads all the sounds in an easily accessible URL array.
     */
    private Sound(){
        soundURL[0] = getClass().getResource("sound/pacman_beginning.wav");
        soundURL[1] = getClass().getResource("sound/pacman_chomp.wav");
        soundURL[2] = getClass().getResource("sound/pacman_death.wav");
        soundURL[3] = getClass().getResource("sound/pacman_eatfruit.wav");
        soundURL[4] = getClass().getResource("sound/pacman_eatghost.wav");
        soundURL[5] = getClass().getResource("sound/pacman_extrapac.wav");
        soundURL[6] = getClass().getResource("sound/pacman_intermission.wav");
        clip = new MediaView();
        mainsound = new MediaView();
    }

    /**
     * Creates an Instance of the sound class if there is none and makes sure
     * only one Instance is created for every Instantiation of the Sound Class.
     *
     * @return Instance of the Sound Class
     */
    public static Sound getInstance() {

        if (instance == null) {
            instance = new Sound();
            instance.beginSoundtrack();
            instance.loop();
        }
        return instance;
    }

    /**
     * Loads a specific sound and makes it play.
     *
     * @param i The index of a sound in the URL array
     */
    public void SetFile(int i){
        Media media = new Media(soundURL[i].toString());
        MediaPlayer player = new MediaPlayer(media);
        this.clip.setMediaPlayer(player);

    }

    /**
     * Loads the file of the background music.
     */
    public void beginSoundtrack(){
        Media media = new Media(soundURL[0].toString());
        MediaPlayer player = new MediaPlayer(media);
        this.mainsound.setMediaPlayer(player);
        musicplaying = true;
    }

    /**
     * plays a sound once
     */
    public void play(){
        clip.getMediaPlayer().setMute(!soundon);
        clip.getMediaPlayer().play();
    }

    /**
     * Stops a sound
     */
    public void stop(){
        musicplaying = false;
        mainsound.getMediaPlayer().stop();
    }

    /**
     * Makes the pacman eating sound.
     */
    public void chomp(){
        clip.getMediaPlayer().setMute(!soundon);
        if (alternate){
            clip.getMediaPlayer().setStartTime(Duration.millis(140));
            clip.getMediaPlayer().setStopTime(Duration.millis(360));
            alternate = false;
            clip.getMediaPlayer().play();
        }
        else{
            clip.getMediaPlayer().setStartTime(Duration.millis(440));
            clip.getMediaPlayer().setStopTime(Duration.millis(700));
            alternate = true;
            clip.getMediaPlayer().play();
        }
    }

    /**
     * plays a sound on a loop
     */
    public void loop(){
        mainsound.getMediaPlayer().setCycleCount(Animation.INDEFINITE);
        mainsound.getMediaPlayer().play();
    }

    /**
     * Sets the sound on mute
     * @param soundon boolean of sound effects options activated.
     */
    public void setsoundon(boolean soundon) {
        this.soundon = soundon;
    }

    /**
     * This method is useful when turning on and off the music in the options menu.
     * @return whether the music playing.
     */
    public boolean isMusicplaying() {
        return musicplaying;
    }

    /**
     *
     * @return MediaPlayer of the sound effects.
     */
    public MediaPlayer getClip(){
        return clip.getMediaPlayer();
    }

    /**
     *
     * @return MediaPlayer of the background music.
     */
    public MediaPlayer getMusic(){return mainsound.getMediaPlayer();}
}
