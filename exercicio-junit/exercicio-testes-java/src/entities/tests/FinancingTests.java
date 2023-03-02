package entities.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import entities.Financing;

public class FinancingTests {

	@Test
	public void constructorShouldCreateObjectWhenValidData() {
		
		double totalAmount = 100000.00;
		double income = 2000.00;
		int months = 80;
		
		Financing financing = new Financing(totalAmount, income, months);
		
		Assertions.assertTrue(financing != null);
	}
	
	@Test
	public void constructorShouldThrowExceptionWhenInvalidData() {
		
		Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
			double totalAmount = 100000.00;
			double income = 2000.00;
			int months = 20;
			
			@SuppressWarnings("unused")
			Financing financing = new Financing(totalAmount, income, months);
		});
	}
	
	@Test
	public void setTotalAmountShouldUpdateValueWhenValidData() {
		
		double totalAmount = 100000.00;
		double income = 2000.00;
		int months = 80;
		Financing financing = new Financing(totalAmount, income, months);
		
		financing.setTotalAmount(90000.00);
		
		Assertions.assertEquals(90000.00, financing.getTotalAmount());
	}
	
	@Test
	public void setTotalAmountShouldThrowsExceptionWhenInvalidData() {
		
		Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
			double totalAmount = 100000.00;
			double income = 2000.00;
			int months = 80;
			Financing financing = new Financing(totalAmount, income, months);
			
			financing.setTotalAmount(100001.00);
		});
	}
	
	@Test
	public void setIncomeShouldUpdateValueWhenValidData() {
		
		double totalAmount = 100000.00;
		double income = 2000.00;
		int months = 80;
		Financing financing = new Financing(totalAmount, income, months);
		
		financing.setIncome(3000.00);
		
		Assertions.assertEquals(3000.00, financing.getIncome());
	}
	
	@Test
	public void setIncomeShouldThrowExceptionWhenInvalidaData() {
		
		Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
			double totalAmount = 100000.00;
			double income = 2000.00;
			int months = 80;
			Financing financing = new Financing(totalAmount, income, months);
			financing.setIncome(1500.00);
		});
	}
	
	@Test
	public void setMonthsShouldUpdateValueWhenValidData() {
		
		double totalAmount = 100000.00;
		double income = 2000.00;
		int months = 80;
		Financing financing = new Financing(totalAmount, income, months);
		
		financing.setMonths(90);
		
		Assertions.assertEquals(90, financing.getMonths());		
	}
	
	@Test
	public void setMonthsShouldThrowExceptionWhenInvalidData() {
	
		Assertions.assertThrowsExactly(IllegalArgumentException.class, () -> {
			double totalAmount = 100000.00;
			double income = 2000.00;
			int months = 80;
			Financing financing = new Financing(totalAmount, income, months);
			
			financing.setMonths(79);
		});
	}
	
	@Test
	public void entryShouldCalculateCorrectlyEntryValue() {
		
		double totalAmount = 100000.00;
		double income = 2000.00;
		int months = 80;
		Financing financing = new Financing(totalAmount, income, months);
		double expected = 20000.00;
		
		double entry = financing.entry();
		
		Assertions.assertEquals(expected, entry);
	}
	
	@Test
	public void quotaShouldCalculateCorrectlyQuotaValue() {
		
		double totalAmount = 100000.00;
		double income = 2000.00;
		int months = 80;
		Financing financing = new Financing(totalAmount, income, months);
		double expected = 1000.00;
		
		double quota = financing.quota();
		
		Assertions.assertEquals(expected, quota);
	}
	
}
