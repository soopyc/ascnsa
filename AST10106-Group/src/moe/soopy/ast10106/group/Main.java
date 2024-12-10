package moe.soopy.ast10106.group;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;

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
		SalariesTaxCalculate st = new SalariesTaxCalculate(file);
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
				st.print_afterTaxIncome();
				break;
			case 'e':
				showRecordsWithFilter();
				break;
			case 'f':
				mpf();
				break;
			case 'g':
				timeDeposit();
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
		System.out.println("b. Show Records");
		System.out.println("c. Find Cash Flow over Period of Time");
		System.out.println("d. Tax Calculation");
		System.out.println("e. Display Spending Catergories");
		System.out.println("f. MPF Net Salary Calculation");
		System.out.println("g. Time Deposit Calculation");
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
		System.out.println("\t\t        Income and Expense Records");
		System.out.println("---------------------------------------------------------------------------");
		System.out.printf("\n  ID \t Income\\Expsense    Amount   Categoirse       Date       Note\n");
		System.out.println("---------------------------------------------------------------------------");
		System.out.println(file.getRecord());
	}

	public static void showRecordsWithFilter() {
		int c = prompter.promptForInteger("Please type 1 for income and 2 for expenses");
		while (true)
			if (c == 1) {
				System.out.println(file.getRecordsByType("income"));
				break;
			} else if (c == 2) {
				System.out.println(file.getRecordsByType("expense"));
				break;
			} else {
				System.out.println("Please input again");
				c = prompter.promptForInteger("Please type 1 for income and 2 for expenses");
			}
	}

	// finished
	public static void mpf() {
		double salary;
		double afterMpfSal = -1;
		System.out.println("-----------------------------------------------------------");
		System.out.println("Info: Here can help you to calculate the Net Salary after the Mandatory Provident Fund.");
		System.out.println("Are you monthly paid OR non-monthly paid?");

		System.out.println("Type 1 for monthly or 2 for weekly.");
		int detM = prompter.promptForInteger("Paid monthly or weekly?");

		if (detM == 1) {
			salary = prompter.promptForDouble("What is your monthly salary?");
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
			salary = prompter.promptForDouble("What is your weekly salary?");
			if (salary < 1960) {
				System.out.println("No contribution needed based on your weekly wage.");
				afterMpfSal = salary;
			} else if (salary >= 1960) {
				System.out.println("You need to contribute 5% of your salary");
				afterMpfSal = salary - (salary * 0.05);
			}
		} else {
			System.out.println("Invalid input, please try again.");
			return;
		}
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t  MPF net salary Report");
		System.out.printf("\nYour Weekly or Monthly Net Salary: $%.2f\n", afterMpfSal);
		System.out.println("-----------------------------------------------------------");

	}

	// calculate the bank time deposit interest after user input the principal,
	// annual interest rate, and duration
	public static void timeDeposit() {
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println(
				"Info: Welcome here, please enter your deposit details to get your interest returns information!");

		// get principal
		double principal = prompter.promptForDouble("Principal:");

		// get annual interest rate
		double annualRate = prompter.promptForDouble("Annual interest rate in %:");

		// get duration in years
		// this is a double because you can do half year deposits.
		double totalYear = prompter.promptForDouble("Duration in years:");

		// final result calculated by calling calTimeDeposit method
		double timeDepositResult = calTimeDeposit(principal, annualRate, totalYear);

		// Display results
		System.out.println("-----------------------------------------------------------------------------");
		System.out.printf("After %.1f years, you will get: $%.2f\n", totalYear, timeDepositResult);

		// Calculate and display net earned in
		double earned = timeDepositResult - principal;
		System.out.printf("Net Earned: %.2f\n", earned);
		System.out.println("-----------------------------------------------------------------------------");

	}

	// call by timeDeposit(), used to calculate and return the time deposit interest
	public static double calTimeDeposit(double principal, double annualRate, double totalYear) {
		// Formula reference (using method 1):
		// https://www.wikihow.com/Calculate-Bank-Interest-on-Savings
		return principal * Math.pow((1 + annualRate / totalYear), totalYear);
	}
}
