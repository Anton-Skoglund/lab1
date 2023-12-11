package renderEngine;

import assets.elements.ActiveElement;
import assets.elements.Element;
import assets.elements.Vehicle;
import assets.elements.vehicles.cars.VehicleFactory;
import assets.elements.vehicles.cars.passengerCars.Saab95;
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

    public void turboOn() {
        for (Element motorVehicle : elementsOnScreen){
            if (motorVehicle instanceof Saab95){
                ((Saab95) motorVehicle).setTurboOn();
            }
        }
    }

    public void turboOff() {
        for (Element motorVehicle : elementsOnScreen){
            if (motorVehicle instanceof Saab95){
                ((Saab95) motorVehicle).setTurboOff();
            }
        }
    }

    public static void start() {
        for (Element motorVehicle : elementsOnScreen
        ) {
            if (motorVehicle instanceof motorVehicles) {
                ((motorVehicles) motorVehicle).start();
            }
        }
    }

    public static void stop() {
        for (Element motorVehicle : elementsOnScreen
        ) {
            if (motorVehicle instanceof motorVehicles) {
                ((motorVehicles) motorVehicle).stop();
            }
        }
    }

    public static void addCar(CarType car){
        Vehicle currentCar;
        if (elementsOnScreen.size() < 10){
            if(car == NO_CAR){
                currentCar = getRandomCar();
            }
            else {
                currentCar = createGivenCar(car);
            }
            setRandomElementAngle(currentCar);
        }
    }

    private static void setRandomElementAngle(Element element) {
        element.setRotation(getRandomFloat(0, 360));
    }

    private static Vehicle createGivenCar(CarType car) {
        Vehicle currentCar;
        currentCar = createVehicle(car);
        elementsOnScreen.add(currentCar);
        return currentCar;
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

    private static <T> List<T> getListWithout(T[] allCarTypes, Object object) {
        return Arrays.stream(allCarTypes)
                .filter(carType -> carType != object)
                .collect(Collectors.toList());
    }

    public static void removeCar(){
        if (!elementsOnScreen.isEmpty()) {
            elementsOnScreen.remove(getRandomElement(elementsOnScreen));
        }
    }


    private static <T> T getRandomElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List is null or empty");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    private static float getRandomFloat(float start, float stop){
        Random random = new Random();
        return (stop - start) * random.nextFloat() + start;
    }

    private static Vehicle createVehicle(CarType carType) {
        // Use the CarFactory to create the specified car type
        return switch (carType) {
            case SAAB -> VehicleFactory.createSaab();
            case VOLVO -> VehicleFactory.createVolvo();
            case SCANIA -> VehicleFactory.createScania();
            default -> throw new IllegalArgumentException("Unknown car type: " + carType);
        };
    }

}
