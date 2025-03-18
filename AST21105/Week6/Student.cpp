#include <iostream>
#include "Student.h"

Student::Student() {
	_name = "Unknown";
	_roll_no = 0;
}

void Student::setName(std::string a) {
	_name = a;
};
void Student::setRollNo(int a) {
	_roll_no = a;
};

void Student::displayInfo() {
	std::cout << _name << " (" << _roll_no << ")" << std::endl;
};
