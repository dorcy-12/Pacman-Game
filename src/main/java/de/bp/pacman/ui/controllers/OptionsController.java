package de.bp.pacman.ui.controllers;

import de.bp.pacman.Sound;
import de.bp.pacman.ui.Home;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/**
 * Controller Class of the options GUI
 */
public class OptionsController {

    private static final Integer GAMESPEED_DEFAULT= 5;

    private static final double REFRESHING_DEFAULT = 60;
    @FXML
    private Button back;

    @FXML
    private ComboBox<Integer> framerate;

    @FXML
    private CheckBox soundfx;

    @FXML
    private ComboBox<String> speed;

    @FXML
    private CheckBox easymov;

    @FXML
    private Spinner<Integer> playerlives;

    @FXML
    private CheckBox music;
    @FXML
    private ComboBox<Integer> mouthanim;

    @FXML
    private Button save;

    private HashMap<String, Integer> gameOptions = null;

    private Sound sound = Sound.getInstance();

    /**
     * Handles the scene switching from the Options GUI to Home GUI
     * @throws IOException if an error occurs during loading of the home GUI
     */
    @FXML
    void backbuttonpressed() throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
        Home home =  new Home();
        home.setOptions(gameOptions);
        home.start(new Stage());
    }

    /**
     * Updates the local variable that holds the options of the game.
     */
    @FXML
    void savebuttonpressed() {
        gameOptions.replace("framerate", (framerate.getValue()==null)? gameOptions.get("framerate"): framerate.getValue());
        gameOptions.replace("easymove", (easymov.isSelected())?1:0);
        gameOptions.replace("soundfx", (soundfx.isSelected())?1:0);

        gameOptions.replace("playerlives", (playerlives.getValue()==null)?gameOptions.get("playerlives"): playerlives.getValue());
        gameOptions.replace("mouthanim", (mouthanim.getValue()==null)?gameOptions.get("mouthanim"): mouthanim.getValue());
        if (Objects.equals(speed.getValue(), "Fast")){
            gameOptions.replace("speed",GAMESPEED_DEFAULT * 2);
        }
        else if (Objects.equals(speed.getValue(), "Normal")){
            gameOptions.replace("speed",GAMESPEED_DEFAULT );
        }
        else if (Objects.equals(speed.getValue(), "Slow")){
            gameOptions.replace("speed",GAMESPEED_DEFAULT/2);
        }

        if (!music.isSelected()){
            gameOptions.replace("music", 0);
            if(sound.isMusicplaying()){
                sound.stop();
            }
        }
        else if (music.isSelected()){
            gameOptions.replace("music",1);
            if(!sound.isMusicplaying()){
                sound.beginSoundtrack();
                sound.loop();
            }
        }

    }

    /**
     * Updates the GUI values when opening the Options GUI.
     * This allows the player to see their saved options.
     *
     * @param gameOptions a Hashmap of options and their values.
     */

    public void updateOptions(HashMap<String, Integer> gameOptions){
        framerate.setPromptText(Integer.toString(gameOptions.get("framerate")));
        playerlives.getValueFactory().setValue(gameOptions.get("playerlives"));
        mouthanim.setPromptText(Integer.toString(gameOptions.get("mouthanim")));
        easymov.setSelected(gameOptions.get("easymove") == 1);
        soundfx.setSelected(gameOptions.get("soundfx") == 1);
        music.setSelected(gameOptions.get("music") == 1);

        if (Objects.equals(gameOptions.get("speed"), GAMESPEED_DEFAULT)){
            speed.setPromptText("Normal");
        }
        else if(gameOptions.get("speed") == GAMESPEED_DEFAULT/2){
            speed.setPromptText("Slow");
        }
        else if(gameOptions.get("speed") == GAMESPEED_DEFAULT*2){
            speed.setPromptText("Fast");
        }
    }

    /**
     * Updates the local gameOptions variable when opening the Options GUI.
     * This helps GUI to update the values as it uses the local gameOptions variable.
     *
     * @param gameOptions a Hashmap of options and their values.
     */
    public void setGameOptions(HashMap<String, Integer> gameOptions) {
        this.gameOptions = gameOptions;
    }
}
