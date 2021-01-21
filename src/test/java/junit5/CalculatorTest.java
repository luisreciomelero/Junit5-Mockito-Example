package junit5;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;
    private Calculator calculatorDos;

    /*
        Esta etiqueta define un metodo que se va a ejecutar una única vez y antes de todo, debe ser static
     */
    @BeforeAll
    public static void beforeAllTest(){
        System.out.println("@BeforeAll -> beforeAllTest()");
    }

    /*
        Esta etiqueta define un metodo que se va a ejecutar una única vez y después de todo, debe ser static
     */
    @AfterAll
    public static void afterAllTest(){
        System.out.println("@afterAll -> afterAllTest()");
    }


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

    /*
         A traves de assertAll obligaremos al test a ejecutar todos los asserts aunque alguno falle
     */
    @Test
    public void assertAllTest(){
        assertAll(
                ()->assertEquals(5, calculator.add(4,1)),
                ()->assertEquals(5, calculator.mult(2,3)),
                ()->assertEquals(5, calculator.divide(10,3))
        );
    }

    @Nested
    class addMethodTest{
        @Test
        public void addPositiveTest(){
            assertEquals( 30, calculator.add(20, 10));
        }
        @Test
        public void addNegativeTest(){
            assertEquals( -30, calculator.add(-20, -10));
        }
        @Test
        public void addPositiveNegativeTest(){
            assertEquals( 0, calculator.add(15, -15));
        }
    }

    /*
        Cuando queremos realizar una bateria de pruebas de un mismo método,
        tambien es posible realizar pruebas parametrizadas.
        Dependencia Junit-jupiter-params
     */
    @ParameterizedTest(name = "index = a={0}, b={1}, sum={2}")
    @MethodSource("addProviderData")
    public void addParameterizedTest(int a, int b, int sum){
        assertEquals(sum, calculator.add(a,b));
    }

    private static Stream<Arguments> addProviderData(){
        return Stream.of(
                Arguments.of(6,2,8),
                Arguments.of(-6,-2,-8),
                Arguments.of(6,-2,4),
                Arguments.of(-6,2,-4),
                Arguments.of(6,0,6)
        );
    }

    /*
       Timeout Test
     */
    @Test
    public void timeoutTest(){
        assertTimeout(Duration.ofMillis(2000), () -> calculator.longTaskOperation());
    }

}