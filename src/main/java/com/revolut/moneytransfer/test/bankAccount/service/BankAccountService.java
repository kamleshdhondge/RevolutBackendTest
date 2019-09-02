package com.revolut.moneytransfer.test.bankAccount.service;

import static com.revolut.moneytransfer.test.util.AppLogger.debug;

import java.util.Collection;
import java.util.Objects;
import java.util.Random;

import com.revolut.moneytransfer.test.bankAccount.factory.BankAccountDTOFactory;
import com.revolut.moneytransfer.test.bankAccount.factory.BankDTOImplEnum;
import com.revolut.moneytransfer.test.bankAccount.model.BankAccountInterface;
import com.revolut.moneytransfer.test.bankAccount.model.beans.BankAccount;
/**This service will be consumed by the controller for all the business level operations related to {@link BankAccount}.
 * <ul>
 * <li>Retrieves all the {@link BankAccount} instances</li>
 * <li>Get  a single the {@link BankAccount} instance by ID</li>
 * <li>Create a single {@link BankAccount} instance and persist in DB</li>
 * </ul>
 * @author kamlesh
 *
 */
public class BankAccountService {
	private BankAccountInterface  bankDTO = null;

	/**Constructor for {@link BankAccountService}. needs to pass the DTO {@link BankDTOImplEnum} for Implementor of DBs
	 * @param dtoImplementorEnum {@link BankDTOImplEnum}
	 */
	public BankAccountService(BankDTOImplEnum dtoImplementorEnum) {
		bankDTO = BankAccountDTOFactory.getInstance(dtoImplementorEnum);
		debug("Bank Account Service Instiated successfully");
	}

	/**Retrieves all the {@link BankAccount} instances
	 * @return
	 */
	public Collection<BankAccount> getAllBankAccounts() {
		debug("Initiating fetch all BankAccounts");
		
		return bankDTO.getAllBankAccounts();
	}

	/**returns the  {@link BankAccount}  searching with the id. 
	 * @param id the ID to locate the account
	 * @return an fetched instance of {@link BankAccount}
	 */
	public BankAccount getBankAccountById(Long id) {
		debug("Initiating fetch  BankAccount for ID: " + id);
		
		return bankDTO.getAccountWithExp(id);
	}



	/**Creates a new bank account instance and saves to the DB.
	 * @param bankAccount an  instance of {@link BankAccount}
	 * @return
	 */
	public BankAccount createBankAccount(BankAccount bankAccount)  {
		debug("Initiating create bankAccount");
		
		validate(bankAccount);
		return bankDTO.createNewAccount(bankAccount);
	}

	/**Validates the bank account whether it is correct instance. Checks for the account number which is mandatory to create a bank account
	 * @param bankAccount an instance of {@link BankAccount}
	 */
	private void validate(BankAccount bankAccount) {
		Objects.requireNonNull(bankAccount.getAccountName(), "Please enter the account owner name" );
		if(bankAccount.getId() == null) {
			bankAccount.setId(new Random().nextLong());
		}
		debug("Bank account instance validated successfully");

	}
}
