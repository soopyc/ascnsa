package moe.soopy.ast10106.lab5c;

import java.util.Scanner;

public class Game {
	private boolean won;
	private int usedAttempts = 0;
	private final int availableAttempts;
	private final int funnyNumber;
	private Scanner scanner;

	// initialize variables and allocate dynamic resources.
	public Game(int attempts) {
		this.won = false;
		this.usedAttempts = 0;
		this.availableAttempts = attempts;
		this.funnyNumber = (int) (Math.random() * 1000);

		this.scanner = new Scanner(System.in);
	}

	public void run() {
//		System.out.printf("DEBUG: %d\n", funnyNumber);
		System.out.printf(
				"Welcome to the game. Your task is to find out a secret number generated by me. You have %d attempts, but for each attempt you can guess 2 numbers.\n",
				this.availableAttempts);

		// main loop
		// we repeat the loop until we run out of attempts.
		for (int attempt = 0; attempt < this.availableAttempts; attempt++) {
			System.out.printf("ATTEMPT %d\n", attempt + 1);
			int num1 = this.promptInteger("Please enter integer 1: ");
			int num2 = this.promptInteger("Please enter integer 2: ");

			// logic to determine what action to take.
			// in python i can write this with match cases since we also have tuples.
			// in java though, switch cases are a little too restrictive.
			// we could use switch cases if we want to output a line for both integers
			// - regardless but we'll follow the example just in case.
			if (num1 == this.funnyNumber || num2 == this.funnyNumber) {
				this.won = true;
				this.usedAttempts = attempt;
				break;
			} else if (num1 > this.funnyNumber && num2 > this.funnyNumber
					|| num1 < this.funnyNumber && num2 < this.funnyNumber) {
				System.out.printf("Both numbers are %s.\n", this.whatsWrongWith(num2));
			} else {
				System.out.printf("Integer 1 is %s while integer 2 is %s.\n", this.whatsWrongWith(num1),
						this.whatsWrongWith(num2));
			}
		}

		// game over logic
		scanner.close();
		if (this.won) {
			// the human brain is 1-indexed, we are 0-indexed.
			System.out.printf("Congratulations! You guessed the correct number in %d attempt(s).\n",
					this.usedAttempts + 1);
			System.out.printf("The secret number is %d.\n", this.funnyNumber);
		} else {
			System.out.println("Oh no! You have exhausted all possible attempts and lost the game.");
			System.out.printf("The number is %d.\n", this.funnyNumber);
		}
		return;
	}

	// trying to apply the DRY concept.
	// whatsWrongWith as in whatsWrongWwith the number provided? was it too large?
	// - or was it too small?
	// may be unnecessary.
	String whatsWrongWith(int num) {
		if (num > this.funnyNumber)
			return "too big";
		else
			return "too small";
	}

	// this is so that we can put things in one line instead of having 2 more lines
	// of boilerplate.
	// ... more like python brainrot though.
	int promptInteger(String prompt) {
		System.out.print(prompt);
		return this.scanner.nextInt();
	}
}
