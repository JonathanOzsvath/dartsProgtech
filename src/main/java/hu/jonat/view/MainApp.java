package hu.jonat.view;

import hu.jonat.model.Gamer;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A MainApp osztály tartalmazza a main-t.
 */
public class MainApp extends Application {

    /**
     * Az első játékos példányosítása.
     */
    public static Gamer first = new Gamer("1.játékos");
    /**
     * Az második játékos példányosítása.
     */
    public static Gamer second = new Gamer("2.játékos");

    @Override
    public void start(Stage stage) throws Exception {
        new MainStage();

    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
