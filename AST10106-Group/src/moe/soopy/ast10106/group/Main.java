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

		// unfinished
		while (true) {
			displayMenu();
			System.out.print("What function do you need? ");
			String button = sc.nextLine();
			switch (button) {

			case "a":
				createRecord();
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
			case "e":
				// method();
				break;
			case "z":
				System.out.println("End! Goodbye!");
				System.exit(0);

			}
		}
	}

	// unfinished
	public static void displayMenu() {
		System.out.println("Welcome!");
		System.out.println("a. Record Daily Expnse and Income");
		System.out.println("b. Show Record");
		System.out.println("c. Find Cash Flow over Period of Time");
		System.out.println("d. Tax Calculation");
		System.out.println("e. Display Spending Catergories");

		System.out.println("z. Exit");
	}

	// unfinished
	public static void createRecord() {
		Scanner sc = new Scanner(System.in);
		askRecordInfo();
		System.out.println("It is a income or expense? ");
		System.out.println("1: Income 2: Expense");
		int checkIE = sc.nextInt();
		String IE;

		while (true) {
			if (checkIE == 1) {
				 IE = "income";
				break;
			} else if (checkIE == 2) {
				 IE = "expense";
				break;
			}
		}
		Record rec = new Record(IE , 12.0, "Beauty", LocalDate.now(), "test");

		System.out.println(rec.toString());
	}

	public static void askRecordInfo() {

	}
}
