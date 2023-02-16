package de.bp.pacman;

import de.bp.pacman.ui.Home;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

/**
 * pretty pacman game
 */
public class Main {

    /**
     * Pretty pacman game
     * @param args not used
     */
    public static void main(String[] args)  {
        File highscores = new File("Highscore.xml");
        if (!highscores.isFile()) {
            InputStream defaultHighscores = Main.class.getClassLoader().getResourceAsStream("reset.xml");
            try {
                Files.copy(defaultHighscores, highscores.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Home.main(args);
    }


}
