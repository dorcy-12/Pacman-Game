package de.bp.pacman.ui.controllers;


import de.bp.pacman.ui.Home;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
/**
 * Controller class of the About GUI
 */
public class AboutController {

    @FXML
    private Button back;

    private HashMap<String,Integer> gameoptions = null;

    /**
     * Handles the scene switching from About GUI to Home GUI
     * @throws IOException if an error occurs during loading of the home GUI
     */
    @FXML
    void backbuttonpressed() throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
        Home home = new Home();
        home.setOptions(gameoptions);
        home.start(new Stage());
    }

    /**
     * Changes and updates the variable gameoptions in order to
     * maintain the options while entering the About GUI .
     *
     * @param gameoptions options of the game from home scene.
     */
    public void setGameoptions(HashMap<String, Integer> gameoptions) {
        this.gameoptions = gameoptions;
    }
}
