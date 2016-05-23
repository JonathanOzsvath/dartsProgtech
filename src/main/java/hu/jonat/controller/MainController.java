package hu.jonat.controller;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import hu.jonat.model.Gamer;
import hu.jonat.view.Chart;
import hu.jonat.view.MainApp;
import hu.jonat.view.MainStage;
import hu.jonat.view.Statistic;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.applet.Main;

/**
 * A MainStage osztály controller osztálya.
 */
public class MainController implements Initializable {

    /**
     * Segédváltozó.
     * Ha az értéke 0, akkor az első játékos nevét lehet beállítani.
     * Ha az értéke 1, akkor az második játékos nevét lehet beállítani.
     * Egyébként a pontérték számolása történik.
     */
    private int counter = 0;
    /**
     * Megadja, hogy ki kezdi a leget.
     * <code>true</code> ha az első játékos kezd, <code>false</code> egyébként
     */
    private boolean kezdes = true;

    /**
     * Az első játékos nevét tartalmazó label.
     */
    @FXML
    private Label firstGamerName;
    /**
     * Az második játékos nevét tartalmazó label.
     */
    @FXML
    private Label secondGamerName;
    /**
     * Az írási mező.
     */
    @FXML
    private TextField textField;
    /**
     * Az első játékos pontját tartalmazó label.
     */
    @FXML
    private Label firstGamerPoint;
    /**
     * Az második játékos pontját tartalmazó label.
     */
    @FXML
    private Label secondGamerPoint;
    /**
     * A statisztika gomb.
     */
    @FXML
    private Button statistic;

    /**
     * Logger instance.
     */
    static Logger LOG = LoggerFactory.getLogger(MainController.class);

