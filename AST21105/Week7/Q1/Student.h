#ifndef STUDENT_H
#define STUDENT_H

#include <string>

class Student {
	private:
		std::string name;
		int grade;

	public:
		Student();
		Student(std::string name, int grade);

		std::string getName();
		void setName(std::string name);

		int getGrade();
		void setGrade(int grade);

		std::string printOutStudentInfo();
};

#endif
