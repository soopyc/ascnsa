#include "QueueArr.h"
#include "StackArr.h"
#include <iostream>
#include <string>

using namespace std;

int main() {

	string usrInput;
	bool palindrome = true;

	cout << "Input a string: ";
	cin >> usrInput;

	StackArr stack(usrInput.size());
	QueueArr queue(usrInput.size());

	//code below...


	return 0;
}