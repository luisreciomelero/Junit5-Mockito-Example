package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

public class AddTest {
    //Este objeto recibirá mocks
    @InjectMocks
    private Add add;
    // Este objeto será un mock
    @Mock
    private ValidNumber validNumber;

    /*
     * Para inicializar nuestro objeto y nuestro mock
     */
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    /*
        Patron When-ThenReturn.
        Definimos como funcionara el mock. Verificamos que funciona
        el patron definiendo el comportamiento, lo ponemos mal a proposito

     */
    @Test
    public void addTest(){
        when(validNumber.check(3)).thenReturn(false);
        boolean checkNumber = validNumber.check(3);
        assertEquals(false , checkNumber);

        when(validNumber.check("a")).thenReturn(true);
        checkNumber = validNumber.check("a");
        assertEquals(true , checkNumber);
    }
     /*
        Patron When-ThenThrow.
        Definimos como funcionara el mock.
        Mockeamos una exception en este caso
     */
    @Test
    public void addMockExceptionTest(){
        when(validNumber.checkZero(0)).thenThrow(new ArithmeticException("No puede ser cero"));
        Exception exception = null;
        try{
            validNumber.checkZero(0);
        }catch (ArithmeticException e){
            exception = e;
        }
        assertNotNull(exception);
    }
    /*
        Patron When-ThenCallRealMethod.
        Definimos como funcionara el mock.
        Mockeamos una llamada al metodo real en este caso
     */
    @Test
    public void addRealMethodTest(){
        when(validNumber.check(3)).thenCallRealMethod();
        assertEquals(true, validNumber.check(3));
        when(validNumber.check("3")).thenCallRealMethod();
        assertEquals(false, validNumber.check("3"));
    }

     /*
        Patron Then-Answer
        Definimos como funcionara el mock.
        Nos permite realizar respuestas más complejas
     */
    @Test
    public void doubleToIntThenAnswerTest(){
        Answer<Integer> answer = new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {
                return 7;
            }
        };
        /*
         * En este caso estamos diciendole al mock que cuando le llamen con el valor
         * 7.7 devuelva un 7.
         * AddInt internamente realizará la suma de las 2 llamadas a doubleToInt de 7.7
         * la suma de 7+7.
         * Si llamamos a add.addInt con cualquier otro numero, como no está especificado en
         * el mock la respuesta para ese numero
         */

        when(validNumber.doubleToInt(7.7)).thenAnswer(answer);
        assertEquals(14, add.addInt(7.7));

    }

    /*
     * Hay 2 patrones o esquemas para hacer los test con mockito:
     * 1. Arrange, Act, Assert
     * 2. Given, When, Then
     * Se corresponden con la inicialización o setUp,
     * la acción que se va a comprobar y
     * la comprobación
     */
// pattern 1.
    @Test
    public void patternTest(){
        //Arrange
        when(validNumber.check(4)).thenReturn(true);
        when(validNumber.check(5)).thenReturn(true);
        //Act
        int result = add.add(4,5);
        // Assert
        assertEquals(9, result);
    }
// pattern 2.
    @Test
    public void patternGWTTest(){
        //Arrange
        given(validNumber.check(4)).willReturn(true);
        given(validNumber.check(5)).willReturn(true);
        //Act
        int result = add.add(4,5);
        // Assert
        assertEquals(9, result);
    }

}
