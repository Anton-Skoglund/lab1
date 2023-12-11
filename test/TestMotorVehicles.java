
import assets.elements.vehicles.cars.PassengerCar;
import assets.elements.vehicles.cars.passengerCars.Volvo240;
import assets.elements.vehicles.engines.TrimmedEngine;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.Arrays;

import static org.junit.Assert.*;

public class TestMotorVehicles {
    private static class CursedCar extends PassengerCar {
        public CursedCar(double cursedTrimFactor){
            super(-1, 0, new TrimmedEngine(100,  cursedTrimFactor, 50000), "😎", Color.MAGENTA, 10, 0);
        }
    }

    Volvo240 volvo;
    CursedCar carWithTrimFactorVeryLarge;
    @Before
    public void initCars(){
        volvo = new Volvo240();
        carWithTrimFactorVeryLarge = new CursedCar(69420);
    }

    @Test
    public void volvoDoorNrShouldBe4(){
        assertEquals(4, volvo.getNrDoors());
    }

    @Test
    public void volvoEnginePowerShouldBe100(){
        assertEquals(100, volvo.getEngine().getMaxEnginePower(), 0.001);
    }

    @Test
    public void carSpeedShouldInitAt0(){
        assertEquals(0, volvo.getSpeed(), 0.001);
    }

    @Test
    public void volvoColorShouldInitBlack(){
        assertEquals(Color.black, volvo.getColor());
    }

    @Test
    public void testCarSetColor(){
        volvo.setColor(Color.green);
        assertEquals(Color.green,volvo.getColor());
    }

    @Test
    public void testVolvoGetModelName(){
        assertEquals( "Volvo240", volvo.getModelName());
    }

    @Test
    public void casRotationShouldInitAt0(){
        assertEquals(0, volvo.getRotation(), 0.001);
    }
     @Test
    public void carPositionShouldInitAtOrigin(){
        assertEquals("[0.0, 0.0]", Arrays.toString(volvo.getPosition()));
     }

    @Test
    public void carGasShouldIncreaseSpeed(){
        volvo.start();
        volvo.gas(0.5);
        assertTrue(volvo.getSpeed() > 0.2);
    }

    @Test
    public void carGasShouldNotGoAbove1(){
        volvo.start();
        volvo.gas(1.5);
        assertEquals(0, volvo.getSpeed(), 0.0);
    }

    @Test
    public void carBrakeShouldDecreaseSpeed(){
        volvo.start();
        volvo.gas(1);
        double highSpeed = volvo.getSpeed();
        volvo.brake(0.5);
        assertTrue(volvo.getSpeed() < highSpeed);
    }

    @Test
    public void carBrakeShouldNotGoAbove1(){
        volvo.start();
        volvo.gas(1);
        double highSpeed = volvo.getSpeed();
        volvo.brake(1.5);
        assertEquals(highSpeed,volvo.getSpeed(), 0.001);
    }
    @Test
    public void testTurnLeft(){
        volvo.turnLeft(90);
        assertEquals(270, volvo.getRotation(),0.001);
    }

    @Test
    public void testTurnRight(){
        volvo.turnRight(45);
        assertEquals(45, volvo.getRotation(), 0.001);
    }

    @Test
    public void volvoShouldMoveToTheLeftAt90DegTurn(){
        volvo.start();
        volvo.gas(1);
        volvo.turnLeft(90);
        volvo.moveTick();
        assertTrue(volvo.getPosition()[0] < 0 && Math.abs(volvo.getPosition()[1]) < 0.001);
    }
    @Test
    public void testIncrementSpeedBelowEnginePower(){
        volvo.start();
        volvo.gas(1);
        assertTrue(volvo.getSpeed() > 0 && volvo.getSpeed() < volvo.getEngine().getMaxEnginePower());
    }
    @Test
    public void testIncrementSpeedAboveEnginePower(){
        carWithTrimFactorVeryLarge.start();
        carWithTrimFactorVeryLarge.gas(1);
        assertEquals(carWithTrimFactorVeryLarge.getEngine().getMaxEnginePower(), carWithTrimFactorVeryLarge.getSpeed(), 0.001);
    }

    @Test
    public void testDecrementSpeedBelowZero(){
        volvo.brake(0.1);
        assertEquals(0, volvo.getSpeed(), 0.001);
    }
    @Test
    public void testDecrementSpeedAboveZero(){
        volvo.start();
        volvo.gas(0.5);
        double highSpeed = volvo.getSpeed();
        volvo.brake(0.1);
        assertTrue(volvo.getSpeed() > 0 && volvo.getSpeed() < highSpeed);
    }
}
