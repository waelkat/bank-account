package com.sg.kata.bank.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import com.sg.kata.bank.account.model.Client;
import com.sg.kata.bank.account.response.Response;
import com.sg.kata.bank.account.response.Response.Status;
import com.sg.kata.bank.account.service.BankServiceImp;

@SpringBootTest
public class BankServicesTest {

	@InjectMocks
	BankServiceImp bankService;
	
	@Test
	public void bankServiceCreationTest() {
		
		int clientID = 1;
		Client client = new Client(clientID, "Wael");
		
		bankService.addClient(client);

		assertEquals(bankService.getClientWithID(clientID), client);

	}

	@Test
	public void makeDepositTest() {

		int clientID = 1;
		Client client = new Client(clientID, "Wael");
		
		bankService.addClient(client);
		Client currentClient = bankService.getClientWithID(clientID);

		float firstDeposit = 15.25f;

		float secondeDeposit = 20f;
		float sumFirstSecond = firstDeposit + secondeDeposit;

		float thirdDeposit = 30.20f;
		float sumAll = sumFirstSecond + thirdDeposit;

		bankService.deposit(clientID, firstDeposit);
		assertEquals(currentClient.getBalance(), firstDeposit);

		bankService.deposit(clientID, secondeDeposit);
		assertEquals(currentClient.getBalance(), sumFirstSecond);

		bankService.deposit(clientID, thirdDeposit);
		assertEquals(currentClient.getBalance(), sumAll);

	}

	@Test
	public void canMakeWithdrawalTest() {

		int clientID = 1;
		Client client = new Client(clientID, "Wael");
		
		bankService.addClient(client);
		Client currentClient = bankService.getClientWithID(clientID);

		assertFalse(bankService.canMakeWithdrawal(currentClient, 15f));

		float firstDeposit = 15.25f;
		float secondeDeposit = 20f;
		float thirdDeposit = 30.20f;
		float sumDeposit = firstDeposit + secondeDeposit + thirdDeposit;
		
		bankService.deposit(clientID, firstDeposit);
		assertTrue(bankService.canMakeWithdrawal(currentClient, firstDeposit));
		
		bankService.deposit(clientID, secondeDeposit);
		bankService.deposit(clientID, thirdDeposit);
		
		//Withdrawal all deposit
		bankService.makeWithdrawal(clientID, sumDeposit);
		
		assertFalse(bankService.canMakeWithdrawal(currentClient, sumDeposit));

	}

	@Test
	public void makeWithdrawalWithNoBalanceTest() {

		int clientID = 1;
		Client client = new Client(clientID, "Wael");
		
		bankService.addClient(client);
		
		float firstDeposit = 15.25f;
		float secondeDeposit = 20f;
		float thirdDeposit = 30.20f;

		float sumDeposit = firstDeposit + secondeDeposit + thirdDeposit;

		//Test without deposit

		Response r = bankService.makeWithdrawal(clientID, sumDeposit);
		assertEquals(r.getStatus(), Status.ERROR);
		
		//Add first deposit to balance
		bankService.deposit(clientID, firstDeposit);
		r = bankService.makeWithdrawal(clientID, sumDeposit);
		assertEquals(r.getStatus(), Status.ERROR);
		
		//Add seconde deposit to balance
		bankService.deposit(clientID, secondeDeposit);
		r = bankService.makeWithdrawal(clientID, sumDeposit);
		assertEquals(r.getStatus(), Status.ERROR);
		
		//Add third deposit to balance and withdrawal first one
		bankService.deposit(clientID, thirdDeposit);
		r = bankService.makeWithdrawal(clientID, firstDeposit);
		assertEquals(r.getStatus(), Status.SUCCESS);
		
		r = bankService.makeWithdrawal(clientID, sumDeposit);
		assertEquals(r.getStatus(), Status.ERROR);
	}

	@Test
	public void makeWithdrawalSameDepositeTest() {
		
		int clientID = 1;
		Client client = new Client(clientID, "Wael");
		
		bankService.addClient(client);
		Client currentClient = bankService.getClientWithID(clientID);
		
		float firstDeposit = 15.25f;
		
		//add and withdrawal the same deposit
		bankService.deposit(clientID, firstDeposit);
		bankService.makeWithdrawal(clientID, firstDeposit);
		assertEquals(currentClient.getBalance(), 0);
	}

	@Test
	public void makeWithdrawalRandomValueTest() {
		
		int clientID = 1;
		Client client = new Client(clientID, "Wael");
		
		bankService.addClient(client);
		Client currentClient = bankService.getClientWithID(clientID);
		
		float firstDeposit = 15.25f;
		float secondeDeposit = 20f;
		float withdrawal = 22.14f;
		float rest = (firstDeposit + secondeDeposit) - withdrawal;
		
		//add and withdrawal the seconde deposit
		bankService.deposit(clientID, firstDeposit);
		bankService.deposit(clientID, secondeDeposit);
		bankService.makeWithdrawal(clientID, withdrawal);
		assertEquals(currentClient.getBalance(), rest);
	}

	@Test
	public void makeWithdrawalAllTest() {

		int clientID = 1;
		Client client = new Client(clientID, "Wael");
		
		bankService.addClient(client);
		Client currentClient = bankService.getClientWithID(clientID);
		
		float firstDeposit = 15.25f;
		float secondeDeposit = 20f;
		float thirdDeposit = 30.20f;
		float sumDeposit = firstDeposit + secondeDeposit + thirdDeposit;
		
		//Add multiple deposit and withdraw all
		bankService.deposit(clientID, firstDeposit);
		bankService.deposit(clientID, secondeDeposit);
		bankService.deposit(clientID, thirdDeposit);
		bankService.makeWithdrawal(clientID, sumDeposit);
		assertEquals(currentClient.getBalance(), 0);
	}

	@Test
	public void showOperationsHistoryTest() {

		int clientID = 1;
		Client client = new Client(clientID, "Wael");
		
		bankService.addClient(client);
		
		float firstDeposit = 15.25f;
		float secondeDeposit = 20f;
		
		float firstWithdrawal = 12f;
		float secondeWithdrawal = 15f;
		
		//Add multiple deposit and withdraw all
		bankService.deposit(clientID, firstDeposit);
		bankService.deposit(clientID, secondeDeposit);

		bankService.makeWithdrawal(clientID, firstWithdrawal);
		bankService.makeWithdrawal(clientID, secondeWithdrawal);
		
		Response r = bankService.getClientHistory(clientID);
		assertEquals(r.getMessages().size(), 4);
	}

}
