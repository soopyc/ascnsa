/**
 * This program reverses a 3-digit integer using only the modulo operation.
 * 
 * @author H10007740
 */

package moe.soopy.ast10106.lab3c;

import java.util.Scanner;

public class DigitReversal_H10007740 {

	public static void main(String[] args) {
		// initialize scanner
		Scanner scanner = new Scanner(System.in);

		// ask user for an integer.
		System.out.print("Enter a 3-digit integer: ");
		int digits = scanner.nextInt();

		// close the scanner
		scanner.close();

		// some bounds check
		if (digits > 999 || digits < 100) {
			System.out.println("Error: the integer is not 3 digits.");
			return;
		}

		int int0 = digits % 10; // get ones digit
		int int1 = digits / 10 % 10; // get tens digit
		int int2 = digits / 100 % 10; // get hundreds digit

		// simply print out the digits in reverse order.
		System.out.printf("The number with inversed digits is: %d%d%d", int0, int1, int2);
	}

}
