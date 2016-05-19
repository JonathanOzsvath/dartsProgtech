package hu.jonat.view;

import hu.jonat.controller.StatisticController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A Statistic osztály a statisztikát tartalmazó ablakot hozza létre.
 */
public class Statistic {

    /**
     * stage inicializálás.
     */
    public static Stage stage;

    /**
     * A StatisticController osztály inicializálása.
     */
    public StatisticController statisticController;

    /**
     * A konstruktor létrehozza a {@code Statistic} objektumot.
     */
    public Statistic() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Statistic.fxml"));

        Parent root = null;

        try {
            root = loader.load();
        } catch (Exception e){
            e.printStackTrace();
        }

        statisticController = loader.getController();

        assert root != null;
        Scene scene = new Scene(root);
        scene.getStylesheets().add("styles/Styles.css");
        stage = new Stage();
        stage.setTitle("Statisztika");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.show();
    }
}
