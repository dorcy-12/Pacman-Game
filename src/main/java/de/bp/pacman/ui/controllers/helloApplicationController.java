package de.bp.pacman.ui.controllers;

import de.bp.pacman.GameManager;
import de.bp.pacman.Leaderboard;
import de.bp.pacman.Player;
import de.bp.pacman.ui.End;
import de.bp.pacman.ui.newScore;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Controller Class of the Ingame GUI
 */

public class helloApplicationController {
    Leaderboard leaderboard = new Leaderboard();

    private HashMap<String,Integer> options = null;

    /**
     * checks whether the game is over periodically in order to
     * close the game window and open other windows while maintaining
     * the same settings
     *
     * @param gameManager controls gameplay
     * @param stage window of the gameplay
     * @param level level of the gameplay
     */
    public void gameOverUpdate(GameManager gameManager, Stage stage, int level){
        Timeline timeLine = new Timeline();
        KeyFrame kf = new KeyFrame(Duration.millis(200),
                event -> {
                        if (gameManager.isCloseGame()){
                            timeLine.stop();
                            Player[] newhighscores = newhighscores(gameManager.getPlayer());
                            if (newhighscores.length == 0){
                                End end = new End();
                                end.setPlayers(gameManager.getPlayer());
                                end.setLevel(level);
                                end.setOptions(options);
                                try {
                                    stage.setScene(end.getEnd());
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            else {
                                newScore ns = new newScore();
                                ns.bestplayers(newhighscores);
                                ns.allplayers(gameManager.getPlayer());
                                ns.setLevel(level);
                                ns.setOptions(options);
                                try {
                                    stage.setScene(ns.getnewScore());
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }

                        }
                });
        timeLine.getKeyFrames().addAll(kf);
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
    }

    /**
     * Checks whether there is a new high score after the game is Over
     * and return the players with a new high score.
     *
     * @param players list of players
     * @return sorted List of players with new Highscores
     */
    public Player[] newhighscores(Player[] players){
        List<Player> bestplayers = new ArrayList<>();
        List<Pair<String,Integer>> scores = leaderboard.getTop10();
        for (Player value:players){
            for(int i = 0;i<10;i++){
                if (value.getPoints() > scores.get(i).getValue()) {
                    bestplayers.add(value);
                    break;
                }
            }
        }

        bestplayers.sort((o1, o2) -> o2.getPoints() - o1.getPoints());
        Player[] best = new Player[bestplayers.size()];
        return bestplayers.toArray(best);

    }

    /**
     * Writes the options variable to maintain the same settings
     * The options are then relayed to the end high score window to allow players
     * to restart the game with same settings.
     *
     * @param options a Hashmap of options and their values.
     */

    public void setOptions(HashMap<String, Integer> options) {
        this.options = options;
    }
}
