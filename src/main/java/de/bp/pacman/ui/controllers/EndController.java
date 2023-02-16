package de.bp.pacman.ui.controllers;


import de.bp.pacman.Leaderboard;
import de.bp.pacman.Player;
import de.bp.pacman.options.Options;
import de.bp.pacman.ui.HelloApplication;
import de.bp.pacman.ui.Home;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;

/**
 * Controller Class of the high score GUI at the end of the game.
 */


public class EndController {

    @FXML
    private Text firstScore;

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
    private Text firstName;

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
    private Button menu;

    @FXML
    private Button playAgain;

    @FXML
    private Text scoreone;

    @FXML
    private Text scoretwo;

    @FXML
    private Text scorethree;

    Leaderboard leaderboard = new Leaderboard();

    private HashMap<String,Integer> options = null;

    private int level = 0;
    private Player[] players;

    /**
     * Takes the player to the Home GUI.
     * @throws Exception if an error occurs during loading of the home GUI
     */
    @FXML
    void menubuttonpressed() throws Exception {
        Stage stage = (Stage) menu.getScene().getWindow();
        stage.close();
        Home hello = new Home();
        hello.setOptions(options);
        hello.start(new Stage());
    }

    /**
     *Restarts the game with the same options and player numbers
     */
    @FXML
    void playbuttonpressed() {
        Stage stage = (Stage) playAgain.getScene().getWindow();
        stage.close();
        HelloApplication hello = new HelloApplication();
        hello.setPlayerNumber(players.length);
        hello.setLevelNumber(level);
        hello.setOptions(new Options(options.get("speed"),options.get("framerate"),options.get("playerlives"), null, (options.get("easymove")==1),(options.get("soundfx")==1)));
        hello.setAllOptions(options);
        hello.start(new Stage());
    }

    /**
     * shows each player's points
     */
    public void showplayers() {
        for (Player value : players) {
            switch (value.getId()) {
                case 1 -> scoreone.setText(Integer.toString(value.getPoints()));
                case 2 -> scoretwo.setText(Integer.toString(value.getPoints()));
                case 3 -> scorethree.setText(Integer.toString(value.getPoints()));
            }
        }
    }

    /**
     *Updates the high score GUI at the end of a game.
     */
    public void top10update(){
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
     * Writes the player list variable
     * in order for the high score to update the new scores
     *
     *  @param players Player List.
     */
    public void allPlayers(Player[] players){
        this.players = players;
    }

    /**
     * Writes the level variable to maintain the same level
     * in case the player wants to play again
     *
     * @param level level of the game map.
     */

    public void setLevel(int level){this.level = level;}

    /**
     * Writes the options variable to maintain the same settings
     * in case the player wants to play again
     *
     * @param options settings of the game
     */
    public void setOptions(HashMap<String, Integer> options) {
        this.options = options;
    }
}
