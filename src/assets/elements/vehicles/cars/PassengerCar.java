package assets.elements.vehicles.cars;

import assets.elements.vehicles.motorVehicles;
import assets.elements.vehicles.engines.Engine;

import java.awt.*;

/**
 * A PassengerCar is a Car with a seat count
 */
public abstract class PassengerCar extends motorVehicles {
    private final int nrSeats;

    public PassengerCar(int nrSeats, int nrDoors, Engine engine, String modelName, Color color, double height, double weight) {
        super(nrDoors, engine, modelName, color, height, weight);
        this.nrSeats = nrSeats;
    }

    /**
     * @return the number of seats of the PassengerCar
     */
    public int getNrSeats(){
        return nrSeats;
    }
}
