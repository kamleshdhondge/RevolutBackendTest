package com.revolut.moneytransfer.test.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**Logger class to log debug and error level messages. This is handly when we want a custom messgae to be logged.<br>
 * Maintainable for any change to be done in all debug or error loging methods
 * @author kamlesh
 *
 */
public class AppLogger {

	private static final Logger log = LoggerFactory.getLogger(AppLogger.class);

	private AppLogger() {}

	public static void debug(String errorMessage) {
		log.debug(errorMessage);
	}

	public static void debug(String errorMessage, Throwable e) {
		log.debug(errorMessage, e);
	}

	


	 public static void error(String errorMessage) {
			log.error(errorMessage);
	  }
	  
	  public static void error(String errorMessage, Throwable e) {
			log.error(errorMessage, e);
	  }
	 
}
