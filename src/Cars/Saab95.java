package Cars;
import MotorVehicle.*;

import java.awt.*;

public class Saab95 extends Car {
    public boolean turboOn;

    public Saab95(){
        super(  2,
                Color.red,
                125,
                "Cars.Saab95");
        setTurboOff();
        stopEngine();
    }

    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }

    protected double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}
