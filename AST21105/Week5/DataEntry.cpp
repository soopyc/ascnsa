#include <iostream>

/* Read n marks from keyboard, where n is a user-specified value */

void performDataEntry(int*& arr, int& n)
{
	system("cls");
	std::cout << "-------------" << std::endl;
	std::cout << "    Input    " << std::endl;
	std::cout << "-------------" << std::endl;
	std::cout << "How many marks to s atore? ";
	std::cin >> n;
	arr = new int[n];
	std::cout << "Input marks: ";
	for(int i=0; i<n; i++)
		std::cin >> arr[i];
}
