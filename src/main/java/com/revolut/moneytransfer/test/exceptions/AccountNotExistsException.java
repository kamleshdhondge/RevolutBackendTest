package com.revolut.moneytransfer.test.exceptions;

/**Runtime exception to throw when a account does not exists
 * @author kamlesh 
 *
 */
public class AccountNotExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ERROR_MESSAGE = "BankAccount does not exists for ID: ";

	public AccountNotExistsException(long bankAccountId) {
		super(ERROR_MESSAGE + bankAccountId);
	}
}
