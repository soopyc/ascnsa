#ifndef EMPLOYEE_H
#define EMPLOYEE_H

#include <string>

class Employee {
	private:
		std::string _name;
		int _joinedYear;
		std::string _address;
	public:
		Employee();
		void setDetails(std::string name, int joinedYear, std::string _address);
		void printInfo();
};

#endif
