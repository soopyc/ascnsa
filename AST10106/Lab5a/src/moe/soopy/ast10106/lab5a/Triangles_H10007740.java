/**
 * Program that prints out 2 triangles.
 * @author H10007740
 */

package moe.soopy.ast10106.lab5a;

import java.util.Scanner;

public class Triangles_H10007740 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("How big should the triangles be?");
		int size = scanner.nextInt();

		scanner.close();

		Triangles_H10007740.solidLeftBottomTriangle(size);
		Triangles_H10007740.hollowRightBottomTriangle(size);
	}

	static void solidLeftBottomTriangle(int size) {
		// the logic for a solid triangle is easier than a hollow one
		// not printing out the spaces is enough to flip it horizontally.

		// for each line up till `size`...
		// starting at 1 because the first line should have something.
		for (int i = 1; i <= size; i++) {
			// first print out the spaces
			// for each line, we get progressively less space.
			for (int space = size; space > i; space--) {
				System.out.print(' ');
			}
			// then print out stars. should be self explanatory.
			for (int star = 0; star < i; star++) {
				System.out.print('*');
			}
			System.out.print('\n');
		}
	}

	static void hollowRightBottomTriangle(int size) {
		// since height and width are the same, we can just do a nested loop here
		// i'm not even going to try to figure out how to do variable width triangles. too much math.
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				// essentially we try to find the edges of the triangle, then make the edges stars
			}
		}
	}
}
