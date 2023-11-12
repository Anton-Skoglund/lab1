package Trucks;

import MotorVehicle.MotorVehicle;

import java.awt.*;
import java.util.Objects;
import java.util.Scanner;

public class Lory extends Truck {
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

    public void getStorageInfo(){
        for (int i = 0; i < storage.length; i++){
            System.out.println((i + 1) + ": " + storage[i]);
        }
    }

    public boolean isStorageFull(){
        int count = 0;
        for (int i = 0; i < storage.length; i++){
            if (Objects.equals(storage[i], null)){
                count++;
            }
        }
        return count == storageSpace;
    }

    public void pickUpCar(){
        double[] loryPosition = getPosition();
        double loryX = loryPosition[0];
        double loryY = loryPosition[1];
        for (MotorVehicle mv : MotorVehicle.motorVehicles) {
            double[] otherPostion = mv.getPosition();
            double otherX = otherPostion[0];
            double otherY = otherPostion[1];
            if (Objects.equals(mv.getModelName(), "Lory") || Objects.equals(mv.getModelName(), "Scania")){
                continue;
            }
            if (!checkMotorVechilesNeraYou(loryX, loryY, otherX, otherY)){
                continue;
            }
            if (!userInputPickUpCarYesOrNo(mv)){
                continue;
            }
            if (!isStorageFull()){
                continue;
            }
            for (int i = 0; i < storage.length; i++){
                if (Objects.equals(storage[i], null)){
                    System.out.println(storage[i]);
                    storage[i] = mv;
                    break;
                }
            }
        }
    }

    public boolean checkMotorVechilesNeraYou(double loryX, double loryY, double otherX, double otherY){
        if (otherX == loryX || otherY == loryY){
            return true;
        }
        else if (!(otherX < loryX && otherX + pickUpRange > loryX) && !(otherX > loryX && otherX - pickUpRange < loryX)){
            return false;
        }
        else if (!(otherY < loryY && otherY + pickUpRange > loryY) && !(otherY > loryY && otherY - pickUpRange < loryY)){
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
    public void TakeOutCar(){

    }
    public void TakeOutCar(int index){

    }
    @Override
    protected double speedFactor() {
        return 0;
    }
}
