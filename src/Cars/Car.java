package Cars;
import MotorVehicle.*;

import java.awt.*;

public abstract class Car extends MotorVehicle {
    public Car(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
    }
    protected abstract double speedFactor();
}
