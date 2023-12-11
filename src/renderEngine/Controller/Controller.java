package renderEngine.Controller;
import java.awt.*;

import renderEngine.Model.InputLogic;
import renderEngine.Model.ModelUpdate;
import renderEngine.View.View;

import javax.swing.*;

/**
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in an appropriate manner by
* modifying the model state and the updating the view.
 **/

public class Controller extends JPanel{
    private int height = View.controllerHeight;
    private JPanel gasPanel = new JPanel();
    private JSpinner amountSpinner = new JSpinner();
    private int amount = 0;
    private JLabel gasLabel = new JLabel("Gas/Brake #");
    private JButton gasButton = new JButton("Gas");
    private JButton brakeButton = new JButton("Brake");
    private JButton addCar = new JButton("+Car");
    private JButton removeCar = new JButton("-Car");

    private JSpinner carSpinner = new JSpinner(new SpinnerListModel(CarType.values()));
    private CarType selectedCarType = CarType.NO_CAR;
    private JButton extendTrayButton = new JButton("Extend Tray");
    private JButton retractTrayButton = new JButton("Retract Tray");
    private JButton turboOnButton = new JButton("Turbo On");
    private JButton turboOffButton = new JButton("Turbo Off");

    private JButton startButton = new JButton("Start all cars");
    private JButton stopButton = new JButton("Stop all cars");
    @Override
    public int getHeight(){
        return height;
    }

    public void addControlPanelToFrame(JFrame frame){
        initUI(frame, this);
        frame.add(this);
    }

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
        this.add(gasPanel,          0);
        this.add(gasButton,         1);
        this.add(addCar,            2);
        this.add(extendTrayButton,  3);
        this.add(turboOnButton,     4);
        this.add(startButton,       5);

        this.add(carSpinner,        6);
        this.add(brakeButton,       7);
        this.add(removeCar,         8);
        this.add(retractTrayButton, 9);
        this.add(turboOffButton,    10);
        this.add(stopButton,        11);

        this.setPreferredSize(new Dimension(View.width, height));
        this.setBackground(Color.CYAN);
    }


    private void createButton(JFrame frame, JButton button, Color background, Color foreground) {
        button.setBackground(background);
        button.setForeground(foreground);
    }


    private void addActionListenerToAllButtons(Controller controller) {
        gasButton.addActionListener(e -> InputLogic.gas(amount));
        brakeButton.addActionListener(e -> InputLogic.brake(amount));

        startButton.addActionListener(e -> InputLogic.start());
        stopButton.addActionListener(e -> InputLogic.stop());

        turboOnButton.addActionListener(e -> InputLogic.turboOn());
        turboOffButton.addActionListener(e -> InputLogic.turboOff());

        extendTrayButton.addActionListener(e -> InputLogic.extendTray());
        retractTrayButton.addActionListener(e -> InputLogic.retractTray());

        addCar.addActionListener(e -> {
            CarType selectedCarType = (CarType) carSpinner.getValue();
            InputLogic.addCar(selectedCarType);
        });
        removeCar.addActionListener(e -> InputLogic.removeCar());
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
}
