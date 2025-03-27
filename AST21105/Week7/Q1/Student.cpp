#include "Student.h"

Student::Student() {
	name = "Unknown Student";
	grade = 0;
}

Student::Student(std::string name, int grade) {
	this->name = name;
	this->grade = grade;
}

std::string Student::getName() {
	return name;
}

void Student::setName(std::string name) {
	this->name = name;
}

int Student::getGrade() {
	return grade;
}

void Student::setGrade(int grade) {
	this->grade = grade;
}

std::string Student::printOutStudentInfo() {
	return name + " with grade " + std::to_string(grade);
}
