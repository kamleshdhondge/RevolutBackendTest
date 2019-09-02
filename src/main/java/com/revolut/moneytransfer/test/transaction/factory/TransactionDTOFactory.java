package com.revolut.moneytransfer.test.transaction.factory;

import static com.revolut.moneytransfer.test.util.AppLogger.error;

import com.revolut.moneytransfer.test.transaction.model.TransactionInterface;
import com.revolut.moneytransfer.test.transaction.model.dto.TransactionDTOMap;
/*** DTO Factory to get the DTO Implementors of Interface {@link TransactionInterface} as per the DB type. Currently it supports Map as DB,
 * Any new DB impl just need to add a case here and no other change will be required in existing caller code.
 * 
 */
public class TransactionDTOFactory {


	/**
	 * To prevent direct instantiation
	 */
	private TransactionDTOFactory() {}

	public static TransactionInterface getInstance(TransactionDTOImplEnum dtoEnum) {
		switch(dtoEnum) {
		case MAP:
			return new TransactionDTOMap();
		default:
			error("Error: Such Implementor is not supported by the Transaction DTO factory: " + dtoEnum);
			return null;
		}

	}

}
