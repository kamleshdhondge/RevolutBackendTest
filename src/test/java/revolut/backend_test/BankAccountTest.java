package revolut.backend_test;

import java.util.Collection;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.Test;

import com.revolut.moneytransfer.test.App;
import com.revolut.moneytransfer.test.bankAccount.factory.BankAccountDTOFactory;
import com.revolut.moneytransfer.test.bankAccount.factory.BankDTOImplEnum;
import com.revolut.moneytransfer.test.bankAccount.model.beans.BankAccount;
import com.revolut.moneytransfer.test.bankAccount.service.BankAccountService;
import com.revolut.moneytransfer.test.exceptions.AccountNotExistsException;
import com.revolut.moneytransfer.test.util.Constants;

import junit.framework.TestCase;

/**All the test cases related to bank account.
 * @author kamlesh
 *
 */
public class BankAccountTest extends TestCase {



	/**
	 * Test for checking all bank accounts 
	 */
	@Test
	public void testGetAllBankAccounts() {
		HttpServer server = App.startServer(App.CONTEXT_URL);
		Client c = ClientBuilder.newClient();
		WebTarget	target = c.target(App.CONTEXT_URL);
		Response response = target.path(Constants.BANK_ACCOUNT_RESOURCE).request().get();

		assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());

		Collection<BankAccount> bankAccount = response.readEntity(new GenericType<Collection<BankAccount>>() {
		});

		assertEquals(bankAccount.size(),
				BankAccountDTOFactory.getInstance(BankDTOImplEnum.MAP).getAllBankAccounts().size());
		server.shutdownNow();
	}

	
	/**
	 * Test to fetch a bank account by ID
	 */
	@Test
	public void testCreateBankAccountAndFetchById() {
		HttpServer server = App.startServer(App.CONTEXT_URL);
		Client c = ClientBuilder.newClient();
		WebTarget target = c.target(App.CONTEXT_URL);
		
		
		BankAccountService bankAccoutService = new BankAccountService(BankDTOImplEnum.MAP);

		BankAccount bankAccount = new BankAccount("Kamlesh");
		bankAccount.setId(Long.valueOf("1"));
		
		Response response = target.path(Constants.BANK_ACCOUNT_RESOURCE).request().post(TestUtils.createEntity(bankAccount));
		assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());
		
		
		
		response = target.path(Constants.BANK_ACCOUNT_RESOURCE + "/1").request().get();

		assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());

		BankAccount returnedAccount = response.readEntity(BankAccount.class);
		BankAccount createdAccount = bankAccoutService.getBankAccountById(returnedAccount.getId());

		assertEquals(returnedAccount, (createdAccount));
		server.shutdownNow();
	}
    


    /**
     * Test to get proper exception if the account does not exists
     */
    @Test(expected=AccountNotExistsException.class)
    public void testGetNullBankAccount() {
    	HttpServer server = App.startServer(App.CONTEXT_URL);
		Client c = ClientBuilder.newClient();
		WebTarget target = c.target(App.CONTEXT_URL);
		
		target.path(Constants.BANK_ACCOUNT_RESOURCE + "/10").request().get();

        server.shutdown();
    }


  
	


}
