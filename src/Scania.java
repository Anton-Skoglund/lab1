import java.awt.*;

public class Scania extends Truck{
    private double minRampAngel = 0;
    private double maxRampAngel = 70;
    public Scania() {
        super(2, Color.red, 200, "Scania");
    }
    public boolean rampAngelBetweenMinAndMax(double min, double max) {
        // [min, max]
        return false;
    }
    public boolean allowedAngel(double angel) {
        return false;
    }

    public boolean driveAngel(double angel) {
        return false;
    }

    protected double speedFactor(){
        return 0.0;
    }
}
