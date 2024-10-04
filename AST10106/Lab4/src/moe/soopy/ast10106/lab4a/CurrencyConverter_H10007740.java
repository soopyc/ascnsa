/**
 * This program shows the currency rate to HKD from a given set of currencies.
 * i.e. Program outputs "7.846 HKD = 1.000 USD" when user enters USD.
 * @author H10007740
 */

package moe.soopy.ast10106.lab4a;

import java.util.Scanner;

public class CurrencyConverter_H10007740 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the source currency: ");
		String currency = scanner.next();
		
		// define currency rates
		double rate = -1; // define fallback value here to prevent use before definition.
		if (currency.equals("USD")) {
			rate = 7.7658237;
		} else if (currency.equals("CNY")) {
			rate = 1.1064964;
		} else if (currency.equals("SGD")) {
			rate = 5.9917295;
		}
		
		if (rate > 0)
			System.out.printf("%.3f %s = %.3f HKD", 1.0, currency, rate);
		else 
			System.out.println("No such currency.");
		
		// close resources
		
	}
}
