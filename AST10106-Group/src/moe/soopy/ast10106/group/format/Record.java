/** 
 * Record type for each expense/income record.
 */

package moe.soopy.ast10106.group.format;

import java.time.LocalDate;

public class Record {
	public String type;
	public double amount;
	public String category;
	public LocalDate date;
	public String notes;

	public Record(String type, double amount, String category, LocalDate date, String notes) {
		this.type = type;
		this.amount = amount;
		this.category = category;
		this.date = date;
		this.notes = notes;
	}

	// expense|1.99|expense test 1|2024-01-01|none
	public static Record parse(String toBeParsed) {
		String[] split = toBeParsed.split("|");
		return new Record(split[0], Double.parseDouble(split[1]), split[2], LocalDate.parse(split[3]), split[4]);
	}
}
