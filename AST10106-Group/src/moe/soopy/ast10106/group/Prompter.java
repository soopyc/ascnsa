/**
 * @author H10007740
 */
package moe.soopy.ast10106.group;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Utility class to prompt user for data while handling invalid cases
 * gracefully.
 */
public class Prompter {
	// scanner types
	// we need to define several of them because of buffer weirdness, but two is
	// enough.
	private Scanner stringScanner;
	private Scanner numberScanner;

	public Prompter() {
		this.stringScanner = new Scanner(System.in);
		this.numberScanner = new Scanner(System.in);
	}

	/**
	 * Release resources when no more prompting is needed. Only run when the
	 * application is exiting.
	 */
	public void cleanup() {
		this.stringScanner.close();
		this.numberScanner.close();
	}

	/**
	 * Prompt the user for a string.
	 *
	 * @param prompt The prompt to ask the user.
	 * @return a string that the user has typed in.
	 */
	public String promptForString(String prompt) {
		while (true) {
			System.out.print("? " + prompt + " ");
			String input = this.stringScanner.nextLine();
			if (input.length() == 0) {
				System.err.println("Invalid input: input is required, please enter something.");
				continue;
			} else {
				return input;
			}
		}
	}

	/**
	 * Prompt the user for an integer, handling errors gracefully.
	 *
	 * @param prompt The prompt to ask the user.
	 * @return an integer that the user has typed in.
	 */
	public int promptForInteger(String prompt) {
		while (true) {
			try {
				System.out.print("? " + prompt + " (integer) ");
				return this.numberScanner.nextInt();
			} catch (InputMismatchException e) {
				System.err.println("Invalid input: input is not an integer.");
			}
		}
	}

	/**
	 * Prompt the user for a double, handling errors gracefully.
	 *
	 * @param prompt The prompt to ask the user.
	 * @return a double that the user has typed in.
	 */
	public double promptForDouble(String prompt) {
		while (true) {
			try {
				System.out.print("? " + prompt + " (integer) ");
				return this.numberScanner.nextDouble();
			} catch (InputMismatchException e) {
				System.err.println("Invalid input: input is not an integer.");
			}
		}
	}

	/**
	 * Prompt the user for a boolean (yes/no) question.
	 * 
	 * Accepted input: ["yes", "true"] for true; ["no", "false"] for false.
	 *
	 * @param prompt The prompt to ask the user.
	 * @return a boolean value from the user.
	 */
	public boolean promptForBoolean(String prompt) {
		while (true) {
			System.out.print("? " + prompt + " [y/n] ");
			String next = this.stringScanner.nextLine();
			switch (next.toLowerCase().charAt(0)) {
			case 'y':
				return true;
			case 't':
				return true;
			case 'n':
				return false;
			case 'f':
				return false;

			default:
				System.err.println("Invalid input: please enter either 'yes' or 'no'.");
			}
		}
	}
}
