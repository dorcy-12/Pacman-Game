package de.bp.pacman.ui;

import de.bp.pacman.ui.controllers.OptionsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;

/**
 * Options GUI
 */
public class OptionGUI {
    private HashMap<String,Integer> options = null;

    /**
     *
     * @return scene of the Options GUI
     * @throws IOException if an error occurs during loading of the FXML document.
     */
    public Scene getOptionsGUI() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("options.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        OptionsController opt = fxmlLoader.<OptionsController>getController();
        opt.setGameOptions(options);
        opt.updateOptions(options);
        String css = this.getClass().getResource("hello-view.css").toExternalForm();
        scene.getStylesheets().add(css);
        return scene;
    }

    /**
     * Sets the local options variable to options saved from the Home GUI in order
     * to display the last saved options in the Options GUI.
     *
     * @param options a Hashmap of options and their values.
     */
    public void setOptions(HashMap<String, Integer> options) {
        this.options = options;
    }
}
