package mockito;
/*
 * En esta clase se realizará la creación de otro mock mediante
 * las anotaciones de mockito
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class AddCreateMockTest2 {
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

    @Test
    public  void addTest() {
        add.add(3, 2);

        //Con esto verificamos que se llamó al metodo de verificación de validnumber
        Mockito.verify(validNumber).check(3);

    }
}
