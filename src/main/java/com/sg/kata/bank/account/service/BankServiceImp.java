package com.sg.kata.bank.account.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.sg.kata.bank.account.model.Client;
import com.sg.kata.bank.account.response.Response;
import com.sg.kata.bank.account.response.Response.Status;

@Service("bankService")
public class BankServiceImp implements BankService, InitializingBean {
	
	List<Client> listClient = new ArrayList<Client>();

	/**
	 * Since no UI : add a default client
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		Client client = new Client(DEFAULT_CLIENT_ID, "Joe", 0f);
		listClient.add(client);
	}
	
	@Override
	public Client addClient(Client client) {
		listClient.add(client);
		return client;
	}

	@Override
	public Client getClientWithID(Integer clientID) {
		return listClient.stream()
				.filter(client -> clientID == client.getClientID())
				.findAny().orElse(null);
	}

	@Override
	public Response deposit(Integer clientID, Float depositValue) {
		Client client = getClient(clientID);
		
		client.deposit(depositValue);
		return getResponse(client, "The deposit of "+depositValue+" was done with success.", Status.SUCCESS);
	}

	@Override
	public Response getBalance(Integer clientID) {
		Client client = getClient(clientID);
		
		return getResponse(client, "You have  "+client.getBalance()+" in your account.", Status.SUCCESS);
	}

	@Override
	public boolean canMakeWithdrawal(Client client, Float withdrawlValue) {
		
		if(client.getBalance() > 0 && client.getBalance() >= withdrawlValue) {
			return true;
		}
		return false;
	}

	@Override
	public Response makeWithdrawal(Integer clientID, Float withdrawlValue) {

		Client client = getClient(clientID);
		
		if(!canMakeWithdrawal(client, withdrawlValue)) {
			return getResponse(client, "You don't have enough balance to make the redraw of " +withdrawlValue+".", Status.ERROR);
		}
		
		client.makeWithdrawal(withdrawlValue);
		return getResponse(client, "The withdrawal of "+ withdrawlValue + " was done with success", Status.SUCCESS);
	}

	@Override
	public Response getClientHistory(Integer clientID) {
		Client client = getClient(clientID);
		
		Response r = getResponse(client, null, Status.SUCCESS);
		client.getOperations().stream().forEach(operation -> r.addMessage(operation.toString()));
		
		return r;
	}
	
	private Client getClient(Integer clientID) {
		Client client = getClientWithID(clientID);
		
		if(client == null) {
			throw new IllegalArgumentException("Client with ID " + clientID + " Not found.");
		}
		return client;
	}

	private Response getResponse(Client client, String message, Status status) {
		Response response = new Response();
		response.setClientInfo(client);
		response.addMessage(message);
		response.setStatus(status);
		return response;
	}

}