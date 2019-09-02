package com.revolut.moneytransfer.test.bankAccount.model.beans;

import java.math.BigDecimal;
import java.util.Random;




/**Bean class to save account realted info. Consists of an ID, Name of the account owner and Current Balance
 * @author kamlesh
 *
 */
public class BankAccount {

    private Long id;
    private String accountName;
    private BigDecimal currentBalance = BigDecimal.ZERO;
    
    public BankAccount() {
    }

    
    public BankAccount(String accountName) {
        this(new Random().nextLong(), accountName);
    }


    public BankAccount(Long id, String accountName) {
        this.id = id;
        this.accountName = accountName;
    }


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		 this.id =  id;
	}



	public String getAccountName() {
		return accountName;
	}


	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}


	public BigDecimal getCurrentBalance() {
		return currentBalance;
	}


	public void setCurrentBalance(BigDecimal currentBalance) {
		this.currentBalance = currentBalance;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + ((currentBalance == null) ? 0 : currentBalance.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BankAccount [id=");
		builder.append(id);
		builder.append(", accountName=");
		builder.append(accountName);
		builder.append(", currentBalance=");
		builder.append(currentBalance);
		builder.append("]");
		return builder.toString();
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (currentBalance == null) {
			if (other.currentBalance != null)
				return false;
		} else if (!currentBalance.equals(other.currentBalance))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}


