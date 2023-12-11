import assets.elements.vehicles.motorVehicles;
import assets.elements.vehicles.cars.passengerCars.Volvo240;

import java.awt.*;
import java.util.Arrays;

public class DebugGetInfo {

    public static void getInfo(motorVehicles motorVehicles){
        System.out.println("Col: " + motorVehicles.getColor());
        System.out.println("Doors: " + motorVehicles.getNrDoors());
        System.out.println("Pow: " + motorVehicles.getEngine().getMaxEnginePower());
        System.out.println("Speed: " + motorVehicles.getSpeed());

        System.out.println("Rot: " + motorVehicles.getRotation());
        System.out.println("Pos: " + Arrays.toString(motorVehicles.getPosition()));

        System.out.println();
    }

    public static void main(String[] args){
        Volvo240 volvo = new Volvo240();
        getInfo(volvo);
        volvo.start();
        volvo.gas(0.3);
        volvo.moveTick();
        getInfo(volvo);
        volvo.turnRight(90);
        volvo.moveTick();
        volvo.turnRight(12);
        volvo.gas(0.6);
        volvo.moveTick();
        volvo.setColor(Color.red);
        getInfo(volvo);
    }
}
