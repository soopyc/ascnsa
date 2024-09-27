package moe.soopy.ast10106.lecture3;

public class Lecture3 {
	public static void main(String[] args) {
		// Trying to use printf
		System.out.printf("Hello world!\nString = %s\nInteger = %d\nFloat = %.4f\nChar = %c\n", "string", 10, 100.0, 'F');
		System.out.printf("Align left :.%-10.0f%-10.2f%-10.5f\n", 100.0, 100.01, 100.001);
		System.out.printf("Align right:.%10.0f%10.2f%10.5f\n", 100.0, 100.01, 100.001);
	}
}
