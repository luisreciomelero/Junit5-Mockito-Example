package mockito;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidNumberTest {

    private ValidNumber validNumber;

    @BeforeEach
    public void setUp(){
        validNumber = new ValidNumber();
    }

    @AfterEach
    public void tearDown(){
        validNumber = null;
    }

    @Test
    public void checkNegativeTest(){
        assertEquals(false, validNumber.check(-5));
    }

    @Test
    public void checkStringTest(){
        assertEquals(false, validNumber.check("a"));
    }

    @Test
    public void checkZeroTest(){
        assertEquals(true, validNumber.checkZero(-2));
    }
    @Test
    public void checkZeroStringTest(){
        assertEquals(false, validNumber.checkZero("0"));
    }
    @Test
    public void checkZero0Test(){
        assertThrows(ArithmeticException.class, ()->validNumber.checkZero(0));
    }

    @Test
    public void doubleToIntTest(){
        assertEquals(9, validNumber.doubleToInt(9.99));
    }
    @Test
    public void doubleToIntTErrorTest(){
        assertEquals(0, validNumber.doubleToInt("0"));
    }


}