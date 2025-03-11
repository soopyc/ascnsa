#include <iostream>
#include "DataEntry.h"
#include "Sort.h"
#include "Search.h"
#include "Engine.h"
#include "Histogram.h"

bool executeMenu(int choice, int*& arr, int& size)
{
	switch(choice)
	{
		case 1:
		if(arr != NULL)
			delete [] arr;
			performDataEntry(arr, size);
			break;
		case 2:
			performSortMarks(arr, size);
			break;
		case 3:
			performSearchMark(arr, size);
			break;
		case 4:
			performComputation(arr, size);
			break;
		case 5:
			performHistogram(arr, size);
			break;
		case 6:
			return true;
	}
	return false;
}

int selectMenu()
{
	system("cls");
	int choice;
	do {
		std::cout << "Select function: " << std::endl;
		std::cout << "----------------" << std::endl;
		std::cout << "1.) Mark Entry" << std::endl;
		std::cout << "2.) Sort Marks" << std::endl;
		std::cout << "3.) Search Mark" << std::endl;
		std::cout << "4.) Compute Statistics " << std::endl;
		std::cout << "5.) Plot Histogram " << std::endl;
		std::cout << "6.) Quit " << std::endl;
		std::cout << std::endl;
		std::cout << "Please enter your choice: ";
		std::cin >> choice;
	} while(choice < 1 || choice > 6);
	return choice;
}

int main()
{
	bool quit;
	int size;
	int* arr = NULL;
	do {
		quit = executeMenu(selectMenu(), arr, size);
	} while (!quit);
	if(arr != NULL)
		delete [] arr;
	return 0;
}
