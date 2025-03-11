void performQuickSort(int arr[], int left, int right)
{
	int i = left;
	int j = right;
	int tmp;
	int pivot = arr[(left + right) / 2];

	while (i <= j)
	{
		while (arr[i] < pivot)
			i++;
		while (arr[j] > pivot)
			j--;

		if (i <= j)
		{
			tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
			i++;
			j--;
		}
	}

	if( left < j )
		performQuickSort( arr, left, j );
	if( i < right)
		performQuickSort( arr, i, right );
}
