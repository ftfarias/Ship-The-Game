/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package domain.universe;

import domain.ship.movement.OmnidirectionalMoveBehavior;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
/**
 *
 * @author Felipe
 */
public class UniverseTest {
    private Universe universe;
    Position mockPosition;

    public UniverseTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        universe = new Universe();
    }

    @After
    public void tearDown() {
        universe = null;
    }

    private Positionable getPositionableMock() {
        Positionable mockMovable = new OmnidirectionalMoveBehavior();
        mockPosition = new Position(1,2);
        mockMovable.setCurrentPosition(mockPosition);
        return mockMovable;
    }

    @Test
    public void testAddMovableObject() {
        Positionable obj = getPositionableMock();
        universe.addObject(obj);
        Set<Positionable> result = universe.getMovablesInPosition(mockPosition,Range.VISUAL);
        assertTrue(result.contains(obj));
        assertEquals(1, result.size());
    }

    @Test
    public void testGetMovablesInPosition() {
    }

}