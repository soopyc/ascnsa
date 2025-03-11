int performBinarySearch(int arr[], int left, int right, int key)
{
	if ( left > right )
		return -1;
	else
	{
		int mid = ( left + right ) / 2;
		if ( key == arr[mid] )
			return mid;
		else if ( key < arr[mid] )
			return performBinarySearch( arr, left, mid-1, key );
		else
			return performBinarySearch( arr, mid+1, right, key );
	}
}
