package moe.soopy.ast10106.lab5b;

import java.util.Scanner;

public class GCD_H10007740 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("First integer: ");
		int x = scanner.nextInt();
		System.out.print("Second integer: ");
		int y = scanner.nextInt();
		
//		int gcd = GCD_H10007740.computeGCDWithLoop(x, y);
		int gcd = GCD_H10007740.computeGCDWithRecursion(x, y);
		
		System.out.printf("The GCD of %d and %d is %d\n", x, y, gcd);
		if (gcd == 1) {
			System.out.println("They are co-primes.");
		} else {
			System.out.println("They are NOT co-primes.");
		}
	}
	
	// this is more disorienting than the recursion based approach
	static int computeGCDWithLoop(int x, int y) {
		int a = x, b = y; // not needed, but i don't like the idea of mutating the provided variables.
		while (true) {
			System.out.printf("Calculating (%d / %d) => ", a, b);
			int quotient = Math.floorDiv(a, b);
			int remainder = a % b;		
			System.out.printf("quotient=%d; remainder=%d\n", quotient, remainder);

			if (remainder == 0) {
				return b;
			}
			// swap out the operands
			a = b;
			b = remainder;
		}
	}

	static int computeGCDWithRecursion(int x, int y) {
		System.out.printf("Calculating (%d / %d) => ", x, y);
		int quotient = Math.floorDiv(x, y);
		int remainder = x % y;
		
		System.out.printf("quotient=%d; remainder=%d\n", quotient, remainder);
		if (remainder == 0) {
			return y;
		} else {
			return GCD_H10007740.computeGCDWithRecursion(y, remainder);
		}
	}
}
