/**
 * Search the indices of a provided integer in a randomly generated list.
 */

package moe.soopy.ast10106.lab6b;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Lab6b {
	public static void main(String[] args) {
		// generate the random array
		int[] array = new int[100];
		for (int i = 0; i < 100; i++) {
			// before +1, this rounds down and probably won't ever reach 20, since hitting 1
			// is very difficult (probably impossible). adding 1 ensures 0 is never a thing,
			// and 20 can be reached instead of being stuck at 19.
			array[i] = (int) (Math.random() * 20) + 1;
		}
		System.out.printf("%d entries list generated: %s\n", array.length, Arrays.toString(array)); // debug
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the integer to find: ");
		int toFind = scanner.nextInt();

		// initialize a new arraylist to store the indices. this is an arraylist because
		// we don't know the size of the array beforehand.
		ArrayList<Integer> foundIndices = new ArrayList<Integer>();
		for (int i = 0; i < array.length; i++) {
			// iterate through the array, find every index that matches toFind and add the
			// index to foundIndices.
			if (array[i] == toFind) {
				foundIndices.add(i);
			}
		}

		// print out values depending on the size of the container.
		if (foundIndices.isEmpty()) {
			System.out.printf("Found nothing matching '%d'.\n", toFind);
		} else {
			System.out.printf("Found '%d' at %d indices: %s\n", toFind, foundIndices.size(), foundIndices);
		}

		// release resources
		scanner.close();
	}
}
