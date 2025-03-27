/******************************************************************************

AST21105 - Lab Exercise 7 - Q2

*******************************************************************************/

#include <iostream>
#include "Rectangle.h"

using namespace std;

int main()
{
    Rectangle r1;
    Rectangle r2(3, 5);
    Rectangle r3(4);

    cout << r1.compArea() << endl;
    cout << r2.compArea() << endl;
    cout << r3.compArea() << endl;

    return 0;
}
