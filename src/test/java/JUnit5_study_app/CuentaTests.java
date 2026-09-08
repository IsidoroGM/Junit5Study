package JUnit5_study_app;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class CuentaTests {

    
    @Test
    void testNombreCuenta(){

        //Con este test, vamos a probar setPersona y el constructor a la vez.

        Cuenta cuenta=new Cuenta("Andres", new BigDecimal("1000.5655"));
        cuenta.setPersona("Andres");
        String esperado= "Andres";
        String real=cuenta.getPersona();

        //Assert comprueba la afirmacion de un valor
        //En este caso, que el valor esperado y el real sean iguales.
        assertEquals(esperado, real);

        //assertTrue comprueba que un valor sea True
        assertTrue(real.equals("Andres"));
    }

    //Test para comprobar el saldo
    @Test
    void testSaldoCuenta(){
        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.5655"));

        //probamos con assertEquals el saldo
        assertEquals(1000.5655, cuenta.getSaldo().doubleValue());

        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }
}
