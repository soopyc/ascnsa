/*
  H10008012's Code
*/
package moe.soopy.ast10106.group;

import moe.soopy.ast10106.group.format.Metadata;
import moe.soopy.ast10106.group.format.OneBigFile;
import moe.soopy.ast10106.group.format.Record;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public static void MS() {
	inputMetadata();
	// This program will appear to the user of the monthly spending 
	//Deduct money by spending Money 
		
	
    //This is date for showing user spending in which day
	LocalDate date = LocalDate.now();
	String name;
	
	System.out.print("Hello! This is monthly_spending tracker");
	
	
	//prompt the name 
	System.out.println("Please type your name here: ");
	
	Scanner scanner =new Scanner(System.in);
	String Name = scanner.next();
	
	ArrayList<Double>monthly_Spend = new ArrayList<Double>();
	// The array will show the user monthly spend
	while(true) {
		System.out.println("Please type your amount that you have spent it in this month(or type 'exit' to finish)");
		//need to confirm the user does he/she knows how much did they spend  
		String input = scanner.nextLine();
		//Just in case the user doesn't want to continue 
		if(input.equalsIgnoreCase("exit")) {
		 break;
		}
	  try {
		 double amountSpent = Double.paraseDouble(input);//  This will calculate 
		 monthlySpend.add(amountSpent);                  // of the amount spent
	  }
	  catch(NumberFormatException){
		 System.out.println("Invalid input.Please enter the right amount.");// this pop up when the input is invaild 
	  }
	  double totalspent = 0;                 //This is calculating of the spending 
	  for(double amount : monthlySpend) {    // I will call it MS 
		  totalSpent +=amount ;
	  }
	System.out.printf("Thank you for typing the right amount! Your total spending amount is $%.2f\n"+ name + totalSpent);
	//Then it will pop up the amount that you have spend 
}
}
