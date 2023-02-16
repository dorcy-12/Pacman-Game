package de.bp.pacman.ui.controllers;

import de.bp.pacman.Leaderboard;
import de.bp.pacman.Player;
import de.bp.pacman.ui.End;
import de.bp.pacman.ui.TextFieldLimited;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Pair;

import javax.xml.transform.TransformerException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * Controller Class of newScore GUI
 */
public class newScoreController {

    @FXML
    private Label labelone;

    @FXML
    private TextFieldLimited inputone;

    @FXML
    private Label labeltwo;

    @FXML
    private TextFieldLimited inputtwo;

    @FXML
    private Label labelthree;

    @FXML
    private TextFieldLimited inputthree;

    @FXML
    private Button next;

    Leaderboard leaderboard = new Leaderboard();

    private HashMap<String,Integer> options = null;

    private int level = 1;
    private Player[] bestplayers;

    private Player[] allplayers;

    /**
     * Switches scenes from newScore GUI to End GUI only if a player has
     * entered their name
     *
     * @throws Exception if an error occurs during loading of the home GUI
     */
    @FXML
    void nextbuttonclicked() throws Exception {
        if (!isinputempty()) {
            Stage stage = (Stage) next.getScene().getWindow();

            updateleaderboard();

            End end = new End();
            end.setPlayers(allplayers);
            end.setLevel(level);
            end.setOptions(options);
            stage.setScene(end.getEnd());
        }
    }

    /**
     * Allows Players with a high score to write their names
     */
    public void bestnames() {
        for (Player value : bestplayers) {
            switch (value.getId()) {
                case 1 -> {
                    labelone.setDisable(false);
                    inputone.setDisable(false);
                }

                case 2 -> {
                    labeltwo.setDisable(false);
                    inputtwo.setDisable(false);
                }
                case 3 -> {
                    labelthree.setDisable(false);
                    inputthree.setDisable(false);
                }
            }
        }
    }

    /**
     * Sets the level of game played.
     * This method is used to maintain and relay the level of the game played
     * to the End GUI in case the player wants to play again.
     *
     * @param level level of played game
     */
    public void setlevel(int level){this.level = level;}

    /**
     * Sets the local variable bestplayers to a new value.
     * Players with a high score are given to the newScore GUI.
     *
     * @param players player list
     */
    public void setBestPlayers(Player[] players) {
        this.bestplayers = players;
    }


    /**
     * Sets the local variable allplayers to a new value.
     * All players and their information are given to the newScore GUI.
     *
     * @param players player list
     */
    public void setAllplayers(Player[] players) {
        this.allplayers = players;
    }

    /**
     * updates the high score list  and high score xml file.
     *
     * @throws TransformerException  general exception that occurs during the course of a transformation.
     *
     */
    public void updateleaderboard() throws TransformerException {
        List<Pair<String, Integer>> update = leaderboard.getTop10();

        for (Player player : bestplayers) {
            update.add(new Pair<>("-", player.getPoints()));
        }

        update.sort(new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });


        for (Player player : bestplayers) {
            for (int i = 0; i < 10; i++) {
                if (Objects.equals(update.get(i).getKey(), "-") && player.getPoints() == update.get(i).getValue()) {
                    switch (player.getId()) {
                        case 1 -> {
                            update.set(i, new Pair<>(inputone.getText(), player.getPoints()));
                        }
                        case 2 -> {
                            update.set(i, new Pair<>(inputtwo.getText(), player.getPoints()));
                        }
                        case 3 -> {
                            update.set(i, new Pair<>(inputthree.getText(), player.getPoints()));
                        }
                    }
                    break;
                }
            }
        }
        leaderboard.setTop10(update);
    }

    /**
     *
     * @return if the player has not filled in their name.
     */

    public boolean isinputempty() {
        boolean inputempty = false;
        for (Player player : bestplayers) {
            if (player.getId() == 1 && inputone.getText().isBlank()) {
                inputempty = true;
                break;
            } else if (player.getId() == 2 && inputtwo.getText().isBlank()) {
                inputempty = true;
                break;
            } else if (player.getId() == 3 && inputthree.getText().isBlank()) {
                inputempty = true;
                break;
            }
        }
        return inputempty;
    }

    /**
     * Writes the options variable to maintain the same settings from
     * the played game.
     * The local options variable will then be used in starting the End Gui
     * in order for the player to play again with the same settings.
     *
     * @param options a Hashmap of options and their values.
     */
    public void setOptions(HashMap<String, Integer> options) {
        this.options = options;
    }
}
