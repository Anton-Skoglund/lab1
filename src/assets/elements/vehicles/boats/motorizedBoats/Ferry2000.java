package assets.elements.vehicles.boats.motorizedBoats;

import assets.elements.vehicles.motorVehicles;
import assets.elements.vehicles.engines.Engine;
import assets.elements.vehicles.boats.MotorizedBoat;
import assets.storages.Storage;
import assets.storages.StorageQueue;

import java.awt.*;
import java.util.ArrayList;

/**
 * A Ferry2000 is a MotorizedBoat which has a number of lanes.
 * Each lane can store a number of cars.
 * A car can only be loaded to the back of the Ferry2000, and unloaded from the front.
 */
public class Ferry2000 extends MotorizedBoat {
    private final ArrayList<StorageQueue<motorVehicles>> lanes;
    private final int laneCount;
    private static final double reach = 5;

    /**
     * The Ferry2000 has:
     * <i><p>
     * <p>Engine: Normal Engine</p>
     * <p>Model Name: Ferry2000</p>
     * <p>Color: Cyan</p>
     * <p>Height: 10 meters</p>
     * <p>Weight: 50000 kg</p>
     * <p>Maximum Swing: 10 degrees</p>
     *
     * <p></i>
     * @param laneCount amount of lanes on the Ferry2000
     * @param laneSize size of each lane
     */
    public Ferry2000(int laneCount, int laneSize) {
        super(new Engine(2000, 3500), "Ferry2000", Color.CYAN, 10, 50000, 10);
        lanes = new ArrayList<>();
        this.laneCount = laneCount;
        for(int i = 0; i < laneCount; i++){
            lanes.add(new StorageQueue<>(this, laneSize));
        }
    }

    /**
     * Load a car to a lane
     * @param motorVehicles car to load
     * @param laneNr lane to load car to
     */
    public void loadCar(motorVehicles motorVehicles, int laneNr){
        if (laneNr <= 0 | laneNr > laneCount){
            System.out.println("That lane number does not exist!");
            return;
        }

        if (distanceTo(motorVehicles) > reach) {
            System.out.printf("%s is too far away%n", motorVehicles);
            return;
        }

        lanes.get(laneNr - 1).add(motorVehicles);
    }

    /**
     * Unload a car from a lane
     * @param laneNr lane to unload car from
     * @return the unloaded car
     */
    public motorVehicles unloadCar(int laneNr){
        if (laneNr <= 0 | laneNr > laneCount){
            System.out.println("That lane number does not exist!");
            return null;
        }
        return lanes.get(laneNr - 1).poll(getRelativePosition(0, 5));
    }

    /**
     * @return The weight of the boat, including engine and storage.
     */
    @Override
    public double getWeight() {
        double w = 0;
        for (Storage<motorVehicles> storage : lanes) {
            for (motorVehicles motorVehicles : storage.getStorageItems()) {
                w += motorVehicles.getWeight();
            }
        }
        return super.getWeight() + w;
    }
}
