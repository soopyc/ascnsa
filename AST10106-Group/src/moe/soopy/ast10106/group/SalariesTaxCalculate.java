/*
 *  @author H10007981
 */

package moe.soopy.ast10106.group;

import moe.soopy.ast10106.group.format.Metadata;
import moe.soopy.ast10106.group.format.OneBigFile;
import java.time.LocalDateTime;

// Date class , array list change to number
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

	/*
	 * How to calculate salaries tax in HK.
	 * 
	 * References:
	 * https://www.gov.hk/tc/residents/taxes/taxfiling/taxrates/salariesrates.htm#pr
	 */

	static OneBigFile file;

	public double totalincome = 0.0;// modifiable
	public double totaldeductions = 0.0;// modifiable
	public double Nettaxableincome = 0.0;// modifiable
	public double Netincome = 0.0;// modifiable
	public double realIncome = 0.0;// modifiable

	// create main object here
	// Main object = new Main();

	// get current date and time
	LocalDateTime now = LocalDateTime.now();

	// get date and time past a year
	LocalDateTime minusYear = now.minusYears(1);

	public SalariesTaxCalculate(double totalincome) {
		this.totalincome = totalincome;

	}

	public double Netincome() {
		totaldeductions = 0.15 * totalincome; // standard tax rate = 0.15
		Netincome = totalincome - totaldeductions;
		return Netincome;// Net income = Total income - Total deductions.
	}

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

	public double realIncome() {/*
								 * check user annual income is over the Preferential tax or not if the user
								 * annual income is over the Preferential tax rate,calculate Salaries Tax in
								 * standard rate
								 */

		if (file.metadata.isMarried() == false
				&& totalincome >= 2022000) {/*
											 * case single and total income over the Preferential tax rate
											 */
			realIncome = Netincome;

		} /*
			 * case married ,but without child and total income over the Preferential tax
			 * rate
			 */

		else if (file.metadata.isMarried() == true && file.metadata.getChildrenCount() == 0 && totalincome >= 3144000) {
			realIncome = Netincome;
		} /*
			 * case married ,have one child and annual income over the Preferential tax rate
			 */
		else if (file.metadata.isMarried() == true && file.metadata.getChildrenCount() == 1 && totalincome >= 4249000) {
			realIncome = Netincome;
		} /*
			 * case married ,have two children and annual income over the Preferential tax
			 * rate
			 */
		else if (file.metadata.isMarried() == true && file.metadata.getChildrenCount() == 2 && totalincome >= 5708000) {
			realIncome = Netincome;
		} /*
			 * case married ,have three children and annual income over the Preferential tax
			 * rate
			 */
		else if (file.metadata.isMarried() == true && file.metadata.getChildrenCount() == 3 && totalincome >= 7918000) {
			realIncome = Netincome;
		}

		else {// if annual income do not over the Preferential tax rate
			realIncome = Nettaxableincome;// net taxable income equal to net taxable income
			if ((double) Netincome < Nettaxableincome) {// if Net income < Net taxable income
				realIncome = Netincome; // net income equal to real income
			}
		}
		return realIncome;
	}
	// calculate the whole year salariesTax, then if the salaries is not record 12
	// months, thus need to complement to 12 months

}
// only calculate one year tax
// static method 
