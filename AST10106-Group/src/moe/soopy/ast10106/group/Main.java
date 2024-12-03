package moe.soopy.ast10106.group;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import moe.soopy.ast10106.group.format.Metadata;
import moe.soopy.ast10106.group.format.OneBigFile;
import moe.soopy.ast10106.group.format.Record;

public class Main {
	final static String FILENAME = "bmtool.obf";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// FOR TESTING ONLY.
		// TODO: feel free to copy some of the code, but remember to delete this entire
		// block for the main program.

//		OneBigFile testFile;
//		try {
//			testFile = OneBigFile.load(FILENAME);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			System.err.println("Could not load file, creating a new one.");
//			testFile = new OneBigFile(new Metadata("Bob the Tester", OffsetDateTime.now(), false,0), null);
//			try {
//				testFile.write(FILENAME);
//			} catch (IOException e1) {
//				System.err.println("Could not write to file.");
//				return;
//			}
//		}
//		System.out.printf("Last modified: %s\n", testFile.metadata.getLastModifiedPretty());
//		testFile.records.add(new Record("income", 12.0, "Others", LocalDate.now(), "test"));
//		testFile.metadata.update(); // call this whenever you update records.
//
//		try {
//			testFile.write(FILENAME);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			System.err.println("Failed to save file.");
//		}

		while (true) {
			displayMenu();
			System.out.print("What function do you need? ");
			String button = sc.nextLine();
			switch (button) {

			case "a":
				// method();
				break;
			case "b":
				// method();
				break;
			case "c":
				// method();
				break;
			case "d":
				// method();
				break;
			case "z":
				System.out.println("End! Goodbye!");
				System.exit(0);

			}

		}
	}

	public static void displayMenu() {
		System.out.println("Welcome!");
		System.out.println("a. Record Daily Expnse and Income");
		System.out.println("b. Show Record");
		System.out.println("c. Find Cash Flow over Period of Time");
		System.out.println("d. Tax Calculation");
		System.out.println("e. Display Spe");

		System.out.println("z. Exit");
	}

}
