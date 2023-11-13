import Cars.TestSaab;
import Cars.TestVolvo;
import MotorVehicle.TestMotorVehicle;
import Trucks.TestLory;
import Trucks.TestScania;
import Trucks.TestTruck;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
@RunWith(Suite.class)
@Suite.SuiteClasses({   TestMotorVehicle.class,

                        TestVolvo.class,
                        TestSaab.class,

                        TestTruck.class,
                        TestScania.class,
                        TestLory.class})

public class TestSuite {

}
