package taxcalc;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * @author Swapnil Khole A class for tax calculation based on grossIncome and
 *         taxPercentage to deduct. calculation module.
 *         
 *         for ex. <40, GBP>, <50, GBP>, <60, GBP> are the income statements.
 *         Assuming same currency for all income then grossAmount is 40 + 50 + 60 = 150
 *         TaxPercentage = 10 then taxAmount is 150 / 10 = 15.
 *         And then the netAmount is 150 - 15 = 135. 
 *
 */
class TaxCalc {

	BigDecimal TaxPercent;

	TaxCalc(BigDecimal TaxPercent) {
		this.TaxPercent = TaxPercent;
	}

	/**
	 * @param income 
	 * validates the income and currencies, calculates gross amounts
	 *               computes tax and then subtracts taxAmount from grossAmount to
	 *               derive final netAmount.
	 * @return a pair of netAmount with currency if we need to use currency in
	 *         further logic.
	 * 
	 */
	public Pair<BigDecimal, String> netAmount(List<TaxCalc.Pair<BigDecimal, String>> income) {
		BigDecimal grossAmount = null;
		BigDecimal taxAmount = null;
		BigDecimal netAmount = null;
		Pair<BigDecimal, String> returnNetAmount = null;
		performValidations(income); // validates the income and currencies

		if (income != null && !income.isEmpty()) {
			grossAmount = calculateGrossAmount(income);
		

		taxAmount = computeTax(grossAmount);// computing the tax deductions.

		netAmount = grossAmount.subtract(taxAmount); // final netAmount
		returnNetAmount = new Pair<BigDecimal, String>(netAmount, income.get(0).currency);
		}
		// a pair to return with netAmount calculated above and the currency
		return returnNetAmount;
	}

	/**
	 * @param grossAmount 
	 * divides grossAmount by taxPercentage.
	 * @return
	 */
	private BigDecimal computeTax(BigDecimal grossAmount) {
		BigDecimal taxAmount = new BigDecimal(0);
		taxAmount = grossAmount.divide(TaxPercent);
		return taxAmount;
	}

	/**
	 * @param income 
	 * addition of all the gross amounts earned.
	 * @return grossAmount
	 */
	private BigDecimal calculateGrossAmount(List<TaxCalc.Pair<BigDecimal, String>> income) {
		BigDecimal grossAmount = new BigDecimal(0);
		for (Pair<BigDecimal, String> pair : income) {
			grossAmount = grossAmount.add(pair.amount);
		}
		return grossAmount;
	}

	/**
	 * @param income 
	 * Perform validations in tax calculation. validateCurrency - unique currency in the list NotANegativeAmount - tax calculations do not contain negative numbers.
	 */
	private void performValidations(List<TaxCalc.Pair<BigDecimal, String>> income) {
		validateCurrency(income);
		validateNotANegativeAmount(income);
	}

	/**
	 * @param income 
	 * throws an AppplicationException if finds a negative number in the gross Amounts.
	 */
	private void validateNotANegativeAmount(List<TaxCalc.Pair<BigDecimal, String>> income) {
		for (Pair<BigDecimal, String> pair : income) {
			if (pair.amount.signum() == -1) {
				throw new ApplicationException("Negative number not allowed!");
			}
		}
	}

	/**
	 * @param income 
	 * throws an AppplicationException if does not find a unique
	 *               currency across the list.
	 */
	private void validateCurrency(List<TaxCalc.Pair<BigDecimal, String>> income) {
		LinkedHashSet<String> currencies = new LinkedHashSet<String>();
		for (Pair<BigDecimal, String> pair : income) {
			currencies.add(pair.currency);
			if (currencies.size() > 1) {
				throw new ApplicationException("different currencies specified");
			}
		}
	}

	static class Pair<A, B> {
		final A amount;
		final B currency;

		Pair(A amount, B currency) {
			this.amount = amount;
			this.currency = currency;
		}

	}
}