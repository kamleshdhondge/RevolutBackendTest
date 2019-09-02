package revolut.backend_test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

public class TestUtils {

	public static Entity<Object> createEntity(Object bankAccount) {
		return Entity.entity(bankAccount, MediaType.valueOf(MediaType.APPLICATION_JSON));
	}
}
