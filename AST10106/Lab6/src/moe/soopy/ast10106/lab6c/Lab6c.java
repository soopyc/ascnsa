/**
 * 2d array operations.
 */

package moe.soopy.ast10106.lab6c;

import java.util.Arrays;

public class Lab6c {
	public static void main(String args[]) {
		// initialize and generate the arrays.
		double[][] matrix1 = generate2dArray(4, 4);
		System.out.println("Matrix 1: ");
		printMatrix(matrix1);
		System.out.println();

		double[][] matrix2 = generate2dArray(4, 4);
		System.out.println("Matrix 2: ");
		printMatrix(matrix2);
		System.out.println();

		// do addition
		System.out.println("Addition:");
		double[][] additionMatrix = new double[4][4];
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				additionMatrix[x][y] = matrix1[x][y] + matrix2[x][y];
			}
		}
		printMatrix(additionMatrix);
		System.out.println();

		// do subtraction
		System.out.println("Subtraction:");
		double[][] subtractionMatrix = new double[4][4];
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				subtractionMatrix[x][y] = matrix1[x][y] - matrix2[x][y];
			}
		}
		printMatrix(subtractionMatrix);
		System.out.println();

		// do multiplication
		System.out.println("Multiplication:");
		double[][] multiplicationMatrix = new double[4][4];
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				multiplicationMatrix[x][y] = matrix1[x][y] * matrix2[x][y];
			}
		}
		printMatrix(multiplicationMatrix);
		System.out.println();

	}

	public static double[][] generate2dArray(int x, int y) {
		double[][] matrix = new double[x][y];
		for (double[] d1 : matrix) {
			// for each item in level 1 of the matrix...
			for (int i = 0; i < d1.length; i++) {
				// generate a random number and put it in di[i].
				// mutability in java is weird, but should be good practice because i shouldn't
				// be mutating the list while iterating through it.

				// clamp the values to a minimal of 100, then...
				// ...get rid of the decimal points, then set the value.
				d1[i] = Math.floor(Math.max(100, Math.random() * 1000));
			}
		}
		return matrix;
	}

	public static void printMatrix(double[][] matrix) {
		for (double[] l1 : matrix) {
			System.out.println(Arrays.toString(l1));
		}
	}
}
