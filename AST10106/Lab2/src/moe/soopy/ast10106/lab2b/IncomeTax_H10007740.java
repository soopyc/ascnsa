/**
 * Simple program to calculate the net income for a user.
 * - done during tutorial time. see git timestamp.
 * @author (H10007740)
 * @date 2024-09-20
 */

package moe.soopy.ast10106.lab2b;

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
		double annualIncome = monthlyIncome * 12; // First start with th annual income.
		mpf = annualIncome * 0.1; // then get the mpf using the annual income
		taxPayable = (annualIncome + allowance) * 0.12; // then get payable tax
		double finalIncome = annualIncome - mpf - taxPayable; // finally get net income

		// Print out the result!
		// but before that close the scanner.
		scanner.close();
		// Using the string format function we can more effectively print out the value without concatenation.
		System.out.println(String.format("Hello %s.\nYour Annual Income is $%s", name, finalIncome));
	}
}
