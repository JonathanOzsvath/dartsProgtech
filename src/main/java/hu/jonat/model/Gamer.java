package hu.jonat.model;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Optional;

/**
 * A Gamer osztály a játékos adatainak tárolására szolgál.
 */
public class Gamer {

    /**
     * Játékos neve.
     */
    private String nev;
    /**
     * Játékos aktuális pontja.
     */
    private int point;
    /**
     * A játékos nyert szetjei.
     */
    private int set;
    /**
     * A játékos nyert legjei.
     */
    private int leg;
    /**
     * A játékos legen belül dobott pontjainak az összege.
     */
    private int sum;
    /**
     * A játékos legen belül eldobott nyilainak a száma.
     */
    private int darts;

    /**
     * A játékos meccsen dobott pontjainak listája.
     */
    public ArrayList<Integer> pontok;
    /**
     * A játékos legen belül dobott pontjainak listája.
     */
    public ArrayList<Integer> legs;
    /**
     * A játékos szetten belül dobott pontjainka listája.
     */
    public ArrayList<Integer> sets;
    /**
     * A játékos legjobb leg átlaga.
     */
    private double bestLeg;
    /**
     * A játékos előző leg átlaga.
     */
    private double previousLeg;
    /**
     * A játékos aktuális leg átlaga.
     */
    private double currentLeg;
    /**
     * A játékos aktuális szett átlaga.
     */
    private double currentSet;
    /**
     * A játékos meccs átlaga.
     */
    private double matchAverage;
    /**
     * Logger instance.
     */
    private ArrayList<Double> matchAverages;
    static Logger LOG = LoggerFactory.getLogger(Gamer.class);


    /**
     * A konstruktor létrhozza a  {@code Gamer} objektumot.
     *
     * @param nev a játékos neve
     */
    public Gamer(String nev) {
        this.nev = nev;
        point = 501;
        leg = 0;
        set = 0;
        darts = 0;
        pontok = new ArrayList<Integer>();
        sets = new ArrayList<Integer>();
        bestLeg = 0;
        previousLeg = 0;
        currentLeg = 0;
        currentSet = 0;
        matchAverage = 0;
        matchAverages = new ArrayList<>();
    }

    /**
     * Visszaadja a játékos nevét.
     *
     * @return a játékos neve
     */
    public String getNev() {
        return nev;
    }

    /**
     * Beállítja a játékos nevét.
     *
     * @param nev a játékos neve
     */
    public void setNev(String nev) {
        this.nev = nev;
    }

    /**
     * Visszaadja a játékos pontszámát.
     *
     * @return a játékos pontszáma
     */
    public int getPoint() {
        return point;
    }

    /**
     * Beállítja a játékos pontszámát.
     *
     * @param point a játékos pontszáma
     */
    public void setPoint(int point) {
        this.point = point;
    }

    /**
     * Visszaadja a játékos aktuális nyert szettjeinek a számát.
     *
     * @return a játékos szettje
     */
    public int getSet() {
        return set;
    }

    /**
     * Beállítja a játékos aktuális nyert szettjeinek a számát.
     *
     * @param set a játékos szettje
     */
    public void setSet(int set) {
        this.set = set;
    }

    /**
     * Visszaadja a játékos nyert legjeinek a számát.
     *
     * @return a játékos legje
     */
    public int getLeg() {
        return leg;
    }

    /**
     * Beállítja a játékos aktuális nyert legjeinek a számát.
     *
     * @param leg a játékos legje
     */
    public void setLeg(int leg) {
        this.leg = leg;
    }

    /**
     * Visszaadja a játékos legen belül dobott pontszámainak az összegét.
     *
     * @return a játékos legen belül dobott pontszámainak összege
     */
    public int getSum() {
        return sum;
    }

    /**
     * Beállítja a játékos legen belül dobott pontszámainak az összegét.
     *
     * @param sum játékos legen belül dobott pontszámainak az összege
     */
    public void setSum(int sum) {
        this.sum = sum;
    }

    /**
     * Visszaadja a játékos legen belül dobott nyilainak a számát.
     *
     * @return a játékos legen belül dobott nyilak száma
     */
    public int getDarts() {
        return darts;
    }

    /**
     * Beállítja a játékos legen belül dobott nyilainak a számát.
     *
     * @param darts a játékos legen belül dobott nyilak száma
     */
    public void setDarts(int darts) {
        this.darts = darts;
    }

    public ArrayList<Integer> getPontok() {
        return pontok;
    }

    public void setPontok(ArrayList<Integer> pontok) {
        this.pontok = pontok;
    }

    public ArrayList<Integer> getLegs() {
        return legs;
    }

    public void setLegs(ArrayList<Integer> legs) {
        this.legs = legs;
    }

    public ArrayList<Integer> getSets() {
        return sets;
    }

