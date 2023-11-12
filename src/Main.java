import MotorVehicle.MotorVehicle;
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
        Scania scania = new Scania();
        scania.tiltUp();
        System.out.println(scania.getRampAngel());
        scania.tiltUp();
        System.out.println(scania.getRampAngel());
        scania.tiltDown();
        scania.tiltDown();
        System.out.println(scania.getRampAngel());
        scania.tiltUp();
        scania.startEngine();
        System.out.println(scania.getCurrentSpeed());

    }
}
