/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ship.infra;

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
public class HelperTest {
    private HelperTest instance;

    public HelperTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        instance = new HelperTest();
    }

    @After
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testBetween() {
        assertEquals(Helper.between(20, 10, 100), 20, 0.01);
        assertEquals(Helper.between(50, 10, 100), 50, 0.01);
        assertEquals(Helper.between(5, 10, 100) , 10, 0.01);
        assertEquals(Helper.between(400, 10, 100), 100, 0.01);
    }

}