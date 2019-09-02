package com.revolut.moneytransfer.test.bankAccount.model.dto;

import static com.revolut.moneytransfer.test.util.AppLogger.debug;
import static com.revolut.moneytransfer.test.util.AppLogger.error;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.revolut.moneytransfer.test.bankAccount.model.BankAccountInterface;
import com.revolut.moneytransfer.test.bankAccount.model.beans.BankAccount;
import com.revolut.moneytransfer.test.datastore.Datastore;
import com.revolut.moneytransfer.test.exceptions.AccountNotExistsException;

/**
 * DTO class. This has all the business level operations implementation as per the interface {@link BankAccountInterface}.<br>
 * 
 * @author kamlesh
 *
 */
public class BankAccountDTOMap implements BankAccountInterface {

	private static String ACCOUNT_SUCCESS_INSERT =  "Account successfully inserted into DB. Account No: ";
	
	/**Saves an instance of {@link BankAccount} to the Database
	 * @param account an Instance of  {@link BankAccount}
	 * @return an Instance of  {@link BankAccount} which is saved in DB
	 */
	private BankAccount saveToDB(BankAccount account) {
		Lock courrencyLock = new ReentrantLock();
		courrencyLock.lock();
		
		Map<Object, Object> dataMap = Datastore.getInstance().getMap();
		dataMap.put(account.getId(), account);
		
		courrencyLock.unlock();
		return account;
	}

	/**Reads the account from the database
	 * @param id The {@link BankAccount} instance ID
	 * @return
	 */
	private BankAccount readAccount(long id) {
		Map<Object, Object> dataMap = Datastore.getInstance().getMap();
		Object resultFromMap = dataMap.get(id);
		
		if( resultFromMap instanceof BankAccount ) {
			return (BankAccount) resultFromMap;
		} else {
			error("Unable to find account from DB with ID : " + id);
			return null;
		}

	}
	
	/**Returns true if the account exists else will return false. Note it does not throw a {@link AccountNotExistsException} Exception.
	 *
	 */
	public boolean accountExists(BankAccount account) {
		return readAccount(account.getId()) != null;
	}
	

	/**
	 *Checks if the {@link BankAccount} with the prescribe id exists in the database. throws a runtime exception {@link AccountNotExistsException} if the account does not exists.<br>
	 */
	public BankAccount getAccountWithExp(long id) {
		BankAccount fetchedAccount = readAccount(id);
		if (fetchedAccount != null) {
			debug("Instace with ID: "+ fetchedAccount.getId() + "Found in DB");
			return fetchedAccount;
		}
		throw new AccountNotExistsException(id);
	}

	/**
	 *Checks the account if already exists and if not inserts into the db.<br>
	 *
	 *Throws a runtime exception if the account already exists.
	 */
	public BankAccount createNewAccount(BankAccount account) {
		if( !accountExists(account)) {
			
			 BankAccount savedaccount = saveToDB(account);
			 debug(ACCOUNT_SUCCESS_INSERT + account.getId());
			 
			 return savedaccount;
		}else {
			throw(new RuntimeException("Account already existis"));
		}
	}

	/**
	 *Fetches all the bank accounts from the database. 
	 */
	public   Collection<BankAccount>  getAllBankAccounts() {
		Map<Object, Object> dataMap = Datastore.getInstance().getMap();
		
		Set<Object> entrySet = dataMap.keySet();
		List<BankAccount> resultConnection = new ArrayList<>();
		for(Object key: entrySet) {
			if(key instanceof Long) {
				Long id = (Long) key;	
				Object dataObject = dataMap.get(id);
				
				if(dataObject instanceof BankAccount) {
					resultConnection.add((BankAccount) dataObject);
				}
	
				
			}
			
		}
		
		debug("Fetched all Accounts from DB. Number of accounts: " + resultConnection.size());
		return resultConnection;
	}

	/**Updates a bank account. Uses ReentrantLock to hanlde concurrency.
	 *
	 */
	@Override
	public void updateBankAccount(BankAccount bankAccount) {
			
			Lock concurrency = new ReentrantLock();
			concurrency.lock();
			/**
			 * This is done so that A runtime exception will be thrown if the account does not exists
			 */
			getAccountWithExp(bankAccount.getId());
			Map<Object, Object> dataMap = Datastore.getInstance().getMap();
			dataMap.put(bankAccount.getId(),bankAccount);

			concurrency.unlock();	
			debug("Update From Account successfull for ID : "  + bankAccount.getId());
	}

}
