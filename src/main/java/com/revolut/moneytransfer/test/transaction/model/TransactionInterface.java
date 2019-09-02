package com.revolut.moneytransfer.test.transaction.model;

import java.util.Collection;

import com.revolut.moneytransfer.test.transaction.model.beans.Transaction;

/**This is interface class for all {@link Transaction} related operations any implementor.  The implementor can change as per DTO implementations<br>
 * Such as MysqlTransactionImplementor, PostGreTransactionSQL implementor. This interface makes sure that these implementors should have these methods as its required by the busniess logic
 * @author kamlesh
 *
 */
public interface TransactionInterface {

	
	/**Fetches all the {@link Transaction} instances from the DB
	 * @return
	 */
	public Collection<Transaction> getAllTransactions();

	
	/** Executes the particular transaction. Saves the exeucted transaction to DB
	 * @param transactionObj The object to save into DB
	 * @return
	 */
	public Transaction executeTransaction(Transaction transactionObj);
}
