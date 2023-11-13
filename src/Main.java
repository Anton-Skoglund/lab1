import Cars.Volvo240;
import MotorVehicle.MotorVehicle;
import Trucks.Lory;
import Trucks.Scania;

import java.util.Arrays;

public class Main {
    public Main(){

    }

    public static void getInfo(MotorVehicle motorVehicle){
        System.out.println("Col: " + motorVehicle.getColor());
        System.out.println("Doors: " + motorVehicle.getNrDoors());
        System.out.println("Pow: " + motorVehicle.getEnginePower());
        System.out.println("Speed: " + motorVehicle.getCurrentSpeed());

        System.out.println("Rot: " + motorVehicle.getRotation());
        System.out.println("Pos: " + Arrays.toString(motorVehicle.getPosition()));

        System.out.println();
    }

    public static void main(String[] args){
        Lory lory = new Lory();
        System.out.println(lory.getRampAngel());
        lory.tiltUp();
        System.out.println(lory.getRampAngel());
        lory.tiltUp();
        lory.tiltDown();
        lory.tiltDown();
        lory.getStorageInfo();
        Volvo240 volvo = new Volvo240();
        Lory lory1 = new Lory();
        Lory lory2 = new Lory();
        lory.PickUpMotorVehicle();
        lory.getStorageInfo();
        lory.setPositon(new double[]{100, 200});
        lory.startEngine();
        lory.gas(0.2);
        System.out.println(Arrays.toString(volvo.getPosition()));
        lory.takeOutMotorVehicle();
        System.out.println(Arrays.toString(volvo.getPosition()));
    }
}
