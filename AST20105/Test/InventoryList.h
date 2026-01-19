#ifndef INVENTORYLIST_H
#define INVENTORYLIST_H

#include "ItemNode.h"
#include <iostream>
#include <string>

using namespace std;

class InventoryList{

public:

	// Head pointer
	ItemNode* head;

public:

	~InventoryList();

	InventoryList();

	//Return true if the list is empty
	bool isEmpty();

	//Print inventory list
	void printInventoryList();

	//Return the most expensive item in the list
	ItemNode* findMostExpensiveItem();

	//Return an itemNode pointer pointing to a list of priority item
	ItemNode* retrievePriorityItemList();

	// Setting the head pointer point to the first element
	void setHead(ItemNode* node);

	// Getting the head pointer of the inventory list
	ItemNode* getHead();

};

#endif
