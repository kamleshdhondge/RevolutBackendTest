package com.revolut.moneytransfer.test.bankAccount.factory;

import static com.revolut.moneytransfer.test.util.AppLogger.error;

import com.revolut.moneytransfer.test.bankAccount.model.BankAccountInterface;
import com.revolut.moneytransfer.test.bankAccount.model.dto.BankAccountDTOMap;
/**
 * DTO Factory to get the DTO Implementors of Interface {@link BankAccountInterface} as per the DB type. Currently it supports Map as DB,
 * Any new DB impl just need to add a case here and no other change will be required in existing caller code.
 * @author kamlesh
 *
 */
public class BankAccountDTOFactory {

	
	/**
	 * To prevent direct instantiation
	 */
	private BankAccountDTOFactory() {}
	
	public static BankAccountInterface getInstance(BankDTOImplEnum dtoEnum) {
		switch(dtoEnum) {
		case MAP:
				return new BankAccountDTOMap();
		default:
			error("Error: Such Implementor is not supported by the BankAccount DTO factory: " + dtoEnum);
			return null;
		}
		
	}
}
