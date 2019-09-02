package com.revolut.moneytransfer.test.exceptions;



/**Runtime exception to throw when an transaction occurs and there is no money in the account
 * @author kamlesh
 *
 */
public class NotEnoughMoneyException extends RuntimeException {
;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ERROR_MESSAGE = "BankAccount does not have enough money. Account ID: ";

	public NotEnoughMoneyException(long bankAccountId) {
		super(ERROR_MESSAGE + bankAccountId);
	}
}
