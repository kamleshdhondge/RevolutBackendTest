package com.revolut.moneytransfer.test.transaction.controller;

import static com.revolut.moneytransfer.test.util.AppLogger.debug;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revolut.moneytransfer.test.transaction.factory.TransactionDTOImplEnum;
import com.revolut.moneytransfer.test.transaction.model.beans.Transaction;
import com.revolut.moneytransfer.test.transaction.service.TransactionService;
import com.revolut.moneytransfer.test.util.Constants;
/**
 * {@link Transaction} controller which maps all the routes to server the business logic 
 * @author kamlesh
 *
 */
@Path("/" + Constants.TRANSACTION_RESOURCE)
@Produces(MediaType.APPLICATION_JSON)
public class TransactionController {


	/**
	 * {@link TransactionService} instance with MAP DTO Implementor
	 */
	private final TransactionService transService = new TransactionService(TransactionDTOImplEnum.MAP);

	/**Gets all the transaction from the database
	 * URL: /Constants.TRANSACTION_RESOURCE. With {@link GET} Method.
	 * eg: /transaction
	 */
	@GET
	public Response getAllTransactons() {
		debug("Inside getAllTransactons API");
		/**
		 * For Multithreading Debug purposes .
		 */
		debug(Thread.currentThread().getId() + "CurrentID ");
		Collection<Transaction> allTransaction = transService.getAllTransactions();
		
		if(allTransaction == null) {
			Response.noContent().build();
		}
		return Response.ok(allTransaction).build();
	}

	/**Executes the transaction and saves the data to the database
	 * URL: /Constants.TRANSACTION_RESOURCE. With {@link POST} Method.
	 * eg: /transaction
	 */
	@POST
	public Response executeTransaction(Transaction transactionObj) throws Exception {
		debug("Inside executeTransaction API");
		
		/**
		 * For Multithreading Debug purposes .
		 */
		debug(Thread.currentThread().getId() + "CurrentID ");
		return Response.ok( transService.executeTranscation(transactionObj)).build();
	}

}
