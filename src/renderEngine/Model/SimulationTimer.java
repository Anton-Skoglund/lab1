package renderEngine.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SimulationTimer {
    /**
     * We count this to the model but separate for cleaner code
     * you could put it in ModelUpdate.java or a new one
     **/
    // 20 updates per second = 50ms
    private final int delay = 50;
    private Timer timer = new Timer(delay, new TimerListener());
    private static SimulationTimer INSTANCE;
    private List<ModelObserver> modelObservers = new ArrayList<>();

    /**
     * Singleton
     */
    private SimulationTimer(){}

    public static SimulationTimer getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new SimulationTimer();
        }
        return INSTANCE;
    }

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            update();
        }
    }

    public void startTimer(){
        timer.start();
    }

    /**
     * Observer
     */

    public void addObserver(ModelObserver modelObserver) {
        this.modelObservers.add(modelObserver);
    }

    public void removeObserver(ModelObserver modelObserver) {
        this.modelObservers.remove(modelObserver);
    }

    public void update() {
        for (ModelObserver modelObserver : this.modelObservers) {
            modelObserver.update();
        }
    }
}
