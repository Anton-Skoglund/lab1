import assets.elements.vehicles.motorVehicles;
import assets.elements.vehicles.boats.motorizedBoats.Ferry2000;
import assets.elements.vehicles.cars.passengerCars.Saab95;
import assets.elements.vehicles.cars.passengerCars.Volvo240;
import assets.elements.vehicles.cars.trucks.ScaniaL280;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Ferry2000Test {

    Ferry2000 ferry;
    @Before
    public void setUp(){
        ferry = new Ferry2000(4, 3);
    }
    @Test
    public void unloadedCarFromLane2ShouldBeCarLoadedToLane2() {
        ferry.loadCar(new Saab95(), 1);
        ferry.loadCar(new Volvo240(), 2);
        motorVehicles unloadedMotorVehicles = ferry.unloadCar(2);
        assertEquals("Volvo240", unloadedMotorVehicles.getModelName());
    }


    @Test
    public void weightShouldIncreaseWithMoreCars() {
        double w1 = ferry.getWeight();
        ferry.loadCar(new Volvo240(), 1);
        double w2 = ferry.getWeight();
        ferry.loadCar(new ScaniaL280(), 4);
        double w3 = ferry.getWeight();
        assertTrue(w1 < w2 && w2 < w3);
    }
}