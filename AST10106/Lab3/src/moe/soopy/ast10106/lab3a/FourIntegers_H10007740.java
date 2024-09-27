/**
 * This simple program asks the user 4 integers and averages them in 4 decimal points.
 * @author H10007740
 * 
 */

package moe.soopy.ast10106.lab3a;

import java.util.Scanner;

public class FourIntegers_H10007740 {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input 4 integers on each line:");
		
		// This could be better done with using an array but we haven't reached that point yet.
		float num1 = (float)scanner.nextInt(); // guideline enforces integer input.
		float num2 = (float)scanner.nextInt();
		float num3 = (float)scanner.nextInt();
		float num4 = (float)scanner.nextInt();
		
		// close the scanner after use.
		scanner.close();
		
		// do the calculation
		float average = (num1 + num2 + num3 + num4) / 4; // is there a built-in function to do this? not that i'd use this now
		System.out.printf("You have provided the numbers: %.1f, %.2f, %.3f, %.4f\n", num1, num2, num3, num4);
		System.out.printf("The average of the numbers is: %.4f\n", average);
	}
}
