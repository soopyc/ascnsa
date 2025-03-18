#ifndef STUDENT_H
#define STUDENT_H

#include <string>

class Student {
	private:
		std::string _name;
		int _roll_no;
	public:
		Student();
		void setName(std::string a);
		void setRollNo(int a);
		void displayInfo();
};

#endif