    /**
     * Az initialize metódus felül defininiálása.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * A TextField eseménykezelése.
     *
     * @param actionEvent esemény
     */
    public void firstTextField(ActionEvent actionEvent) {
        if (counter == 0) {
            MainApp.first.setNev(textField.getText());
            firstGamerName.setText(MainApp.first.getNev());
            LOG.info("Az elso jatekos neve: {}", MainApp.first.getNev());
            textField.clear();
            textField.setPromptText("Adja meg a 2.játékos nevét!");
            counter++;
        } else {
            if (counter == 1) {
                MainApp.second.setNev(textField.getText());
                secondGamerName.setText(MainApp.second.getNev());
                LOG.info("A masodik jatekos neve: {}", MainApp.second.getNev());
                textField.clear();
                textField.setPromptText("Adja meg a 3 dobás összegét!");
                counter++;
            } else {
                if (kezdes) {
                    if (MainApp.first.getDarts() == 0) {
                        LOG.info("{} kezdte a leget.", MainApp.first.getNev());
                    }
                    if (MainApp.first.getDarts() - MainApp.second.getDarts() == 0) {
                        MainApp.first.throwing3(Integer.parseInt(textField.getText()));
                        firstGamerPoint.setText(String.valueOf(MainApp.first.getPoint()));
                        textField.clear();
                        textField.setPromptText("Adja meg a 3 dobás összegét!");
                        if (MainApp.first.checkout()) {
                            endLeg(true);
                            endLegInformation(MainApp.first.getNev());
                            if (MainApp.first.getSet() == 3) {
                                endGame(MainApp.first.getNev());
                            }
                        }
                    } else {
                        MainApp.second.throwing3(Integer.parseInt(textField.getText()));
                        secondGamerPoint.setText(String.valueOf(MainApp.second.getPoint()));
                        textField.clear();
                        textField.setPromptText("Adja meg a 3 dobás összegét!");
                        if (MainApp.second.checkout()) {
                            endLeg(false);
                            endLegInformation(MainApp.second.getNev());
                            if (MainApp.first.getSet() == 3) {
                                endGame(MainApp.second.getNev());
                            }
                        }
                    }
                } else {
                    if (MainApp.second.getDarts() == 0) {
                        LOG.info("{} kezdte a leget.", MainApp.second.getNev());
                    }
                    if (MainApp.second.getDarts() - MainApp.first.getDarts() == 0) {
                        MainApp.second.throwing3(Integer.parseInt(textField.getText()));
                        secondGamerPoint.setText(String.valueOf(MainApp.second.getPoint()));
                        textField.clear();
                        textField.setPromptText("Adja meg a 3 dobás összegét!");
                        if (MainApp.second.checkout()) {
                            endLeg(false);
                            endLegInformation(MainApp.second.getNev());
                            if (MainApp.first.getSet() == 3) {
                                endGame(MainApp.second.getNev());
                            }
                        }
                    } else {
                        MainApp.first.throwing3(Integer.parseInt(textField.getText()));
                        firstGamerPoint.setText(String.valueOf(MainApp.first.getPoint()));
                        textField.clear();
                        textField.setPromptText("Adja meg a 3 dobás összegét!");
                        if (MainApp.first.checkout()) {
                            endLeg(true);
                            endLegInformation(MainApp.first.getNev());
                            if (MainApp.first.getSet() == 3) {
                                endGame(MainApp.first.getNev());
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * A leg vége kezelése.
     *
     * @param winOrLose ki nyer.
     */
    public void endLeg(boolean winOrLose) {

        MainApp.first.setPoint(501);
        MainApp.second.setPoint(501);
        MainApp.first.setDarts(0);
        MainApp.second.setDarts(0);

        if (MainApp.first.getBestLeg() < MainApp.first.getCurrentLeg()) {
            MainApp.first.setBestLeg(MainApp.first.getCurrentLeg());
        }
        if (MainApp.second.getBestLeg() < MainApp.second.getCurrentLeg()) {
            MainApp.second.setBestLeg(MainApp.second.getCurrentLeg());
        }
        MainApp.first.setPreviousLeg(MainApp.first.getCurrentLeg());
        MainApp.second.setPreviousLeg(MainApp.second.getCurrentLeg());
        MainApp.first.setCurrentLeg(0);
        MainApp.second.setCurrentLeg(0);
        MainApp.first.setSum(0);
        MainApp.second.setSum(0);

        kezdes = !kezdes;

        if (winOrLose) {
            MainApp.first.setLeg(MainApp.first.getLeg() + 1);
            if (MainApp.first.getLeg() == 3) {
                if ((MainApp.first.getSet() + MainApp.second.getSet()) % 2 != 0) {
                    kezdes = true;
                } else {
                    kezdes = false;
                }
                MainApp.first.setSet(MainApp.first.getSet() + 1);
                MainApp.first.setLeg(0);
                MainApp.first.sets.clear();

                MainApp.second.setLeg(0);
                MainApp.second.sets.clear();
            }
        } else {
            MainApp.second.setLeg(MainApp.second.getLeg() + 1);
            if (MainApp.second.getLeg() == 3) {
                if ((MainApp.first.getSet() + MainApp.second.getSet()) % 2 != 0) {
                    kezdes = true;
                } else {
                    kezdes = false;
                }
                MainApp.second.setSet(MainApp.second.getSet() + 1);
                MainApp.second.setLeg(0);
                MainApp.second.sets.clear();

                MainApp.first.setLeg(0);
                MainApp.first.sets.clear();
            }
        }
        firstGamerPoint.setText(String.valueOf(MainApp.first.getPoint()));
        secondGamerPoint.setText(String.valueOf(MainApp.second.getPoint()));
    }

    /**
     * Leg vége információs ablak.
     *
     * @param nev ki nyerte a leget
     */
    public void endLegInformation(String nev) {
        LOG.info("{} nyerte a leget!", nev);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Leg Information");
        alert.setHeaderText(null);
        alert.setContentText(nev + " nyerte a leget! :)");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            alert.close();
        }
    }

    /**
     * Meccs vége információs ablak.
     *
     * @param nev ki nyerte a meccset
     */
    public void endGame(String nev) {
        LOG.info("{} nyerte a meccset!", nev);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Match Information");
        alert.setHeaderText(null);
        alert.setContentText(nev + " nyert! :) A játék véget ért!");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            alert.close();
        }
    }

    /**
     * Egyes gomb eseménykezelése.
     *
     * @param actionEvent esemény
     */
    public void pushOne(ActionEvent actionEvent) {
        textField.setText(textField.getText() + 1);
    }

    /**
     * Kettes gomb eseménykezelése.
     *
     * @param actionEvent esemény
     */
    public void pushTwo(ActionEvent actionEvent) {
        textField.setText(textField.getText() + 2);
    }

    /**
     * Hármas gomb eseménykezelése.
     *
     * @param actionEvent esemény
     */
    public void pushThree(ActionEvent actionEvent) {
        textField.setText(textField.getText() + 3);
    }

    /**
     * Négyes gomb eseménykezelése.
     *
     * @param actionEvent esemény
     */
    public void pushFour(ActionEvent actionEvent) {
        textField.setText(textField.getText() + 4);
    }

    /**
     * Ötös gomb eseménykezelése.
     *
     * @param actionEvent esemény
     */
    public void pushFive(ActionEvent actionEvent) {
        textField.setText(textField.getText() + 5);
    }

    /**
     * Hatos gomb eseménykezelése.
     *
     * @param actionEvent esemény
     */
    public void pushSix(ActionEvent actionEvent) {
        textField.setText(textField.getText() + 6);
    }

    /**
     * Hetes gomb eseménykezelése.
     *
     * @param actionEvent esemény
     */
    public void pushSeven(ActionEvent actionEvent) {
        textField.setText(textField.getText() + 7);
    }

    /**
     * Nyolcas gomb eseménykezelése.
     *
     * @param actionEvent esemény
     */
    public void pushEight(ActionEvent actionEvent) {
        textField.setText(textField.getText() + 8);
    }

    /**
     * Kilences gomb eseménykezelése.
     *
     * @param actionEvent esemény
     */
    public void pushNine(ActionEvent actionEvent) {
        textField.setText(textField.getText() + 9);
    }

    /**
     * Nullás gomb eseménykezelése.
     *
     * @param actionEvent esemény
     */
    public void pushNulla(ActionEvent actionEvent) {
        textField.setText(textField.getText() + 0);
    }

    /**
     * Torles gomb eseménykezelése.
     *
     * @param actionEvent esemény
     */
    public void pushTorles(ActionEvent actionEvent) {
        textField.clear();
    }

    /**
     * OK gomb eseménykezelése.
     *
     * @param actionEvent esemény
     */
    public void pushOk(ActionEvent actionEvent) {
        firstTextField(actionEvent);
    }

    /**
     * Statisztika gomb eseménykezelése.
     *
     * @param actionEvent esemény
     * @throws IOException kivétel
     */
    public void statisticView(ActionEvent actionEvent) throws IOException {
        new Statistic();
        MainStage.stage.close();

        /*Stage stage = (Stage) statistic.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Statistic.fxml"));

        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();*/
    }

    /**
     * JSON fájlba mentés filechooser segítségével.
     *
     * @param actionEvent esemény
     */
    public void save(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JSON file (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showSaveDialog(MainStage.stage);

        JSONObject obj = new JSONObject();
        JSONObject first = new JSONObject();
        JSONObject second = new JSONObject();
        JSONObject valtozok = new JSONObject();

        first.put("nev", MainApp.first.getNev());
        first.put("point", MainApp.first.getPoint());
        first.put("set", MainApp.first.getSet());
        first.put("leg", MainApp.first.getLeg());
        first.put("sum", MainApp.first.getSum());
        first.put("darts", MainApp.first.getDarts());
        first.put("pontok", MainApp.first.getPontok());
        first.put("legs", MainApp.first.getLegs());
        first.put("sets", MainApp.first.getSets());
        first.put("bestLeg", MainApp.first.getBestLeg());
        first.put("previousLeg", MainApp.first.getPreviousLeg());
        first.put("currentLeg", MainApp.first.getCurrentLeg());
        first.put("currentSet", MainApp.first.getCurrentSet());
        first.put("matchAverage", MainApp.first.getMatchAverage());
        first.put("matchAverages", MainApp.first.getMatchAverages());

        second.put("nev", MainApp.second.getNev());
        second.put("point", MainApp.second.getPoint());
        second.put("set", MainApp.second.getSet());
        second.put("leg", MainApp.second.getLeg());
        second.put("sum", MainApp.second.getSum());
        second.put("darts", MainApp.second.getDarts());
        second.put("pontok", MainApp.second.getPontok());
        second.put("legs", MainApp.second.getLegs());
        second.put("sets", MainApp.second.getSets());
        second.put("bestLeg", MainApp.second.getBestLeg());
        second.put("previousLeg", MainApp.second.getPreviousLeg());
        second.put("currentLeg", MainApp.second.getCurrentLeg());
        second.put("currentSet", MainApp.second.getCurrentSet());
        second.put("matchAverage", MainApp.second.getMatchAverage());
        second.put("matchAverages", MainApp.second.getMatchAverages());

        valtozok.put("counter", counter);
        valtozok.put("kezdes", kezdes);

        obj.put("firstGamer", first);
        obj.put("secondGamer", second);
        obj.put("valtozok", valtozok);


        if (file != null) {
            SaveFile(obj.toJSONString(), file);
        }
    }

    /**
     * JSON fájlból olvasás filechooser segítségével.
     *
     * @param actionEvent esemény
     * @throws ParseException JSON parser kivétel
     * @throws IOException kivétel
     */
    public void open(ActionEvent actionEvent) throws ParseException, IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("JSON file (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showOpenDialog(MainStage.stage);

        JSONParser parser = new JSONParser();

        Object obj = parser.parse(new FileReader(file));
        JSONObject jsonObject = (JSONObject) obj;
        JSONObject firstGamerJSON = (JSONObject) jsonObject.get("firstGamer");
        JSONObject secondGamerJSON = (JSONObject) jsonObject.get("secondGamer");
        JSONObject valtozokJSON = (JSONObject) jsonObject.get("valtozok");

        MainApp.first.setNev(firstGamerJSON.get("nev").toString());
        MainApp.first.setPoint(Integer.parseInt(firstGamerJSON.get("point").toString()));
        MainApp.first.setSet(Integer.parseInt(firstGamerJSON.get("set").toString()));
        MainApp.first.setLeg(Integer.parseInt(firstGamerJSON.get("leg").toString()));
        MainApp.first.setSum(Integer.parseInt(firstGamerJSON.get("sum").toString()));
        MainApp.first.setDarts(Integer.parseInt(firstGamerJSON.get("darts").toString()));
        MainApp.first.setPontok((ArrayList<Integer>) firstGamerJSON.get("pontok"));
        MainApp.first.setLegs((ArrayList<Integer>) firstGamerJSON.get("legs"));
        MainApp.first.setSets((ArrayList<Integer>) firstGamerJSON.get("sets"));
        MainApp.first.setBestLeg(Double.parseDouble(firstGamerJSON.get("bestLeg").toString()));
        MainApp.first.setPreviousLeg(Double.parseDouble(firstGamerJSON.get("previousLeg").toString()));
        MainApp.first.setCurrentLeg(Double.parseDouble(firstGamerJSON.get("currentLeg").toString()));
        MainApp.first.setCurrentSet(Double.parseDouble(firstGamerJSON.get("currentSet").toString()));
        MainApp.first.setCurrentSet(Double.parseDouble(firstGamerJSON.get("matchAverage").toString()));
        MainApp.first.setMatchAverages((ArrayList<Double>) firstGamerJSON.get("matchAverages"));

        MainApp.second.setNev(secondGamerJSON.get("nev").toString());
        MainApp.second.setPoint(Integer.parseInt(secondGamerJSON.get("point").toString()));
        MainApp.second.setSet(Integer.parseInt(secondGamerJSON.get("set").toString()));
        MainApp.second.setLeg(Integer.parseInt(secondGamerJSON.get("leg").toString()));
        MainApp.second.setSum(Integer.parseInt(secondGamerJSON.get("sum").toString()));
        MainApp.second.setDarts(Integer.parseInt(secondGamerJSON.get("darts").toString()));
        MainApp.second.setPontok((ArrayList<Integer>) secondGamerJSON.get("pontok"));
        MainApp.second.setLegs((ArrayList<Integer>) secondGamerJSON.get("legs"));
        MainApp.second.setSets((ArrayList<Integer>) secondGamerJSON.get("sets"));
        MainApp.second.setBestLeg(Double.parseDouble(secondGamerJSON.get("bestLeg").toString()));
        MainApp.second.setPreviousLeg(Double.parseDouble(secondGamerJSON.get("previousLeg").toString()));
        MainApp.second.setCurrentLeg(Double.parseDouble(secondGamerJSON.get("currentLeg").toString()));
        MainApp.second.setCurrentSet(Double.parseDouble(secondGamerJSON.get("currentSet").toString()));
        MainApp.second.setCurrentSet(Double.parseDouble(secondGamerJSON.get("matchAverage").toString()));
        MainApp.second.setMatchAverages((ArrayList<Double>) secondGamerJSON.get("matchAverages"));

        counter = Integer.parseInt(valtozokJSON.get("counter").toString());
        kezdes = Boolean.parseBoolean(valtozokJSON.get("kezdes").toString());

        update();
    }

    /**
     * Összes statisztika egy diagrammon kezelése.
     *
     * @param actionEvent esemény
     */
    public void menuOsszes(ActionEvent actionEvent) {
        new Chart(0);
    }

    /**
     * Pont diagramm meghívása.
     * @param actionEvent esemény
     */
    public void pontChart(ActionEvent actionEvent) {
        new Chart(1);
    }

    /**
     * Set diagramm meghívása.
     * @param actionEvent esemény
     */
    public void setsChart(ActionEvent actionEvent) {
        new Chart(2);
    }

    /**
     * Set diagramm meghívása.
     * @param actionEvent esemény
     */
    public void legsChart(ActionEvent actionEvent) {
        new Chart(3);
    }

    /**
     * Legjobb leg diagramm meghívása.
     * @param actionEvent esemény
     */
    public void bestLegChart(ActionEvent actionEvent) {
        new Chart(4);
    }

    /**
     * Előző leg diagramm meghívása.
     * @param actionEvent esemény
     */
    public void previousLegChart(ActionEvent actionEvent) {
        new Chart(5);
    }

    /**
     * Aktuális leg diagramm meghívása.
     * @param actionEvent esemény
     */
    public void scurrentLegChart(ActionEvent actionEvent) {
        new Chart(6);
    }

    /**
     * Aktuális set diagramm meghívása.
     * @param actionEvent esemény
     */
    public void scurrentSetChart(ActionEvent actionEvent) {
        new Chart(7);
    }

    /**
     * Meccs diagramm meghívása.
     * @param actionEvent esemény
     */
    public void matchAverageChart(ActionEvent actionEvent) {
        new Chart(8);
    }

    /**
     * Eldobott nyilak diagramm meghívása.
     * @param actionEvent esemény
     */
    public void dartsChart(ActionEvent actionEvent) {
        new Chart(9);
    }

    /**
     * Fájl létrehozása és kiírása.
     *
     * @param content tartalom
     * @param file elérési út
     */
    private void SaveFile(String content, File file) {
        try {
            FileWriter fileWriter = null;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            LOG.error("A mentés közben hiba történt", ex);
        }
    }

    /**
     * A felhasználói felület frissítése.
     */
    private void update() {
        firstGamerName.setText(MainApp.first.getNev());
        firstGamerPoint.setText(String.valueOf(MainApp.first.getPoint()));

        secondGamerName.setText(MainApp.second.getNev());
        secondGamerPoint.setText(String.valueOf(MainApp.second.getPoint()));

    }

    /**
     * Új játék kezdése.
     * @param actionEvent esemény
     */
    public void newGame(ActionEvent actionEvent) {
        MainApp.first = new Gamer("1.játékos");
        MainApp.second = new Gamer("2.jákétos");

        counter = 0;
        kezdes = true;
        update();
    }

    /**
     * Ablak bezárása.
     * @param actionEvent esemény
     */
    public void exit(ActionEvent actionEvent) {
        MainStage.stage.close();
    }
}