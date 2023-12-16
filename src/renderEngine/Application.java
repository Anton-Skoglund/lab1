package renderEngine;

import renderEngine.Model.ModelUpdate;
import renderEngine.Model.SimulationTimer;
import renderEngine.View.View;

public class Application {
    public static void main(String[] args) {
        SimulationTimer simulationTimer = SimulationTimer.getInstance();
        ModelUpdate modelUpdate = ModelUpdate.getInstance();

        View view = new View("DrivingSim 1.0");   // Not singleton because we might want more views

        simulationTimer.addObserver(modelUpdate);
        simulationTimer.addObserver(view);
        simulationTimer.startTimer();
    }
}
