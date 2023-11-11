package Cars;

import java.awt.*;

public class Volvo240 extends Car {

    public Volvo240(){
        super(4,Color.black, 100, "Cars.Volvo240");
        stopEngine();
    }

    protected double speedFactor(){
        double trimFactor = 1.25;
        return getEnginePower() * 0.01 * trimFactor;
    }
}
