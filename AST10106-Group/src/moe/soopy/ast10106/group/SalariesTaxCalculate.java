/*
 *  @author H10007981
 *  @author H10007740
 */

package moe.soopy.ast10106.group;

import moe.soopy.ast10106.group.format.OneBigFile;
import moe.soopy.ast10106.group.format.Record;

import java.time.LocalDate;
import java.util.UUID;

public class SalariesTaxCalculate {
	// Net taxable income = total income - total deductions - total tax exemptions.
	// Net income = Total income - Total deductions. // standard tax rate
	// The lower of the two is levied.

	// Those whose annual income is close to the following levels are subject to
	// standard tax rate:
	// 1. single: 2,022,000
	// 2. married: 3,144,000
	// 3. Married with one child^: 4,249,000
	// 4. Married with two children^: 5,708,000
	// 5. Married with three children^: 7,918,000

	OneBigFile file;

	public double totalincome = 0.0;
	public double Tax_Exemption = 132000.0;
	double[] salaryPerMonth;
	boolean[] monthsWithSalary;

	public SalariesTaxCalculate(OneBigFile file) {
		this.file = file;
	}

	/**
	 * Calculate salaries and store to instance arrays.
	 */
	public void calculateSalary() {
		this.salaryPerMonth = new double[12];
		this.monthsWithSalary = new boolean[12];
		LocalDate now = LocalDate.now();

		for (Record rec : this.file.getRecordsByType("income")) {
			// check if record is salary
			if (!rec.category.toLowerCase().contains("salary"))
				continue; // skip record if it is not salary
			if (rec.date.getYear() != now.getYear())
				continue; // skip record if it is not from this year.
			int recordMonth = rec.date.getMonth().getValue() - 1; // to get the correct month index
			this.monthsWithSalary[recordMonth] = true; // used to calculate how much we need to extrapolate
			this.salaryPerMonth[recordMonth] += rec.amount;
		}
	}

	/**
	 * calculate the annual income or Fill in the missing months
	 * 
	 * @return annual income
	 */
	public void makeUpTotalIncome() {
		// calculate how many months contains salary records
		int monthsWithSalaryRecords = 0;
		for (boolean month : this.monthsWithSalary)
			monthsWithSalaryRecords += month == true ? 1 : 0;
		double salaryAverage = 0;
		for (double month : this.salaryPerMonth)
			salaryAverage += month;
		salaryAverage /= monthsWithSalaryRecords; // get the average salary per month with records
		if (Double.isNaN(salaryAverage))
			salaryAverage = 0;
		// extrapolate predicted salary for the entire year
		this.totalincome = salaryAverage * 12;
	}

	/**
	 * How to calculate salaries tax in HK.
	 * 
	 * References:
	 * https://www.gov.hk/tc/residents/taxes/taxfiling/taxrates/salariesrates.htm#pr
	 * 
	 * @return total income for the year after deducting salaries tax in standard
	 *         rate
	 */

	public double GetTaxBasic() {// calculate the salaries tax in standard rate
		return 0.15 * totalincome;
	}

	/**
	 * How to calculate salaries tax in HK.
	 * 
	 * References:
	 * https://www.gov.hk/tc/residents/taxes/taxfiling/taxrates/salariesrates.htm#pr
	 * 
	 * @return total income for the year after deducting salaries tax in Progressive
	 *         growth rate
	 * 
	 */
	public double GetTaxInProgressive() {// calculate the salaries tax in Progressive growth rate
		double ActualAmount = totalincome - Tax_Exemption;
		if (ActualAmount < 50000) {
			return (ActualAmount) * 0.02;
		} else if (ActualAmount <= 100000) {
			return 0.02 * 50000 + 0.06 * (ActualAmount - 50000);
		} else if (ActualAmount <= 150000) {
			return 0.02 * 50000 + 0.06 * 50000 + 0.1 * (ActualAmount - 100000);
		} else if (ActualAmount <= 200000) {
			return 0.1 * 50000 + 0.06 * 50000 + 0.02 * 50000 + 0.14 * (ActualAmount - 150000);
		} else {
			return 0.14 * 50000 + 0.1 * 50000 + 0.06 * 50000 + 0.02 * 50000 + 0.17 * (ActualAmount - 200000);
		}
	}

	/**
	 * The amount of tax you pay depends on your personal situation and income level
	 * 
	 * @return total income for the year after deducting salaries tax
	 */
	public double calculateSalariesTax() {/*
											 * check user annual income is over the Preferential tax or not if the user
											 * annual income is over the Preferential tax rate,calculate Salaries Tax in
											 * standard rate
											 */

		double salariesTax = 0.0;
		if (this.file.metadata.isMarried() == false
				&& totalincome >= 2022000) {/*
											 * case single and total income over the Preferential tax rate
											 */
			salariesTax = this.GetTaxBasic();

		} /*
			 * case married ,but without child and total income over the Preferential tax
			 * rate
			 */

		else if (this.file.metadata.isMarried() == true && this.file.metadata.getChildrenCount() == 0
				&& totalincome >= 3144000) {
			salariesTax = this.GetTaxBasic();
		} /*
			 * case married ,have one child and annual income over the Preferential tax rate
			 */
		else if (this.file.metadata.isMarried() == true && this.file.metadata.getChildrenCount() == 1
				&& totalincome >= 4249000) {
			salariesTax = this.GetTaxBasic();
		} /*
			 * case married ,have two children and annual income over the Preferential tax
			 * rate
			 */
		else if (this.file.metadata.isMarried() == true && this.file.metadata.getChildrenCount() == 2
				&& totalincome >= 5708000) {
			salariesTax = this.GetTaxBasic();
		} /*
			 * case married ,have three children and annual income over the Preferential tax
			 * rate
			 */
		else if (this.file.metadata.isMarried() == true && this.file.metadata.getChildrenCount() == 3
				&& totalincome >= 7918000) {
			salariesTax = this.GetTaxBasic();
		}

		else {// if annual income do not over the Preferential tax rate
			salariesTax = this.GetTaxInProgressive();//

		}

		return salariesTax;
	}

	public void run() {
		this.calculateSalary();
		this.makeUpTotalIncome();

		// get local time here
		LocalDate date = LocalDate.now();
		System.out.printf("\n\tFile Number:%s ", UUID.randomUUID().toString());
		System.out.printf("\n\tIssue date:%s", date);
		System.out.printf("\n\tYour salaries tax is:%s", this.calculateSalariesTax());
		System.out.printf("\n\tYour annual net income is:%s\n", totalincome - this.calculateSalariesTax());
	}

}
// only calculate one year salaries tax
