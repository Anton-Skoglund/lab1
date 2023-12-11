package renderEngine;

import assets.elements.Element;
import renderEngine.Controller.Controller;
import renderEngine.Model.ModelUpdate;
import renderEngine.Model.SimulationTimer;
import renderEngine.View.View;

import java.util.ArrayList;

public class Application {
    // The frame that represents this instance View of the MVC pattern

    public static void main(String[] args) {
        SimulationTimer simulationTimer = new SimulationTimer();
        ArrayList<Element> elementsOnScreen = new ArrayList<>();
        Controller controller = new Controller();
        View view = new View("DrivingSim 1.0", controller, elementsOnScreen);
        ModelUpdate modelUpdate = new ModelUpdate(elementsOnScreen);
        simulationTimer.addObserver(modelUpdate);
        simulationTimer.addObserver(view);

        simulationTimer.startTimer();
    }
}
