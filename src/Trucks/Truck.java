package Trucks;
import MotorVehicle.*;

import java.awt.*;

public abstract class Truck extends MotorVehicle implements Ramp {
    private double rampAngel;
    private final double minRampAngel;
    private final double maxRampAngel;
    private final double angelChangeSpeed;

    public Truck(int nrDoors, Color color, double enginePower, String modelName, double rampAngel, double minRampAngel, double maxRampAngel, double angelChangeSpeed) {
        super(nrDoors, color, enginePower, modelName);
        this.rampAngel = rampAngel;
        this.minRampAngel = minRampAngel;
        this.maxRampAngel = maxRampAngel;
        this.angelChangeSpeed = angelChangeSpeed;
    }

    public double getRampAngel(){
        return rampAngel;
    }
    protected void setRampAngel(double newAngel){
        rampAngel = newAngel;
    }
    public double getMinRampAngel(){
        return minRampAngel;
    }
    public double getMaxRampAngel(){
        return maxRampAngel;
    }

    public double getAngelChangeSpeed(){
        return angelChangeSpeed;
    }



    @Override
    public void startEngine(){
        if (driveAngel(rampAngel)){
            currentSpeed = 0.1;
        }
    }
    public boolean driveAngel(double angel){
        if (angel == 0){
            return true;
        }else{
            System.out.println("Ramp angel most be 0 deg to start, current ramp angel = " + getRampAngel());
            return false;
        }
    }

    public boolean checkRampAngel(double newAngel){
        if(newAngel >= minRampAngel && newAngel <= maxRampAngel){
            return true;
        }else{
            return false;
        }
    }
    public abstract void tiltUp();
    public abstract void tiltDown();

    protected abstract double speedFactor();

    protected void printAngelNotAllowed(double angel){
        System.out.println(("New angel " + angel + " is not allowed."));
    }
    protected void printAngelNotAllowed(double newAngel, double oldAngel){
        System.out.println(("New angel " + newAngel + " is not allowed, angel is still " + oldAngel));
    }
}
