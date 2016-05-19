package hu.jonat.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * A Statisztika osztály a statisztikához szükséges elemeket tartalmazza.
 */
public class Statisztika {

    /**
     * A lista id-ja.
     */
    private final SimpleIntegerProperty id;
    /**
     * A lista statisztikai meghatározása.
     */
    private final SimpleStringProperty statisztika;
    /**
     * A lista első játékosának adatai.
     */
    private final SimpleDoubleProperty firstGamer;
    /**
     * A lista második játékosának adatai.
     */
    private final SimpleDoubleProperty secondGamer;

    /**
     * A konstruktor létrehozza a {@code Statisztika} objektumot.
     *
     * @param id a lista id-ja
     * @param statisztika a lista statisztikai meghatározása
     * @param firstGamer a lista első játékosának adata
     * @param secondGamer a llista második játékosának adata
     */
    public Statisztika(int id, String statisztika, double firstGamer, double secondGamer) {
        this.id = new SimpleIntegerProperty(id);
        this.statisztika = new SimpleStringProperty(statisztika);
        this.firstGamer = new SimpleDoubleProperty(firstGamer);
        this.secondGamer = new SimpleDoubleProperty(secondGamer);
    }

    /**
     *Visszaadja a lista id-ját.
     *
     * @return lista id
     */
    public int getId() {
        return id.get();
    }

    /**
     * Visszaadja a lista id-ját.
     *
     * @return lista id
     */
    public SimpleIntegerProperty idProperty() {
        return id;
    }

    /**
     *Visszaadja a lista statisztikai meghatározását.
     *
     * @return a lista statisztikai meghatározása
     */
    public String getStatisztika() {
        return statisztika.get();
    }

    /**
     *Visszaadja a lista statisztikai meghatározását.
     *
     * @return a lista statisztikai meghatározása
     */
    public SimpleStringProperty statisztikaProperty() {
        return statisztika;
    }

    /**
     *Visszaadja a lista első játékosának adatát.
     *
     * @return a lista első játékosának adata
     */
    public double getFirstGamer() {
        return firstGamer.get();
    }

    /**
     *Visszaadja a lista első játékosának adatát.
     *
     * @return a lista első játékosának adata
     */
    public SimpleDoubleProperty firstGamerProperty() {
        return firstGamer;
    }

    /**
     *Visszaadja a lista második játékosának adatát.
     *
     * @return a lista második játékosának adata
     */
    public double getSecondGamer() {
        return secondGamer.get();
    }

    /**
     *Visszaadja a lista második játékosának adatát.
     *
     * @return a lista második játékosának adata
     */
    public SimpleDoubleProperty secondGamerProperty() {
        return secondGamer;
    }
}
