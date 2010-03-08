/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ship.ui.map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ship.domain.ship.Ship;
import ship.domain.universe.Position;
import ship.domain.universe.Universe;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
/**
 *
 * @author Felipe T. Farias <ftfarias@gmail.com>
 */
public class MapPanelTest {
    private MapPanel instance;

    public MapPanelTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        Universe universe = mock(Universe.class);
        Ship ship = mock(Ship.class);
        when(ship.getCurrentPosition()).thenReturn(new Position(10, 15));
        instance = new MapPanel(ship);
    }

    @After
    public void tearDown() {
        instance = null;
    }


    @Test
    public void testCalcLowLimit() {
        assertEquals(-16, instance.calcLowLimit(-15));
        assertEquals(-1, instance.calcLowLimit(0));
        assertEquals(4, instance.calcLowLimit(5));
        assertEquals(39, instance.calcLowLimit(40));
      }

    @Test
    public void testCalcHighLimit() {
        assertEquals(-14, instance.calcHighLimit(-15));
        assertEquals(1, instance.calcHighLimit(0));
        assertEquals(6, instance.calcHighLimit(5));
        assertEquals(41, instance.calcHighLimit(40));
    }


}