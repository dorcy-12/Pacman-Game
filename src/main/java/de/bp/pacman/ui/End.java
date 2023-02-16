package de.bp.pacman.ui;

import de.bp.pacman.Player;
import de.bp.pacman.Sound;
import de.bp.pacman.ui.controllers.EndController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.util.HashMap;

/**
 * High score GUI after a game
 */
public class End  {

    Player[] playerinfo = new Player[]{};

    int level = 1;
    private Sound sound = Sound.getInstance();

    private HashMap<String,Integer> options = null;

    /**
     * @return scene of the High score GUI at the end of a game
     * @throws Exception if an error occurs during loading of the FXML document
     */
    public Scene getEnd() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("End.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        String css = this.getClass().getResource("hello-view.css").toExternalForm();
        scene.getStylesheets().add(css);
        EndController end = fxmlLoader.<EndController>getController();
        end.allPlayers(playerinfo);
        end.setLevel(level);
        end.setOptions(options);
        end.showplayers();
        end.top10update();
        if(sound.isMusicplaying()){
            sound.getMusic().setMute(false);
        }
        return scene;
    }

    /**
     * Sets the local PLayer variable to a new value.
     * This method is used when switching from the Ingame or newScore GUI
     * to the End GUI to maintain the number of Players incase the player/players
     * want to play again.
     *
     * @param players List of Players
     */
    public void setPlayers(Player[] players){
        this.playerinfo = players;
    }

    /**
     * Sets the local level variable to a new value.
     * This method is used when switching from the Ingame or newScore GUI
     * to the End GUI to maintain the game's level incase a player wants to play again.
     *
     * @param level level of the game
     */
    public void setLevel(int level){this.level = level;}

    /**
     * Maintains the chosen options while opening the end high score GUI.
     *
     * @param options a Hashmap of options and their values.
     */
    public void setOptions(HashMap<String, Integer> options) {
        this.options = options;
    }
}
