package revolut.backend_test;

import java.util.Collection;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.revolut.moneytransfer.test.App;
import com.revolut.moneytransfer.test.bankAccount.factory.BankAccountDTOFactory;
import com.revolut.moneytransfer.test.bankAccount.factory.BankDTOImplEnum;
import com.revolut.moneytransfer.test.bankAccount.model.beans.BankAccount;
import com.revolut.moneytransfer.test.bankAccount.service.BankAccountService;
import com.revolut.moneytransfer.test.util.Constants;

import junit.framework.TestCase;

public class BankAccountTest extends TestCase {

	private static HttpServer server = App.startServer();
	private static WebTarget target;

	/*
	 * @BeforeClass public static void beforeAll() { server = App.startServer();
	 * Client c = ClientBuilder.newClient(); target = c.target(App.CONTEXT_URL); }
	 * 
	 * @AfterClass public static void afterAll() { server.shutdownNow(); }
	 */
	
	@AfterClass
    public static void afterAll() {
        server.shutdownNow();
    }
	@Test
	public void testGetAllBankAccounts() {
		
		Client c = ClientBuilder.newClient();
		target = c.target(App.CONTEXT_URL);
		Response response = target.path(Constants.BANK_ACCOUNT_RESOURCE).request().get();

		assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());

		Collection<BankAccount> bankAccount = response.readEntity(new GenericType<Collection<BankAccount>>() {
		});

		assertEquals(bankAccount.size(),
				BankAccountDTOFactory.getInstance(BankDTOImplEnum.MAP).getAllBankAccounts().size());
		
	}

	
	@Test
	public void testCreateBankAccountAndFetchById() {
		Client c = ClientBuilder.newClient();
		target = c.target(App.CONTEXT_URL);
		
		
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
	
	}

	
	
	


}
