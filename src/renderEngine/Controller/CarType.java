package renderEngine.Controller;

public enum CarType {
    NO_CAR(""),
    SAAB("Saab"),
    VOLVO("Volvo"),
    SCANIA("Scania");

    private final String typeName;

    CarType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public String toString() {
        return typeName;
    }
}