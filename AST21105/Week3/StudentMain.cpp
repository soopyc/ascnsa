#include <iostream>
using namespace std;

//Put your answer below

void W3_Q1(int *a, int *b) {
	int temp = *a;
	*a = *b;
	*b = temp;
	return;
}

void W3_Q2(int array[], int size) {
	for (int i = 0; i < size; i++) {
		array[i] *= 2;
	}
}

const int Q3_MAT_SIZE = 3;
int** W3_Q3(int (*mat1)[Q3_MAT_SIZE], int **mat2, int size) {
	// imo vecs makes the most sense in most cases except embedded, partly because i've used rust and
	// mostly because limited size arrays aren't all that useful outside of space constraints

	/**
	 * {
	 *   {1, 1, 1},
	 *   {2, 2, 2},
	 *   {3, 3, 3},
	 * }
	 */

	// tests later frees the array, so assuming a dynamic array is needed.
	// tbh we probably needed a heap array anyways
	int **product = new int*[size];

	// you love to see pyramids
	// initialize the array and do the calc
	for (int x = 0; x < size; x++) {
		product[x] = new int[size];
		for (int y = 0; y < size; y++) {
			product[x][y] = 0; // init memory so it doesn't become ub
			for (int z = 0; z < size; z++) {
				product[x][y] += mat1[x][z] * mat2[z][y];
			}
		}
	}

	return product;
}

char* W3_Q4(string s, const char f) {
	for (char c : s) {
		if (c == f) return &c;
	}
	return nullptr;
}

//Put your answer above

// DO NOT MODIFY ANYTHING BELOW!!!
int main()
{
    cout << "============Begin of Task 1 (W3_Q1) Test==========" << endl;
    int a = 5, b = 10;
    cout << "Before swapping: a = " << a << " and b = " << b << endl;
    W3_Q1(&a, &b);
    cout << "After swapping: a = " << a << " and b = " << b << endl;
    cout << "============End of Task 1 (W3_Q1) Test==========" << endl;
    cout << endl;

    cout << "============Begin of Task 2 (W3_Q2) Test==========" << endl;
    int size_q2 = 5;
    int q2_arr[] = {1, 2, 3, 4, 5};
    cout << "Before W3_Q2 modification: ";
    for(int i = 0; i < size_q2; i++){
        cout << q2_arr[i] << " ";
    }
    cout << endl;
    W3_Q2(q2_arr, size_q2);
    cout << "After W3_Q2 modification: ";
    for(int i = 0; i < size_q2; i++){
        cout << q2_arr[i] << " ";
    }
    cout << endl;
    cout << "============End of Task 2 (W3_Q2) Test==========" << endl;
    cout << endl;

    cout << "============Begin of Task 3 (W3_Q3) Test==========" << endl;
    int size_q3 = 3;
    //q3_arr1 is a static array
    int q3_arr1[3][3] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

    //q3_arr2 is a dynamic array
    int** q3_arr2 = new int*[size_q3];
    for(int i = 0; i < size_q3; i++){
        q3_arr2[i] = new int[size_q3];
    }

    int val = 9;
    for (int i = 0; i < size_q3; i++){
        for(int j = 0; j < size_q3; j++){
            q3_arr2[i][j] = val-- ;
        }
    }

    cout << "Elements in matrix 1: " << endl;
    for (int i = 0; i < size_q3; i++){
        for(int j = 0; j < size_q3; j++){
            cout << q3_arr1[i][j] << " ";
        }
        cout << endl;
    }
    cout << endl;
    cout << "Elements in matrix 2: " << endl;
    for (int i = 0; i < size_q3; i++){
        for(int j = 0; j < size_q3; j++){
            cout << q3_arr2[i][j] << " ";
        }
        cout << endl;
    }
    cout << endl;
    int** prod_arr = W3_Q3(q3_arr1, q3_arr2, size_q3);
    cout << "Elements in the product of 2 matrices: " << endl;
    for (int i = 0; i < size_q3; i++){
        for(int j = 0; j < size_q3; j++){
            cout << prod_arr[i][j] << " ";
        }
        cout << endl;
    }
    cout << "============End of Task 3 (W3_Q3) Test==========" << endl;
    cout << endl;
    //freeing the 2-D arrays structure in heap
    for(int i = 0; i < size_q3; i++){
        delete[] prod_arr[i];
        delete[] q3_arr2[i];
    }
    delete[] prod_arr;
    delete[] q3_arr2;

    cout << "============Begin of Task 4 (W3_Q4) Test==========" << endl;
    string msg_q4 = "Hello there!";
    char* ch1_q4 = W3_Q4(msg_q4, 'o');
    char* ch2_q4 = W3_Q4(msg_q4, 'p');

    cout << "Character " << 'o'<< (ch1_q4? " found!" : " not found") << endl;
    cout << "Character " << 'p' << (ch2_q4? " found!" : " not found") << endl;
    cout << "============End of Task 4 (W3_Q4) Test==========" << endl;
    return 0;
}
