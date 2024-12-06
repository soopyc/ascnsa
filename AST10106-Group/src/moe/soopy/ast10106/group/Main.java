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
	static OneBigFile file;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// FOR TESTING ONLY.
		// TODO: feel free to copy some of the code, but remember to delete this entire
		// block for the main program.

		try {
			file = OneBigFile.load(FILENAME);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Could not load file, creating a new one.");
			file = new OneBigFile(new Metadata("Bob the Tester", OffsetDateTime.now(), false,0), null);
			try {
				file.write(FILENAME);
			} catch (IOException e1) {
				System.err.println("Could not write to file.");
				return;
			}
		}
//		System.out.printf("Last modified: %s\n", testFile.metadata.getLastModifiedPretty());
//		testFile.records.add(new Record("income", 12.0, "Others", LocalDate.now(), "test"));
//		testFile.metadata.update(); // call this whenever you update records.
//


		// unfinished
		while (true) {
			displayMenu();
			System.out.print("What function do you need? ");
			String button = sc.nextLine();
			switch (button) {
			case "1":
				inputMetadata();
				break;
			case "2":
				retrieveMetadata();
				break;
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
	public static void inputMetadata() {
		Scanner sc = new Scanner(System.in);
		String Name;
		boolean Married;
		int Children;
		System.out.println("What is your name? ");
		Name = sc.next();
		file.metadata.setName(Name);
		System.out.println("Are you married? Press 1 for yes and 2 for no");
		Married = sc.next()== "1";
		file.metadata.setMarriageStatus(Married);
		System.out.println("How many children do you have?");
		Children = sc.nextInt();
		file.metadata.setChildrenCount(Children);
	}
	public static void retrieveMetadata() {
		System.out.println("----------------------");
		System.out.println("Name = " + file.metadata.getName());
		System.out.println("Married? " + file.metadata.isMarried());
		System.out.println("Number of Children: " + file.metadata.getChildrenCount());
		System.out.println("----------------------");
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
		String IE;
		askRecordInfo();
		System.out.println("It is a income or expense? ");
		System.out.println("1: Income 2: Expense");

		while (true) {
			int checkIE = sc.nextInt();
			if (checkIE == 1) {
				IE = "income";
				break;
			} else if (checkIE == 2) {
				IE = "expense";
				break;
			} else {
				System.out.print("Either 1 or 2: ");
			}
		}
		Record rec = new Record(IE, 30.0, "Food", LocalDate.now(), "test");
		file.addRecord(rec);
		try {
			file.write(FILENAME);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Failed to save file.");
		}
		
		
		System.out.println(rec.toString());
	}

	public static void askRecordInfo(){}
		
	
}
