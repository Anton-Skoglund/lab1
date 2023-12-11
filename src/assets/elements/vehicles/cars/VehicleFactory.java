package assets.elements.vehicles.cars;

import assets.elements.Vehicle;
import assets.elements.vehicles.cars.passengerCars.Saab95;
import assets.elements.vehicles.cars.passengerCars.Volvo240;
import assets.elements.vehicles.cars.trucks.ScaniaL280;

public class VehicleFactory {
    public static Vehicle createSaab() {
        return new Saab95();
    }
    public static Vehicle createVolvo() {
        return new Volvo240();
    }
    public static Vehicle createScania() {
        return new ScaniaL280();
    }
}
