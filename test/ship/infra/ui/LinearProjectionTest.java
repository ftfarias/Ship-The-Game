/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ship.infra.ui;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Felipe T. Farias <ftfarias@gmail.com>
 */
public class LinearProjectionTest {
    private LinearProjection instance;

    public LinearProjectionTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        instance = new LinearProjection(3.0,1.0);
    }

    @After
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testConvert() {
        assertEquals(4, instance.convert(1));
        assertEquals(7, instance.convert(2));
        assertEquals(10, instance.convert(3));
        assertEquals(13, instance.convert(4));
    }

    @Test
    public void testConvertDouble() {
        assertEquals(4.0, instance.convertDouble(1),0.01);
        assertEquals(7.0, instance.convertDouble(2),0.01);
        assertEquals(10.0, instance.convertDouble(3),0.01);
        assertEquals(13.0, instance.convertDouble(4),0.01);
    }

}