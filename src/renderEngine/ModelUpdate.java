package renderEngine;

import assets.elements.ActiveElement;
import assets.elements.Element;
import assets.elements.Vehicle;
import assets.elements.vehicles.cars.VehicleFactory;
import assets.elements.vehicles.cars.passengerCars.Saab95;
import assets.elements.vehicles.cars.passengerCars.Volvo240;
import assets.elements.vehicles.cars.trucks.ManTGX;
import assets.elements.vehicles.cars.trucks.ScaniaL280;
import assets.elements.vehicles.motorVehicles;
import renderEngine.Controller.CarType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import javax.swing.*;

import static renderEngine.Controller.CarType.NO_CAR;

public class ModelUpdate implements ModelObserver{
    static private ArrayList<Element> elementsOnScreen;
    private JFrame frame;
    //TODO
    // - Get the right values
    // - Mabye have centerpoint of car instad of cornenr
    // - Add illegal stat for when car is out of bounds

    private int frameWidth = 0;
    private int frameHeight = 500;
    private int controllerHeight = 200;
    private int sizeOfCar = 50;

    private int minX = 0;
    private int maxX = frameWidth;

    private int minY = 0;
    private int maxY = frameHeight - controllerHeight;

    public ModelUpdate(ArrayList<Element> elementsOnScreen){
        this.elementsOnScreen = elementsOnScreen;
    }

    @Override
    public void update(){
        for (Element motorVehicle : elementsOnScreen) {
            if (motorVehicle instanceof ActiveElement) {
                if (motorVehicle.getPosition()[0] > 650 || motorVehicle.getPosition()[0] < 0) {
                    double rot = motorVehicle.getRotation();
                    motorVehicle.setRotation(0 - rot);
                }

                if (motorVehicle.getPosition()[1] > 500 || motorVehicle.getPosition()[1] < 0) {
                    double rot = motorVehicle.getRotation();
                    motorVehicle.setRotation(180 - rot);
                }
                ((ActiveElement) motorVehicle).moveTick();
            }
        }
    }


    // Calls the gas method for each car once
    public static void gas(int amount) {
        double gasAmount = ((double) amount) / 100;
        for (Element motorVehicle : elementsOnScreen) {
            if (motorVehicle instanceof motorVehicles) {
                ((motorVehicles) motorVehicle).gas(gasAmount);
            }
        }
    }
    public static void brake(int amount) {
        double brakeAmount = ((double) amount) / 100;
        for (Element motorVehicle : elementsOnScreen) {
            if (motorVehicle instanceof motorVehicles){
                ((motorVehicles) motorVehicle).brake(brakeAmount);
            }
        }
    }

    public static void addCar(CarType car){
        Vehicle currentCar;
        if (elementsOnScreen.size() < 10){
            if(car == NO_CAR){
                currentCar = getRandomCar();
                System.out.println("Random vehicle was added");

            }
            else {
                currentCar = createGivenCar(car);
                System.out.println(currentCar.getModelName() + " was added");
            }
            setRandomElementAngle(currentCar);
        }
    }
    public static void removeCar(){
        if (!elementsOnScreen.isEmpty()) {
            elementsOnScreen.remove(getRandomElement(elementsOnScreen));
        }
        System.out.println("Random car was removed");

    }

    public static void turboOn() {
        for (Element motorVehicle : elementsOnScreen){
            if (motorVehicle instanceof Saab95){
                ((Saab95) motorVehicle).setTurboOn();
            }
        }
        System.out.println("Turbo on");

    }
    public static void turboOff() {
        for (Element motorVehicle : elementsOnScreen){
            if (motorVehicle instanceof Saab95){
                ((Saab95) motorVehicle).setTurboOff();
            }
        }
        System.out.println("Turbo off");
    }

    public static void extendTray() {
        for (Element motorVehicle : elementsOnScreen) {
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
        for (Element motorVehicle : elementsOnScreen){
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
        for (Element motorVehicle : elementsOnScreen) {
            if (motorVehicle instanceof motorVehicles) {
                ((motorVehicles) motorVehicle).start();
            }
        }
        System.out.println("Start all cars");
    }
    public static void stop() {
        for (Element motorVehicle : elementsOnScreen) {
            if (motorVehicle instanceof motorVehicles) {
                ((motorVehicles) motorVehicle).stop();
            }
        }
        System.out.println("Stop all cars");

    }

    /* HELP METHODS */

    private static Vehicle createVehicle(CarType carType) {
        // Use the CarFactory to create the specified car type
        return switch (carType) {
            case SAAB -> VehicleFactory.createSaab();
            case VOLVO -> VehicleFactory.createVolvo();
            case SCANIA -> VehicleFactory.createScania();
            default -> throw new IllegalArgumentException("Unknown car type: " + carType);
        };
    }

    private static Vehicle createGivenCar(CarType car) {
        Vehicle currentCar;
        currentCar = createVehicle(car);
        elementsOnScreen.add(currentCar);
        return currentCar;
    }

    private static <T> List<T> getListWithout(T[] allCarTypes, Object object) {
        return Arrays.stream(allCarTypes)
                .filter(carType -> carType != object)
                .collect(Collectors.toList());
    }


    private static Vehicle getRandomCar() {
        Vehicle currentCar;
        CarType[] allCarTypes = CarType.values();

        // Filter out NO_CAR
        List<CarType> carTypesWithoutNoCar = getListWithout(allCarTypes, NO_CAR);

        CarType randomCar = getRandomElement(carTypesWithoutNoCar);
        currentCar = createVehicle(randomCar);
        elementsOnScreen.add(currentCar);
        return currentCar;
    }

    private static <T> T getRandomElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List is null or empty");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    private static void setRandomElementAngle(Element element) {
        element.setRotation(getRandomFloat(0, 360));
    }


    private static float getRandomFloat(float start, float stop){
        Random random = new Random();
        return (stop - start) * random.nextFloat() + start;
    }
}
