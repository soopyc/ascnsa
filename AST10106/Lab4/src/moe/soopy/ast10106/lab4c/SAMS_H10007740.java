/**
 * This program is a simple implementation of a SAMS system, utilizing
 * a previously made module to calculate the grade.
 * 
 * @author H10007740
 */

package moe.soopy.ast10106.lab4c;

import java.util.Scanner;
// use our existing code for the grade algorithm.
import moe.soopy.ast10106.lab4b.Ex4b_H10007740;

public class SAMS_H10007740 {

	// define a static member `scanner` to be initialized in main
	// - the reason why we used this instead of initializing and closing scanner
	// - object every time is because supposedly, the close method sends an EOF to
	// - the input stream and that essentially
	// - blocks future inputs.
	//
	// - the more real reason is that it returns a "No line found" exception.
	//
	// - This method basically circumvents the problem by not having to release
	// - resources or to have initialized a class. We could technically also have
	// - not released resources but that's _bad_ practice.
	static Scanner scanner;

	// define static constants for use later.
	static final double weightLab = 0.15;
	static final double weightClasswork = 0.13;
	static final double weightProject = 0.32;
	static final double weightExam = 0.40;

	public static void main(String[] args) {
		// initialize the static member scanner.
		scanner = new Scanner(System.in);

		// prompt user for data
		String courseCode = SAMS_H10007740.getString("Course code");
		String studentName = SAMS_H10007740.getString("Student name");
		String studentID = SAMS_H10007740.getString("Student ID");

		double markLab = SAMS_H10007740.getMark("Lab");
		double markClasswork = SAMS_H10007740.getMark("Classwork");
		double markProject = SAMS_H10007740.getMark("Project");
		double markExam = SAMS_H10007740.getMark("Exam");

		// release resources
		scanner.close();

		// calculate total mark based on weighting
		double totalMark = markLab * weightLab + markClasswork * weightClasswork + markProject * weightProject
				+ markExam * weightExam;

		// print out the table
		System.out.println("\n\n ===== Study Report =====");
		System.out.printf("Course Code: %s\n", courseCode);
		System.out.printf("Student: %s (%s)\n\n", studentName, studentID);

		// this is essentially a "template" for the 4 items.
		System.out.printf("%-20s%-10s%-15s%15s\n", "Assignment Type", "Marks", "Weighting (%)", "Course Mark");
		System.out.println("-".repeat(20 + 10 + 15 + 15));
		// print out the table following the format.
		// ideally we would use a hashmap or an array for this but i haven't learned
		// that yet so /shrug.
		System.out.printf("%-20s%-10.1f%-15.0f%15.2f\n", "Labs", markLab, weightLab * 100, markLab * weightLab);
		System.out.printf("%-20s%-10.1f%-15.0f%15.2f\n", "Classwork", markClasswork, weightClasswork * 100,
				markClasswork * weightClasswork);
		System.out.printf("%-20s%-10.1f%-15.0f%15.2f\n", "Project", markProject, weightProject * 100,
				markProject * weightProject);
		System.out.printf("%-20s%-10.1f%-15.0f%15.2f\n", "Exam", markExam, weightExam * 100, markExam * weightExam);
		System.out.printf("%-20s%40.2f\n", "Total", totalMark);
		System.out.printf("Grade: %s", Ex4b_H10007740.determineGrade(totalMark));

	}

	static String getString(String item) {
		System.out.printf("Please enter %s (string): ", item);
		String next = scanner.nextLine();
		return next;
	}

	// we could technically make this less targeted but this will do for now.
	static double getMark(String item) {
		System.out.printf("Please enter mark for %s (2 d.p., out of 100.): ", item);
		double next = scanner.nextDouble();
		return next;
	}
}
