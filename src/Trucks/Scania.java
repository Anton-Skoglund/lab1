package Trucks;

import java.awt.*;

public class Scania extends Truck{

    public Scania() {
        super(  2,
                Color.red,
                200,
                "Scania",
                0,
                0,
                70,
                10);
    }
    @Override
    protected double speedFactor(){
        return getCurrentSpeed() + 1;
    }
}
