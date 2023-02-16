package de.bp.pacman.ui.controllers;

import de.bp.pacman.Leaderboard;
import de.bp.pacman.ui.Home;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Controller Class of Highscore GUI from Home GUI
 */
public class HighscoreController {

    @FXML
    private Text firstScore;

    @FXML
    private Text firstName;

    @FXML
    private Text secondScore;

    @FXML
    private Text thirdScore;

    @FXML
    private Text fourthScore;

    @FXML
    private Text fifthScore;

    @FXML
    private Text sixthScore;

    @FXML
    private Text seventhScore;

    @FXML
    private Text eighthScore;

    @FXML
    private Text ninthScore;

    @FXML
    private Text tenthScore;

    @FXML
    private Text secondName;

    @FXML
    private Text thirdName;

    @FXML
    private Text fourthName;

    @FXML
    private Text fifthName;

    @FXML
    private Text sixthName;

    @FXML
    private Text seventhName;

    @FXML
    private Text eighthName;

    @FXML
    private Text ninthName;

    @FXML
    private Text tenthName;

    @FXML
    private Button back;

    private HashMap<String,Integer> gameoptions = null;
    Leaderboard leaderboard = new Leaderboard();

    /**
     * Handles the scene switching from the Highscore GUI to Home GUI
     * @throws IOException if an error occurs during loading of the home GUI
     */
    @FXML
    void backbuttonpressed() throws Exception {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
        Home homescreen = new Home();
        homescreen.setOptions(gameoptions);
        homescreen.start(new Stage());
    }

    /**
     * Updates the high score GUI values
     */
    public void highscoreupdate(){
        List<Pair<String, Integer>> scores = leaderboard.getTop10();
        for (int i =0; i<10;i++){
            switch (i){
                case 0 ->{
                    firstScore.setText(Integer.toString(scores.get(i).getValue()));
                    firstName.setText(scores.get(i).getKey());
                }
                case 1 ->{
                    secondScore.setText(Integer.toString(scores.get(i).getValue()));
                    secondName.setText(scores.get(i).getKey());
                }
                case 2 ->{
                    thirdScore.setText(Integer.toString(scores.get(i).getValue()));
                    thirdName.setText(scores.get(i).getKey());
                }
                case 3 ->{
                    fourthScore.setText(Integer.toString(scores.get(i).getValue()));
                    fourthName.setText(scores.get(i).getKey());
                }
                case 4 ->{
                    fifthScore.setText(Integer.toString(scores.get(i).getValue()));
                    fifthName.setText(scores.get(i).getKey());
                }
                case 5 ->{
                    sixthScore.setText(Integer.toString(scores.get(i).getValue()));
                    sixthName.setText(scores.get(i).getKey());
                }
                case 6 ->{
                    seventhScore.setText(Integer.toString(scores.get(i).getValue()));
                    seventhName.setText(scores.get(i).getKey());
                }
                case 7 ->{
                    eighthScore.setText(Integer.toString(scores.get(i).getValue()));
                    eighthName.setText(scores.get(i).getKey());
                }
                case 8 ->{
                    ninthScore.setText(Integer.toString(scores.get(i).getValue()));
                    ninthName.setText(scores.get(i).getKey());
                }
                case 9 ->{
                    tenthScore.setText(Integer.toString(scores.get(i).getValue()));
                    tenthName.setText(scores.get(i).getKey());
                }
            }
        }
    }

    /**
     * Writes the options variable to maintain the same settings when switching
     * windows back to the home GUI
     *
     * @param gameoptions a Hashmap of options and their values.
     */

    public void setGameoptions(HashMap<String, Integer> gameoptions) {
        this.gameoptions = gameoptions;
    }
}
