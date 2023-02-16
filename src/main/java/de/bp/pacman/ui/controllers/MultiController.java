package de.bp.pacman.ui.controllers;

import de.bp.pacman.options.Options;
import de.bp.pacman.ui.HelloApplication;
import de.bp.pacman.ui.Home;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
/**
 * Controller Class of Multi Player GUI
 */
public class MultiController {

    @FXML
    private Label decP;

    @FXML
    private Label players;

    @FXML
    private Label incP;

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
     * Handles the scene switching from the Multi Player GUI to Home GUI
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
     * decreases the level count from the click of the label decL
     */

    @FXML
    void decLclicked(MouseEvent event) {
        int lev  = Integer.parseInt(level.getText());
        if (lev > 1){
            level.setText(Integer.toString(lev-1));
        }
    }

    /**
     * decreases the player count from the click of the label decP
     */
    @FXML
    void decPclicked(MouseEvent event) {
        int playernr  = Integer.parseInt(players.getText());
        if (playernr == 3){
            players.setText(Integer.toString(playernr-1));
        }
    }

    /**
     * Increase the level count from the click of the label incL
     */
    @FXML
    void incLclicked(MouseEvent event) {
        int lev  = Integer.parseInt(level.getText());
        if (lev < 5){
            level.setText(Integer.toString(lev+1));
        }

    }

    /**
     * Increase the player count from the click of the label incP
     */

    @FXML
    void incPclicked() {
        int playernr  = Integer.parseInt(players.getText());
        if (playernr == 2){
            players.setText(Integer.toString(playernr+1));
        }
    }

    /**
     * Starts a new game with multiple players and the chosen settings.
     */

    @FXML
    void startbuttonpressed() {
        Stage stage = (Stage) start.getScene().getWindow();
        stage.close();
        HelloApplication hello =  new HelloApplication();
        hello.setPlayerNumber(Integer.parseInt(players.getText()));
        hello.setLevelNumber(Integer.parseInt(level.getText()));
        hello.setOptions(new Options(options.get("speed"),options.get("framerate"),options.get("playerlives"), null, (options.get("easymove")==1),(options.get("soundfx")==1)));
        hello.setAllOptions(options);
        hello.start(new Stage());
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
