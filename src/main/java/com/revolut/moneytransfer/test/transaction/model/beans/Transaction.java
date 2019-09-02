package com.revolut.moneytransfer.test.transaction.model.beans;

import java.math.BigDecimal;
import java.util.Date;

/**Transaction Object bean class. Has a ID, FromAccountNo with the id of bank account from where to withraw, toAccNo is the id of account to deposit the amount.<br>
 * Amount which is the amount to transfer
 * Status {@link TransEnum} which is the status of transactio
 * and A message if the needed to be send back to client.
 * @author kamlesh
 *
 */
public class Transaction {


	

	private Long transactionId;
	private Long fromAccNo = (long) 0 ;
	private Long toAccNo = (long) 0;
	private BigDecimal amount = BigDecimal.ZERO;

	private Date creationDate = new Date();
	private Date updateDate = new Date();
	private TransEnum status = TransEnum.ACTIVE;
	private String failMessage = "";

	public Transaction() {}


	public Transaction(Long fromAccNo, Long toAccNo, BigDecimal amount) {

		this.fromAccNo = fromAccNo;
		this.toAccNo = toAccNo;
		this.amount = amount;
	}



	public Long getTransactionId() {
		return transactionId;
	}



	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}



	public Long getFromAccNo() {
		return fromAccNo;
	}



	public void setFromAccNo(Long fromAccNo) {
		this.fromAccNo = fromAccNo;
	}



	public Long getToAccNo() {
		return toAccNo;
	}



	public void setToAccNo(Long toAccNo) {
		this.toAccNo = toAccNo;
	}



	public BigDecimal getAmount() {
		return amount;
	}



	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}



	public Date getCreationDate() {
		return creationDate;
	}



	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}



	public Date getUpdateDate() {
		return updateDate;
	}



	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}



	public TransEnum getStatus() {
		return status;
	}



	public void setStatus(TransEnum status) {
		this.status = status;
	}



	public String getFailMessage() {
		return failMessage;
	}



	public void setFailMessage(String failMessage) {
		this.failMessage = failMessage;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((failMessage == null) ? 0 : failMessage.hashCode());
		result = prime * result + ((fromAccNo == null) ? 0 : fromAccNo.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((toAccNo == null) ? 0 : toAccNo.hashCode());
		result = prime * result + ((transactionId == null) ? 0 : transactionId.hashCode());
		result = prime * result + ((updateDate == null) ? 0 : updateDate.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (failMessage == null) {
			if (other.failMessage != null)
				return false;
		} else if (!failMessage.equals(other.failMessage))
			return false;
		if (fromAccNo == null) {
			if (other.fromAccNo != null)
				return false;
		} else if (!fromAccNo.equals(other.fromAccNo))
			return false;
		if (status != other.status)
			return false;
		if (toAccNo == null) {
			if (other.toAccNo != null)
				return false;
		} else if (!toAccNo.equals(other.toAccNo))
			return false;
		if (transactionId == null) {
			if (other.transactionId != null)
				return false;
		} else if (!transactionId.equals(other.transactionId))
			return false;
		if (updateDate == null) {
			if (other.updateDate != null)
				return false;
		} else if (!updateDate.equals(other.updateDate))
			return false;
		return true;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transaction [transactionId=");
		builder.append(transactionId);
		builder.append(", fromAccNo=");
		builder.append(fromAccNo);
		builder.append(", toAccNo=");
		builder.append(toAccNo);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append(", status=");
		builder.append(status);
		builder.append(", failMessage=");
		builder.append(failMessage);
		builder.append("]");
		return builder.toString();
	}
}