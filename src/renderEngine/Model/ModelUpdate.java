package renderEngine.Model;

import assets.elements.ActiveElement;
import assets.elements.Element;
import renderEngine.View.View;

import java.util.ArrayList;
import javax.swing.*;


public class ModelUpdate implements ModelObserver {
    static protected ArrayList<Element> elementsOnScreen;

    private static int frameWidth = View.width;
    private static int frameHeight = View.height;
    private int controllerHeight = 200;
    private int widthOfCar = 100;
    private int heightOfCar = 60;

    private int minX = 0;
    private int maxX = frameWidth - widthOfCar;

    private int minY = 0;
    private int maxY = frameHeight - controllerHeight - heightOfCar;

    public ModelUpdate(ArrayList<Element> elementsOnScreen){
        this.elementsOnScreen = elementsOnScreen;
    }

    @Override
    public void update(){
        for (Element motorVehicle : elementsOnScreen) {
            if (motorVehicle instanceof ActiveElement) {
                if (motorVehicle.getPosition()[0] > maxX || motorVehicle.getPosition()[0] < minX) {
                    double rot = motorVehicle.getRotation();
                    motorVehicle.setRotation(0 - rot);
                }

                if (motorVehicle.getPosition()[1] > maxY || motorVehicle.getPosition()[1] < minY) {
                    double rot = motorVehicle.getRotation();
                    motorVehicle.setRotation(180 - rot);
                }
                ((ActiveElement) motorVehicle).moveTick();
            }
        }
    }
}
