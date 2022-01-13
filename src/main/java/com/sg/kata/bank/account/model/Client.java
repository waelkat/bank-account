package com.sg.kata.bank.account.model;

import java.util.ArrayList;
import java.util.List;

import com.sg.kata.bank.account.model.Operation.OperationType;

public class Client {

	private String name;
	private Integer clientID;
	private Float balance = 0f;
	private List<Operation> operations = new ArrayList<Operation>();
	
	
	public Client(Integer clientID, String name) {
		this.clientID = clientID;
		this.name = name;
	}


	public Client(Integer clientID, String name, Float initialBalance) {
		this.clientID = clientID;
		this.name = name;
		this.balance = initialBalance;
	}


	public String getName() {
		return name;
	}

	public int getClientID() {
		return clientID;
	}


	/**
	 * Add a deposit to the current customer and create a new operation for this customer
	 * @param float depositValue
	 */
	public void deposit(Float depositValue) {

		addNewOperation(OperationType.DEPOSIT, depositValue);
		this.balance += depositValue;
	}


	public float getBalance() {
		return balance;
	}


	/**
	 * Make a withdrawal for the current customer  and create a new operation for this customer
	 * @param withdrawlValue
	 */
	public void makeWithdrawal(Float withdrawlValue) {
		if(this.balance > 0 && this.balance >= withdrawlValue) {
			
			addNewOperation(OperationType.WITHDRAWAL, withdrawlValue);
			this.balance -= withdrawlValue;
		}
	}
	
	/**
	 * Add a new operation for this customer.
	 * @param String operationType
	 * @param float withdrawlValue
	 */
	private void addNewOperation(OperationType operationType, Float withdrawlValue) {

		Operation operation = new Operation();
		
		operation.setOperationType(operationType);
		operation.setAmount(withdrawlValue);
		
		Float clientBalance = this.balance;
		operation.setClientBalance(clientBalance);
		
		this.operations.add(operation);
	}


	public List<Operation> getOperations() {
		return operations;
	}
	

}