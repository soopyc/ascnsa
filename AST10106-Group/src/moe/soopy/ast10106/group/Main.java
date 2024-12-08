package moe.soopy.ast10106.group;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Scanner;

import moe.soopy.ast10106.group.format.Metadata;
import moe.soopy.ast10106.group.format.OneBigFile;
import moe.soopy.ast10106.group.format.Record;

public class Main {
	final static String FILENAME = "bmtool.obf";
	static OneBigFile file;
	static Prompter prompter = new Prompter();

	public static void main(String[] args) {
		// load the file. if it doesn't exist, create a new one and prompt the user for
		// data.
		try {
			file = OneBigFile.load(FILENAME);
		} catch (IOException e) {
			System.err.println("Could not load file, creating a new one.");
			file = new OneBigFile(new Metadata("TEMPORARY DATA", OffsetDateTime.now(), false, 0), null);
			inputMetadata();
			saveFile();
		}

		System.out.printf("Last modified: %s\n", file.metadata.getLastModifiedPretty());

		// TODO: unfinished
		while (true) {
			displayMenu();
			char nextOperation = prompter.promptForString("What would you like to do?").charAt(0);
			switch (nextOperation) {
			case 'a':
				createRecord();
				break;
			case 'b':
				showRecord();
				break;
			case 'c':
				// method();
				break;
			case 'd':
				//SalariesTaxCalculate object = new SalariesTaxCalculate();
				break;
			case 'e':
				// method();
				break;
			case 'f':
				mpf();
				break;
			case 't':
				retrieveMetadata();
				break;
			case 'u':
				inputMetadata();
				break;
			case 'z':
				System.out.println("End. Goodbye!");
				prompter.cleanup();
				saveFile();
				return;

			default:
				System.err.println("Error: invalid option.");
			}
		}
	}

	public static void inputMetadata() {
		String name;
		boolean married;
		int children;

		name = prompter.promptForString("What is your name?");
		file.metadata.setName(name);
		married = prompter.promptForBoolean("Are you married?");
		file.metadata.setMarriageStatus(married);
		children = prompter.promptForInteger("How many children do you have?");
		file.metadata.setChildrenCount(children);
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
		System.out.println("a. Record Daily Expeznse and Income");
		System.out.println("b. Show Record");
		System.out.println("c. Find Cash Flow over Period of Time");
		System.out.println("d. Tax Calculation");
		System.out.println("e. Display Spending Catergories");
		System.out.println("f. Calculate net salary based on the MPF system");
		System.out.println("t. Show personal particulars");
		System.out.println("u. Update personal particulars");

		System.out.println("z. Save and Exit");
	}

	// finished
	public static void createRecord() {
		String IE;
		double amount;
		String type;
		String notes;
		askRecordInfo();
		System.out.println("It is a income or expense? ");
		System.out.println("1: Income 2: Expense");

		while (true) {
			int checkIE = prompter.promptForInteger("");
			if (checkIE == 1) {
				IE = "income";
				break;
			} else if (checkIE == 2) {
				IE = "expense";
				break;
			} else {
				System.out.println("Unknown input: please enter either 1 or 2.");
			}
		}
		amount = prompter.promptForDouble("Please enter the price or amount");
		type = prompter.promptForString("Please enter the type of record");
		notes = prompter.promptForString("Please enter any notes or enter a space");

		Record rec = new Record(IE, amount, type, LocalDate.now(), notes);
		file.addRecord(rec);
		// saveFile();

		System.out.println(rec.toString());
	}

	public static void askRecordInfo() {
	}

	static void saveFile() {
		try {
			file.write(FILENAME);
		} catch (IOException e) {
			System.err.println("Failed to save file.");
		}
	}

	public static void showRecord() {
		System.out.println("Nothing here yet");
	}
	
	// finished 
	public static void mpf() {
		Scanner sc = new Scanner(System.in);
		int detM;
		double salary;
		double afterMpfSal = -1;
		System.out.println("-----------------------------------------------------------");
		System.out.println("Here can help you to calculate the Net Salary after the Mandatory Provident Fund.");
		System.out.println("Are you monthly paid OR non-monthly paid?");
		System.out.println("1. Monthly Paid \t2. Non-monthly Paid");

		while (true) {
			detM = sc.nextInt();
			if (detM == 1 || detM == 2)
				break;
			else
				System.out.println("Please enter 1 or 2 only.");
		}

		if (detM == 1) {
			System.out.println("What is your monthly salary?");
			salary = sc.nextDouble();
			if (salary < 7100) {
				afterMpfSal = salary;
				System.out.println("No contribution needed based on your weekly paid.");
			} else if (salary < 30000 && salary >= 7100) {
				afterMpfSal = salary - (salary * 0.05);
				System.out.println("You need to contribute 5% of your salary.");
			} else if (salary >= 30000) {
				afterMpfSal = salary - 1500;
				System.out.println("You need to contribute $1500 of your salary.");
			}

		} else if (detM == 2) {
			System.out.println("What is your weekly salary?");
			salary = sc.nextDouble();
			if (salary < 1960) {
				afterMpfSal = salary;
				System.out.println("No contribution needed based on your weekly paid.");
			} else if (salary >= 1960) {
				System.out.println("You need to contribute 5% of your salary");
				afterMpfSal = salary - (salary * 0.05);
			}

		}
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t  MPF net salary Report");
		System.out.printf("\nYour Weekly or Monthly Net Salary: $%.2f\n", afterMpfSal);
		System.out.println("-----------------------------------------------------------");

	}
}
