public interface Ramp {
    // valde att göra interface för att man kanske vill göra släp
    // som har rampar och inte är en lastbil.

    void tiltUp();
    void tiltDown();

    boolean allowedAngel(double angel);
    boolean driveAngel(double angel);
}
