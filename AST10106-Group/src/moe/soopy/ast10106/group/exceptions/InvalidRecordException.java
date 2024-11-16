/**
 * Exception for when the format cannot be parsed correctly for some defined reason.
 * 
 * @author H10007740
 */
package moe.soopy.ast10106.group.exceptions;

public class InvalidRecordException extends Exception {
	// Prompted by IDE.
	private static final long serialVersionUID = -2740635903398854033L;

	public InvalidRecordException(String message) {
		super("Invalid record format: " + message);
	}
}
