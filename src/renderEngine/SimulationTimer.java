package renderEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SimulationTimer {
    // 20 updates per second = 50ms
    private final int delay = 50;

    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.

    private Timer timer = new Timer(delay, new TimerListener());
    private List<ModelObserver> modelObservers = new ArrayList<>();

    /** Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     **/
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            modelUpdate();
        }
    }

    public void startTimer(){
        timer.start();
    }

    public void addObserver(ModelObserver modelObserver) {
        this.modelObservers.add(modelObserver);
    }

    public void removeObserver(ModelObserver modelObserver) {
        this.modelObservers.remove(modelObserver);
    }

    public void modelUpdate() {
        for (ModelObserver modelObserver : this.modelObservers) {
            modelObserver.update();
        }
    }
}
