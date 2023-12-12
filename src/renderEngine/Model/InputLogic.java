package renderEngine.Model;

import assets.elements.Element;
import assets.elements.Vehicle;
import assets.elements.vehicles.cars.passengerCars.Saab95;
import assets.elements.vehicles.cars.trucks.ManTGX;
import assets.elements.vehicles.cars.trucks.ScaniaL280;
import assets.elements.vehicles.motorVehicles;
import renderEngine.Controller.CarType;

import renderEngine.ElementsToRender;
import static renderEngine.Controller.CarType.NO_CAR;

public class InputLogic {
    private static ElementsToRender elementsToRender = ElementsToRender.getInstance();

    // Calls the gas method for each car once
    public static void gas(int amount) {
        double gasAmount = ((double) amount) / 100;
        for (Element motorVehicle : elementsToRender) {
            if (motorVehicle instanceof motorVehicles) {
                ((motorVehicles) motorVehicle).gas(gasAmount);
            }
        }
        System.out.println("Accelerate down all cars");
    }
    public static void brake(int amount) {
        double brakeAmount = ((double) amount) / 100;
        for (Element motorVehicle : elementsToRender) {
            if (motorVehicle instanceof motorVehicles){
                ((motorVehicles) motorVehicle).brake(brakeAmount);
            }
        }
        System.out.println("Slow down all cars");
    }

    public static void addCar(CarType car){
        Vehicle currentCar;
        if (elementsToRender.size() < 10){
            if(car == NO_CAR){
                currentCar = ModelUtilities.getRandomCar(elementsToRender);
                System.out.println("Random vehicle was added");

            }
            else {
                currentCar = ModelUtilities.createGivenCar(car, elementsToRender);
                System.out.println(currentCar.getModelName() + " was added");
            }
            ModelUtilities.setRandomElementAngle(currentCar);
        }
    }
    public static void removeCar(){
        if (!elementsToRender.isEmpty()) {
            elementsToRender.remove(ModelUtilities.getRandomElement(elementsToRender));
        }
        System.out.println("Random car was removed");

    }

    public static void turboOn() {
        for (Element motorVehicle : elementsToRender){
            if (motorVehicle instanceof Saab95){
                ((Saab95) motorVehicle).setTurboOn();
            }
        }
        System.out.println("Turbo on");

    }
    public static void turboOff() {
        for (Element motorVehicle : elementsToRender){
            if (motorVehicle instanceof Saab95){
                ((Saab95) motorVehicle).setTurboOff();
            }
        }
        System.out.println("Turbo off");
    }

    public static void extendTray() {
        for (Element motorVehicle : elementsToRender) {
            if (motorVehicle instanceof ScaniaL280) {
                ((ScaniaL280) motorVehicle).extendTray(70);
                System.out.println("ScaniaL280 trey extend");
            }
            if (motorVehicle instanceof ManTGX) {
                ((ManTGX) motorVehicle).extendTray();
                System.out.println("ManTGX trey retracted");
            }
        }
    }
    public static void retractTray() {
        for (Element motorVehicle : elementsToRender){
            if (motorVehicle instanceof ScaniaL280){
                ((ScaniaL280) motorVehicle).retractTray(70);
                System.out.println("ScaniaL280 trey retract");
            }
            if (motorVehicle instanceof ManTGX){
                ((ManTGX) motorVehicle).retractTray();
                System.out.println("ManTGX trey retracted");
            }
        }
    }

    public static void start() {
        for (Element motorVehicle : elementsToRender) {
            if (motorVehicle instanceof motorVehicles) {
                ((motorVehicles) motorVehicle).start();
            }
        }
        System.out.println("Start all cars");
    }
    public static void stop() {
        for (Element motorVehicle : elementsToRender) {
            if (motorVehicle instanceof motorVehicles) {
                ((motorVehicles) motorVehicle).stop();
            }
        }
        System.out.println("Stop all cars");
    }

}
