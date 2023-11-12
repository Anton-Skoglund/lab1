package Trucks;

import java.awt.*;

public class Scania extends Truck{

    public Scania() {
        super(  2, Color.red,
                200,
                "Scania",
                0,
                0,
                70,
                10);
    }
    public void tiltUp() {
        double newAngel = getRampAngel() + getAngelChangeSpeed();
        if (checkRampAngel(newAngel)){
            setRampAngel(newAngel);
        }else{
            printAngelNotAllowed(newAngel, getRampAngel());
        }
    }
    public void tiltDown() {
        double newAngel = getRampAngel() - getAngelChangeSpeed();
        if (checkRampAngel(newAngel)){
            setRampAngel(newAngel);
        }else{
            printAngelNotAllowed(newAngel, getRampAngel());
        }
    }

    protected double speedFactor(){
        return getCurrentSpeed() + 1;
    }
}
