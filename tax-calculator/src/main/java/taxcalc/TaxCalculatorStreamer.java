package taxcalc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class TaxCalculatorStreamer {
	List<TaxCalculation> income = null;
	BigDecimal taxPercentage = null;
	
	TaxCalculatorStreamer(List<TaxCalculation> income, BigDecimal taxPercentage) {
		this.income = income;
		this.taxPercentage = taxPercentage;
	}

	public TaxCalculation netAmount(List<TaxCalculation> income, BigDecimal taxPercentage) {
		TaxCalculation returnTaxCalc = null;
		
		if (income== null || income.stream().findAny().isEmpty()) {
			throw new ApplicationException("no income provided");
		}
		if (!income.stream().allMatch(s -> s.getCurrency().equals(income.get(0).getCurrency()))) {
			throw new ApplicationException("not matching currencies");
		}
		if (income.stream().anyMatch(s -> s.getAmount().signum() == -1)) { 
			throw new ApplicationException("negative amount"); 
		}
		BigDecimal sum = income.stream()
				.map(x -> x.getAmount())
				.reduce(BigDecimal.ZERO, BigDecimal::add);		
		returnTaxCalc = new TaxCalculation(sum.subtract(sum.divide(taxPercentage)), income.get(0).getCurrency());
		return returnTaxCalc;
	}

	public static void main(String args[]) {
		List<TaxCalculation> income = Arrays.asList(
				new TaxCalculation(new BigDecimal(40), "EUR"),
				new TaxCalculation(new BigDecimal(50), "EUR"), 
				new TaxCalculation(new BigDecimal(60), "EUR"));
		TaxCalculatorStreamer calcDemo = new TaxCalculatorStreamer(income, new BigDecimal(10));
		calcDemo.netAmount(income, new BigDecimal(10));
	}

	/*
	 * private TaxCalculation performValidations(TaxCalculation taxCalculation) {
	 * validateCurrency(taxCalculation); validateNotANegativeAmount(taxCalculation);
	 * }
	 */
}
