#ifndef RECTANGLE_H
#define RECTANGLE_H

class Rectangle {
	private:
		int length;
		int width;

	public:
		/* Creates a rectangle with 0 x 0 as its dimensions. */
		Rectangle();
		/* Creates a rectangle with side_length^2 as its dimensions. */
		Rectangle(int side_length);
		/* Creates a rectangle with length x width as its dimensions. */
		Rectangle(int length, int width);
		// Rectangle(Rectangle& other); not needed because we aren't initializing anything with Rectangle from the type.
		// ~Rectangle(); not needed because everything is in stack, not heap.

		int compArea();
};

#endif
