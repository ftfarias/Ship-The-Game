/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ship.infra.ui;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ship.domain.universe.Position;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Felipe T. Farias <ftfarias@gmail.com>
 */
public class CoordinatesProjectionTest {

    private CoordinatesProjection instance;

    public CoordinatesProjectionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        instance = new CoordinatesProjection(1, 2, 3, 4);
    }

    @After
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testConvertX() {
        assertEquals(3, instance.convertX(1));
        assertEquals(4, instance.convertX(2));
        assertEquals(5, instance.convertX(3));
        assertEquals(6, instance.convertX(4));
    }

    @Test
    public void testConvertY() {
        assertEquals(7, instance.convertY(1));
        assertEquals(10, instance.convertY(2));
        assertEquals(13, instance.convertY(3));
        assertEquals(16, instance.convertY(4));
    }

    @Test
    public void testConstructor2forScalar() {
        Graphics2D g2d = mock(Graphics2D.class);
        Rectangle r = new Rectangle(100, 200);
        when(g2d.getClipBounds()).thenReturn(r);
        Position p = new Position(0, 0);

        instance = new CoordinatesProjection(p, 5, g2d);

        assertEquals(20, instance.getXScalar(),0.01);
        assertEquals(40, instance.getYScalar(),0.01);
    }

    @Test
    public void testConstructor2forLinear() {
        Graphics2D g2d = mock(Graphics2D.class);
        Rectangle r = new Rectangle(10, 20);
        when(g2d.getClipBounds()).thenReturn(r);
        Position p = new Position(5, 5);

        instance = new CoordinatesProjection(p, 10, g2d);

        assertEquals(5, instance.getXLinear(),0.01);
        assertEquals(10, instance.getYLinear(),0.01);

        assertEquals(5, instance.convertX(0),0.01);
        assertEquals(10, instance.convertY(0),0.01);

    }

    @Test
    public void testConstructor2forBoth() {
        Graphics2D g2d = mock(Graphics2D.class);
        Rectangle r = new Rectangle(100, 200);
        when(g2d.getClipBounds()).thenReturn(r);
        Position p = new Position(5, 10);

        instance = new CoordinatesProjection(p, 10, g2d);


        assertEquals(50, instance.convertX(0),0.01);
        assertEquals(200, instance.convertY(0),0.01);
    }
}
