package de.bp.pacman.ui;

import de.bp.pacman.Sound;
import de.bp.pacman.ui.controllers.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

/**
 * Home Application
 */
public class Home extends Application {
    private static final int gamespeed = 5;

    private static final int framerate = 60;

    //Instantiates the sound class as soon as home is opened first.

    private final Sound sound = Sound.getInstance();

    /**
     * Instatiates the Home GUI with default option settings.
     */

    public HashMap<String, Integer> options = new HashMap<String, Integer>() {{
        put("framerate", framerate);
        put("speed", gamespeed);
        put("easymove", 1);
        put("playerlives", 3);
        put("mouthanim", 10);
        put("soundfx", 1);
        put("music",1);
    }};

    /**
     * Starts the Home GUI
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * @throws IOException if an error occurs during loading of the FXML document
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        String css = this.getClass().getResource("hello-view.css").toExternalForm();
        HomeController home = fxmlLoader.<HomeController>getController();
        home.setGameoptions(options);
        scene.getStylesheets().add(css);
        primaryStage.setTitle("Pacman");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Starts the pacman game
     * @param args not used
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Maintains the chosen options through every scene.
     * This method is also used when changing scenes from options GUI
     * to Home GUI in order to update the new Options chosen.
     *
     * @param options a Hashmap of options and their values.
     */
    public void setOptions(HashMap<String, Integer> options) {
        this.options = options;
    }

}
