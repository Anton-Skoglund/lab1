package renderEngine.Model;

import assets.elements.Element;
import assets.elements.Vehicle;
import assets.elements.vehicles.cars.VehicleFactory;
import renderEngine.Controller.CarType;
import renderEngine.ElementsToRender;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static renderEngine.Controller.CarType.NO_CAR;

public class ModelUtilities {
    static Vehicle createVehicle(CarType carType) {
        // Use the CarFactory to create the specified car type
        return switch (carType) {
            case SAAB -> VehicleFactory.createSaab();
            case VOLVO -> VehicleFactory.createVolvo();
            case SCANIA -> VehicleFactory.createScania();
            default -> throw new IllegalArgumentException("Unknown car type: " + carType);
        };
    }

    static Vehicle createGivenCar(CarType car, ElementsToRender elementsToRender) {
        Vehicle currentCar;
        currentCar = createVehicle(car);
        elementsToRender.add(currentCar);
        return currentCar;
    }

    static <T> List<T> getListWithout(T[] allCarTypes, Object object) {
        return Arrays.stream(allCarTypes)
                .filter(carType -> carType != object)
                .collect(Collectors.toList());
    }


    static Vehicle getRandomCar(ElementsToRender elementsToRender) {
        Vehicle currentCar;
        CarType[] allCarTypes = CarType.values();

        // Filter out NO_CAR
        List<CarType> carTypesWithoutNoCar = getListWithout(allCarTypes, NO_CAR);

        CarType randomCar = getRandomElement(carTypesWithoutNoCar);
        currentCar = createVehicle(randomCar);
        elementsToRender.add(currentCar);
        return currentCar;
    }

    static <T> T getRandomElement(List<T> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List is null or empty");
        }

        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    static void setRandomElementAngle(Element element) {
        element.setRotation(getRandomFloat(0, 360));
    }


    static float getRandomFloat(float start, float stop){
        Random random = new Random();
        return (stop - start) * random.nextFloat() + start;
    }
}
