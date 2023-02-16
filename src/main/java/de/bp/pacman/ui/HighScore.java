package de.bp.pacman.ui;


import de.bp.pacman.ui.controllers.HighscoreController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.util.HashMap;

/**
 * General Highscore GUI from home
 */
public class HighScore{
    private HashMap<String,Integer> gameoptions = null;
    /**
     * @return scene of the Highscore GUI from Home GUI
     * @throws Exception if an error occurs during loading of the FXML document
     */

    public Scene gethighscore() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("highscore.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        String css = this.getClass().getResource("hello-view.css").toExternalForm();
        HighscoreController hs = fxmlLoader.<HighscoreController>getController();
        hs.setGameoptions(gameoptions);
        hs.highscoreupdate();
        scene.getStylesheets().add(css);
        return scene;
    }

    /**
     * Maintains the chosen options while opening the highscore GUI.
     *
     * @param gameoptions a Hashmap of options and their values.
     */
    public void setGameoptions(HashMap<String, Integer> gameoptions) {
        this.gameoptions = gameoptions;
    }
}
