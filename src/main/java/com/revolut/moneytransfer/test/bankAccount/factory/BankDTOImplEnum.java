package com.revolut.moneytransfer.test.bankAccount.factory;

/**Other types of implementors of the DTO such as Postgre, Mysql, Mongo, or a simple HashMAP will be configured here.
 * After adding the implementor, add the enum so that the {@link BankAccountDTOFactory} can use it.
 * @author kamlesh
 *
 */
public enum BankDTOImplEnum {
	/**
	 * Currently has only Map Implementor, Any other DB implementors needs to addedd here
	 */
	MAP
}
