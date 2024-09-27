/**
 * Simple program to calculate the net income for a user.
 * - derivative of lab2b, see that code for the old version.
 *   - adds prettier table-like output
 * @author (H10007740)
 * @date 2024-09-27
 */

package moe.soopy.ast10106.lab3b;

import java.util.Scanner;

public class IncomeTax_H10007740 {
	public static void main(String[] args) {
		// Class initialization
		Scanner scanner = new Scanner(System.in);

		// Variable definition
		String name = "";
		double monthlyIncome = 0.0;
		double mpf = 0.0;
		double allowance = 0.0;
		double taxPayable = 0.0;

		// Query the user for their input
		// Comments only shown for the first question because they are self-explanatory.
		System.out.print("What is your name? "); // print prompt
		name = scanner.nextLine(); // read user input until next line

		System.out.print("What is your monthly income? ");
		monthlyIncome = scanner.nextDouble(); // read user input until \n, treat it as a double.

		System.out.print("What is your allowance? ");
		allowance = scanner.nextDouble();

		// Calculate the values
		double annualIncome = monthlyIncome * 12; // First start with the annual income.
		mpf = annualIncome * 0.1; // then get the mpf using the annual income
		taxPayable = (annualIncome + allowance) * 0.12; // then get payable tax
		double finalIncome = annualIncome - mpf - taxPayable; // finally get net income

		// Print out the result!
		// but before that close the scanner.
		scanner.close();

		// Instead of using the string format function, we can just call printf.
		// First print the identification message.
		System.out.printf("Hello, %s\nYour annual income is: $%8.2f\n\n\n", name, annualIncome);
		// Then print a static heading
		System.out.println("ANNUAL INCOME STATEMENT DETAILS");
		System.out.println("*******************************");

		// Afterwards do a printout of the statements with dynamic content.
		// Since the longest "heading cell" string is 14 characters long,
		// - we'll do a 15 space right-padded (left aligned) string + content. (the +1
		// - space is for clarity.)
		// This is divided into different lines for clarity. If we do it in a single
		// - line it looks horrendous.
		System.out.printf("%-15s:  %8s\n", "NAME", name);
		System.out.printf("%-15s: $%10.1f\n", "MONTHLY INCOME", monthlyIncome);
		System.out.printf("%-15s: $%10.1f\n", "ALLOWANCE", allowance);
		System.out.printf("%-15s: $%10.1f\n", "TAX PAYABLE", taxPayable);
		System.out.printf("%-15s: $%10.1f\n", "MPF", mpf);
		System.out.printf("%-15s: $%10.1f\n", "ANNUAL INCOME", annualIncome);

	}
}
