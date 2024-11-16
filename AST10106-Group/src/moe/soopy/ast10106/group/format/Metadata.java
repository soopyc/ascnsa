/** 
 *  Metadata type
 *  
 *  @author H10007740
 */

package moe.soopy.ast10106.group.format;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Metadata {
	String name;
	// https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/time/OffsetDateTime.html
	OffsetDateTime lastModified;

	public Metadata(String name, OffsetDateTime lastModified) {
		this.name = name;
		this.lastModified = lastModified;
	}

	// name\nlastmodified
	public static Metadata parse(String toBeParsed) {
		String[] split = toBeParsed.split("\n");
		return new Metadata(split[0], OffsetDateTime.parse(split[1]));
	}

	// update lastModified timestamp.
	public void update() {
		this.lastModified = OffsetDateTime.now();
	}

	// getter and setter to enforce behavior.
	// i.e. we must update the timestamp on metadata updates.

	// get the last modified time in a pretty format.
	// no setters because lastModified must not be randomly modified.
	// please use this.update().
	public String getLastModifiedPretty() {
		return this.lastModified.format(DateTimeFormatter.RFC_1123_DATE_TIME);
	}

	// get the name of the user.
	public String getName() {
		return this.name;
	}

	// set the name of the user and update the timestamp.
	public void setName(String name) {
		this.name = name;
		this.update();
	}

	// serialize the format
	public String toString() {
		return String.format("%s\n%s", this.name, this.lastModified);
	}
}
