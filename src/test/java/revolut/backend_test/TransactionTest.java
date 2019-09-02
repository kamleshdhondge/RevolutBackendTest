package revolut.backend_test;

import java.math.BigDecimal;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.revolut.moneytransfer.test.App;
import com.revolut.moneytransfer.test.bankAccount.model.beans.BankAccount;
import com.revolut.moneytransfer.test.transaction.model.beans.Transaction;
import com.revolut.moneytransfer.test.util.Constants;

import junit.framework.TestCase;

public class TransactionTest extends TestCase {

	private static HttpServer server = App.startServer();
	private static WebTarget target; 
	
	
	@AfterClass
    public static void afterAll() {
        server.shutdownNow();
    }
	@Test
	public void testexecuteTransactionTest() {
		Client c = ClientBuilder.newClient();
		target = c.target(App.CONTEXT_URL);
		
		
		BankAccount kamleshAccount = new BankAccount("Kamlesh");
		kamleshAccount.setId(Long.valueOf("1"));
		kamleshAccount.setCurrentBalance(BigDecimal.valueOf(200));
		
		
		Response response = target.path(Constants.BANK_ACCOUNT_RESOURCE).request().post(TestUtils.createEntity(kamleshAccount));
		assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());
		

		BankAccount markAccount = new BankAccount("Mark");
		markAccount.setId(Long.valueOf("2"));
		markAccount.setCurrentBalance(BigDecimal.valueOf(100));
		
		response = target.path(Constants.BANK_ACCOUNT_RESOURCE).request().post(TestUtils.createEntity(markAccount));
		assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());
		
		Transaction transactionObj = new Transaction(Long.valueOf("1"), Long.valueOf("2"), BigDecimal.valueOf(150));
		

		response = target.path(Constants.TRANSACTION_RESOURCE).request().post(TestUtils.createEntity(transactionObj));
		assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());
		
		
		response = target.path(Constants.BANK_ACCOUNT_RESOURCE+"/2").request().get();
		BankAccount fetchedAccount = response.readEntity(BankAccount.class);
		
		assertEquals(fetchedAccount.getCurrentBalance(), BigDecimal.valueOf(250));
		
		response = target.path(Constants.BANK_ACCOUNT_RESOURCE+"/1").request().get();
		BankAccount oldFetchedAccount = response.readEntity(BankAccount.class);
		
		assertEquals(oldFetchedAccount.getCurrentBalance(), BigDecimal.valueOf(50));
	}
	

}
