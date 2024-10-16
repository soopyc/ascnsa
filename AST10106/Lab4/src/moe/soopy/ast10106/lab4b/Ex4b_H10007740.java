/**
 * This program calculates a grade based on a user inputted mark value.
 * 
 * @author H10007740
 */

package moe.soopy.ast10106.lab4b;

import java.util.Scanner;

public class Ex4b_H10007740 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// prompt the user for input
		System.out.print("Please enter a mark with 2 decimal points: ");
		double mark = scanner.nextDouble();

		// release resources
		scanner.close();

		// determine the grade by using the static method below.
		String grade = Ex4b_H10007740.determineGrade(mark);

		System.out.printf("Grade: %s", grade);
	}

	public static String determineGrade(double mark) {
		if (mark < 50) { // TODO: could we use switch cases here?
			return "FAIL";
		} else if (mark < 60) {
			return "PASS";
		} else if (mark < 75) {
			return "CREDIT";
		} else if (mark < 85) {
			return "DISTINCTION";
		} else { // everything else is handled, >= 85 is high distinction.
			return "HIGH DISTINCTION";
		}
	}
}
