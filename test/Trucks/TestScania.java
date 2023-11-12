package Trucks;

import Cars.Volvo240;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.Arrays;

import static org.junit.Assert.*;


public class TestScania {
    Scania scania;
    @Before
    public void init(){
        scania = new Scania();
    }

    @Test
    public void testTiltUpAngelIsAllowed(){
        scania.tiltUp();
        assertEquals(scania.getRampAngel(), 10, 0);
    }
    @Test
    public void testTiltUpAngelNotAllowed(){
        for (int i = 0; i <= 10; i++){
            scania.tiltUp();
        }
        assertEquals(scania.getRampAngel(), scania.getMaxRampAngel(), 0);
    }
    @Test
    public void testTiltDownAngelIsAllowed(){
        scania.tiltUp();
        scania.tiltDown();
        assertEquals(scania.getRampAngel(), 0, 0);

    }
    @Test
    public void testTiltDownAngelNotAllowed(){
        scania.tiltDown();
        assertEquals(scania.getRampAngel(), 0, 0);
    }
}
