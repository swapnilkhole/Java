package taxcalc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TaxCalculatorStreamerTest {

	private static List<TaxCalculation> income;
	private static BigDecimal taxPercentage = new BigDecimal(10);

	
	@Before
	public void setupTestData() {
		 income = new ArrayList<TaxCalculation>();
				income.add(new TaxCalculation(new BigDecimal(40), "EUR"));
				income.add(new TaxCalculation(new BigDecimal(50), "EUR"));
				income.add(new TaxCalculation(new BigDecimal(60), "EUR"));				
	}
	
	@Test
	public void verifyEmptyListIsNotProcessed() {
		try {
			income = null;
			TaxCalculatorStreamer taxCalc = new TaxCalculatorStreamer(income, taxPercentage);
			TaxCalculation netAmount = taxCalc.netAmount(income, taxPercentage);
			
		}catch (ApplicationException ae) {
			assertEquals(ae.getErrorMessage(), "no income provided");
			income = new ArrayList<TaxCalculation>();
			TaxCalculatorStreamer taxCalc = new TaxCalculatorStreamer(income, taxPercentage);
			try {
			TaxCalculation netAmount = taxCalc.netAmount(income, taxPercentage);
			}catch(ApplicationException ae1) {
				assertEquals(ae.getErrorMessage(), "no income provided");
			}
		}
	}

	/**
	 * verify if taxCalculator is calculating the tax correctly.
	 */
	@Test
	public void verifyCorrectTax() {
		try {
			TaxCalculatorStreamer taxCalc = new TaxCalculatorStreamer(income, taxPercentage);
			TaxCalculation netAmount = taxCalc.netAmount(income, taxPercentage);
			assertEquals(new BigDecimal(135.0), netAmount.amount);
		} catch (ApplicationException ae) {
			fail("should not have failed. Please verify");
		}
	}

	/**
	 * the currencies in tax calculation should be unique for all income
	 */
	@Test
	public void cannotSumDifferentCurrencies() {
		try {
			income.add(new TaxCalculation(new BigDecimal(40), "USD"));
			TaxCalculatorStreamer taxCalc = new TaxCalculatorStreamer(income, taxPercentage);
			taxCalc.netAmount(income, taxPercentage);
			fail("fail"); 
		} catch (ApplicationException ae) {
			assertEquals(ae.getErrorMessage(), "not matching currencies");
		}
	}

	/**
	 * negative amount not allowed in the income
	 */
	@Test
	public void cannotHaveNegativeAmounts() {
		try {
			income.add(new TaxCalculation(new BigDecimal(-30), "EUR"));
			TaxCalculatorStreamer taxCalc = new TaxCalculatorStreamer(income, taxPercentage);
			taxCalc.netAmount(income, taxPercentage);
			fail("fail");
		} catch (ApplicationException ae) {
			assertEquals(ae.getErrorMessage(), "negative amount");
		}
	}

	@After
	public void cleanUpTestData() {
		income = null;
	}
	
	
	
}
