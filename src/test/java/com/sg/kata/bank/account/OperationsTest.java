package com.sg.kata.bank.account;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sg.kata.bank.account.model.Operation;
import com.sg.kata.bank.account.model.Operation.OperationType;

@SpringBootTest
public class OperationsTest {
	
	@Test
	public void createWithdrawalOperationTest() {
		Operation operation = new Operation();
		
		operation.setOperationType(OperationType.WITHDRAWAL);
		operation.setAmount(15);
		operation.setClientBalance(15);

		assertEquals(operation.getAmount(), 15);
		assertEquals(operation.getClientBalance(), 15);
		assertEquals(operation.getOperationType(), OperationType.WITHDRAWAL);

	}

	@Test
	public void createDepositeOperationTest() {
		Operation operation = new Operation();

		operation.setOperationType(OperationType.DEPOSIT);
		operation.setAmount(15);
		operation.setClientBalance(15);
		
		assertEquals(operation.getAmount(), 15);
		assertEquals(operation.getClientBalance(), 15);
		assertEquals(operation.getOperationType(), OperationType.DEPOSIT);

	}
}
