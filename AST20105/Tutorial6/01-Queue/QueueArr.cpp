#include "QueueArr.h"
#include <string>
#include <iostream>

using namespace std;

QueueArr::QueueArr(int size) {

	values = new char[size];
	maxSize = size;
	front = 0;
	back = -1;
	counter = 0;
}

QueueArr::~QueueArr() {
	delete[] values;
}

bool QueueArr::isEmpty() const {
	
	if (counter)
		return false;
	else
		return true;
}

bool QueueArr::isFull() const {
	if (counter < maxSize)
		return false;
	else
		return true;
}

bool QueueArr::enqueue(char x) {
	if (isFull()) {
		cout << "Error! The queue is full." << endl;
		return false;
	}
	else {
		back = (back + 1) % maxSize;
		values[back] = x;
		counter++;
		return true;
	}
}

char QueueArr::dequeue() {
	char x = -1;
	if (isEmpty()) {
		cout << "Error! The queue is empty." << endl;
		return -1;
	}
	else {
		x = values[front];
		front = (front + 1) % maxSize;
		counter--;
		return x;
	}
}

void QueueArr::displayQueue() const {
	cout << "Front -->";
	for (int i = 0; i < counter; i++) {
		if (i == 0)
			cout << "\t";
		else
			cout << "\t\t";
		cout << values[(front + i) % maxSize];
		if (i != counter - 1)
			cout << endl;
		else
			cout << "\t<--Back" << endl;
	}
	cout << endl;
}