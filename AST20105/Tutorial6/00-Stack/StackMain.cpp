// SPDX-License-Identifier: CC0-1.0
// Author: hwc39103 (H10007740)

#include "StackArr.h"
#include <string>
#include <iostream>
#include <fstream>

using namespace std;

// provided template
string readFile() {
	string tempString = "";
	string filename;

	cout << "Input file name: ";
	cin >> filename;
	ifstream inputFile(filename);

	// Prompt user again if wrong filename received
	while (!inputFile.good()) {
		cout << "Wrong file name, input again please: ";
		cin >> filename;
		inputFile.open(filename);
	}

	// Append all lines from the file into a long string
	while ((!inputFile.eof())) {
		string str;
		getline(inputFile, str);
		tempString += str;
	}

	return tempString;
}
// end provided template

char getMapping(const char &chr) {
	switch (chr) {
		// opening matches
		// used for the ``expected character`` logging feature.
		case '{':
			return '}';
		case '(':
			return ')';
		case '[':
			return ']';

		// closing matches
		case '}':
			return '{';
		case ')':
			return '(';
		case ']':
			return '[';

		default:
			return -1;
	}
}

int main() {

	bool valid = true;
	string fileString = "";
	fileString = readFile();

	StackArr stack(fileString.size());

	unsigned int currentChar = 0;
	for (auto chr : fileString) {
		currentChar++;
		switch (chr) {
			// opening chars
			case '{':
			case '(':
			case '[':
				// stderr used so we can redirect all spam (debug logging) to /dev/null when we don't need them
				fprintf(stderr, "Found opening char %c at pos %i\n", chr, currentChar);
				stack.push(chr);
				break;

			default:
				// if mapping returned -1, then chr is not a bracket and we can skip that.
				// ... if it **is** though, then we will have to check it.
				// we shouldn't get opening brackets here because they should already be caught by the named cases above.
				char mapping = getMapping(chr);

				if (getMapping(chr) != -1) {
					// first check if the stack is already empty. that immediately indicates a failure.
					if (stack.isEmpty()) {
						valid = false;
						printf("ERROR: Found %c but stack was already empty (at pos %i)\n", chr, currentChar);
						continue;
					}

					char stackPrev = stack.pop();
					if (mapping != stackPrev) {
						valid = false;
						printf("ERROR: Expected %c, got %c instead (at pos %i)\n", getMapping(stackPrev), chr, currentChar);
						continue;
					}

					fprintf(stderr, "Found %c, expected %c, stack.pop=%c (at pos %i)\n", chr, getMapping(stackPrev), stackPrev, currentChar);
				}
		}
	}

	if (valid && stack.isEmpty()) {
		cout << "File passes bracket balancing checks." << endl;
	} else if (valid && !stack.isEmpty()) {
		cout << "File does not pass the balancing check: less closing brackets than expected." << endl;
		stack.displayStack();
	} else {
		cout << "File does not pass balancing checks. See errors above." << endl;
	}

	return 0;
}
