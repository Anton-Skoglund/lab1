package TestSimulations;

import Cars.Saab95;
import Cars.Volvo240;
import Trucks.Lory;
import Trucks.Scania;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestSimLory {
    //TODO
    // - fix simulation to try and run and have asserts to see that it works
    Lory lory;
    Volvo240 volvo1;
    Volvo240 volvo2;
    Volvo240 volvo3;
    Saab95 saab1;
    Saab95 saab2;
    Scania scania;
    Lory loryTryToPickUp;

    @Before
    public void init(){
        lory = new Lory();

        volvo1 = new Volvo240();
        volvo2 = new Volvo240();
        volvo3 = new Volvo240();
        saab1 = new Saab95();
        saab2 = new Saab95();

        scania = new Scania();
        loryTryToPickUp = new Lory();
    }
    @Test
    public void testSimulation(){
        volvo1.setPositon(new double[]{100,100});
        volvo2.setPositon(new double[]{200,200});
        volvo3.setPositon(new double[]{300,300});
        saab1.setPositon(new double[]{0,0});
        saab2.setPositon(new double[]{50,50});
    }
}
