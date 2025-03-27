/******************************************************************************

AST21105 - Lab Exercise 7 - Q3

*******************************************************************************/
#include <iostream>
#include <string>
#include "WineList.h"

using namespace std;



int main()
{
    //DO NOT MODIFY
    string* redWineList = new string[3];
    redWineList[0] = "Cabernet Sauvignon";
    redWineList[1] = "Merlot";
    redWineList[2] = "Pinot Noir";

    WineList redWine(redWineList, 3);
    redWineList = nullptr;

    WineList redWineDup = redWine;
    redWineDup.removeWine(0);

    cout << "Red Wine List: " << endl;
    redWine.printWineList();
    cout << "Red Wine List (Duplicates): " << endl;
    redWineDup.printWineList();

    return 0;
}
