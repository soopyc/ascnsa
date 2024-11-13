/**
 * The one big file format
 */
package moe.soopy.ast10106.group.format;

import java.util.ArrayList;

public class OneBigFile {
	Metadata metadata;
	ArrayList<Record> records;
	
	// initialize the class.
	public OneBigFile(Metadata metadata, ArrayList<Record> records) {
		this.metadata = metadata;
		this.records = records;
	}
	
	// parse the entire file into a object oriented format.
	public static OneBigFile parse(String toBeParsed) {
		String[] split = toBeParsed.split("---");
		Metadata metadata = Metadata.parse(split[0]); // metadata field, before dashes
		
		// iterate through each line after the dashes, wherein each line is a record.
		// ignore lines in which there are parsing errors.
		String[] rawRecords = split[1].split("\n");
		ArrayList<Record> records = new ArrayList<Record>();
		for (String rawRecord : rawRecords) {
			try {
				records.add(Record.parse(rawRecord));
			} catch (Exception e) {
				System.err.println("Could not parse record: " + rawRecord);
			}
		}
		
		return new OneBigFile(metadata, records);
	}
}
