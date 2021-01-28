package mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

/*
 * En esta clase crearemos un mock que se encargue de testear la clase add
 * sin entrar a validar si validNumber funciona correctamente, de esto se encargarán
 * los test de integracion
 */

public class AddCreateMockitoTest {

    private Add add;
    private ValidNumber validNumber;

    @BeforeEach
    public void setUp(){
        //Creamos un Mock para la clase de la que depende la clase que probamos
        validNumber = Mockito.mock(ValidNumber.class);
        add = new Add(validNumber);
    }

    @Test
    public  void addTest() {
        add.add(3, 2);

        //Con esto verificamos que se llamó al metodo de verificación de validnumber
        Mockito.verify(validNumber).check(3);

        //Comprobamos que por otro valor no ha sido llamado:
        //Mockito.verify(validNumber).check(5);

        /*
         * Por el comportamiento de mockito, si comprobamos el 2 nos devolveria que tampoco ha pasado
         * esto no se debe más que a la lógica de add, que al llegar a chequear el 3 y obtener un false, no continua
         */
    }



}
