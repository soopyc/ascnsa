#ifndef SORT_H
#define SORT_H

/*
	[ Quicksort Algorithm ]

	1. Choose a pivot value
		We take the the middle element as pivot value.
	2. Partition
		Rearrange elements in the array, such that all elements that
		are less than the pivot are moved to the left part of the array
		and all elements greater than the pivot moved to the right
		part of the array.
		Note:
		The pivot should now be placed in its final position in the sorted array.
	3. Sort the left unsorted array and right unsorted array
		Apply quicksort algorithm recursively for the left and the right parts.
*/
void performQuickSort(int arr[], int left, int right);

#endif
