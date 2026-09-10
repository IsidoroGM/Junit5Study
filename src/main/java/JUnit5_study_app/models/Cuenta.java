package JUnit5_study_app.models;

import java.math.BigDecimal;

import JUnit5_study_app.exeptions.dineroInsuficienteExceptions;

public class Cuenta {

    private Banco banco;
    private String persona;
    private BigDecimal saldo;

    public Cuenta() {
    }

    public Cuenta(String persona, BigDecimal saldo) {
        this.persona = persona;
        this.saldo = saldo;
    }

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }


    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void debito(BigDecimal monto){
        BigDecimal nuevoSaldo=this.saldo.subtract(monto);

        //Implementamos el manejo de excepciones
        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0){
            throw new dineroInsuficienteExceptions("Dinero insuficiente");
        }

        //Si no ocurre la excepción, el saldo será el actualizado tras el metodo subtract
        this.saldo=nuevoSaldo;

    }

    public void credito(BigDecimal monto){
        this.saldo=this.saldo.add(monto);

    }

    @Override
    public boolean equals(Object obj) {
        
        if(!(obj instanceof Cuenta)){
            return false;
        }

        Cuenta c= (Cuenta) obj;
        if(this.persona == null || this.saldo == null){
            return false;
        }

        return this.persona.equals(c.getPersona()) && this.saldo.equals(c.getSaldo());
    }

    
    

}
