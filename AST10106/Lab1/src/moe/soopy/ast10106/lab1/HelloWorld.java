/**
 * I managed to lose my original source files so this is a re-implementation (copy of slides) of what I did.
 * 
 * This is a simple hello world program with some arithmetic operations.
 * @author Cassie Cheung (H10007740)
 */

package moe.soopy.ast10106.lab1;

public class HelloWorld {

	public static void main(String[] args) {
		System.out.println("hello world");
		HelloWorld.basicArithmetics();
	}
	
	/**
	 * Does basic arithmetic operations and prints out the answer.
	 */
	static void basicArithmetics() {
		int sum = 0;
		int int1, int2;
		
		int1 = 10;
		int2 = 20;
		
		sum = int1 + int2;
		System.out.println(String.format("The result of %s + %s = %s", int1, int2, sum));
	}

}
