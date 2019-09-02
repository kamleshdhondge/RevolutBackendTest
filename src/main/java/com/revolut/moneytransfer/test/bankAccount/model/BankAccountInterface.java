package com.revolut.moneytransfer.test.bankAccount.model;

import java.util.Collection;

import com.revolut.moneytransfer.test.bankAccount.model.beans.BankAccount;
import com.revolut.moneytransfer.test.exceptions.AccountNotExistsException;

/**This is interface class for all account related operations any implementor.  The implementor can change as per DTO implementations<br>
 * Such as MysqlImplementor, PostGreSQL implementor. This interface makes sure that these implementors should have these methods as its required by the busniess logic
 * @author kamlesh
 *
 */
public interface BankAccountInterface {

	/**Checks if the {@link BankAccount} with the prescribe id exists in the database. throws a runtime exception {@link AccountNotExistsException} if the account does not exists.<br>
	 * @param id {@link BankAccount} id
	 * @return {@link BankAccount} instance fetched from DB.
	 */
	public BankAccount getAccountWithExp(long id);
	
	
	/**
	 *Fetches all the bank accounts from the database. 
	 ** @return {@link Collection<BankAccount} of all accounts from DB
	 */
	public Collection<BankAccount>  getAllBankAccounts();

	/**Saves a ew {@link BankAccount} instance into DB. Checks if the account is not already created and then only creates it.<br>
	 **Throws a runtime exception if the account already exists.
	 * @param account instance of {@link BankAccount} to save into DB
	 * @return saved {@link BankAccount} instance
	 */
	public BankAccount createNewAccount(BankAccount account);
	
	/**Checks if the account already exists into DB
	 * @param account an instance of {@link BankAccount}
	 * @return True if the account exists else false
	 */
	public boolean accountExists(BankAccount account);
	
	/** Updates an {@link BankAccount} inside Db
	 * @param bankAccount The instance to update
	 */
	public void updateBankAccount(BankAccount bankAccount);


}
