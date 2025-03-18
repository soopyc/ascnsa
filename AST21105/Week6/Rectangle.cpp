#include "Rectangle.h"

Rectangle::Rectangle() {
	_width = 0;
	_height = 0;
};
// Rectangle(double width, double height) {
// 	_width = width;
// 	_height = height;
// };

void Rectangle::setWidth(double a) {
	_width = a;
};
void Rectangle::setHeight(double a) {
	_height = a;
};

double Rectangle::getWidth() {
	return _width;
};
double Rectangle::getHeight() {
	return _height;
};

double Rectangle::computeArea() {
	return _width * _height;
};
