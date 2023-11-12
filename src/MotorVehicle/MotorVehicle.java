package MotorVehicle;

import java.awt.*;
import java.io.NotActiveException;
import java.util.ArrayList;

public abstract class MotorVehicle implements Movable{
    // Keep count of number of truck and index them
    // Change to list, so you can append forever
    // Need to check that no null exception when list is empyt happens
    public static int numberOfObjects = 0;
    public static ArrayList<MotorVehicle> motorVehicles = new ArrayList<MotorVehicle>();

    {
        motorVehicles.add(this);
        numberOfObjects++;
    }

    private final int nrDoors; // Number of doors on the car
    private boolean engineIsOn;
    private final double enginePower; // Engine power of the car

    // Protected because being able to reach it form truck
    protected double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private final String modelName; // The car model name
    private double currentRotation; // Plane rotation in degrees
    private double[] currentPosition; // x and y position

    public MotorVehicle(int nrDoors, Color color, double enginePower, String modelName){
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        resetTransform(); // Sets position to 0,0 and rotation to 0 degrees
        stopEngine();
    }

    public void move(){
        currentPosition[0] += getCurrentSpeed() * Math.sin(Math.toRadians(getRotation()));
        currentPosition[1] += getCurrentSpeed() * Math.cos(Math.toRadians(getRotation()));
    }

    public void turnLeft(double degrees){
        currentRotation -= degrees;
        currentRotation = ((360 + (currentRotation % 360)) % 360); // Limit value to range [0, 360)

    }
    public void turnRight(double degrees){
        currentRotation += degrees;
        currentRotation = ((360 + (currentRotation % 360)) % 360); // Limit value to range [0, 360)
    }

    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public String getModelName() {
        return modelName;
    }

    public double getRotation(){
        return currentRotation;
    }
    public double[] getPosition(){
        return currentPosition;
    }


    public void resetTransform(){
        currentPosition = new double[]{0,0};
        currentRotation = 0;
    }

    public void startEngine(){
        currentSpeed = 0.1;
        engineIsOn = true;
    }


    public void stopEngine(){
        engineIsOn = false;
        currentSpeed = 0;
    }

    protected abstract double speedFactor();

    private void incrementSpeed(double amount){
        double newSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower());
        if (newSpeed > currentSpeed) currentSpeed = newSpeed;
    }

    private void decrementSpeed(double amount){
        double newSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
        if (newSpeed < currentSpeed) currentSpeed = newSpeed;
    }

    public void gas(double amount){
        if (!engineIsOn){
            System.out.println("Engine must be ON to use gas");
            return;
        }
        if (amount < 0 || amount > 1) {
            System.out.println("Gas amount must be double in range [0,1]");
            return;
        }
        incrementSpeed(amount);
    }

    public void brake(double amount) {
        if (amount < 0 || amount > 1) {
            System.out.println("Brake amount must be double in range [0,1]");
            return;
        }
        decrementSpeed(amount);
    }
}
