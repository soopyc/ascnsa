/*
 *  @author H10007981
 */

package moe.soopy.ast10106.group;

import moe.soopy.ast10106.group.format.Metadata;
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

	static OneBigFile file;

	public double totalincome = 0.0;
	public double totaldeductions = 0.0;
	public double Nettaxableincome = 0.0;
	public double Netincome = 0.0;
	public double afterTaxIncome = 0.0;
	double[] months = new double[12];

	// create object here
	SalariesTaxCalculate callMethods = new SalariesTaxCalculate();

	/**
	 * check how many months records in total
	 */
	public void check_recordsDate() {
		int count = 0;
		int i = 0;// i equal to index of array
		double amounts = 0.0;
		for (Record rec : file.getRecordsByType("income")) {
			/*
			 * loop one time = one day ,then 30 days = one month,thus index of months add
			 * one
			 */

			amounts += rec.getCurrentAmount();
			count++;// count loop times which is equal to day's income
			if (count == 30) {
				months[i] = amounts;// move to next index
				i++;// index move right
				count = 0; // reset count here
				amounts = 0.0;// reset amounts here

			}
		}
		// case days less than a month
		months[i] = amounts;// move to next index

	}

	/**
	 * check each index of months array whether has element
	 * 
	 * @return the first empty index or -1 when all index have elements
	 */
	public int check_elementOfMonths() {
		for (int i = 0; i <= months.length; i++) {
			if (months[i] == 0) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * calculate the annual income or Fill in the missing months
	 * 
	 * @return annual income
	 */
	public double makeUp_monthlyRecord() {
		double sum = 0.0;
		if (callMethods.check_elementOfMonths() == -1) {
			for (double element : months) {
				sum += element;
			}
			return totalincome = sum;
		} else {
			for (double element : months) {
				sum += element;
			}
			sum = 12 * (sum / callMethods.check_elementOfMonths() + 1);
		}

		return totalincome = sum;

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

	public double Netincome() {
		totaldeductions = 0.15 * totalincome; // standard tax rate = 0.15
		Netincome = totalincome - totaldeductions;
		return Netincome;// Net income = Total income - Total deductions.
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
	public double Nettaxableincome() {// calculate the net income in Progressive growth rate
		double Nettaxableincome = 0.0;
		if (totalincome < 5000) {
			Nettaxableincome = totalincome;
		} else if (totalincome >= 5000) {
			Nettaxableincome = 0.02 * 5000 + (totalincome - 5000);
		} else if (totalincome >= 10000) {
			Nettaxableincome = 0.06 * 5000 + 0.02 * 5000 + (totalincome - 10000);
		} else if (totalincome >= 15000) {
			Nettaxableincome = 0.1 * 5000 + 0.06 * 5000 + 0.02 * 5000 + (totalincome - 15000);
		} else if (totalincome >= 20000) {
			Nettaxableincome = 0.14 * 5000 + 0.1 * 5000 + 0.06 * 5000 + 0.02 * 5000 + (totalincome - 20000);
		} else
			Nettaxableincome = 0.14 * 5000 + 0.1 * 5000 + 0.06 * 5000 + 0.02 * 5000 + 0.17 * (totalincome - 20000);
		return Nettaxableincome;
	}

	/**
	 * The amount of tax you pay depends on your personal situation and income level
	 * 
	 * @return total income for the year after deducting salaries tax
	 */
	public double afterTaxIncome() {/*
									 * check user annual income is over the Preferential tax or not if the user
									 * annual income is over the Preferential tax rate,calculate Salaries Tax in
									 * standard rate
									 */

		if (file.metadata.isMarried() == false
				&& totalincome >= 2022000) {/*
											 * case single and total income over the Preferential tax rate
											 */
			afterTaxIncome = Netincome;

		} /*
			 * case married ,but without child and total income over the Preferential tax
			 * rate
			 */

		else if (file.metadata.isMarried() == true && file.metadata.getChildrenCount() == 0 && totalincome >= 3144000) {
			afterTaxIncome = Netincome;
		} /*
			 * case married ,have one child and annual income over the Preferential tax rate
			 */
		else if (file.metadata.isMarried() == true && file.metadata.getChildrenCount() == 1 && totalincome >= 4249000) {
			afterTaxIncome = Netincome;
		} /*
			 * case married ,have two children and annual income over the Preferential tax
			 * rate
			 */
		else if (file.metadata.isMarried() == true && file.metadata.getChildrenCount() == 2 && totalincome >= 5708000) {
			afterTaxIncome = Netincome;
		} /*
			 * case married ,have three children and annual income over the Preferential tax
			 * rate
			 */
		else if (file.metadata.isMarried() == true && file.metadata.getChildrenCount() == 3 && totalincome >= 7918000) {
			afterTaxIncome = Netincome;
		}

		else {// if annual income do not over the Preferential tax rate
			afterTaxIncome = Nettaxableincome;// net taxable income equal to net taxable income
			if ((double) Netincome < Nettaxableincome) {// if Net income < Net taxable income
				afterTaxIncome = Netincome; // net income equal to real income
			}
		}
		return afterTaxIncome;
	}

	public void print_afterTaxIncome() {
		// get local time here
		LocalDate date = LocalDate.now();
		System.out.printf("\n\tFile Number:%s \n\tIssue date:&s \n\tYour after-tax income is:%s \n",
				UUID.randomUUID().toString(), date, afterTaxIncome);
	}

}
// only calculate one year salaries tax
