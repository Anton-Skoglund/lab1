package assets.elements.vehicles.cars;
import assets.elements.vehicles.motorVehicles;
import assets.elements.vehicles.engines.Engine;

import java.awt.*;

/**
 * A Truck is a Car extended with a tray, the tray can retract and extend.
 */
public abstract class Truck extends motorVehicles {

    private boolean trayRetracted; // true if tray is in its retracted position

    /**
     * @param nrDoors number of doors of the Truck
     * @param engine the engine of the Truck
     * @param modelName the model name of the Truck
     * @param color the color of the Truck
     * @param height the height of the Truck
     * @param weight the bare weight of the Truck, without any engine
     */
    public Truck(int nrDoors, Engine engine, String modelName, Color color, double height, double weight){
        super(nrDoors, engine, modelName, color, height, weight);
        setTrayRetracted(true);
    }

    /**
     * Set the tray to be retracted or not
     * @param bool true if retracted
     */
    protected void setTrayRetracted(boolean bool){
        trayRetracted = bool;
    }

    /**
     * @return true if tray is retracted, false otherwise
     */
    public boolean isTrayRetracted(){
        return trayRetracted;
    }

}
