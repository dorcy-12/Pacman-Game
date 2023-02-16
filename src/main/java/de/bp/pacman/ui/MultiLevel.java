package de.bp.pacman.ui;

import de.bp.pacman.ui.controllers.MultiController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.util.HashMap;

/**
 * Multi Player GUI for level and player number selection.
 */
public class MultiLevel {

    private HashMap<String,Integer> options = null;
    /**
     * @return scene of the Multi Player GUI
     * @throws Exception if an error occurs during loading of the FXML document
     */
    public Scene getMultiLevel() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MultiPlevel.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        MultiController multi = fxmlLoader.<MultiController>getController();
        multi.setOptions(options);
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
