package moe.soopy.asc10106.lab7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TempOperator {
	static Scanner lineScanner;
	static Scanner doubleScanner;

	public static void main(String[] args) {
		// initialize scanner
		lineScanner = new Scanner(System.in);
		doubleScanner = new Scanner(System.in);

		ArrayList<Double> temperatures = new ArrayList<Double>();

		// main loop
		while (true) {
			// menu implementation
			char next = menuImpl();
			switch (next) {
			case 'a':
				getTemp(temperatures);
				break;
			case 'b':
				maxTemp(temperatures);
				break;
			case 'c':
				minTemp(temperatures);
				break;
			case 'd':
				averageTemp(temperatures);
				break;
			case 'e':
				aboveAverageDays(temperatures);
				break;
			case 'f':
				modeTemp(temperatures);
				break;
			case 'q':
				System.out.println("Exiting...");
				return; // return from the function, short-circuiting everything to exit.
			default:
				System.out.println("Invalid operation, please try again.");
			}
		}
	}

	// print out the menu and prompt the user.
	static char menuImpl() {
		System.out.println("What would you like to do? Specify one of [abcdefz].");
		System.out.println("\ta. Input temperatures");
		System.out.println("\tb. Find maximum temp.");
		System.out.println("\tc. Find minimum temp.");
		System.out.println("\td. Find average temp.");
		System.out.println("\te. Find above avg. days.");
		System.out.println("\tf. Find mode of temps");
		System.out.println("\tq. Exit the program");
		System.out.print("Please enter the operation: ");

		String nextLn = lineScanner.nextLine().toLowerCase();
		return nextLn.charAt(0);
	}

	// try to prompt the user for a double, catching potential errors.
	static double tryPromptDouble(String prompt) {
		while (true) {
			System.out.print(prompt);
			try {
				return doubleScanner.nextDouble();
			} catch (Exception _e) {
				System.out.println("An error occurred. Please ensure your input is correct.");
				doubleScanner.nextLine(); // flush the buffer to prevent continuous erroring.
			}
		}
	}

	// get temperature from the user by adding one temperature to the list at a
	// time.
	static void getTemp(ArrayList<Double> temperatures) {
		int days = 0;
		// reset arraylist
		// since the `temperatures` is a reference/pointer to the actual list, we can
		// just add items without worry about returning anything.
		temperatures.clear();

		// print out help message
		System.out.println("Welcome to the temperature insertion wizard.");
		System.out.println("You may specify up to 31 days of temperature.");
		System.out.println("Stop by specifying any value below -100.");

		while (days <= 31) {
			days++;
			double next = tryPromptDouble(String.format("Please enter temperature for day %d: ", days));

			// exit if we receive any value below -100.
			if (next <= -100) {
				return;
			}

			// add the value to the list.
			temperatures.add(next);
		}
		return; // do a return for consistency
	}

	// find out the maximum temperature by iterating through each item in the list.
	static void maxTemp(ArrayList<Double> temperatures) {
		double max = Double.MIN_VALUE;
		for (double current : temperatures) {
			max = Math.max(max, current);
		}
		System.out.printf("The maximum temperature is: %.1f\n", max);
	}

	// calculate the minimum temperature by iterating through each item in the list.
	static void minTemp(ArrayList<Double> temperatures) {
		double min = Double.MAX_VALUE;
		for (double current : temperatures) {
			min = Math.min(min, current);
		}
		System.out.printf("The minimum temperature is: %.1f\n", min);
	}

	// helper function to calculate the average.
	static double findAverage(ArrayList<Double> temperatures) {
		double total = 0;
		for (double temperature : temperatures) {
			total += temperature;
		}
		double average = total / temperatures.size();

		return average;
	}

	// calculate the average temperature by summing every item in the list, then
	// divide by the size of the list.
	static void averageTemp(ArrayList<Double> temperatures) {
		// there is a map/forEach function in arraylist but i don't know how to write
		// lambdas.
		// we'll do it the old way.
		// or well, offload it to another function to do that for us.

		double average = findAverage(temperatures);
		System.out.printf("The average temperature throughout %d days is: %.2f\n", temperatures.size(), average);
	}

	// find days which temperature is higher than average.
	static void aboveAverageDays(ArrayList<Double> temperatures) {
		// see comments in averageTemp.
		double average = findAverage(temperatures);
		int daysAboveAverage = 0;
		for (double current : temperatures) {
			if (current > average)
				daysAboveAverage++;
		}
		System.out.printf("There are %d day(s) with temperatures above average.\n", daysAboveAverage);
	}

	// find the mode of the temperatures.
	static void modeTemp(ArrayList<Double> temperatures) {
		// first setup a map. when doing this in python, i typically like to use a dict
		// to store stuff like mappings of values.
		// reference: https://www.geeksforgeeks.org/map-interface-java-examples/
		// https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/Map.html
		Map<Double, Integer> modeMap = new HashMap<Double, Integer>();

		for (double temperature : temperatures) {
			modeMap.putIfAbsent(temperature, 0); // set a default value to avoid errors in the next line
			modeMap.put(temperature, modeMap.get(temperature) + 1); // python: modeMap[temperature] += 1
		}
		System.out.println(modeMap); // debug

		// this is probably the ugliest and most inefficient solution ever.
		// a better way would be to sort the map first and somehow only extract the
		// highest frequency keys, ignoring everything else.
		ArrayList<Double> temperatureModes = new ArrayList<Double>();
		int freq = 0;
		for (Map.Entry<Double, Integer> temperature : modeMap.entrySet()) {
			if (temperature.getValue() > freq) {
				freq = temperature.getValue();
				temperatureModes.clear(); // clear the arraylist because the supposed "mode" is now invalid
			}

			if (temperature.getValue() == freq) {
				temperatureModes.add(temperature.getKey());
			}
		}
		System.out.printf("freq: %d\n", freq);
		System.out.printf("Mode of the temperatures: %s\n", temperatureModes);
	}
}
