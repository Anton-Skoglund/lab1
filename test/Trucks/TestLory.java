package Trucks;

import Cars.Saab95;
import Cars.Volvo240;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestLory {
    //TODO
    // - Change the setInStorage from tryToPickUp for better testing
    // - Fix Lory and Scania pick up.
    // -

    Lory lory;
    Volvo240 volvo1;
    Volvo240 volvo2;
    Volvo240 volvo3;
    Saab95 saab1;
    Saab95 saab2;
    Scania scania;
    Lory loryTryToPickUp;

    @Before
    public void init(){
        lory = new Lory();

        volvo1 = new Volvo240();
        volvo2 = new Volvo240();
        volvo3 = new Volvo240();
        saab1 = new Saab95();
        saab2 = new Saab95();

        scania = new Scania();
        loryTryToPickUp = new Lory();
    }
    @Test
    public void testRampIsDownTrue() {
        assertTrue(lory.rampIsDown());
    }
    @Test
    public void testRampIsDownFalse() {
        lory.tiltUp();
        assertFalse(lory.rampIsDown());
    }
    @Test
    public void testIsStorageFullTrue(){
        lory.placeMotorVehicleInStorage(volvo1);
        lory.placeMotorVehicleInStorage(volvo2);
        lory.placeMotorVehicleInStorage(volvo3);
        lory.placeMotorVehicleInStorage(saab1);
        lory.placeMotorVehicleInStorage(saab2);
        assertTrue(lory.isStorageFull());
    }
    @Test
    public void testIsStorageFullFalse(){
        lory.placeMotorVehicleInStorage(volvo1);
        lory.placeMotorVehicleInStorage(volvo2);

        assertFalse(lory.isStorageFull());
    }
    @Test
    public void testIsStorageEmptyTrue(){
        assertTrue(lory.isStorageEmpty());
    }
    @Test
    public void testIsStorageEmptyFalse(){
        lory.placeMotorVehicleInStorage(volvo1);
        lory.placeMotorVehicleInStorage(volvo2);

        assertFalse(lory.isStorageEmpty());
    }
    @Test
    public void testLastIndexInStorage(){
        lory.placeMotorVehicleInStorage(volvo1);
        lory.placeMotorVehicleInStorage(volvo2);
        assertEquals(lory.lastIndexInStorage(), 1);
    }

    @Test
    public void testCheckMotorVehiclesNeraYouTrueIfSameCoordinate(){
        double[] loryPosition = lory.getPosition();
        double loryX = loryPosition[0];
        double loryY = loryPosition[1];
        double[] otherPosition = volvo1.getPosition();
        double otherX = otherPosition[0];
        double otherY = otherPosition[1];
        assertTrue(lory.checkMotorVehiclesNeraYou(loryX, loryY, otherX, otherY));

    }

    @Test
    public void testCheckMotorVehiclesNeraYouTrueIfWithInPickUpRange(){
        lory.setPositon(new double[]{100, 100});
        volvo1.setPositon(new double[]{105, 95});

        double[] loryPosition = lory.getPosition();
        double loryX = loryPosition[0];
        double loryY = loryPosition[1];
        double[] otherPosition = volvo1.getPosition();
        double otherX = otherPosition[0];
        double otherY = otherPosition[1];
        assertTrue(lory.checkMotorVehiclesNeraYou(loryX, loryY, otherX, otherY));

    }
    @Test
    public void testCheckMotorVehiclesNeraYouFalse(){
        lory.setPositon(new double[]{100, 100});
        volvo1.setPositon(new double[]{115, 80});

        double[] loryPosition = lory.getPosition();
        double loryX = loryPosition[0];
        double loryY = loryPosition[1];
        double[] otherPosition = volvo1.getPosition();
        double otherX = otherPosition[0];
        double otherY = otherPosition[1];
        assertFalse(lory.checkMotorVehiclesNeraYou(loryX, loryY, otherX, otherY));
    }

    @Test
    public void testTakeOutMotorVehicleRampNotDown(){
        lory.placeMotorVehicleInStorage(volvo1);
        lory.tiltUp();
        lory.takeOutMotorVehicle();
        assertFalse(lory.isStorageEmpty());
    }
    @Test
    public void testTakeOutMotorVehicleStorageEmpty(){
        lory.takeOutMotorVehicle();
        assertTrue(lory.isStorageEmpty());
    }

    @Test
    public void testPickUpMotorVehicleRampNotDown(){
        lory.tiltUp();
        lory.PickUpMotorVehicle();
        assertTrue(lory.isStorageEmpty());
    }
    /*
    @Test
    public void testPickUpMotorVehicleLory(){
        lory.PickUpMotorVehicle();
        assertTrue(lory.isStorageEmpty());
    }
    @Test
    public void testPickUpMotorVehicleScania(){
        lory.PickUpMotorVehicle();
        assertTrue(lory.isStorageEmpty());
    }
    */

}
