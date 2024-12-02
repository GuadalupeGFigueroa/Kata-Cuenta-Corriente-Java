package dev.guadalupe.kata_cuenta_corriente_java.account;


public class CheckingAccount extends Account {

    private float overdraft; 
   
    public CheckingAccount(long id, float balance, float annualRate, float monthlyFee) {
    super(id, balance, annualRate, monthlyFee);  
    this.overdraft = 0; 
  }
  
  public float getOverdraft() {
    return overdraft;
  }
  
  public void withdraw(float amount) {
    if (amount > 0) { 
        if (amount <= getBalance()) { 
            setBalance(getBalance() - amount); 
        } else { 
            float deficit = amount - getBalance(); 
            overdraft += deficit; 
            setBalance(0); 
        }
        setNumWithdrawals(getNumWithdrawals() + 1); 
    } else { 
       throw new IllegalArgumentException("Insufficient balance for this withdrawal."); 
    }
  }
  
  @Override
  public void deposit(float amount) {
    if (amount > 0) { 
      if (overdraft > 0) { 
        if (amount >= overdraft) { 
          amount -= overdraft; 
          overdraft = 0; 
        } else { 
          overdraft -= amount; 
          amount = 0; 
        }
      }
      setBalance(getBalance() + amount); 
      setNumDeposits(getNumDeposits() + 1); 
    } else { 
       throw new IllegalArgumentException("The deposit amount must be greater than zero."); 
    }
  }


@Override
public String printAccountDetails(){
  return super. printAccountDetails() + "\nOverdraft: $" + overdraft;
}
}

