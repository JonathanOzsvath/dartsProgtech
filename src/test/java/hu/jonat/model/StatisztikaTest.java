package hu.jonat.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jonat on 2015.05.26..
 */
public class StatisztikaTest {

    Statisztika statisztika;

    @Before
    public void setUp() throws Exception {
        statisztika = new Statisztika((Integer) 3, "pont", 234, 145);
    }

    @Test
    public void testGetId() throws Exception {
        assertEquals((Integer) 3, (Integer) statisztika.getId());
    }

    @Test
    public void testIdProperty() throws Exception {

    }

    @Test
    public void testGetStatisztika() throws Exception {
        assertEquals("pont", statisztika.getStatisztika());
    }

    @Test
    public void testStatisztikaProperty() throws Exception {

    }

    @Test
    public void testGetFirstGamer() throws Exception {
        assertEquals(234, statisztika.getFirstGamer(), 0.0);
    }

    @Test
    public void testFirstGamerProperty() throws Exception {

    }

    @Test
    public void testGetSecondGamer() throws Exception {
        assertEquals(145, statisztika.getSecondGamer(), 0.0);
    }

    @Test
    public void testSecondGamerProperty() throws Exception {

    }
}