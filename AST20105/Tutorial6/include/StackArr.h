#ifndef STACKARR_H
#define STACKARR_H

class StackArr {
private:
	int maxTop;
	int stackTop;
	char *values;

public:
	StackArr(int);
	~StackArr();
	bool isEmpty() const;
	bool isFull() const;
	char top() const;
	void push(const char& x);
	char pop();
	void displayStack() const;
};

#endif
#pragma once
