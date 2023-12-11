import assets.elements.vehicles.motorVehicles;
import assets.elements.vehicles.boats.motorizedBoats.Ferry2000;
import assets.elements.vehicles.cars.trucks.ScaniaL280;
import assets.storages.StorageStack;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StorageStackTest {

    StorageStack<Ferry2000> stack;
    motorVehicles scania;
    @Before
    public void setUp(){
        scania = new ScaniaL280();
        stack = new StorageStack<>(scania, 3);
    }

    @Test
    public void contentShouldPopAtPosition(){
        Ferry2000 f = new Ferry2000(4, 4);
        stack.add(f);
        stack.pop(new double[]{5,5});
        assertEquals(5, f.getPosition()[0], 0.001);
    }

    @Test
    public void contentShouldPopTop(){
        Ferry2000 f1 = new Ferry2000(4, 4);
        Ferry2000 f2 = new Ferry2000(3, 3);
        stack.add(f1);
        stack.add(f2);
        Ferry2000 poppedF = stack.pop();
        assertEquals(f2, poppedF);
    }

}