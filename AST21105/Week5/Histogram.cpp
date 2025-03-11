#include <iostream>

void performHistogram(int arr[], int size)
{
	system("cls");
	int bins;
	std::cout << "How many bins you want to use to group the data ? ";
	std::cin >> bins;
	double max = arr[0];
	double min = arr[0];
	for (int i = 1; i < size; i++)
	{
		if (max < arr[i])
			max = arr[i];
		if (min > arr[i])
			min = arr[i];
	}
	std::cout << "min = " << min << std::endl;
	std::cout << "max = " << max << std::endl;
	double dataRange = max - min;
	double classRange = dataRange / bins;
	double lowerBound = min;
	double upperBound = min + classRange;
	for (int i = 0; i < bins; i++, upperBound += classRange, lowerBound += classRange)
	{
		bool last = i == (bins - 1);
		std::cout << "[" << lowerBound << ", " << upperBound << (last ? "]" : ")") << " : ";
		for (int j = 0; j < size; j++)
		{
			if (arr[j] >= lowerBound && (last || arr[j] < upperBound))
				std::cout << "*";
		}
		std::cout << std::endl;
	}
	system("pause");
}
