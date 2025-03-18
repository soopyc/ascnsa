#ifndef RECTANGLE_H
#define RECTANGLE_H

#include <string>

class Rectangle {
	private:
		double _width;
		double _height;
	public:
		Rectangle();
		Rectangle(double width, double height);
		void setWidth(double a);
		void setHeight(double a);
		double getWidth();
		double getHeight();

		double computeArea();
};

#endif
