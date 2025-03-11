#include <iostream>
#include "Sort.h"
#include "Search.h"

void performSortMarks(int arr[], const int& size)
{
	system("cls");
	std::cout << "size: " << size << std::endl;
	std::cout << "-------------" << std::endl;
	std::cout << "   Sorting   " << std::endl;
	std::cout << "-------------" << std::endl;
	std::cout << "Marks sorted in ascending order: ";
	performQuickSort(arr, 0, size-1);
	for(int i=0; i<size; i++)
		std::cout << arr[i] << " ";
	std::cout << std::endl;
	system("pause");
}

void performSearchMark(int arr[], const int& size)
{
	system("cls");
	std::cout << "-------------" << std::endl;
	std::cout << "  Searching  " << std::endl;
	std::cout << "-------------" << std::endl;
	do {
		int noFind;
		std::cout << "Mark to find (-1 to quit)? ";
		std::cin >> noFind;
		if (noFind == -1)
			break;
		int index = performBinarySearch(arr, 0, size-1, noFind);
		if (index != -1)
			std::cout << "The mark " << noFind << " is found at index " << index << std::endl;
		else
			std::cout << "The mark is not found" << std::endl;
	} while (true);
}

void performComputation(int arr[], int size)
{
	system("cls");
	double sum = 0;
	double sumSquared = 0;
	for (int i = 0; i < size; i++)
	{
		sum += arr[i];
		std::cout << arr[i] << " ";
	}
	std::cout << std::endl;
	double mean = sum / size;
	for(int i=0; i<size; i++)
		sumSquared += (arr[i] - mean)*(arr[i] - mean);
	double variance = sumSquared / (size - 1);
	std::cout << "Mean : " << mean << std::endl;
	std::cout << "Unbiased variance : " << variance << std::endl;
	system("pause");
}
