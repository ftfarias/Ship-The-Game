/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.universe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author felipefarias
 */
public class PositionTest {

    public PositionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetX() {
        Position p1 = new Position(23.5, 0);
        assertEquals(23.5, p1.getX(), Position.TOUCH_RANGE);
    }

    @Test
    public void testGetY() {
        Position p1 = new Position(0, 13.9);
        assertEquals(13.9, p1.getY(), Position.TOUCH_RANGE);
    }

    @Test
    public void testDistance() {
        Position p1 = new Position(0, 0);
        Position p2 = new Position(4, 3);
        assertEquals(5.0, p1.distance(p2), Position.TOUCH_RANGE);
    }

    @Test
    public void testInRange1() {
        Position p1 = new Position(0, 0);
        Position p2 = new Position(0, 5);
        assertTrue(p1.inRange(p2, 5));
        assertFalse(p1.inRange(p2, 4.9));
    }

    @Test
    public void testInRange2() {
        Position p1 = new Position(0, 0);
        Position p2 = new Position(1, 0);
        assertTrue(p1.inRange(p2, 1));
        assertFalse(p1.inRange(p2, 0.9));
    }

    @Test
    public void testEquals() {
    }

    @Test
    public void testHashCode() {
    }
}
