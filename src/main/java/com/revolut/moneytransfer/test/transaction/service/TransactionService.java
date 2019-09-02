package com.revolut.moneytransfer.test.transaction.service;

import static com.revolut.moneytransfer.test.util.AppLogger.debug;

import java.util.Collection;

import com.revolut.moneytransfer.test.transaction.factory.TransactionDTOFactory;
import com.revolut.moneytransfer.test.transaction.factory.TransactionDTOImplEnum;
import com.revolut.moneytransfer.test.transaction.model.TransactionInterface;
import com.revolut.moneytransfer.test.transaction.model.beans.TransEnum;
import com.revolut.moneytransfer.test.transaction.model.beans.Transaction;
/**This service will be consumed by the controller for all the business level operations related to {@link Transaction}.
 * <ul>
 * <li>Retrieves all the {@link Transaction} instances</li>
 * <li>Execute a particular {@link Transaction} and save to DB</li>
 * </ul>
 * @author kamlesh
 *
 */
public class TransactionService {
	TransactionInterface  transDTO = null;
	
	/**Constructor for {@link TransactionService}. needs to pass the DTO {@link TransactionDTOImplEnum} for Implementor of DBs
	 * @param dtoImplmentationWay {@link TransactionDTOImplEnum}
	 */
	public TransactionService(TransactionDTOImplEnum dtoImplmentationWay) {
		  transDTO = TransactionDTOFactory.getInstance(dtoImplmentationWay);
		  debug("Transaction Service Instiated successfully");
	}


    /**Retrieves all the transaction Instances from the DB.
     * @return
     */
    public Collection<Transaction> getAllTransactions() {
    	debug("getAllTransactionsInstiated successfully");
        return transDTO.getAllTransactions();
    }

   
    /**Executes a particular {@link Transaction} and updates balances of from and to accounts accordingly
     * @param transactionObj instance of {@link Transaction}
     * @return a instance where the status is successful.
     * @throws Exception
     */
    public Transaction executeTranscation(Transaction transactionObj) throws Exception  {
    	
    	   debug("Starting of Transaction executor");
    	   Transaction executedTransaction  = null;
    	   
    	   executedTransaction = transDTO.executeTransaction(transactionObj);
           
           debug("Transaction executor ended");
           
           if(executedTransaction == null || executedTransaction.getStatus().equals(TransEnum.FAILED)) {
        	   throw(new Exception("Transaction failed for ID: " + transactionObj.getTransactionId()));
           }
           return executedTransaction;
    }
    
}
