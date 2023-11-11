package Cars;

import Cars.Saab95;
import org.junit.Before;
import org.junit.Test;
import MotorVehicle.*;
import Cars.*;


import static org.junit.Assert.*;

public class TestSaab {
    Saab95 saab;

    @Before
    public void before(){
        saab = new Saab95();
    }

    @Test
    public void testSpeedFactorWithTurboOn(){
        saab.setTurboOn();
        assertEquals(125 * 0.01 * 1.3, saab.speedFactor() , 0.001);
    }
    @Test
    public void testSpeedFactorWithTurboOff(){
        saab.setTurboOff();
        saab.speedFactor();
        assertEquals(125 * 0.01 * 1, saab.speedFactor(), 0.001);
    }

}
