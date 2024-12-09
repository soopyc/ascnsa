/** 
 * Record type for each expense/income record.
 * 
 * @author H10007740
 */

package moe.soopy.ast10106.group.format;

import java.time.LocalDate;
import java.util.UUID;

import moe.soopy.ast10106.group.exceptions.InvalidRecordException;

public class Record {
	final public String id; // can only be set once.
	final public String type; // can only be set once.
	public double amount; // modifiable
	public String category; // modifiable
	public LocalDate date; // modifiable
	public String notes; // modifiable
	

	public Record(String id, String type, double amount, String category, LocalDate date, String notes) {
		this.id = id;
		this.type = type;
		this.amount = amount;
		this.category = category;
		this.date = date;
		this.notes = notes;
	}

	// when creating a new record, we wouldn't have an ID on hand. generate one
	// here.
	// usage: new Record("income", 4.0, "some_category", LocalDate.now(),
	// "some_notes")
	public Record(String type, double amount, String category, LocalDate date, String notes) {
		this(UUID.randomUUID().toString(), type, amount, category, date, notes);
	}

	// uuid|expense|1.99|expense test 1|2024-01-01|none
	public static Record parse(String toBeParsed) throws InvalidRecordException {
		// | (pipe) means split every character??
		// \| does a literal | which splits the string properly.
		String[] split = toBeParsed.split("\\|");
		if (split.length != 6) {
			throw new InvalidRecordException("Invalid record: number of fields does not equal to 6.");
		}
		String type = split[1];
		if (!type.equals("income") && !type.equals("expense"))
			throw new InvalidRecordException("Record type " + type + " is not one of [income, expense]");

		return new Record(split[0], type, Double.parseDouble(split[2]), split[3], LocalDate.parse(split[4]), split[5]);
	}

	public String toString() {
		return String.format("%s|%s|%f|%s|%s|%s", this.id, this.type, this.amount, this.category, this.date,
				this.notes);
	}

	/**
	 * format the record in pretty string
	 * @return format at string
	 */
	public String getPrettyRecord() {
		String[] splitStrId = this.id.split("-");
		String fsSecId;
		if (splitStrId.length > 0) {
			fsSecId = splitStrId[0]; // before: 8d4e552f-0c51-485f-be0a-ece0bb3f877b after: splitStrId[0]: 8d4e552f
		} else {
			fsSecId = "";
		}
		return String.format("%s|%15s|%10.2f|%10s|%15s|%10s", fsSecId, this.type, this.amount, this.category, this.date,
				this.notes);
	}

	public String getType() {
		return this.type;
	}
	
}
