package JUnit5_study_app;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import JUnit5_study_app.exeptions.dineroInsuficienteExceptions;
import JUnit5_study_app.models.Banco;
import JUnit5_study_app.models.Cuenta;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

public class CuentaTests {

    @Test
    @DisplayName ("Probando nombre de la cuenta")
    void testNombreCuenta() {

        // Con este test, vamos a probar setPersona y el constructor a la vez.

        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.5655"));
        cuenta.setPersona("Andres");
        String esperado = "Andres";
        String real = cuenta.getPersona();
        assertNotNull(real);

        // Assert comprueba la afirmacion de un valor
        // En este caso, que el valor esperado y el real sean iguales.
        assertEquals(esperado, real);

        // assertTrue comprueba que un valor sea True
        assertTrue(real.equals("Andres"), "el nombre cuenta instancia debe ser igual a la real");
    }

    // Test para comprobar el saldo
    @Test
    @DisplayName ("Test para comprobar el saldo")
    void testSaldoCuenta() {
        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.5655"));

        // probamos con assertEquals el saldo
        assertEquals(1000.5655, cuenta.getSaldo().doubleValue());

        assertNotNull(cuenta.getSaldo());

        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    @DisplayName ("testeando referencias que sean iguales con el metodo equals")
    void testReferenciaCuenta() {

        Cuenta cuenta = new Cuenta("Jhon Doe", new BigDecimal("8900.9999"));
        Cuenta cuenta2 = new Cuenta("Jhon Doe", new BigDecimal("8900.9999"));

        // assertNotEquals(cuenta2 , cuenta);

        // assertEquals da error porque apunta a referencias distintas, y debemos
        // refactorizar.
        // Para ello, hemos sobrescrito el metodo equals en la clase Cuenta
        assertEquals(cuenta2, cuenta);
    }

    @Test
    void testDebitoCuenta() {

        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.5655"));
        cuenta.debito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(900, cuenta.getSaldo().intValue());
        assertEquals("900.5655", cuenta.getSaldo().toPlainString());
    }

    @Test
    void testCreditoCuenta() {

        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.5655"));
        cuenta.credito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1100, cuenta.getSaldo().intValue());
        assertEquals("1100.5655", cuenta.getSaldo().toPlainString());
    }

    @Test
    void testDineroInsuficienteExteptionCuenta() {
        Cuenta cuenta = new Cuenta("Andres", new BigDecimal("1000.6566"));

        // Comprobamos la exception
        Exception exception = assertThrows(dineroInsuficienteExceptions.class, () -> {
            cuenta.debito(new BigDecimal(1500));
        });

        // tambien comprobamos el mensaje de error
        String actual = exception.getMessage();
        String esperado = "Dinero insuficiente";
        assertEquals(esperado, actual);
    }

    @Test
    void testTransferenciaEntreCuentas() {
        Cuenta cuenta1 = new Cuenta("Jhon Doe", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Andres", new BigDecimal("1500.5655"));

        Banco banco = new Banco();
        banco.setNombre("Revolut");
        banco.transferir(cuenta2, cuenta1, new BigDecimal(500));

        assertEquals("1000.5655", cuenta2.getSaldo().toPlainString());
        assertEquals("3000", cuenta1.getSaldo().toPlainString());
    }

    @Test
    @DisplayName ("Probando relaciones entre las cuentas y el banco con assertAll.")
    @Disabled 
    void testRelacionBancoCuentas() {
        fail(()-> ("Fallo intencional"));
        
        Cuenta cuenta1 = new Cuenta("Jhon Doe", new BigDecimal("2500"));
        Cuenta cuenta2 = new Cuenta("Andres", new BigDecimal("1500.5655"));

        Banco banco = new Banco();
        banco.addCuenta(cuenta1);
        banco.addCuenta(cuenta2);

        banco.setNombre("Revolut");
        banco.transferir(cuenta2, cuenta1, new BigDecimal(500));

        assertAll(() -> assertEquals("1000.5655", cuenta2.getSaldo().toPlainString(),
                        ()-> "el valor del saldo de la cuenta2 no es el esperado."),
                () -> assertEquals("3000", cuenta1.getSaldo().toPlainString(),
                        ()-> "El valor del saldo de la cuenta1 no es el esperado"),
                () -> assertEquals("Revolut", cuenta1.getBanco().getNombre(),
                        ()->""),
                () -> // Comprobamos que la cuenta pertenezca a un usuario
                    assertEquals("Andres", banco.getCuentas().stream()
                            .filter(c -> c.getPersona().equals("Andres"))
                            .findFirst()
                            .get().getPersona()),
                () -> assertTrue(banco.getCuentas().stream()
                            .anyMatch(c -> c.getPersona().equals("Jhon Doe"))));

    }

}
