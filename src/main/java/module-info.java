/**
 * uses javafx and controlsfx
 */
module de.bp.pacman {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.xml;
    requires javafx.media;

    opens de.bp.pacman to javafx.fxml;
    exports de.bp.pacman;
    exports de.bp.pacman.ui;
    opens de.bp.pacman.ui to javafx.fxml;
    exports de.bp.pacman.ui.fieldtype;
    opens de.bp.pacman.ui.fieldtype to javafx.fxml;
    opens de.bp.pacman.ui.controllers to javafx.fxml;
}