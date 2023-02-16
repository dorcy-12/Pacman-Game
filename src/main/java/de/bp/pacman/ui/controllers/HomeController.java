package de.bp.pacman.ui.controllers;

import de.bp.pacman.ui.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
/**
 * Controller Class of Home GUI
 */
public class HomeController {

    @FXML
    private Label multi;

    @FXML
    private Label highscore;

    @FXML
    private Label single ;

    @FXML
    private Label options ;

    @FXML
    private Label about;

    private int players = 0;

    private HashMap<String,Integer> gameoptions = null;

    /**
     * Switches the scenes from the Home GUI to the about GUI.
     *
     * @throws IOException if an error occurs during loading of the aboutGUI
     */
    @FXML
    void aboutbuttonclicked() throws Exception {
        Stage stage = (Stage) about.getScene().getWindow();
        About about = new About();
        about.setGameoptions(gameoptions);
        stage.setScene(about.getAbout());
    }
    /**
     * Switches the scenes from the Home GUI to the high score  GUI.
     *
     * @throws IOException if an error occurs during loading of the high score GUI
     */

    @FXML
    void highscorebuttonclicked() throws Exception {
        Stage stage = (Stage) highscore.getScene().getWindow();
        HighScore hs = new HighScore();
        hs.setGameoptions(gameoptions);
        stage.setScene(hs.gethighscore());
    }
    /**
     * Switches the scenes from the Home GUI to the Multi player GUI.
     *
     * @throws IOException if an error occurs during loading of the Multi Player GUI
     */

    @FXML
    void multibuttonclicked() throws Exception {
        Stage stage = (Stage) multi.getScene().getWindow();
        MultiLevel ml = new MultiLevel();
        ml.setOptions(gameoptions);
        stage.setScene(ml.getMultiLevel());
    }

    /**
     * Switches the scenes from the Home GUI to the Single Player GUI.
     *
     * @throws IOException if an error occurs during loading of the Single Player GUI
     */
    @FXML
    void singlebuttonclicked() throws Exception {
        Stage stage = (Stage) single.getScene().getWindow();
        SingleLevel sl = new SingleLevel();
        sl.setOptions(gameoptions);
        stage.setScene(sl.getSingleLevel());
    }

    /**
     * Switches the scenes from the Home GUI to the options GUI.
     *
     * @throws IOException if an error occurs during loading of the Options GUI
     */
    @FXML
    void optionsbuttonclicked() throws IOException {
        Stage stage = (Stage) options.getScene().getWindow();
        OptionGUI opt = new OptionGUI();
        opt.setOptions(gameoptions);
        stage.setScene(opt.getOptionsGUI());
    }

    /**
     * Writes the options variable to maintain the same settings when switching
     * to and from any windows.
     *
     * @param gameoptions a Hashmap of options and their values.
     */
    public void setGameoptions(HashMap<String, Integer> gameoptions) {
        this.gameoptions = gameoptions;
    }



}
