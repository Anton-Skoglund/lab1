package renderEngine;

import assets.elements.ActiveElement;
import assets.elements.Element;

import java.util.ArrayList;
import javax.swing.*;

public class ModelUpdate implements ModelObserver{
    private ArrayList<Element> elementsOnScreen;
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
}
