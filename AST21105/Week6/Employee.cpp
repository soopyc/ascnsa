#include <iostream>
#include "Employee.h"

void printSection(std::string section) {
	std::cout << section << "\t";
};

Employee::Employee() {
	_name = "Unknown";
	_joinedYear = 1970;
	_address = "Unknown";
};

void Employee::setDetails(std::string name, int joinedYear, std::string address) {
	_name = name;
	_joinedYear = joinedYear;
	_address = address;
};

void Employee::printInfo() {
	printSection(_name);
	printSection(std::to_string(_joinedYear));
	printSection(_address);
	std::cout << std::endl;
};
