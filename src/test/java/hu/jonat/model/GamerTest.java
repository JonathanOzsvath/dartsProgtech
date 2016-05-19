package hu.jonat.model;

import hu.jonat.model.Gamer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit teszt a {@link Gamer} osztályhoz.
 */
public class GamerTest {

    Gamer gamer;

    @Before
    public void setUp() throws Exception {
        gamer = new Gamer("Teszt");
        gamer.setNev("jonat");

        gamer.throwing3(40);
        gamer.throwing3(67);
        gamer.throwing3(131);
    }

    @Test
    public void testGetNev() {
        assertEquals("jonat", gamer.getNev());
    }

    @Test
    public void testSetNev() {
        gamer.setNev("jonat2");
        assertEquals("jonat2", gamer.getNev());
    }

    @Test
    public void testGetPoint() {
        assertEquals((Integer) 263, (Integer) gamer.getPoint());
    }

    @Test
    public void testSetPoint() {
        gamer.setPoint(123);
        assertEquals((Integer) 123, (Integer) gamer.getPoint());
    }

    @Test
    public void testGetSet() {
        assertEquals((Integer) 0, (Integer) gamer.getSet());
    }

    @Test
    public void testSetSet() {
        gamer.setSet(2);
        assertEquals((Integer) 2, (Integer) gamer.getSet());
    }

    @Test
    public void testGetLeg() {
        assertEquals((Integer) 0, (Integer) gamer.getLeg());
    }

    @Test
    public void testSetLeg() {
        gamer.setLeg(1);
        assertEquals((Integer) 1, (Integer) gamer.getLeg());
    }

    @Test
    public void testGetSum() {
        assertEquals((Integer) 238, (Integer) gamer.getSum());
    }

    @Test
    public void testSetSum() {
        gamer.setSum(190);
        assertEquals((Integer) 190, (Integer) gamer.getSum());
    }

    @Test
    public void testGetDarts() {
        assertEquals((Integer) 9, (Integer) gamer.getDarts());
    }

    @Test
    public void testSetDarts() {
        gamer.setDarts(11);
        assertEquals((Integer) 11, (Integer) gamer.getDarts());
    }

    @Test
    public void testGetBestLeg() {
        assertEquals(0, gamer.getBestLeg(), 0.0);
    }

    @Test
    public void testSetBestLeg() {
        gamer.setBestLeg(2);
        assertEquals(2, gamer.getBestLeg(), 0.0);
    }

    @Test
    public void testGetPreviousLeg() {
        assertEquals(0, gamer.getPreviousLeg(), 0.0);
    }

    @Test
    public void testSetPreviousLeg() {
        gamer.setPreviousLeg(156.8);
        assertEquals(156.8, gamer.getPreviousLeg(), 0.0);
    }

    @Test
    public void testGetCurrentLeg() {
        assertEquals(79.3, gamer.getCurrentLeg(), 0.1);
    }

    @Test
    public void testSetCurrentLeg() {
        gamer.setCurrentLeg(96.8);
        assertEquals(96.8, gamer.getCurrentLeg(), 0.1);
    }

    @Test
    public void testGetCurrentLeg2() throws Exception {
        double expected = 0;

        expected = gamer.getSum() / (gamer.getDarts() / 3.0);

        double actual = gamer.getCurrentSet2();
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void testGetCurrentSet() {
        assertEquals(79.3, gamer.getCurrentSet(), 0.1);
    }

    @Test
    public void testSetCurrentSet() {
        gamer.setCurrentSet(96.8);
        assertEquals(96.8, gamer.getCurrentSet(), 0.1);
    }

    @Test
    public void testGetCurrentSet2() throws Exception {
        double expected = 0;
        for (Integer set : gamer.sets) {
            expected += set;
        }
        expected = expected / (double) gamer.sets.size();

        double actual = gamer.getCurrentSet2();
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void testGetMatchAverage() {
        assertEquals(79.3, gamer.getMatchAverage(), 0.1);
    }

    @Test
    public void testSetMatchAverage() {
        gamer.setMatchAverage(96.8);
        assertEquals(96.8, gamer.getMatchAverage(), 0.1);
    }

    @Test
    public void testGetMatchAverage2() throws Exception {
        double expected = 0;
        for (Integer match : gamer.pontok) {
            expected += match;
        }
        expected = expected / gamer.pontok.size();

        double actual = (double) gamer.getMatchAverage2();
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void testThrowing3() throws Exception {
        int dobottPont = 120;
        int point = gamer.getPoint();
        int sum = gamer.getSum();
        int darts = gamer.getDarts();
        int sets = gamer.sets.size();
        double currentSet = 0;
        int pontok = gamer.pontok.size();
        double matchAverage = 0;

        gamer.throwing3(dobottPont);
        double currentLeg = gamer.getSum() / (gamer.getDarts() / 3.0);

        if (dobottPont <= 180) {
            if ((gamer.getPoint() - dobottPont) >= 0) {
                assertEquals(point - dobottPont, gamer.getPoint());
                assertEquals(sum + dobottPont, gamer.getSum());
                assertEquals(darts + 3, gamer.getDarts());
                assertEquals(currentLeg, gamer.getCurrentLeg(), 0.0);
                assertEquals(sets + 1, gamer.sets.size());
                for (Integer set : gamer.sets) {
                    currentSet += set;
                }
                currentSet /= gamer.sets.size();
                assertEquals(currentSet, gamer.getCurrentSet(), 0.0);
                assertEquals(pontok + 1, gamer.pontok.size());
                for (Integer match : gamer.pontok) {
                    matchAverage += match;
                }
                matchAverage /= (double) gamer.pontok.size();
                assertEquals(matchAverage, gamer.getMatchAverage(), 0.0);
            }
        } else {

        }
    }

    @Test
    public void testCheckout() throws Exception {
        boolean ans = true;
        if (gamer.getPoint() == 0) {
            assertEquals(ans, gamer.checkout());
        } else {
            assertEquals(!ans, gamer.checkout());
        }
    }
}