package Trucks;

import Cars.Volvo240;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.Arrays;

import static org.junit.Assert.*;


public class TestTruck {
    Scania scania;
    @Before
    public void init(){
        scania = new Scania();
    }

    @Test
    public void testGetRampAngel(){
        assertEquals(scania.getRampAngel(), 0, 0);
    }

    @Test
    public void testGetMinRampAngel(){
        assertEquals(scania.getMinRampAngel(), 0, 0);
    }
    @Test
    public void testGetMaxRampAngel(){
        assertEquals(scania.getMaxRampAngel(), 70, 0);
    }
    @Test
    public void testGetAngelChangeSpeed(){
        assertEquals(scania.getAngelChangeSpeed(), 10, 0);
    }
    @Test
    public void testStartEngineRampAngelIsZero(){
        scania.startEngine();
        assertEquals(scania.getCurrentSpeed(), 0.1, 0);
    }
    @Test
    public void testStartEngineRampAngelNotZero(){
        scania.tiltUp();
        scania.startEngine();
        assertEquals(scania.getCurrentSpeed(), 0, 0);
    }
    @Test
    public void testDriveAngelIsZero(){
        assertTrue(scania.driveAngel(0));
    }
    @Test
    public void testDriveAngelNotZero(){
        assertFalse(scania.driveAngel(10));
    }
    @Test
    public void testCheckRampAngelIsAllowed(){
        assertTrue(scania.checkRampAngel(10));
    }
    @Test
    public void testCheckRampAngelNotAllowed(){
        assertFalse(scania.checkRampAngel(80));
    }
}
