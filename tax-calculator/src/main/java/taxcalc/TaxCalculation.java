package taxcalc;

import java.math.BigDecimal;

public class TaxCalculation {
	
	
	TaxCalculation(BigDecimal amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}
	
	public BigDecimal amount;
	public String currency;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

}