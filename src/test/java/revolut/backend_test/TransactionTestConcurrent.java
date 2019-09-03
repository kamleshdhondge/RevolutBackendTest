package revolut.backend_test;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.Test;

import com.revolut.moneytransfer.test.App;
import com.revolut.moneytransfer.test.bankAccount.model.beans.BankAccount;
import com.revolut.moneytransfer.test.transaction.model.beans.Transaction;
import com.revolut.moneytransfer.test.util.Constants;

import junit.framework.TestCase;


/**Tests Concurrent transactions that take place. To test there are 3 threads running at the same time. Excepted to complete these transaction within 1 seconds
 * @author kamlesh 
 * 
 *
 */
public class TransactionTestConcurrent extends TestCase {
	
	private static  HttpServer server = App.startServer(App.CONTEXT_URL);
	private static	Client c = ClientBuilder.newClient();
	private static WebTarget target = c.target(App.CONTEXT_URL);
    
    
	/**Creates 3 threads and executes them simultaneously. Its analogous to fire 3 concurrent requests to the API.
	 * @throws InterruptedException
	 */
	
	@Test
	public  void testConcurrentTransaction() throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(3);
	   
		//Thread1
		Runnable taskOne  = new Runnable() {
			
			@Override
			public void run() {
				testConcurrentTransactionCreation(5, 6);
				
			}
		};
		//Thread2
	    Runnable task2  = new Runnable() {
			
			@Override
			public void run() {
				testConcurrentTransactionCreation(10, 11);
				
			}
		};
		//Thread3
		   Runnable task3  = new Runnable() {
				
				@Override
				public void run() {
					testConcurrentTransactionCreation(12, 13);
					
				}
			};
			
		
		executor.execute(taskOne);
		
		System.out.println("One submited");
		executor.execute(task2);
		System.out.println("Two submited");

		executor.execute(task3);
		System.out.println("Three submited");

		executor.awaitTermination(1, TimeUnit.SECONDS);
		executor.shutdown();
		server.shutdown();

		}
	
	
    private  void testConcurrentTransactionCreation(	long id1 ,long id2 )  {
   
    
    	BankAccount kamleshAccount = new BankAccount("Kamlesh");
		kamleshAccount.setId(id1);
		kamleshAccount.setCurrentBalance(BigDecimal.valueOf(200));
		Response response = target.path(Constants.BANK_ACCOUNT_RESOURCE).request().post(TestUtils.createEntity(kamleshAccount));
		assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());
		

		BankAccount markAccount = new BankAccount("Mark");
		markAccount.setId(id2);
		markAccount.setCurrentBalance(BigDecimal.valueOf(100));
		
		response = target.path(Constants.BANK_ACCOUNT_RESOURCE).request().post(TestUtils.createEntity(markAccount));
		assertEquals(Response.Status.OK, response.getStatusInfo().toEnum());
		
		Transaction transactionObj = new Transaction(id1, id2, BigDecimal.valueOf(150));
		

		response = target.path(Constants.TRANSACTION_RESOURCE).request().post(TestUtils.createEntity(transactionObj));
    	
    }

	

}
