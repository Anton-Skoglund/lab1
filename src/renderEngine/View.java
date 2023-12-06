package renderEngine;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the full view of the MVC pattern of your car simulator.
 * It initializes with being center on the screen and attaching it's controller in it's state.
 * It communicates with the Controller by calling methods of it when an action fires of in
 * each of it's components.
 * TODO: Write more actionListeners and wire the rest of the buttons
 **/
//TEST
public class View extends JFrame implements ModelObserver {
    private static final int X = 800;
    private static final int Y = 800;
    // The controller member
    private GameView gameView;
    private Controller controller;

    public View(String frameName, Controller controller){
        this.controller = controller;
        //TODO
        // - controller.cars???
        this.gameView = new GameView(X, Y-240, controller.motorVehicles);
        setupView(frameName);
        this.add(gameView);
        controller.initControlPanel(this);
        controller.addControlPanelToFrame(this);
    }

    @Override
    public void update() {
        //System.out.println("(CAR VIEW) UPDATE IN");
        this.gameView.repaint();
    }

    public int getX(){
        return X;
    }
    public int getY(){
        return Y;
    }

    // Sets everything in place and fits everything
    // TODO: Take a good look and make sure you understand how these methods and components work
    private void setupView(String title) {
        this.setTitle(title);
        this.setPreferredSize(new Dimension(X,Y));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setUpFrame();
    }

    private void setUpFrame() {
        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();
        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}