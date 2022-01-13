package com.sg.kata.bank.account.response;

import java.util.ArrayList;
import java.util.List;

import com.sg.kata.bank.account.model.Client;

public class Response {

	private List<String> messages = new ArrayList<>();
	private Client clientInfo;
	private Status status = Status.SUCCESS;
	

	public List<String> getMessages() {
		return messages;
	}

	public void addMessage(String message) {
		if(message != null) {
			this.messages.add(message);
		}
	}

	public Client getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(Client clientInfo) {
		this.clientInfo = clientInfo;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public enum Status {
		SUCCESS,ERROR
	}
}
