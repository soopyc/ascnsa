/**
 * Break a given list with a provided break point. 
 * Breakpoint character will be on the next list.
 */

package moe.soopy.ast10106.lab6a;

import java.util.ArrayList;
import java.util.Collections;

public class Lab6a {
	public static void main(String args[]) {
		// define parameters.
		Character[] characters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k' };
		char breakpoint = 'f';

		// initialize the array list.
		ArrayList<Character> originalList = new ArrayList<Character>();
		Collections.addAll(originalList, characters);
		ArrayList<Character> list1 = new ArrayList<Character>();
		ArrayList<Character> list2 = new ArrayList<Character>();

		if (originalList.contains(breakpoint)) {
			// we try not to mutate lists when iterating, this causes us to not be able to
			// use functions like pop without resorting to a standard incremental for loop.
			int brokenAtIndex = 0;
			for (char a : originalList) {
				if (a == breakpoint) { // self explanatory.
					break;
				}
				// add 1 to brokenAtIndex because we are shifting by 1 item.
				// this is after the break because without doing so we effectively remove the
				// item.
				brokenAtIndex++;
				list1.add(a);
			}

			// apparently we have to use incremental for loops either way.
			for (int i = brokenAtIndex; i < originalList.size(); i++) {
				list2.add(originalList.get(i));
			}
		}

		System.out.println("orig: " + originalList);
		System.out.println("bkpt: " + breakpoint);
		System.out.println("list1: " + list1);
		System.out.println("list2: " + list2);
	}
}
