#include <iostream>
#include "Rectangle.h"
#include "Student.h"
#include "Employee.h"
using namespace std;

//This main.cpp is for testing purpose.  Don't change it  

int main(){
//====Test for Q1====
    Rectangle r1;
    
    r1.setHeight(5.5);
    r1.setWidth(2.8);
    cout << "Area of r1 is " << r1.computeArea() << endl;
    cout << endl;
//====Test for Q2====
    Student james;
    james.setName("James Doe");
    james.setRollNo(2);
    james.displayInfo();
    cout << endl;
//====Test for Q3====
    Employee robert, sam, john;
    robert.setDetails("Robert", 1994, "64C Wall Street");
    sam.setDetails("Sam", 2000, "68D Wall Street");
    john.setDetails("John", 1999, "26B Wall Street");
    robert.printInfo();
    sam.printInfo();
    john.printInfo();
}