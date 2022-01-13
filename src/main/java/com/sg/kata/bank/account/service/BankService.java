package com.sg.kata.bank.account.service;

import com.sg.kata.bank.account.model.Client;
import com.sg.kata.bank.account.response.Response;

public interface BankService {

	public static final Integer DEFAULT_CLIENT_ID = 123;
	
	/**
	 * Add a new client to the bank clients
	 * <br>This method can be used in case we need to add a bank administration interface
	 * @param Client client
	 */
	public Client addClient(Client client);

	/**
	 * Get the client form the client list using client ID
	 * <br>This method can be used in case we need to add a bank administration interface
	 * @param int clientID
	 * @return Client object or null if none was found
	 */
	public Client getClientWithID(Integer clientID);

	/**
	 * Add the deposit amount to the specified client balance
	 * @param int clientID
	 * @param float depositValue
	 * @throws IllegalArgumentException if the client is not found
	 */
	public Response deposit(Integer clientID, Float depositValue);

	/**
	 * Get the balance of the account
	 * @param int clientID
	 * @throws IllegalArgumentException if the client is not found
	 */
	public Response getBalance(Integer clientID);

	/**
	 * Test if the current client can make a Withdrawal from his account. 
	 * @param Client client
	 * @param float withdrawlValue
	 * @return true if the client can make a Withdrawal
	 * @throws IllegalArgumentException if the client is not found
	 */
	public boolean canMakeWithdrawal(Client client, Float withdrawlValue);

	/**
	 * Make a Withdrawal with the specified amount for the current customer
	 * @param int clientID
	 * @param float withdrawlValue
	 * @throws IllegalArgumentException if the client is not found
	 */
	public Response makeWithdrawal(Integer clientID, Float withdrawlValue);

	/**
	 * Get the current client transactions history
	 * @param int clientID
	 * @return String : the client history
	 */
	public Response getClientHistory(Integer clientID);

}