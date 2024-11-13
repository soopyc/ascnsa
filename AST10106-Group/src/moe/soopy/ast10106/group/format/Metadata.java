/** 
 * Record type for each expense/income record.
 */

package moe.soopy.ast10106.group.format;

import java.time.LocalDateTime;

public class Metadata {
	public String name;
	public LocalDateTime lastModified;

	public Metadata(String name, LocalDateTime lastModified) {
		this.name = name;
		this.lastModified = lastModified;
	}

	// expense|1.99|expense test 1|2024-01-01|none
	public static Metadata parse(String toBeParsed) {
		String[] split = toBeParsed.split("\n");
		return new Metadata(split[0], LocalDateTime.parse(split[1]));
	}
}
