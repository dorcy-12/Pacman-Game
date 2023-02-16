package de.bp.pacman.ui;

import de.bp.pacman.Player;
import de.bp.pacman.Sound;
import de.bp.pacman.ui.controllers.newScoreController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.util.HashMap;

/**
 * New Highscore GUI
 */
public class newScore {

    Player[] newPlayers = new Player[]{};
    Player[] allPlayers = new Player[]{};

    private int level = 1;

    private Sound sound = Sound.getInstance();

    private HashMap<String,Integer> options = null;

    /**
     * @return scene of the newScore GUI
     * @throws Exception if an error occurs during loading of the FXML document
     */
    public Scene getnewScore() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("newscore.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        String css = this.getClass().getResource("hello-view.css").toExternalForm();
        scene.getStylesheets().add(css);
        newScoreController newscore = fxmlLoader.<newScoreController>getController();
        newscore.setBestPlayers(newPlayers);
        newscore.setAllplayers(allPlayers);
        newscore.setlevel(level);
        newscore.setOptions(options);
        newscore.bestnames();
        if(sound.isMusicplaying()){
            sound.getMusic().setMute(false);
        }
        return scene;
    }
    /**
     * @param players player List
     * Modifies the variable of players with a highscore
     */
    public void bestplayers(Player[] players){
        this.newPlayers = players;
    }

    /**
     * This method receives all players who just played in order to
     * relay them to the End GUI.
     * This allows the game later to be replayed again from the End GUI
     * with the same players/
     *
     * @param players list of players
     */
    public void allplayers(Player[] players){
        this.allPlayers = players;
    }

    /**
     * This method receives the level of the just played game in order to
     * relay it to the End GUI.
     * This allows the game later to be replayed again from the End GUI
     * with the same level.
     *
     * @param level level of the just played game.
     */
    public void setLevel(int level){this.level = level;}

    /**
     * This method receives all players who just played in order to
     * relay them to the End GUI.
     * This allows the game later to be replayed again from the End GUI
     * with the same players/
     *
     * @param options a Hashmap of options and their values.
     */
    public void setOptions(HashMap<String, Integer> options) {
        this.options = options;
    }
}
