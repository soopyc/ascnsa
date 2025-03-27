#include "Rectangle.h"

Rectangle::Rectangle() {
	length = 0;
	width = 0;
}

Rectangle::Rectangle(int side_length) {
	length = side_length;
	width = side_length;
}

Rectangle::Rectangle(int length, int width) {
	this->length = length;
	this->width = width;
}

int Rectangle::compArea() {
	return length * width;
}
