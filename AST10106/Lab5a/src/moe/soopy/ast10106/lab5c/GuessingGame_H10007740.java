/**
 * Guessing game with a twist. Probably easier as well.
 * @author H10007740
 */

package moe.soopy.ast10106.lab5c;

public class GuessingGame_H10007740 {
	
	public static void main(String[] args) {
		// main logic stored inside game object. see game.java for the logic.
		// this is mainly because we wanted to use `this` to reference the own class instead of typing the full ident.
		Game game = new Game(5);
		game.run();
	}
}
