// provided template

#ifndef MYSINGLYLIST_H
#define MYSINGLYLIST_H

#include "SinglyList.hh"

class MySinglyList
{
	private:
		SinglyList sLL;
	public:
		MySinglyList(); // complete
		bool checkSortedOrder() const; // complete
		void addValue(double value);
		void insertNodeWith888(double value);
		Node* moveLastNodeToBegin();
		void displayList() const; // complete
};

#endif
