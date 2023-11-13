package Trucks;

import MotorVehicle.MotorVehicle;

import java.awt.*;
import java.util.Objects;
import java.util.Scanner;

public class Lory extends Truck {
    //TODO
    // - Add a speedFactor and test it
    // - Check parent class TRUCK, when try to pick up truck

    //All cars are 1 space wide
    int storageSpace = 5;
    int pickUpRange = 10;
    private final MotorVehicle[] storage = new MotorVehicle[storageSpace];

    public Lory() {
        super(  2,
                Color.blue,
                400,
                "Lory",
                0,
                0,
                1,
                1);
    }

    public boolean rampIsDown(){
        if (getRampAngel() == 0){
            return true;
        }
        else{
            return false;
        }
    }

    public void getStorageInfo(){
        for (int i = 0; i < storage.length; i++){
            System.out.println((i + 1) + ": " + storage[i]);
        }
    }

    public boolean isStorageFull(){
        int count = 0;
        for (int i = 0; i < storage.length; i++){
            if (!Objects.equals(storage[i], null)){
                count++;
            }
        }
        return count == storageSpace;
    }
    public boolean isStorageEmpty(){
        int count = 0;
        for (int i = 0; i < storage.length; i++){
            if (!Objects.equals(storage[i], null)){
                count++;
            }
        }
        return count == 0;
    }
    public int lastIndexInStorage(){
        for (int i = storage.length - 1; i > 0; i--){
            if(storage[i] != null){
                return i;
            }
        }
        return 0;
    }

    public void PickUpMotorVehicle(){
        if (!rampIsDown()){
            System.out.println("Ramp is not down.");
            return;
        }

        double[] loryPosition = getPosition();
        double loryX = loryPosition[0];
        double loryY = loryPosition[1];

        for (MotorVehicle mv : MotorVehicle.motorVehicles) {
            double[] otherPosition = mv.getPosition();
            double otherX = otherPosition[0];
            double otherY = otherPosition[1];
            if (Objects.equals(mv.getModelName(), "Lory") || Objects.equals(mv.getModelName(), "Scania")){
                continue;
            }
            if (!checkMotorVehiclesNeraYou(loryX, loryY, otherX, otherY)){
                continue;
            }
            if (!userInputPickUpCarYesOrNo(mv)){
                continue;
            }
            if (!isStorageFull()){
                continue;
            }
            placeMotorVehicleInStorage(mv);
        }
    }

    protected void placeMotorVehicleInStorage(MotorVehicle mv){
        for (int i = 0; i < storage.length; i++){
            if (Objects.equals(storage[i], null)){
                storage[i] = mv;
                break;
            }
        }
    }

    public boolean checkMotorVehiclesNeraYou(double loryX, double loryY, double otherX, double otherY){
        if (otherX == loryX || otherY == loryY){
            return true;
        }
        if (!(otherX < loryX && otherX + pickUpRange > loryX) && !(otherX > loryX && otherX - pickUpRange < loryX)){
            return false;
        }
        if (!(otherY < loryY && otherY + pickUpRange > loryY) && !(otherY > loryY && otherY - pickUpRange < loryY)){
            return false;
        }
        return true;
    }

    public boolean userInputPickUpCarYesOrNo(MotorVehicle mv){
        Scanner myObj = new Scanner(System.in);

        System.out.println("Do you want to pick up: " + mv + " y/n");
        String answer = myObj.nextLine();
        if (Objects.equals(answer, "y") || Objects.equals(answer, "n")){
            if(Objects.equals(answer, "y")){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    // overriding to try it out, but could have getLastCarOut, and getIndexCarOut
    public void takeOutMotorVehicle(){
        if (!rampIsDown()){
            System.out.println("Ramp is not down.");
            return;
        }
        if(isStorageEmpty()){
            System.out.println("Storage is empty, can you empty the empty?");
            return;
        }
        // SET POSITION
        double[] dropOfPosition = {getPosition()[0] + pickUpRange, getPosition()[1] + pickUpRange};
        storage[lastIndexInStorage()].setPositon(dropOfPosition);
        storage[lastIndexInStorage()] = null;
    }

    @Override
    protected double speedFactor() {
        for(MotorVehicle mv : storage){
            if(mv == null){
                continue;
            }
            mv.setPositon(getPosition());
        }
        return 0;
    }
}
