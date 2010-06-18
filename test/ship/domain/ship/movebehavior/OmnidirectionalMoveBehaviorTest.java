/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ship.domain.ship.movebehavior;

import ship.domain.universe.Position;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ship.domain.ship.powergrid.PowerGrid;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import ship.infra.observer.Observer;

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
//        PowerGenerator mockPowerGenerator = mock(PowerGenerator.class);
//        when(mockPowerGenerator.getEnergy(anyDouble())).thenReturn(100000.0);

//        PowerGrid powerGrid = mock(PowerGrid.class);
//        when(powerGrid.requestEnergy(anyDouble())).thenReturn(Double.MAX_VALUE);

        PowerGrid powerGrid = new PowerGrid() {

            @Override
            public double requestEnergy(double amountRequested) {
                return amountRequested;
            }

            @Override
            public double getPowerGridBalance() {
                return 0;
            }

            @Override
            public void timeElapsed(long timeElapsed) {
            }

            @Override
            public void registerObserver(Observer observer) {
            }

            @Override
            public void removeAllObservers() {
            }

            @Override
            public void removeObserver(Observer observer) {
            }

            @Override
            public void beforeTimeElapsed() {
            }

            @Override
            public void afterTimeElapsed() {
            }

            @Override
            public String getDescription() {
                return "";
            }

            @Override
            public long getWeight() {
                return 0l;
            }

            @Override
            public long getSize() {
                return 0l;
            }

            @Override
            public long getValue() {
                return 0l;
            }
        };

        instance.setPowerGrid(powerGrid);
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
    public void testIsMoving() {
        instance.setCurrentPosition(Position.ORIGIN);
        assertFalse(instance.isMoving());
        instance.moveTo(new Position(10, 10));
        assertTrue(instance.isMoving());
    }

    @Test
    public void testSetCurrentPosition() {
        Position p = new Position(1, 2);
        instance.setCurrentPosition(p);
        assertEquals(p, instance.getCurrentPosition());
    }

    @Test
    public void testMoveToInAxisX1() {
        System.out.println("testMoveToInAxisX1");
        Position origin = Position.ORIGIN;
        Position destiny = new Position(10, 0);
        instance.setCurrentPosition(origin);
        instance.setSpeed(2.5);

        instance.moveTo(destiny);
        instance.timeElapsed(2);
        assertEquals(new Position(5, 0), instance.getCurrentPosition());

        instance.moveTo(destiny);
        instance.timeElapsed(1);
        assertEquals(new Position(7.5, 0), instance.getCurrentPosition());
    }

    @Test
    public void testMoveToInAxisXChangingSpeed() {
        Position origin = Position.ORIGIN;
        Position destiny = new Position(10, 0);
        instance.setCurrentPosition(origin);
        instance.setSpeed(2.5);

        instance.moveTo(destiny);
        instance.timeElapsed(1);
        assertEquals(new Position(2.5, 0), instance.getCurrentPosition());

        instance.setSpeed(5);
        instance.moveTo(destiny);
        instance.timeElapsed(1);
        assertEquals(new Position(7.5, 0), instance.getCurrentPosition());

        instance.setSpeed(0.1);
        instance.moveTo(destiny);
        instance.timeElapsed(1);
        assertEquals(new Position(7.6, 0), instance.getCurrentPosition());
    }

    @Test
    public void testMoveToInAxisY() {
        Position origin = Position.ORIGIN;
        Position destiny = new Position(0, 10);
        instance.setCurrentPosition(origin);
        instance.setSpeed(2.5);

        instance.moveTo(destiny);
        instance.timeElapsed(2);
        assertEquals(new Position(0, 5), instance.getCurrentPosition());

        instance.moveTo(destiny);
        instance.timeElapsed(1);
        assertEquals(new Position(0, 7.5), instance.getCurrentPosition());
    }

    @Test
    public void testMoveToBothAxis() {
        Position origin = Position.ORIGIN;
        Position destiny = new Position(30, 40);
        instance.setCurrentPosition(origin);
        instance.setSpeed(2.5);

        instance.moveTo(destiny);
        instance.timeElapsed(2);
        assertEquals(new Position(3, 4), instance.getCurrentPosition());

        instance.moveTo(new Position(0, 0));
        instance.timeElapsed(1);
        assertEquals(new Position(1.5, 2), instance.getCurrentPosition());

        instance.moveTo(new Position(0, 0));
        instance.timeElapsed(1);
        assertEquals(new Position(0, 0), instance.getCurrentPosition());
    }

    @Test
    public void testMoveToLimit() {
        Position origin = new Position(0, 0);
        Position destiny = new Position(30, 40);
        instance.setCurrentPosition(origin);
        instance.setSpeed(1);

        instance.moveTo(destiny);
        instance.timeElapsed(60);
        assertEquals(destiny, instance.getCurrentPosition());
    }

    @Test
    public void testMoveToLimit2() {
        Position origin = new Position(0, 0);
        Position destiny = new Position(-30, -40);
        instance.setCurrentPosition(origin);
        instance.setSpeed(1);

        instance.moveTo(destiny);
        instance.timeElapsed(60);
        assertEquals(destiny, instance.getCurrentPosition());
    }

    @Test
    public void testMoveToLimit3() {
        Position origin = new Position(0, 0);
        Position destiny = new Position(-30, 40);
        instance.setCurrentPosition(origin);
        instance.setSpeed(1);

        instance.moveTo(destiny);
        instance.timeElapsed(60);
        assertEquals(destiny, instance.getCurrentPosition());

    }

    @Test
    public void testMoveToLimit4() {
        Position origin = new Position(0, 0);
        Position destiny = new Position(30, -40);
        instance.setCurrentPosition(origin);
        instance.setSpeed(1);

        instance.moveTo(destiny);
        instance.timeElapsed(60);
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

