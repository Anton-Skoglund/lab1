package renderEngine;

import assets.elements.Element;
import assets.elements.vehicles.cars.passengerCars.Saab95;
import assets.elements.vehicles.cars.passengerCars.Volvo240;
import assets.elements.vehicles.cars.trucks.ScaniaL280;
import renderEngine.Controller.Controller;
import renderEngine.View.View;

import java.util.ArrayList;

public class Application {
    // The frame that represents this instance View of the MVC pattern

    public static void main(String[] args) {
        SimulationTimer simulationTimer = new SimulationTimer();
        //TODO
        // - STUPID BOTH HAVE MOTOR VEHICLES
        ArrayList<Element> elementsOnScreen = new ArrayList<>();
        Controller controller = new Controller(elementsOnScreen);
        View view = new View("DrivingSim 1.0", controller, elementsOnScreen);
        ModelUpdate modelUpdate = new ModelUpdate(elementsOnScreen);
        simulationTimer.addObserver(modelUpdate);
        simulationTimer.addObserver(view);

        simulationTimer.timer.start();
    }
}
