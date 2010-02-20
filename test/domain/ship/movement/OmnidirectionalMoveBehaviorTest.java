/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.ship.movement;

import domain.universe.Position;
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
public class OmnidirectionalMoveBehaviorTest {

    private OmnidirectionalMoveBehavior instance;

    public OmnidirectionalMoveBehaviorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        instance = new OmnidirectionalMoveBehavior();
    }

    @After
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testCanMoveTo() {
        assertTrue(instance.canMoveTo(null));
    }

    @Test
    public void testGetCurrentPosition() {
        Position p = new Position(1, 2);
        instance.setCurrentPosition(p);
        assertEquals(p, instance.getCurrentPosition());
    }

    @Test
    public void testSetCurrentPosition() {
        Position p = new Position(1, 2);
        instance.setCurrentPosition(p);
        assertEquals(p, instance.getCurrentPosition());
    }

    @Test
    public void testMoveToInAxisX1() {
        Position origin = new Position(0, 0);
        Position destiny = new Position(10, 0);
        instance.setCurrentPosition(origin);
        instance.setSpeed(5);

        instance.moveTo(destiny, 1);
        assertEquals(new Position(5, 0), instance.getCurrentPosition());

        instance.moveTo(destiny, 0.5);
        assertEquals(new Position(7.5, 0), instance.getCurrentPosition());
    }

    @Test
    public void testMoveToInAxisXChangingSpeed() {
        Position origin = new Position(0, 0);
        Position destiny = new Position(10, 0);
        instance.setCurrentPosition(origin);
        instance.setSpeed(2.5);

        instance.moveTo(destiny, 1);
        assertEquals(new Position(2.5, 0), instance.getCurrentPosition());

        instance.setSpeed(5);
        instance.moveTo(destiny, 1);
        assertEquals(new Position(7.5, 0), instance.getCurrentPosition());

        instance.setSpeed(0.1);
        instance.moveTo(destiny, 1);
        assertEquals(new Position(7.6, 0), instance.getCurrentPosition());
    }

    @Test
    public void testMoveToInAxisY() {
        Position origin = new Position(0, 0);
        Position destiny = new Position(0, 10);
        instance.setCurrentPosition(origin);
        instance.setSpeed(5);

        instance.moveTo(destiny, 1);
        assertEquals(new Position(0, 5), instance.getCurrentPosition());

        instance.moveTo(destiny, 0.5);
        assertEquals(new Position(0, 7.5), instance.getCurrentPosition());
    }

    @Test
    public void testMoveToBothAxis() {
        Position origin = new Position(0, 0);
        Position destiny = new Position(30, 40);
        instance.setCurrentPosition(origin);
        instance.setSpeed(5);

        instance.moveTo(destiny, 1);
        assertEquals(new Position(3,4), instance.getCurrentPosition());

        instance.moveTo(new Position(0, 0), 0.5);
        assertEquals(new Position(1.5, 2), instance.getCurrentPosition());

        instance.moveTo(new Position(0, 0), 0.5);
        assertEquals(new Position(0, 0), instance.getCurrentPosition());
    }

    @Test
    public void testMoveToLimit() {
        Position origin = new Position(0, 0);
        Position destiny = new Position(30, 40);
        instance.setCurrentPosition(origin);
        instance.setSpeed(1);

        instance.moveTo(destiny, 60);
        assertEquals(destiny, instance.getCurrentPosition());
    }

    @Test
    public void testMoveToLimit2() {
        Position origin = new Position(0, 0);
        Position destiny = new Position(-30, -40);
        instance.setCurrentPosition(origin);
        instance.setSpeed(1);

        instance.moveTo(destiny, 60);
        assertEquals(destiny, instance.getCurrentPosition());
    }

    @Test
    public void testMoveToLimit3() {
        Position origin = new Position(0, 0);
        Position destiny = new Position(-30, 40);
        instance.setCurrentPosition(origin);
        instance.setSpeed(1);

        instance.moveTo(destiny, 60);
        assertEquals(destiny, instance.getCurrentPosition());

    }

    @Test
    public void testMoveToLimit4() {
        Position origin = new Position(0, 0);
        Position destiny = new Position(30, -40);
        instance.setCurrentPosition(origin);
        instance.setSpeed(1);

        instance.moveTo(destiny, 60);
        assertEquals(destiny, instance.getCurrentPosition());
    }

    @Test
    public void testInRange_Position_double() {
        Position p1 = new Position(0, 0);
        instance.setCurrentPosition(p1);
        Position p2 = new Position(0, 5);
        assertTrue(instance.inRange(p2, 5));
        assertFalse(instance.inRange(p2, 4.9));
    }
}
