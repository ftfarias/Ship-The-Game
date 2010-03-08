/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ship.domain.ship.powergrid;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ship.domain.ship.battery.Battery;
import ship.domain.ship.powergenerator.PowerGenerator;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 *
 * @author Felipe T. Farias <ftfarias@gmail.com>
 */
public class BasicPowerGridTest {

    private BasicPowerGrid instance;
    private Battery battery;

    public BasicPowerGridTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
//        PowerGenerator mockPowerGenerator = mock(PowerGenerator.class);
//        when(mockPowerGenerator.getEnergy(anyDouble())).thenReturn(100.0);

        PowerGenerator mockPowerGenerator = new PowerGenerator() {

            @Override
            public double getEnergy(double timeElapsed) {
                return 100 * timeElapsed;
            }
        };

        battery = mock(Battery.class);
        when(battery.drain(100.0)).thenReturn(100.0);
        when(battery.drain(150.0)).thenReturn(150.0);
        when(battery.drain(500.0)).thenReturn(300.0);


        instance = new BasicPowerGrid(mockPowerGenerator, battery);
    }

    @After
    public void tearDown() {
        instance = null;
    }

    @Test
    public void testTimeElapsed() {
        assertEquals(0, instance.avaliableEnergy, 0.0001);
        instance.timeElapsed(1);
        assertEquals(100, instance.avaliableEnergy, 0.0001);
        instance.timeElapsed(3.5);
        assertEquals(350, instance.avaliableEnergy, 0.0001);
    }

    @Test
    public void testRequestEnergyLess() {
        instance.timeElapsed(1.01);
        assertEquals(101, instance.avaliableEnergy, 0.0001);

        assertEquals(100, instance.requestEnergy(100), 0.0001);

        assertEquals(1, instance.avaliableEnergy, 0.0001);
        instance.update();
        verify(battery).charge(1.0);
    }

    @Test
    public void testRequestEnergyExactAmount() {
        instance.timeElapsed(1.0);
        assertEquals(100, instance.avaliableEnergy, 0.0001);

        assertEquals(100, instance.requestEnergy(100), 0.0001);

        assertEquals(0, instance.avaliableEnergy, 0.0001);
        instance.update();
        verify(battery, never()).charge(anyDouble());
        verify(battery, never()).drain(anyDouble());
    }

    @Test
    public void testRequestEnergy3() {
        instance.timeElapsed(1.0);
        assertEquals(100, instance.avaliableEnergy, 0.0001);

        assertEquals(200, instance.requestEnergy(200), 0.0001);

        assertEquals(0, instance.avaliableEnergy, 0.0001);
        instance.update();
        verify(battery).drain(100);
    }

    @Test
    public void testRequestEnergyOverload() {
        instance.timeElapsed(1.0);
        assertEquals(100, instance.avaliableEnergy, 0.0001);

        assertEquals(400, instance.requestEnergy(600), 0.0001);

        assertEquals(0, instance.avaliableEnergy, 0.0001);
        instance.update();
        verify(battery).drain(500);
        assertEquals(0, instance.avaliableEnergy, 0.0001);
    }


    @Test
    public void testUpdate() {
        assertEquals(0, instance.avaliableEnergy, 0.0001);
        instance.timeElapsed(1);
        assertEquals(100, instance.avaliableEnergy, 0.0001);
        instance.update();
        verify(battery).charge(100.0);
    }

    @Test
    public void testGetPowerGridBalance() {
        assertEquals(0, instance.avaliableEnergy, 0.0001);
        instance.timeElapsed(1);
        assertEquals(100, instance.avaliableEnergy, 0.0001);
        instance.update();
        assertEquals(100, instance.getPowerGridBalance(), 0.0001);
    }

    @Test
    public void testGetPowerGridBalance2() {
        assertEquals(0, instance.avaliableEnergy, 0.0001);
        instance.timeElapsed(1);
        assertEquals(250, instance.requestEnergy(250), 0.0001);
        instance.update();
        assertEquals(-150, instance.getPowerGridBalance(), 0.0001);
    }
}
