/******************************************************************************

AST21105 - Lab 7 Exercise Q1

*******************************************************************************/

#include "Student.h"
#include <iostream>
using namespace std;

int main(){
    Student s1("Mandy", 75);
    cout << s1.printOutStudentInfo() << endl;
    
    Student s2;
    s2.setName("Benny");
    s2.setGrade(59);
    
    cout << s2.printOutStudentInfo() << endl;
}