package hu.jonat.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A MainStage osztály a fő ablakot hozza látre.
 */
public class MainStage {

    /**
     * stage inicializálása.
     */
    public static Stage stage;
    /**
     * scene inicializálása.
     */
    public static Scene scene;

    /**
     * A konstruktor létrehozza a {@code MainStage} objektumot.
     *
     * @throws Exception hibás fxml betöltés
     */
    public MainStage() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));

        scene = new Scene(root);
        scene.getStylesheets().add("styles/Styles.css");
        stage = new Stage();
        stage.setTitle("Darts");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }
}
