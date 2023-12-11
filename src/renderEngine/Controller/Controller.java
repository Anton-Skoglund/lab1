package renderEngine.Controller;
import java.awt.*;
import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import assets.elements.Element;
import assets.elements.Vehicle;
import assets.elements.vehicles.cars.VehicleFactory;
import assets.elements.vehicles.motorVehicles;
import assets.elements.vehicles.cars.passengerCars.Saab95;
import renderEngine.ModelUpdate;

import javax.swing.*;
import java.util.ArrayList;

import static renderEngine.Controller.CarType.NO_CAR;

/**
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in an appropriate manner by
* modifying the model state and the updating the view.
 **/

public class Controller extends JPanel{
    private JPanel gasPanel = new JPanel();
    private JSpinner amountSpinner = new JSpinner();
    private int amount = 0;
    private JLabel gasLabel = new JLabel("Gas/Brake #");
    private JButton gasButton = new JButton("Gas");
    private JButton brakeButton = new JButton("Brake");
    private JButton addCar = new JButton("Add Car");
    private JButton removeCar = new JButton("Remove Car 😎");

    private JSpinner carSpinner = new JSpinner(new SpinnerListModel(CarType.values()));
    private CarType selectedCarType = CarType.NO_CAR;
    private JButton extendTrayButton = new JButton("Scania Extend Tray");
    private JButton retractTrayButton = new JButton("Retract Tray");

    private JButton startButton = new JButton("Start all cars");
    private JButton stopButton = new JButton("Stop all cars");


    public void initUI(JFrame frame, Controller controller) {
        createSpinnerModel();
        createGasPanel();
        createUI(frame);
        createButton(frame, startButton, Color.blue, Color.green);
        createButton(frame, stopButton, Color.red, Color.black);
        addActionListenerToAllButtons(controller);
    }

    private void createUI(JFrame frame) {
        this.setLayout(new GridLayout(2,6));
        this.add(gasPanel, 0);
        this.add(gasButton, 1);
        this.add(addCar, 2);
        this.add(extendTrayButton, 3);
        this.add(startButton, 4);

        this.add(carSpinner, 5);
        this.add(brakeButton, 6);
        this.add(removeCar, 7);
        this.add(retractTrayButton, 8);
        this.add(stopButton, 9);

        this.setPreferredSize(new Dimension((frame.getWidth())-100, 200));
        this.setBackground(Color.CYAN);
    }


    private void createButton(JFrame frame, JButton startButton, Color blue, Color green) {
        startButton.setBackground(blue);
        startButton.setForeground(green);
        startButton.setPreferredSize(new Dimension(frame.getWidth() / 5 - 15, 200));
    }


    private void addActionListenerToAllButtons(Controller controller) {
        gasButton.addActionListener(e -> ModelUpdate.gas(amount));

        brakeButton.addActionListener(e -> ModelUpdate.brake(amount));

        startButton.addActionListener(e -> ModelUpdate.start());

        stopButton.addActionListener(e -> ModelUpdate.stop());


        addCar.addActionListener(e -> {
            CarType selectedCarType = (CarType) carSpinner.getValue();
            ModelUpdate.addCar(selectedCarType);
        });
        removeCar.addActionListener(e -> ModelUpdate.removeCar());
    }


    private void createGasPanel() {
        gasPanel.setLayout(new BorderLayout());
        gasPanel.add(gasLabel, BorderLayout.PAGE_START);
        gasPanel.add(amountSpinner, BorderLayout.PAGE_END);
    }


    private void createSpinnerModel() {
        SpinnerModel spinnerModel =
                new SpinnerNumberModel(0, //initial value
                        0, //min
                        100, //max
                        1);//step
        amountSpinner = new JSpinner(spinnerModel);
        amountSpinner.addChangeListener(e -> amount = (int) ((JSpinner)e.getSource()).getValue());
    }
    // -- CONTROLLER UI --

    public void addControlPanelToFrame(JFrame frame){
        initUI(frame, this);
        frame.add(this);
    }

}
