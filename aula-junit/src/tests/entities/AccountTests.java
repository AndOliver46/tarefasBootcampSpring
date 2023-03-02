package tests.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import entities.Account;
import testes.factory.AccountFactory;

public class AccountTests {

	@Test
	public void depositShouldIncreaseBalanceAndDisocuntFeeWhenPositiveAmount() {

		double amount = 200.0;
		double extpectedValue = 196.0;
		Account acc = AccountFactory.createEmptyAccount();

		acc.deposit(amount);

		Assertions.assertEquals(extpectedValue, acc.getBalance());
	}

	@Test
	public void depositShouldDoNothingWhenNegativaAmount() {

		double extpectedValue = 100.0;
		Account acc = AccountFactory.createAccount(extpectedValue);
		double amount = -200.0;

		acc.deposit(amount);

		Assertions.assertEquals(extpectedValue, acc.getBalance());
	}

	@Test
	public void fullWithdrawShouldClearBalanceAndReturnFullBalance() {

		double extpectedValue = 0.0;
		double initialBalance = 800.0;
		Account acc = AccountFactory.createAccount(initialBalance);

		double result = acc.fullWithdraw();

		Assertions.assertEquals(extpectedValue, acc.getBalance());
		Assertions.assertEquals(initialBalance, result);
	}

	@Test
	public void withdrawShouldDecreaseBalanceWhenSufficientBalance() {

		Account acc = AccountFactory.createAccount(800.00);

		acc.withdraw(500.00);

		Assertions.assertEquals(300.00, acc.getBalance());

	}

	@Test
	public void withdrawShouldThrowExceptionWhenInsufficientBalance() {
		
		Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
			Account acc = AccountFactory.createAccount(800.00);
			acc.withdraw(801.00);
		});
	}
}
