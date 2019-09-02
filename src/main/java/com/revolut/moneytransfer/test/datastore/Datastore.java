package com.revolut.moneytransfer.test.datastore;

import java.util.HashMap;
import java.util.Map;

/**This is the database class used to mimic a datastore. For In Memory Datastore, it uses a HashMap which will be created <br>
 * Using a singleton Pattern
 * @author kamlesh
 *
 */
public class Datastore {

	private Map<Object, Object> dbMap = new HashMap<>();

	
	private static Datastore dbInstance =  new Datastore();
	
	/**
	 * Done to prevent direct instantiation of class
	 */
	private Datastore() {}
	
	public static Datastore getInstance() {
		return dbInstance;
	}
	
	
	
	public Map<Object, Object> getMap(){
		return this.dbMap;
	}
}


