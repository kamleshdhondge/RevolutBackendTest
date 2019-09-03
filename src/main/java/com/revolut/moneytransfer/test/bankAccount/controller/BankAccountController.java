package com.revolut.moneytransfer.test.bankAccount.controller;

import static com.revolut.moneytransfer.test.util.AppLogger.debug;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.revolut.moneytransfer.test.bankAccount.factory.BankDTOImplEnum;
import com.revolut.moneytransfer.test.bankAccount.model.beans.BankAccount;
import com.revolut.moneytransfer.test.bankAccount.service.BankAccountService;
import com.revolut.moneytransfer.test.exceptions.AccountNotExistsException;
import com.revolut.moneytransfer.test.util.Constants;
/**
 * {@link BankAccount} controller which maps all the routes to server the business logic 
 * @author kamlesh
 *
 */
@Path("/" + Constants.BANK_ACCOUNT_RESOURCE)
@Produces(MediaType.APPLICATION_JSON)
public class BankAccountController {


	private final BankAccountService bankAccountService = new BankAccountService(BankDTOImplEnum.MAP);


	/**Create  a {@link BankAccount} instance and saves to the DB.
	 * URL: /Constants.BANK_ACCOUNT_RESOURCE. With {@link POST} Method.
	 * eg: /bankaccount
	 * @param bankAccount the Json deseralized instance of {@link BankAccount}
	 */
	@POST
	public Response createBankAccount(BankAccount bankAccount)  {
		debug("Inside CreateBankAccount API");

		BankAccount createdBankAccount = bankAccountService.createBankAccount(bankAccount);

		debug("Exiting CreateBankAccount API: Success");

		return Response.ok(createdBankAccount).build();
	}



	/**Gets all instances of  {@link BankAccount}from DB.
	 * URL: /Constants.BANK_ACCOUNT_RESOURCE. With {@link GET} Method.
	 * eg: /bankaccount
	 */
	@GET
	public Response getAllBankAccounts()  {
		debug("Inside Get All");
		Collection<BankAccount> allBankAccounts = bankAccountService.getAllBankAccounts();
		if(allBankAccounts == null) {
			Response.noContent().build();
		}
		return Response.ok(allBankAccounts).build();
	}


	/**Gets all instances of  {@link BankAccount}from DB by an ID filter.
	 * URL: /Constants.BANK_ACCOUNT_RESOURCE. With {@link GET} Method.
	 * eg: /bankaccount
	 * @param id: The ID of the bank account to retrive
	 */
	@GET
	@Path("{id}") 
	public Response getAllBankAccountById(@PathParam("id") Long id)  {
		try {
			BankAccount  bankaccountbyId = bankAccountService.getBankAccountById(id);
			
			
			return Response.ok(bankaccountbyId).build();
			
		} catch(AccountNotExistsException accountDoesNotExists) {
			 WebApplicationException webApplicationException = new  WebApplicationException("No Bank Account Found", Response.Status.NOT_FOUND);
			 webApplicationException.initCause(accountDoesNotExists);
			 throw(webApplicationException);
		}
	}
}
