package moe.soopy.ast10106.lab8a;

import java.util.Scanner;

public class FindCharFromWord_H10007740 {
	public static void main(String[] args) {
		// initialize scanner
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a sentence separated with spaces: ");
		String original = scanner.nextLine().strip(); // strip to remove whitespaces
		System.out.print("Enter a character to find: ");
		char finder = scanner.nextLine().strip().charAt(0); // same as above.

		System.out.printf("Words containing the character %s: ", finder);
		for (String findee : original.split(" ")) { // split the string with spaces.
			// for each string `findee`, check if it contains a characrer `finder`.
			// if it exists, print it out.
			if (findee.toLowerCase().contains(Character.toString(finder))) {
				System.out.printf("%s ", findee);
			}
		}
		scanner.close();
	}
}
