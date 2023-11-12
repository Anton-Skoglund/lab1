package Cars;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class TestVolvo {
    Volvo240 volvo;
    @Before
    public void before(){
        volvo = new Volvo240();
    }
    @Test
    public void testSpeedFactor(){
        assertEquals(100 * 0.01 * 1.25, volvo.speedFactor(), 0);
    }
}
