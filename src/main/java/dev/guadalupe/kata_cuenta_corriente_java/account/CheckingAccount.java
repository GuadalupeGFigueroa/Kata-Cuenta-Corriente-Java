package dev.guadalupe.kata_cuenta_corriente_java.account;

import dev.guadalupe.kata_cuenta_corriente_java.account.Account;

public class CheckingAccount extends Account {

    private float overdraft; 
    //El atributo overdraft (sobregiro) permite registrar cuánto dinero se ha retirado más allá del saldo disponible.

    //Constructor
    public CheckingAccount(long id, float balance, float annualRate, float monthlyFee) {
    super(id, balance, annualRate, monthlyFee); //En Java, el operador super se utiliza para hacer referencia a la clase padre de la clase actual. 
    this.overdraft = 0; // Sobregiro inicializado en 0
  }
  //Getters y Setters
  public float getOverdraft() {
    return overdraft;
  }
  //Redefinir el método withdraw para manejar el sobregiro
  public void withdraw(float amount) {
    if (amount > 0) { // Si el importe es mayor de 0
        if (amount <= getBalance()) { // Verificar que el importe sea menor o igual al saldo actual
            setBalance(getBalance() - amount); // Restar el importe al saldo actual después de efectuar el pago
        } else { // Si el importe es mayor al saldo actual, signigifica que no hay saldo suficiente
            float deficit = amount - getBalance(); // Calcular el sobregiro
            overdraft += deficit; // Sumar el sobregiro al atributo overdraft
            setBalance(0); // Establecer el saldo en 0, ya que no hay saldo disponible
        }
        setNumWithdrawals(getNumWithdrawals() + 1); // Incrementar el contador de retiros
    } else { // Si el importe es menor o igual a 0, entonces no se puede realizar el pago porque no es un valor válido.
       throw new IllegalArgumentException("Insufficient balance for this withdrawal."); 
    }
  }
  //Redefinir el método deposit
  @Override
  public void deposit(float amount) {
    if amount > 0 { // Si el importe es mayor de 0
      if (overdraft > 0) { // Si hay sobregiro
        if (amount >= overdraft) { // Si el importe es mayor o igual al sobregiro
          amount -= overdraft; // Restar el sobregiro al importe
          overdraft = 0; // Establecer el sobregiro en 0
        } else { // Si el importe es menor al sobregiro, por lo cual no lo cubre
          overdraft -= amount; // Restar el importe al sobregiro
          amount = 0; // Establecer el importe en 0
        }
      }
      setBalance(getBalance() + amount); // Sumar el importe al saldo actual
      setNumDeposits(getNumDeposits() + 1); // Incrementar el contador de consignaciones
    } else { // Si el importe es menor o igual a 0, entonces no se puede realizar el pago porque no es un valor válido.
       throw new IllegalArgumentException("The deposit amount must be greater than zero."); 
    }
  }

//Redefinir el método generateMonthlyFee
@Override
public String printAccountDetails(){
  return super. printAccountDetails() + "\nOverdraft: $" + overdraft;
}
}

