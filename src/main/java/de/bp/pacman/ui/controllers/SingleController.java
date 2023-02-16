package de.bp.pacman.ui.controllers;


import de.bp.pacman.options.Options;
import de.bp.pacman.ui.HelloApplication;
import de.bp.pacman.ui.Home;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

/**
 * Controller class of the Single Player GUI
 */
public class SingleController {

    @FXML
    private Label decL;

    @FXML
    private Label level;

    @FXML
    private Label incL;

    @FXML
    private Button back;

    @FXML
    private Button start;

    private HashMap<String,Integer> options = null;

    /**
     * Handles the scene switching from the Highscore GUI to Home GUI
     * @throws IOException if an error occurs during loading of the home GUI
     */
    @FXML
    void backbuttonpressed(ActionEvent event) throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
        Home home =  new Home();
        home.setOptions(options);
        home.start(new Stage());
    }

    /**
     * Starts a new game with multiple players and the chosen settings.
     */
    @FXML
    void startbuttonpressed(ActionEvent event) {

        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
        HelloApplication hello =  new HelloApplication();
        hello.setPlayerNumber(1);
        hello.setLevelNumber(Integer.parseInt(level.getText()));
        hello.setOptions(new Options(options.get("speed"),options.get("framerate"),options.get("playerlives"), null, (options.get("easymove")==1),(options.get("soundfx")==1)));
        hello.setAllOptions(options);
        hello.start(new Stage());
    }
    /**
     * decrease the level count from the click of the label decL
     */
    @FXML
    void decLclicked() {
        int lev  = Integer.parseInt(level.getText());
        if (lev > 1){
            level.setText(Integer.toString(lev-1));
        }
    }

    /**
     * Increase the level count from the click of the label incL
     */
    @FXML
    void incLclicked() {
        int lev  = Integer.parseInt(level.getText());
        if (lev < 5){
            level.setText(Integer.toString(lev+1));
        }

    }
    /**
     * Writes the options variable to maintain the same settings from
     * the Home GUI in order to start a game with the chosen settings.
     *
     * @param options a Hashmap of options and their values.
     */
    public void setOptions(HashMap<String, Integer> options) {
        this.options = options;
    }
}