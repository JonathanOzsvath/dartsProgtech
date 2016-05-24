package hu.jonat.view;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Diagramm osztály.
 */
public class Chart {

    /**
     * Pont string.
     */
    final static String pont = "Pont";
    /**
     * Set string.
     */
    final static String set = "Set";
    /**
     * Leg string.
     */
    final static String leg = "Leg";
    /**
     * Legjobb Leg string.
     */
    final static String bestLeg = "Legjobb Leg";
    /**
     * Előző Leg string.
     */
    final static String previousLeg = "Előző Leg";
    /**
     * Aktuális Leg Átlaga string.
     */
    final static String currentLeg = "Aktuális Leg átlaga";
    /**
     * Aktuális Set átlaga string.
     */
    final static String currentSet = "Aktuális Set átlaga";

    /**
     * Az id alapján történik a döntés melyik diagramm hívódik meg.
     */
    private int id;
    /**
     * x tengely.
     */
    private static Axis xAxis;
    /**
     * y tengely.
     */
    private static Axis yAxis;
    /**
     * vonal diagramm.
     */
    private static LineChart<Number, Number> lineChart;
    /**
     * oszlop diagramm.
     */
    private static BarChart<String, Number> barChart;
    /**
     * első játékos adatai.
     */
    private XYChart.Series seriesFirst;
    /**
     * második játékos adatai.
     */
    private XYChart.Series seriesSecond;
    /**
     * scene.
     */
    private static Scene scene;
    /**
     * stage.
     */
    private static Stage stage;

    /**
     * Chart osztály konstruktora.
     * @param id id kiválasztja a diagrammot
     */
    public Chart(int id) {
        this.id = id;

        seriesFirst = new XYChart.Series();
        seriesSecond = new XYChart.Series();

        stage = new Stage();
        stage.setTitle("Diagramm");

        switch (id){
            case 0:
                seriesFirst.getData().add(new XYChart.Data(pont,(Number)MainApp.first.getPoint()));
                seriesFirst.getData().add(new XYChart.Data(set,(Number)MainApp.first.getSet()));
                seriesFirst.getData().add(new XYChart.Data(leg,(Number)MainApp.first.getLeg()));
                seriesFirst.getData().add(new XYChart.Data(bestLeg,(Number)MainApp.first.getBestLeg()));
                seriesFirst.getData().add(new XYChart.Data(previousLeg,(Number)MainApp.first.getPreviousLeg()));
                seriesFirst.getData().add(new XYChart.Data(currentLeg,(Number)MainApp.first.getCurrentLeg()));
                seriesFirst.getData().add(new XYChart.Data(currentSet,(Number)MainApp.first.getCurrentSet()));

                seriesSecond.getData().add(new XYChart.Data(pont,(Number)MainApp.second.getPoint()));
                seriesSecond.getData().add(new XYChart.Data(set,(Number)MainApp.second.getSet()));
                seriesSecond.getData().add(new XYChart.Data(leg,(Number)MainApp.second.getLeg()));
                seriesSecond.getData().add(new XYChart.Data(bestLeg,(Number)MainApp.second.getBestLeg()));
                seriesSecond.getData().add(new XYChart.Data(previousLeg,(Number)MainApp.second.getPreviousLeg()));
                seriesSecond.getData().add(new XYChart.Data(currentLeg,(Number)MainApp.second.getCurrentLeg()));
                seriesSecond.getData().add(new XYChart.Data(currentSet,(Number)MainApp.second.getCurrentSet()));
                setChart(2,seriesFirst,seriesSecond);
                break;
            case 1:
                seriesFirst.getData().add(new XYChart.Data("",MainApp.first.getPoint()));
                seriesSecond.getData().add(new XYChart.Data("", MainApp.second.getPoint()));
                setChart(2,seriesFirst,seriesSecond);
                break;
            case 2:
                seriesFirst.getData().add(new XYChart.Data("",MainApp.first.getSet()));
                seriesSecond.getData().add(new XYChart.Data("", MainApp.second.getSet()));
                setChart(2,seriesFirst,seriesSecond);
                break;
            case 3:
                seriesFirst.getData().add(new XYChart.Data("",MainApp.first.getLeg()));
                seriesSecond.getData().add(new XYChart.Data("", MainApp.second.getLeg()));
                setChart(2,seriesFirst,seriesSecond);
                break;
            case 4:
                seriesFirst.getData().add(new XYChart.Data("",MainApp.first.getBestLeg()));
                seriesSecond.getData().add(new XYChart.Data("", MainApp.second.getBestLeg()));
                setChart(2,seriesFirst,seriesSecond);
                break;
            case 5:
                seriesFirst.getData().add(new XYChart.Data("",MainApp.first.getPreviousLeg()));
                seriesSecond.getData().add(new XYChart.Data("", MainApp.second.getPreviousLeg()));
                setChart(2,seriesFirst,seriesSecond);
                break;
            case 6:
                seriesFirst.getData().add(new XYChart.Data("",MainApp.first.getCurrentLeg()));
                seriesSecond.getData().add(new XYChart.Data("", MainApp.second.getCurrentLeg()));
                setChart(2,seriesFirst,seriesSecond);
                break;
            case 7:
                seriesFirst.getData().add(new XYChart.Data("",MainApp.first.getCurrentSet()));
                seriesSecond.getData().add(new XYChart.Data("", MainApp.second.getCurrentSet()));
                setChart(2,seriesFirst,seriesSecond);
                break;
            case 8:
                for (int x = 0; x < MainApp.first.getPontok().size(); x++) {
                    seriesFirst.getData().add(new XYChart.Data(x + 1, MainApp.first.getMatchAverages().get(x)));
                }

                for (int x = 0; x < MainApp.second.getPontok().size(); x++) {
                    seriesSecond.getData().add(new XYChart.Data(x + 1, MainApp.second.getMatchAverages().get(x)));
                }
                setChart(1,seriesFirst, seriesSecond);
                break;
            case 9:
                seriesFirst.getData().add(new XYChart.Data("",MainApp.first.getDarts()));
                seriesSecond.getData().add(new XYChart.Data("", MainApp.second.getDarts()));
                setChart(2,seriesFirst,seriesSecond);
                break;
            default:
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Leg Information");
                alert.setHeaderText(null);
                alert.setContentText("Nincs ilyen diagramm!!!");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    alert.close();
                    stage.close();
                }
                break;
        }

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case ESCAPE:
                        stage.close();
                        break;
                }
            }
        });
    }

    /**
     *Diagramm beállítása.
     *
     * @param chartType diagramm típusa
     * @param seriesFirst első játékos adatai
     * @param seriesSecond második játékos adatai
     */
    public static void setChart(int chartType, XYChart.Series seriesFirst, XYChart.Series seriesSecond){
        seriesFirst.setName(MainApp.first.getNev());
        seriesSecond.setName(MainApp.second.getNev());

        switch (chartType){
            case 1:
                xAxis = new NumberAxis();
                yAxis = new NumberAxis();
                lineChart = new LineChart<Number, Number>(xAxis, yAxis);
                lineChart.getData().addAll(seriesFirst, seriesSecond);
                scene = new Scene(lineChart, 640, 480);
                break;
            case 2:
                xAxis = new CategoryAxis();
                yAxis = new NumberAxis();
                barChart = new BarChart<String, Number>(xAxis,yAxis);
                barChart.getData().addAll(seriesFirst, seriesSecond);
                scene = new Scene(barChart, 480, 640);
                break;
        }

        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }

}
