import assets.elements.vehicles.cars.passengerCars.Saab95;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;
public class TestSaab {
    Saab95 saab;

    @Before
    public void before(){
        saab = new Saab95();
    }

    @Test
    public void turboOnShouldMoveFaster(){
        saab.start();
        saab.gas(0.5);
        double normalSpeed = saab.getSpeed();

        saab.stop();

        saab.start();
        saab.setTurboOn();
        saab.gas(0.5);

        assertTrue(saab.getSpeed() > normalSpeed);
    }



}
