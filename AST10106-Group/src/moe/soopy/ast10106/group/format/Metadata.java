/** 
 *  Metadata type
 *  
 *  @author H10007740
 */

package moe.soopy.ast10106.group.format;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Metadata {
	private String name;
	private boolean married;
	private int children;
	// https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/time/OffsetDateTime.html
	private OffsetDateTime lastModified;

	public Metadata(String name, OffsetDateTime lastModified, boolean married, int children) {
		this.name = name;
		this.lastModified = lastModified;
		this.married = married;
		this.children = children;
	}

	// name\nlastmodified
	public static Metadata parse(String toBeParsed) {
		String[] split = toBeParsed.split("\n");

		String name = split[0].strip();
		OffsetDateTime lastModifiedAt = OffsetDateTime.parse(split[1].strip());
		boolean married = Boolean.parseBoolean(split[2].strip());
		int children = Integer.parseInt(split[3].strip());
		return new Metadata(name, lastModifiedAt, married, children);
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

	public boolean isMarried() {
		return this.married;
	}

	public void setMarriageStatus(boolean married) {
		this.married = married;
	}

	public int getChildrenCount() {
		return this.children;
	}

	public void setChildrenCount(int children) {
		this.children = children;
	}

	// serialize the format
	public String toString() {
		return String.format("%s\n%s\n%b\n%d", this.name, this.lastModified, this.married, this.children);
	}
}
