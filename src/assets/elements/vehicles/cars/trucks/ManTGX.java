package assets.elements.vehicles.cars.trucks;
import assets.elements.vehicles.motorVehicles;
import assets.elements.vehicles.engines.Engine;
import assets.elements.vehicles.cars.Truck;
import assets.storages.StorageStack;


import java.awt.*;

/**
 * A ManTGX is a Truck with a Car storage.
 * It can only store cars below 2 meters.
 * A ManTGX must have its tray retracted to start,
 * and extended to load or unload cars.
 */
public final class ManTGX extends Truck {
    private final StorageStack<motorVehicles> storage;
    private static final int maxStorageSize = 6;
    private static final double reach = 5;
    private static final double maxHeight = 2;

    /**
     * A ManTGX has:
     * <i><p>
     *
     * <p>Doors: 2</p>
     * <p>Engine: Normal Engine</p>
     * <p>Model Name: ManTGX</p>
     * <p>Color: Red</p>
     * <p>Height: 3 meters</p>
     * <p>Weight: 20000 kg</p>
     *
     * <p></i>
     * It is also endowed with a storage of cars, with a maximum of 6 cars.
     *
     */
    public ManTGX(){
        super(2, new Engine(450, 500), "ManTGX", Color.RED, 3, 20000);
        this.storage = new StorageStack<>(this, maxStorageSize);
    }


    /**
     * Retract the tray
     */
    public void retractTray(){
        setTrayRetracted(true);
    }

    /**
     * Extend the tray
     */
    public void extendTray(){
        if (isStarted()){
            System.out.println("Engine must be OFF to extend tray");
            return;
        }
        setTrayRetracted(false);
    }

    /**
     * Load a car to the ManTGX
     * @param motorVehicles the car to load
     */
    public void loadCar(motorVehicles motorVehicles) {
        if (isTrayRetracted()){
            System.out.println("Tray needs to be extended to load car");
            return;
        }
        if (motorVehicles instanceof ManTGX) {
            System.out.println("ManTGX cannot carry other ManTGX's");
            return;
        }
        if (motorVehicles.getHeight() > maxHeight) {
            System.out.printf("%s is too high%n", motorVehicles);
            return;
        }

        if (distanceTo(motorVehicles) > reach) {
            System.out.printf("%s is too far away%n", motorVehicles);
            return;
        }

        storage.add(motorVehicles);
    }

    /**
     * Unload the car which was loaded last
     */
    public void unloadCar() {
        if (!isTrayRetracted()) {
            System.out.println("Tray needs to be extended to unload car");
            return;
        }
        storage.pop(getRelativePosition(180, -2));
    }

    /**
     * Starts the car
     * Note: the tray needs to be retracted to start a ManTGX
     */
    @Override
    public void start(){
        if (!isTrayRetracted()) {
            System.out.println("Tray needs to be retracted to start ManTGX");
            return;
        }
        super.start();
    }

    /**
     * @return the total weight of the car including engine and storage
     */
    @Override
    public double getWeight() {
        double w = 0;
        for (motorVehicles motorVehicles : storage.getStorageItems()){
            w += motorVehicles.getWeight();
        }
        return super.getWeight() + w;
    }
}
