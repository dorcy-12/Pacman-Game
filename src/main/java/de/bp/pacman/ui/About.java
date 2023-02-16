package de.bp.pacman.ui;

import de.bp.pacman.ui.controllers.AboutController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.util.HashMap;

/**
 * About GUI
 */

public class About {
    private HashMap<String,Integer> gameoptions = null;
    /**
     * @return scene of the About GUI
     * @throws Exception if an error occurs during loading of the FXML document
     */
    public Scene getAbout() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("About.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        AboutController ab = fxmlLoader.<AboutController>getController();
        ab.setGameoptions(gameoptions);
        String css = this.getClass().getResource("hello-view.css").toExternalForm();
        scene.getStylesheets().add(css);
        return scene;
    }

    /**
     * Maintains the chosen options while opening the About GUI.
     *
     * @param gameoptions a Hashmap of options and their values.
     */
    public void setGameoptions(HashMap<String, Integer> gameoptions) {
        this.gameoptions = gameoptions;
    }
}
