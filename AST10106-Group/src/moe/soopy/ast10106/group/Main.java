package moe.soopy.ast10106.group;

import java.io.IOException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;

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

		// prompt program information
		System.out.println("Info: This Program provide the Essential Functions of the Personal Accounting.");
		// TODO: unfinished
		while (true) {
			displayMenu();
			char nextOperation = prompter.promptForString("What would you like to do?").charAt(0);
			switch (nextOperation) {
			case 'a':
				createRecord();
				break;
			case 'b':
				showRecordsWithFilter();
				break;
			case 'c':
				System.out.println("\t\t\tIncome and Expense Records");
				displayRecord(file.records);
				break;
			case 'd':
				deleteRecord();
				break;
			case 'e':
				Monthly_Spending();
				break;
			case 'f':
				System.out.println("--------------------------------------------------------------------------");
				st.run();
				System.out.println("--------------------------------------------------------------------------");
				break;
			case 'g':
				mpf();
				break;
			case 'h':
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

	// main menu
	public static void displayMenu() {
		System.out.println("Welcome!");
		System.out.println("a. Record Daily Expense and Income");
		System.out.println("b. Display by Record Types");
		System.out.println("c. Show Records");
		System.out.println("d. Delete Expense and Income Records");
		System.out.println("e. Find Cash Flow over Period of Time");
		System.out.println("f. Tax Calculation");
		System.out.println("g. MPF Net Salary Calculation");
		System.out.println("h. Time Deposit Calculation");
		System.out.println("t. Show personal particulars");
		System.out.println("u. Update personal particulars");

		System.out.println("z. Save and Exit");
	}

	// add income/expense records
	public static void createRecord() {
		String IE;
		double amount;
		String type;
		String notes;

		System.out.println("It is a income or expense? ");

		String[] selection = { "income", "expense" };
		IE = prompter.promptForStringSelection("income/expense", selection);
		amount = prompter.promptForDouble("Please enter the price or amount");
		type = prompter.promptForString("Please enter the type of record");
		notes = prompter.promptForString("Please enter any notes or enter a space");
		notes = notes.strip().equals("") ? "-" : notes;

		Record rec = new Record(IE, amount, type, LocalDate.now(), notes);
		file.addRecord(rec);
		// saveFile();

		// System.out.println(rec.toString());
	}

	static void saveFile() {
		try {
			file.write(FILENAME);
		} catch (IOException e) {
			System.err.println("Failed to save file.");
		}
	}

	public static void Monthly_Spending() {
		Double SavingAmount = 0.0;
		Double SpentAmount = 0.0;
		Double TotalAmount = 0.0;

		displayText("Checking how much amount did you spent?");
		displayText("1: Yes! I need to check 2: Nope! I pressed the wrong button");

		while (true) {
			int choiceMS = prompter.promptForInteger("");
			if (choiceMS == 1) {
				break;
			} else if (choiceMS == 2) {
				return;// Exit the method if the user pressed wrong button
			} else {
				displayText("Page is having maintenance");
			}
		}
		for (Record record : file.getRecordsByType("income")) {
			SavingAmount += record.getCurrentAmount();
		}
		for (Record record : file.getRecordsByType("expense")) {
			SpentAmount += record.getCurrentAmount();
		}
		TotalAmount = SavingAmount - SpentAmount;
		
		System.out.println("----------------------------------------");
		displayText("Total Saving Amount: " + SavingAmount);
		displayText("Total Spent Amount: " + SpentAmount);
		displayText("Net Amount: " + TotalAmount);
		System.out.println("----------------------------------------");
	}

	private static void displayText(String message) {
		System.out.println(message);
	}

	// display income/expense records
	public static void displayRecord(Record record) {
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("  ID \t Income/Expense    Amount    Category         Date        Note");
		System.out.println("---------------------------------------------------------------------------");
		System.out.println(record.formatRecord());
		System.out.println("---------------------------------------------------------------------------");
	}

	public static void displayRecord(ArrayList<Record> records) {
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("  ID \t Income/Expense    Amount    Category         Date        Note");
		System.out.println("---------------------------------------------------------------------------");
		for (Record record : records) {
			System.out.println(record.formatRecord());
		}
		System.out.println("---------------------------------------------------------------------------");
	}

	public static void showRecordsWithFilter() {
		String[] selection = { "income", "expense" };
		System.out.println("Which type would you like to check?");
		String c = prompter.promptForStringSelection("income/expense", selection);
		displayRecord(file.getRecordsByType(c));
	}

	// delete a single record by id.
	public static void deleteRecord() {
		System.out.println("Which record would you like to delete? Enter a the ID of a specific record.");
		System.out.println("Type cancel to cancel.");
		String input = prompter.promptForString("ID Prefix");
		if (input.equals("cancel"))
			return;
		Record toDelete = file.getRecordByID(input);
		if (toDelete == null) {
			System.err.printf("Could not find record with ID starting with %s.\n", input);
			return;
		}
		file.records.remove(toDelete);
		System.out.println("Removed record:");
		displayRecord(toDelete);
	}

	// used for calculate net salary after the mandatory contribute
	public static void mpf() {
		double salary;
		double afterMpfSal = -1;
		// prompt this function information
		System.out.println("-----------------------------------------------------------");
		System.out.println("Info: Here can help you to calculate the Net Salary after the Mandatory Provident Fund.");
		System.out.println("Are you paid monthly or weekly?");

		// get monthly paid or non-monthly paid
		System.out.println("Type 1 for monthly or 2 for weekly.");
		int detM = prompter.promptForInteger("Paid monthly or weekly?");

		// if monthly paid, then get monthly salary and calculate the net salary
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

			// if non-monthly paid, then get weekly salary and calculate the net salary
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
			// default: if not 1 or 2 then prompt this
			System.out.println("Invalid input, please try again.");
			return;
		}

		// print result
		System.out.println("-----------------------------------------------------------");
		System.out.println("\t  MPF net salary Report");
		System.out.printf("Your Original Salary: $%.2f ", salary);
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
