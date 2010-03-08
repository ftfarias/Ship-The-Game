/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ship.domain.ship.battery;

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
public class BasicBatteryTest {
    private BasicBattery instance;

    public BasicBatteryTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        instance = new BasicBattery(50, 100);
    }

    @After
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testGetCharge() {
        assertEquals(50, instance.getCharge(),0.01);
    }

    @Test
    public void testGetMaxCharge() {
        assertEquals(100, instance.getMaxCharge(),0.01);
    }

    @Test
    public void testCharge() {
        assertEquals(50, instance.getCharge(),0.01);

        assertEquals(10, instance.charge(10),0.01);
        assertEquals(60, instance.getCharge(),0.01);

        assertEquals(20, instance.charge(20),0.01);
        assertEquals(80, instance.getCharge(),0.01);


        assertEquals(20, instance.charge(30),0.01);
        assertEquals(100, instance.getCharge(),0.01);

        assertEquals(0, instance.charge(30),0.01);
        assertEquals(100, instance.getCharge(),0.01);
    }

    @Test
    public void testAvaliableChargingCapacity() {
        assertEquals(50, instance.getAvaliableChargingCapacity(),0.01);

        assertEquals(10, instance.charge(10),0.01);
        assertEquals(40, instance.getAvaliableChargingCapacity(),0.01);

        assertEquals(20, instance.drain(20),0.01);
        assertEquals(60, instance.getAvaliableChargingCapacity(),0.01);
    }

    @Test
    public void testDrain() {
        assertEquals(50, instance.getCharge(),0.01);

        assertEquals(10, instance.drain(10),0.01);
        assertEquals(40, instance.getCharge(),0.01);

        assertEquals(30, instance.drain(30),0.01);
        assertEquals(10, instance.getCharge(),0.01);

        assertEquals(10, instance.drain(20),0.01);
        assertEquals(0, instance.getCharge(),0.01);

        assertEquals(0, instance.drain(20),0.01);
        assertEquals(0, instance.getCharge(),0.01);

    }

}