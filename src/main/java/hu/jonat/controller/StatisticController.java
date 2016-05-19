package hu.jonat.controller;

import hu.jonat.model.Statisztika;
import hu.jonat.view.Chart;
import hu.jonat.view.MainApp;
import hu.jonat.view.MainStage;
import hu.jonat.view.Statistic;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * A Statistic osztály controller osztálya.
 */
public class StatisticController implements Initializable {

    /**
     * Aa táblázat inicializálása.
     */
    @FXML
    private TableView<Statisztika> tableID;

    /**
     * A táblázat első oszlopa.
     */
    @FXML
    private TableColumn<Statisztika, Integer> id;

    /**
     * A táblázat második oszlopa.
     */
    @FXML
    private TableColumn<Statisztika, String> statisztika;

    /**
     * A táblázat harmadik oszlopa.
     */
    @FXML
    private TableColumn<Statisztika, Double> firstGamer;

    /**
     * A táblázat negyedik oszlopa.
     */
    @FXML
    private TableColumn<Statisztika, Double> secondGamer;
    /**
     * A vissza gomb.
     */
    @FXML
    public Button back;

    /**
     * Az id számlálója.
     */
    int count = 1;

    /**
     * Statisztikát tartalmazó lista.
     */
    ObservableList<Statisztika> data = FXCollections.observableArrayList(
            new Statisztika(count++,"Points",MainApp.first.getPoint(),MainApp.second.getPoint()),
            new Statisztika(count++,"Sets",MainApp.first.getSet(),MainApp.second.getSet()),
            new Statisztika(count++,"Legs",MainApp.first.getLeg(),MainApp.second.getLeg()),
            new Statisztika(count++,"Best leg",MainApp.first.getBestLeg(),MainApp.second.getBestLeg()),
            new Statisztika(count++,"Previous leg",MainApp.first.getPreviousLeg(),MainApp.second.getPreviousLeg()),
            new Statisztika(count++,"Current leg",MainApp.first.getCurrentLeg(),MainApp.second.getCurrentLeg()),
            new Statisztika(count++,"Current set",MainApp.first.getCurrentSet(),MainApp.second.getCurrentSet()),
            new Statisztika(count++,"Match average",MainApp.first.getMatchAverage(),MainApp.second.getMatchAverage()),
            new Statisztika(count++,"Leg-ben dobott nyilak",MainApp.first.getDarts(),MainApp.second.getDarts())
    );

    /**
     * Az initialize metódus felül defininiálása.
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        id.setText("id");
        statisztika.setText("Statisztika");
        firstGamer.setText(MainApp.first.getNev());
        secondGamer.setText(MainApp.second.getNev());
        id.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(cellData.getValue().getId()));
        statisztika.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<String>(cellData.getValue().getStatisztika()));
        firstGamer.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Double>(cellData.getValue().getFirstGamer()));
        secondGamer.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Double>(cellData.getValue().getSecondGamer()));
        tableID.setItems(data);
    }

    /**
     * A "vissza" gomb esemény kezelése.
     *
     * @param actionEvent esemény
     */
    public void handleBackButton(ActionEvent actionEvent) throws IOException {
        Statistic.stage.close();
        MainStage.stage.show();

        /*Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Main.fxml"));

        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/
    }

    public void tableViewClicked(Event event) {
//        System.out.println(tableID.getSelectionModel().getSelectedItem().getId());
//        System.out.println(MainApp.first.getPontok());
        new Chart(tableID.getSelectionModel().getSelectedItem().getId());
    }
}
