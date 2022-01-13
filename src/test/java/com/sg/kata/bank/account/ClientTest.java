package com.sg.kata.bank.account;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sg.kata.bank.account.model.Client;

@SpringBootTest
public class ClientTest {

	@Test
	public void defaultClientCreationTest() {
		Client client = new Client(1, "Wael");

		assertEquals(client.getBalance(), 0);
		assertEquals(client.getName(), "Wael");
		assertEquals(client.getOperations().size(), 0);

	}

	@Test
	public void clientWithBalanceCreationTest() {
		Client client = new Client(1, "Wael", 20f);

		assertEquals(client.getBalance(), 20f);
		assertEquals(client.getName(), "Wael");
		assertEquals(client.getOperations().size(), 0);

	}
	
}
