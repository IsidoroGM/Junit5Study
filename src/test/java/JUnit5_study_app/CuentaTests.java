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
        assertNotNull(real);

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

        assertNotNull(cuenta.getSaldo());


        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void testReferenciaCuenta() {

        Cuenta cuenta=new Cuenta("Jhon Doe", new BigDecimal("8900.9999"));
        Cuenta cuenta2=new Cuenta("Jhon Doe", new BigDecimal("8900.9999"));
        
        //assertNotEquals(cuenta2 , cuenta);

        //assertEquals da error porque apunta a referencias distintas, y debemos refactorizar.
        //Para ello, hemos sobrescrito el metodo equals en la clase Cuenta
        assertEquals(cuenta2, cuenta);
    }

    @Test
    void testDebitoCuenta() {
        
        Cuenta cuenta=new Cuenta("Andres", new BigDecimal("1000.5655"));
        cuenta.debito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(900, cuenta.getSaldo().intValue());
        assertEquals("900.5655", cuenta.getSaldo().toPlainString());
    }

    @Test
    void testCreditoCuenta() {
        
        Cuenta cuenta=new Cuenta("Andres", new BigDecimal("1000.5655"));
        cuenta.credito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1100, cuenta.getSaldo().intValue());
        assertEquals("1100.5655", cuenta.getSaldo().toPlainString());
    }

}
