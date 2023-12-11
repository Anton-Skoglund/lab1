package renderEngine.Controller;

public enum CarType {
    NO_CAR("l"),
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
}