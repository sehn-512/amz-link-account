package sea.scplus.consumer.vo.payment;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * 
When return code 201
{
  "_links": {
    "void": {
      "method": "POST",
      "href": "/pts/v2/payments/6139707629806620603002/voids"
    },
    "self": {
      "method": "GET",
      "href": "/pts/v2/payments/6139707629806620603002"
    }
  },
  "clientReferenceInformation": {
    "code": "TC50171_3"
  },
  "id": "6139707629806620603002",
  "orderInformation": {
    "amountDetails": {
      "totalAmount": "102.21",
      "authorizedAmount": "102.21",
      "currency": "USD"
    }
  },
  "paymentAccountInformation": {
    "card": {
      "type": "001"
    }
  },
  "paymentInformation": {
    "accountFeatures": {
      "category": "A"
    },
    "tokenizedCard": {
      "type": "001"
    }
  },
  "processorInformation": {
    "approvalCode": "831000",
    "networkTransactionId": "558196000003814",
    "transactionId": "558196000003814",
    "responseCode": "000",
    "avs": {
      "code": "Y",
      "codeRaw": "Y"
    }
  },
  "status": "AUTHORIZED",
  "submitTimeUtc": "2021-02-22T05:12:43Z"
}

 */
public class ResponsePaymentInstallmentInformation implements Serializable {
	
	public ResponsePaymentInstallmentInformation() {
		super();
	}
	
	private static final long serialVersionUID = -4754502940820722424L;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	additionalCosts;
	
	@JsonInclude(value = Include.NON_NULL)
	String additionalCostsPercentage;

	@JsonInclude(value = Include.NON_NULL)
	String amount;
	
	@JsonInclude(value = Include.NON_NULL)
	String amountFunded;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	amountRequestedPercentage;
	
	@JsonInclude(value = Include.NON_NULL)
	String annualFinancingCost;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	annualInterestRate;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	expenses;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	expensesPercentage;
	
	@JsonInclude(value = Include.NON_NULL)
	String fees;
	
	@JsonInclude(value = Include.NON_NULL)
	String feesPercentage;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	frequency;
	
	@JsonInclude(value = Include.NON_NULL)
	String insurance;
	
	@JsonInclude(value = Include.NON_NULL)
	String insurancePercentage;
	
	@JsonInclude(value = Include.NON_NULL)
	String invoiceData;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	monthlyInterestRate;
	
	@JsonInclude(value = Include.NON_NULL)
	String planType;
	
	@JsonInclude(value = Include.NON_NULL)
	String sequence;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	taxes;
	
	@JsonInclude(value = Include.NON_NULL)
	String taxesPercentage;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	totalAmount;
	
	@JsonInclude(value = Include.NON_NULL)
	int totalCount;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	minimumTotalCount;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	maximumTotalCount;
	
	@JsonInclude(value = Include.NON_NULL)
	String 		firstInstallmentAmount;
	
	@JsonInclude(value = Include.NON_NULL)
	String 	firstInstallmentDate;

	public String getAdditionalCosts() {
		return additionalCosts;
	}

	public void setAdditionalCosts(String additionalCosts) {
		this.additionalCosts = additionalCosts;
	}

	public String getAdditionalCostsPercentage() {
		return additionalCostsPercentage;
	}

	public void setAdditionalCostsPercentage(String additionalCostsPercentage) {
		this.additionalCostsPercentage = additionalCostsPercentage;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAmountFunded() {
		return amountFunded;
	}

	public void setAmountFunded(String amountFunded) {
		this.amountFunded = amountFunded;
	}

	public String getAmountRequestedPercentage() {
		return amountRequestedPercentage;
	}

	public void setAmountRequestedPercentage(String amountRequestedPercentage) {
		this.amountRequestedPercentage = amountRequestedPercentage;
	}

	public String getAnnualFinancingCost() {
		return annualFinancingCost;
	}

	public void setAnnualFinancingCost(String annualFinancingCost) {
		this.annualFinancingCost = annualFinancingCost;
	}

	public String getAnnualInterestRate() {
		return annualInterestRate;
	}

	public void setAnnualInterestRate(String annualInterestRate) {
		this.annualInterestRate = annualInterestRate;
	}

	public String getExpenses() {
		return expenses;
	}

	public void setExpenses(String expenses) {
		this.expenses = expenses;
	}

	public String getExpensesPercentage() {
		return expensesPercentage;
	}

	public void setExpensesPercentage(String expensesPercentage) {
		this.expensesPercentage = expensesPercentage;
	}

	public String getFees() {
		return fees;
	}

	public void setFees(String fees) {
		this.fees = fees;
	}

	public String getFeesPercentage() {
		return feesPercentage;
	}

	public void setFeesPercentage(String feesPercentage) {
		this.feesPercentage = feesPercentage;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public String getInsurancePercentage() {
		return insurancePercentage;
	}

	public void setInsurancePercentage(String insurancePercentage) {
		this.insurancePercentage = insurancePercentage;
	}

	public String getInvoiceData() {
		return invoiceData;
	}

	public void setInvoiceData(String invoiceData) {
		this.invoiceData = invoiceData;
	}

	public String getMonthlyInterestRate() {
		return monthlyInterestRate;
	}

	public void setMonthlyInterestRate(String monthlyInterestRate) {
		this.monthlyInterestRate = monthlyInterestRate;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getTaxes() {
		return taxes;
	}

	public void setTaxes(String taxes) {
		this.taxes = taxes;
	}

	public String getTaxesPercentage() {
		return taxesPercentage;
	}

	public void setTaxesPercentage(String taxesPercentage) {
		this.taxesPercentage = taxesPercentage;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getMinimumTotalCount() {
		return minimumTotalCount;
	}

	public void setMinimumTotalCount(String minimumTotalCount) {
		this.minimumTotalCount = minimumTotalCount;
	}

	public String getMaximumTotalCount() {
		return maximumTotalCount;
	}

	public void setMaximumTotalCount(String maximumTotalCount) {
		this.maximumTotalCount = maximumTotalCount;
	}

	public String getFirstInstallmentAmount() {
		return firstInstallmentAmount;
	}

	public void setFirstInstallmentAmount(String firstInstallmentAmount) {
		this.firstInstallmentAmount = firstInstallmentAmount;
	}

	public String getFirstInstallmentDate() {
		return firstInstallmentDate;
	}

	public void setFirstInstallmentDate(String firstInstallmentDate) {
		this.firstInstallmentDate = firstInstallmentDate;
	}
	
	

}
