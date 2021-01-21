package junit5;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;
    private Calculator calculatorDos;

    /* Esta etiqueta nos permite definir un metodo que se ejecutará antes de cada test
    * Lo utilizamos para instanciar nuestro objeto calculator
     */
    @BeforeEach
    public void setUp(){
        calculator = new Calculator();
        System.out.println("@BeforeEach -> setUp()");
    }
    /* Esta etiqueta nos permite definir un metodo que se ejecutará despues de cada test
     * Lo utilizamos para liberar recursos
     */
    @AfterEach
    public void tierDown(){
        calculator = null;
        System.out.println("@AfterEach -> tierDown()");
    }

    @Test
    public void calculatorNotNullTest(){
        assertNotNull(calculator, "Calculator no debe ser nulo");
        System.out.println("@Test -> calculatorNotNullTest()");

    }
    @Test
    public void calculatorNullTest(){
        assertNull(calculatorDos, "Calculator debe ser nulo");
        System.out.println("@Test -> calculatorNullTest()");

    }

    @Test
    public void addTest(){
        assertEquals( 30, calculator.add(20, 10));
        System.out.println("@Test -> addTest()");
    }

    @Test
    public void subtractTest(){
        assertEquals( 10, calculator.subtract(20, 10));
        System.out.println("@Test -> subtractTest()");
    }

    @Test
    public void divideTest(){
        assertEquals(5, calculator.divide(10,2));
        System.out.println("@Test -> divideTest()");
    }
    /*
    Mediante esta etiqueta nombramos los test
     */
    @DisplayName("División entre 0")
    @Test
    public void divideByZeroTest(){
        assertThrows(ArithmeticException.class, ()->calculator.divide(10, 0), "No se puede dividir entre 0");
        System.out.println("@Test -> divideByZeroTest()");
    }
    /*
    Con esta etiqueta deshabilitamos los test, indicando la razón de esto
   */
    @Disabled("Test repetido")
    @Test
    public void divideTest2(){
        assertEquals(5, calculator.divide(10,2));
        System.out.println("@Test -> divideTest()");
    }
}