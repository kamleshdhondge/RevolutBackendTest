package com.revolut.moneytransfer.test.transaction.model.dto;

import static com.revolut.moneytransfer.test.util.AppLogger.debug;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.revolut.moneytransfer.test.bankAccount.factory.BankAccountDTOFactory;
import com.revolut.moneytransfer.test.bankAccount.factory.BankDTOImplEnum;
import com.revolut.moneytransfer.test.bankAccount.model.BankAccountInterface;
import com.revolut.moneytransfer.test.bankAccount.model.beans.BankAccount;
import com.revolut.moneytransfer.test.datastore.Datastore;
import com.revolut.moneytransfer.test.exceptions.NotEnoughMoneyException;
import com.revolut.moneytransfer.test.transaction.model.TransactionInterface;
import com.revolut.moneytransfer.test.transaction.model.beans.TransEnum;
import com.revolut.moneytransfer.test.transaction.model.beans.Transaction;

/** DTO class. This has all the business level operations implementation as per the interface {@link TransactionInterface}.<br>
 * 

 * @author kamlesh
 *
 */
public class TransactionDTOMap  implements TransactionInterface{

	private static String transactionIDStart = "TRANS_";
	@Override
	public Collection<Transaction> getAllTransactions() {
		Map<Object, Object> dataMap = Datastore.getInstance().getMap();

		Set<Object> entrySet = dataMap.keySet();
		List<Transaction> resultConnection = new ArrayList<>();
		for(Object key: entrySet) {
			if(key instanceof String) {
				String id = (String) key;	
				Object dataObject = dataMap.get(id);

				if(dataObject instanceof Transaction) {
					resultConnection.add((Transaction) dataObject);
				}


			}

		}
		
		debug("Found number of transactions from DB: " + resultConnection.size());
		return resultConnection;
	}


	

	
	@Override
	public Transaction executeTransaction(Transaction transactionObj) {
		Lock courrencyLock = new ReentrantLock();
		courrencyLock.lock();

		BankAccountInterface bankdto = BankAccountDTOFactory.getInstance(BankDTOImplEnum.MAP);
		BankAccount fromBankAccount = bankdto.getAccountWithExp(transactionObj.getFromAccNo());



		BigDecimal newBalance = fromBankAccount.getCurrentBalance().subtract(transactionObj.getAmount());

		if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
			transactionObj.setStatus(TransEnum.FAILED);
			courrencyLock.unlock();
			transactionObj.setFailMessage(String.format("Not Enough Money, Current Balance: %f",
					fromBankAccount.getCurrentBalance().doubleValue()));
			throw(new NotEnoughMoneyException( fromBankAccount.getId()));
		} else {
			
			fromBankAccount.setCurrentBalance(newBalance);


			BankAccount toBankAccount = bankdto.getAccountWithExp(transactionObj.getToAccNo());

			toBankAccount.setCurrentBalance(toBankAccount.getCurrentBalance().add(transactionObj.getAmount()));

			bankdto.updateBankAccount(fromBankAccount);
			bankdto.updateBankAccount(toBankAccount);

			transactionObj.setStatus(TransEnum.SUCCEED);
			
			createTransaction(transactionObj);
			courrencyLock.unlock();
		}
		
		
		return transactionObj;

	}



	/**Saves the {@link Transaction} instance to DB.
	 * @param transactionObj
	 */
	private void createTransaction(Transaction transactionObj) {
		Map<Object,Object> dataMap = Datastore.getInstance().getMap();
		Lock concurrencyLock = new ReentrantLock();
		concurrencyLock.lock();
		dataMap.put(transactionIDStart + transactionObj.getTransactionId(), transactionObj);
		concurrencyLock.unlock();
	}
}

