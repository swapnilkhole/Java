package taxcalc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TaxCalcTest {

	private static List<TaxCalc.Pair<BigDecimal, String>> income;
	private static BigDecimal taxPercentage = new BigDecimal(10);

	@Before
	public void setupTestData() {
		income = new ArrayList<>();

		income.add(new TaxCalc.Pair<>(new BigDecimal(40), "GBP"));
		income.add(new TaxCalc.Pair<>(new BigDecimal(50), "GBP"));
		income.add(new TaxCalc.Pair<>(new BigDecimal(60), "GBP"));

	}

	/**
	 * verify if taxCalculator is calculating the tax correctly.
	 */
	@Test
	public void verifyCorrectTax() {
		try {
			TaxCalc taxCalc = new TaxCalc(taxPercentage);
			TaxCalc.Pair<BigDecimal, String> netAmount = taxCalc.netAmount(income);
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
			income.add(new TaxCalc.Pair<>(new BigDecimal(70), "EUR"));
			TaxCalc taxCalc = new TaxCalc(taxPercentage);
			taxCalc.netAmount(income);
			fail("fail"); 
		} catch (ApplicationException ae) {
			assertEquals(ae.getErrorMessage(), "different currencies specified");
		}
	}

	/**
	 * negative amount not allowed in the income
	 */
	@Test
	public void cannotHaveNegativeAmounts() {
		try {
			income.add(new TaxCalc.Pair<>(new BigDecimal(-40), "GBP"));
			TaxCalc taxCalc = new TaxCalc(taxPercentage);
			taxCalc.netAmount(income);
			fail("fail");
		} catch (ApplicationException ae) {
			assertEquals(ae.getErrorMessage(), "Negative number not allowed!");
		}
	}

	@After
	public void cleanUpTestData() {
		income = null;
	}
}