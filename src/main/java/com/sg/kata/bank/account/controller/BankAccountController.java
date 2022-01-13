package com.sg.kata.bank.account.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sg.kata.bank.account.response.Response;
import com.sg.kata.bank.account.service.BankService;

@RestController
public class BankAccountController {

	@Autowired
	private BankService bankService;
	
	//To make it simple to test I will use Get instead of Post
	@GetMapping("deposit-money")
	public Response depositMoney(@RequestParam(name = "clientId", required = false) Integer id, @RequestParam(name = "depositValue", required = false) Float depositValue) {
		//Since there is no UI then we will hard code the client and deposit value
		Float value = 1 + new Random().nextFloat() * (5000 - 1);
		return bankService.deposit(BankService.DEFAULT_CLIENT_ID, value);
	}
	
	@GetMapping("balance")
	public Response getBalance(@RequestParam(name = "clientId", required = false) Integer id) {
		//Since there is no UI then we will hard code the client 
		return bankService.getBalance(BankService.DEFAULT_CLIENT_ID);
	}
	
	//To make it simple to test I will use Get instead of Post
	@GetMapping("make-withdrawal")
	public Response makeWithdrawal(@RequestParam(name = "clientId", required = false) Integer id, @RequestParam(name = "depositValue", required = false) Float depositValue) {
		//Since there is no UI then we will hard code the client 
		Float value = 1 + new Random().nextFloat() * (5000 - 1);
		return bankService.makeWithdrawal(BankService.DEFAULT_CLIENT_ID, value);
	}
	
	@GetMapping("get-history")
	public Response getHistory(@RequestParam(name = "clientId", required = false) Integer id) {
		//Since there is no UI then we will hard code the client 
		return bankService.getClientHistory(BankService.DEFAULT_CLIENT_ID);
	}

}