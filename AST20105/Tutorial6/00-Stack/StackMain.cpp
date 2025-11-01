#include "StackArr.h"
#include <string>
#include <iostream>
#include <fstream>

using namespace std;

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


int main() {

	bool valid = true;
	string fileString = "";

	fileString = readFile();

	StackArr stack(fileString.size());

	//code below...


	return 0;
}
