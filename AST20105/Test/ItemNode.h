#ifndef ITEMNODE_H
#define ITEMNODE_H
#include <string>

using namespace std;

class ItemNode{

public:  //Don't set it to private, otherwise you will need a bunch of setter/getter
	/*
		Task 1: Create a node structure the following data members (attributes):
			1) "itemName" as string data type
			2) "priorityItem" as boolean type
			3) "qty" as integer type
			3) "price" as double data type
			4) "next" as ItemNode pointer

		After this task, an ItemNode structure with attribute mentioned above should be constructed
	*/
	//code task 1 below...

	bool priorityItem;
	string itemName;
	double price;
	int qty;
	ItemNode *next;

public:

	// This is a constructor done for you.  You should be able to finish the test with this constructor
	ItemNode(bool priorityItem, string itemName, double price, int qty, ItemNode* n){
		this->priorityItem = priorityItem;
		this->itemName = itemName;
		this->price = price;
		this->qty = qty;
		this->next = n;
	}
};
#endif
