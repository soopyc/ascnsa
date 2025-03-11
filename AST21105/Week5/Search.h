#ifndef SEARCH_H
#define SEARCH_H

/*
	[ Binary Search Algorithm ]

	Check the middle element of the sorted array to see if it is equal to
	the key. If it is, then the position is found.
	Otherwise, the left half or right half is chosen for further searching
	based on whether the key is greater than or less than the middle element.
*/
int performBinarySearch(int arr[], int left, int right, int key);

#endif
