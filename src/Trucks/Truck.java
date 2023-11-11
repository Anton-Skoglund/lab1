package Trucks;
import MotorVehicle.*;

import java.awt.*;

public abstract class Truck extends MotorVehicle implements Ramp {
    private double rampAngel = 0;
    private double minRampAngel = 0;
    private double maxRampAngel = 70;

    public Truck(int nrDoors, Color color, double enginePower, String modelName) {
        super(nrDoors, color, enginePower, modelName);
    }

    @Override
    public void startEngine(){
        // check if angel = driveAngel
    }
    public abstract boolean allowedAngel(double angel);
    public abstract boolean driveAngel(double angel);

    public double getRampAngel(){
        return rampAngel;
    }
    public void setRampAngel(double newAngel){
        // check allowedAngel
    }

    public void tiltUp(){

    }
    public void tiltDown(){

    }

    protected abstract double speedFactor();

}
