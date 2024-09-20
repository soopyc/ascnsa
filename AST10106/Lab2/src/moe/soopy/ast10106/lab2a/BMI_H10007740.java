/**
 * Simple program to calculate the BMI value of a user.
 * - done during tutorial time, see git timestamp.
 * @author (H10007740)
 * @date 2024-09-20
 */
package moe.soopy.ast10106.lab2a;

import java.util.Scanner;
import java.lang.Math;
import java.text.DecimalFormat;

public class BMI_H10007740 {
	public static void main(String[] args) {
		// Initialize a scanner
		Scanner scanner = new Scanner(System.in);

		// https://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
		DecimalFormat dFormat = new DecimalFormat("#.##");

		// Get user input for their height in meters.
		System.out.print("Enter your height in meters: ");
		double height = scanner.nextDouble();
		// TODO: maybe check for extreme values like cm here?

		// Get user input for their weight in kilograms.
		System.out.print("Enter your weight in kilograms: ");
		double weight = scanner.nextDouble();

		// Call static function to calculate BMI
		double bmi = BMI_H10007740.calculateBMI(height, weight);

		// Release resources and print the result.
		scanner.close();
		System.out.println("Your BMI is: " + dFormat.format(bmi));
	}

	static double calculateBMI(double height, double weight) {
		// Use the standard formula for calculating BMI
		// adopt standard library method Power (Math.pow)
		return weight / (Math.pow(height, 2));
	}
}
