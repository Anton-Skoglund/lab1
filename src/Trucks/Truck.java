package Trucks;
import MotorVehicle.*;

import java.awt.*;

public abstract class Truck extends MotorVehicle implements Ramp {
    private int rampAngel;
    private final int minRampAngel;
    private final int maxRampAngel;
    private final int angelChangeSpeed;
    // int instead of double Simplifies the second part of the assignment
    // I can't think of any reason for sub integer degree specificity for angel
    // of ramp.


    public Truck(int nrDoors, Color color, double enginePower, String modelName, int rampAngel, int minRampAngel, int maxRampAngel, int angelChangeSpeed) {
        super(nrDoors, color, enginePower, modelName);
        this.rampAngel = rampAngel;
        this.minRampAngel = minRampAngel;
        this.maxRampAngel = maxRampAngel;
        this.angelChangeSpeed = angelChangeSpeed;
    }

    public int getRampAngel(){
        return rampAngel;
    }

    public int getMinRampAngel(){
        return minRampAngel;
    }
    public int getMaxRampAngel(){
        return maxRampAngel;
    }

    public int getAngelChangeSpeed(){
        return angelChangeSpeed;
    }



    @Override
    public void startEngine(){
        if (driveAngel(rampAngel)){
            currentSpeed = 0.1;
            engineIsOn = true;
        }
        else{
            System.out.println("Ramp angel most be 0");
        }
    }
    public boolean driveAngel(int angel){
        if (angel == 0){
            return true;
        }else{
            System.out.println("Ramp angel most be 0 deg to start, current ramp angel = " + getRampAngel());
            return false;
        }
    }

    public boolean checkRampAngel(int newAngel){
        if(newAngel >= minRampAngel && newAngel <= maxRampAngel){
            return true;
        }else{
            return false;
        }
    }
    public void tiltUp() {
        int newAngel = getRampAngel() + getAngelChangeSpeed();
        if (checkRampAngel(newAngel)){
            rampAngel = newAngel;
        }else{
            printAngelNotAllowed(newAngel, getRampAngel());
        }
    }

    public void tiltDown() {
        int newAngel = getRampAngel() - getAngelChangeSpeed();
        if (checkRampAngel(newAngel)){
            rampAngel = newAngel;
        }else{
            printAngelNotAllowed(newAngel, getRampAngel());
        }
    }

    protected abstract double speedFactor();

    protected void printAngelNotAllowed(int angel){
        System.out.println(("New angel " + angel + " is not allowed."));
    }
    protected void printAngelNotAllowed(int newAngel, int oldAngel){
        System.out.println(("New angel " + newAngel + " is not allowed, angel is still " + oldAngel));
    }
}
