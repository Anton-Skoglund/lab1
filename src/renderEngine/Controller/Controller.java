package renderEngine.Controller;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import assets.elements.Element;
import assets.elements.Vehicle;
import assets.elements.vehicles.cars.VehicleFactory;
import assets.elements.vehicles.motorVehicles;
import assets.elements.vehicles.cars.passengerCars.Saab95;

import javax.swing.*;
import java.util.ArrayList;

import static renderEngine.Controller.CarType.NO_CAR;

/**
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in an appropriate manner by
* modifying the model state and the updating the view.
 **/

public class Controller {


    private final ControllerUI UI = new ControllerUI();
    private ArrayList<Element> elementsOnScreen = new ArrayList<>();

    public Controller(ArrayList<Element> elementsOnScreen) {
        this.elementsOnScreen = elementsOnScreen;
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gasAmount = ((double) amount) / 100;
        for (Element motorVehicle : elementsOnScreen) {
            if (motorVehicle instanceof motorVehicles) {
                ((motorVehicles) motorVehicle).gas(gasAmount);
            }
        }
    }
    void brake(int amount) {
        double brakeAmount = ((double) amount) / 100;
        for (Element motorVehicle : elementsOnScreen) {
            if (motorVehicle instanceof motorVehicles){
                ((motorVehicles) motorVehicle).brake(brakeAmount);
            }
        }
    }

    void turboOn() {
        for (Element motorVehicle : elementsOnScreen){
            if (motorVehicle instanceof Saab95){
                ((Saab95) motorVehicle).setTurboOn();
            }
        }
    }

    void turboOff() {
        for (Element motorVehicle : elementsOnScreen){
            if (motorVehicle instanceof Saab95){
                ((Saab95) motorVehicle).setTurboOff();
            }
        }
    }

    void start() {
        for (Element motorVehicle : elementsOnScreen
        ) {
            if (motorVehicle instanceof motorVehicles) {
                ((motorVehicles) motorVehicle).start();
            }
        }
    }

    void stop() {
        for (Element motorVehicle : elementsOnScreen
        ) {
            if (motorVehicle instanceof motorVehicles) {
                ((motorVehicles) motorVehicle).stop();
            }
        }
    }

    void addCar(CarType car){
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

    private Vehicle createGivenCar(CarType car) {
        Vehicle currentCar;
        currentCar = createVehicle(car);
        elementsOnScreen.add(currentCar);
        return currentCar;
    }

    private Vehicle getRandomCar() {
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

    void removeCar(){
        if (!elementsOnScreen.isEmpty()) {
            elementsOnScreen.remove(getRandomElement(elementsOnScreen));
        }
    }

    // -- CONTROLLER UI --

    public void addControlPanelToFrame(JFrame frame){
        UI.initUI(frame, this);
        frame.add(UI);
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