    public void setSets(ArrayList<Integer> sets) {
        this.sets = sets;
    }

    /**
     * Visszaadja a játékos legjobb átlagú leg átlagát.
     *
     * @return a játékos legjobb átlagú leg átlaga
     */
    public double getBestLeg() {
        return bestLeg;
    }

    /**
     * Beállítja a játékos legjobb átlagú leg átlagát.
     *
     * @param bestLeg a játékos legjobb átlagú leg átlaga
     */
    public void setBestLeg(double bestLeg) {
        this.bestLeg = bestLeg;
    }

    /**
     * Visszaadja a játékos előző leg átlagát.
     *
     * @return a játékos előző leg átlaga
     */
    public double getPreviousLeg() {
        return previousLeg;
    }

    /**
     * Beállítja a játékos előző leg átlagát.
     *
     * @param previousLeg a játékos előző leg átlaga
     */
    public void setPreviousLeg(double previousLeg) {
        this.previousLeg = previousLeg;
    }

    /**
     * Visszaadja a játékos aktuális leg átlagát.
     *
     * @return a játékos aktuális leg átlaga
     */
    public double getCurrentLeg() {
        return currentLeg;
    }

    /**
     * Visszaadja a játékos aktuális leg számolt átlagát.
     *
     * @return a játékos aktuális leg számolt átlaga
     */
    public double getCurrentLeg2() {
        return sum/(darts/3.0);
    }


    /**
     * Beállítja a játékos aktuális leg átlagát.
     *
     * @param currentLeg a játékos aktuális leg átlaga
     */
    public void setCurrentLeg(double currentLeg) {
        this.currentLeg = currentLeg;
    }

    /**
     * Visszaadja a játékos aktuális szett átlagát.
     *
     * @return a játékos aktuális szett átlaga
     */
    public double getCurrentSet() {
        return currentSet;
    }

    /**
     * Visszaadja a játékos aktuális szett számolt átlagát.
     *
     * @return a játékos aktuális szett számolt átlaga
     */
    public double getCurrentSet2() {
        int tmp = 0;
        for (Integer set : sets){
            tmp += set;
        }
        return tmp/(double)sets.size();
    }

    /**
     * Beállítja a játékos aktuális szett átlagát.
     *
     * @param currentSet a játékos aktuális szett átlaga
     */
    public void setCurrentSet(double currentSet) {
        this.currentSet = currentSet;
    }

    /**
     * Visszaadja a játékos meccs átlagát.
     *
     * @return a játékos meccs átlaga
     */
    public double getMatchAverage() {
        return matchAverage;
    }

    /**
     * Visszaadja a játékos meccs számolt átlagát.
     *
     * @return a játékos meccs számolt átlaga
     */
    public double getMatchAverage2() {
        double tmp = 0;
        for (Integer match : pontok){
            tmp += match;
            //System.out.println(match);
        }
        return tmp/(double)pontok.size();
    }

    /**
     * Beállítja a játékos meccs átlagát.
     *
     * @param matchAverage a játékos meccs átlaga
     */
    public void setMatchAverage(double matchAverage) {
        this.matchAverage = matchAverage;
    }


    public ArrayList<Double> getMatchAverages() {
        return matchAverages;
    }

    public void setMatchAverages(ArrayList<Double> matchAverages) {
        this.matchAverages = matchAverages;
    }

    /**
     * Beállítja a pontértéket amikor 3 nyilas dobás történt.
     *
     * @param dobottPont a dobott pontérték
     */
    public void throwing3(int dobottPont) {
        if (dobottPont <= 180) {
            if ((point - dobottPont) >= 0) {
                point -= dobottPont;
                sum += dobottPont;
                darts += 3;
                currentLeg = getCurrentLeg2();
                sets.add(dobottPont);
                currentSet = getCurrentSet2();
                pontok.add(dobottPont);
                matchAverage = getMatchAverage2();
                matchAverages.add(matchAverage);
            }
        }
        else {
            warning(3,dobottPont);
        }
    }

    /**
     * Megadja, hogy a játékos kiszált-e.
     * Igaz értékkel tér vissza ha pontosan kiszált, egyébként hamissal.
     *
     * @return <code>true</code> ha a játékos pontosan kiszállt, <code>false</code> egyébként
     */
    public boolean checkout() {
        if (point == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Figyelmeztető dialógus ablak.
     * Figyelmeztet ha nem lehet a kapott értéket kidobni.
     *
     * @param n dobott nyilak száma
     * @param dobottPont dobott pontok értéke
     */
    public void warning(int n,int dobottPont){
        LOG.warn("Nem lehet {} nyilbol {} pontot dobni", n, dobottPont);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Nem lehet " + n + " nyilbol " + dobottPont + " pontot dobni!");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            alert.close();
        }
    }
}