package renderEngine.Model;

import assets.elements.ActiveElement;
import assets.elements.Element;
import renderEngine.ElementsToRender;
import renderEngine.View.*;

public class ModelUpdate implements ModelObserver {
    private ElementsToRender elementsToRender = ElementsToRender.getInstance();

    private static int frameWidth = View.width;
    private static int frameHeight = View.height;
    private int controllerHeight = 200;
    private int widthOfCar = 100;
    private int heightOfCar = 60;

    private int minX = 0;
    private int maxX = frameWidth - widthOfCar;
    private int minY = 0;
    private int maxY = frameHeight - controllerHeight - heightOfCar;

    private static ModelUpdate INSTANCE;

    /**
     * Singleton
     */
    private ModelUpdate(){}

    public static ModelUpdate getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new ModelUpdate();
        }
        return INSTANCE;
    }

    /**
     * Update forced by ModelObserver.java
     */
    @Override
    public void update(){
        for (Element motorVehicle : elementsToRender) {
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
