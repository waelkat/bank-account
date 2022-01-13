package com.sg.kata.bank.account.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Operation {

	private float amount;
	private float clientBalance = 0;
	private OperationType operationType;
	private LocalDateTime creationDate;
	
	public Operation() {
		this.creationDate = LocalDateTime.now();
	}
	
	@Override
	public String toString() {
		String toString = "";
		Float newBalance;
		
		if(OperationType.WITHDRAWAL.equals(operationType)) {
			toString += "Withdraw an amount of ";
			newBalance = getClientBalance() - getAmount();
			
		} else {
			toString += "Deposit an amount of ";
			newBalance = getClientBalance() + getAmount();
		}
		
		toString += getAmount() +" .";
		toString += "The balance before the operation was : " + getClientBalance() +". ";
		toString += "Your new balance is : " + newBalance +". ";
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		toString += "Operation date : " + getCreationDate().format(formatter);
		
		return toString;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public void setClientBalance(float clientBalance) {
		this.clientBalance = clientBalance;
	}

	public float getAmount() {
		return this.amount;
	}

	public float getClientBalance() {
		return this.clientBalance;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}
	
	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public enum OperationType {
		WITHDRAWAL,DEPOSIT
	}

}