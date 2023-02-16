package de.bp.pacman.ui;

import de.bp.pacman.ui.controllers.SingleController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;

/**
 * Single Player GUI for level selection
 */
public class SingleLevel {

    private HashMap<String,Integer> options = null;
    /**
     *
     * @return scene Single Player GUI
     * @throws IOException if an error occurs during loading of the FXML document
     */

    public Scene getSingleLevel() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SinglePlevel.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        SingleController single = fxmlLoader.<SingleController>getController();
        single.setOptions(options);
        String css = this.getClass().getResource("hello-view.css").toExternalForm();
        scene.getStylesheets().add(css);
        return scene;
    }

    /**
     * Maintains the chosen options in Multi Level GUI.
     *
     * @param options a Hashmap of options and their values.
     */
    public void setOptions(HashMap<String, Integer> options) {
        this.options = options;
    }
}
