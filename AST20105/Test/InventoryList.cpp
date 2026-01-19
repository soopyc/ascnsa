#include "InventoryList.h"
#include "ItemNode.h"
#include <algorithm>
#include <string>
#include <iostream>

InventoryList::InventoryList(){
	/*
		Tasks 2: Creating 6 Inventory Nodes with the following info (priorityItem, itemName, price, qty)

		Item 1: (true, "72mm Lens Polaroid Filter", 799, 20)
		Item 2: (false, "Shutter control cable", 25, 15)
		Item 3: (false, "Tripod mount", 15, 41)
		Item 4: (true, "Re-chargeable batteries", 50, 60)
		Item 5: (true, "Kodak 135 film", 10, 50)
		Item 6: (false, "A&A Leather Strap", 40, 22)

		After this task, a linked list with 6 items should be created.
	*/
	//code task 2 below...

	// only the last item can have its `next` set to a nullptr, so we'll do that first.
	this->head = new ItemNode(false, "A&A Leather Strap", 40, 22, nullptr);
	this->head = new ItemNode(true, "Kodak 135 film", 10, 50, this->head);
	this->head = new ItemNode(true, "Re-chargeable batteries", 50, 60, this->head);
	this->head = new ItemNode(false, "Tripod mount", 15, 41, this->head);
	this->head = new ItemNode(false, "Shutter control cable", 25, 15, this->head);
	this->head = new ItemNode(true, "72mm Lens Polaroid Filter", 799, 20, this->head);
}

// Check whether a list is empty
bool InventoryList::isEmpty(){
	return head==NULL? true : false;
}

/*
	Tasks 3: Printing the inventory list on screen

    The function should be able to display item details stored in the linked list structure on screen.
	The actual format of printing, please refer to the problem description

	After this task, item data in the linked list should be displayed on screen.
*/

void InventoryList::printInventoryList(){
	//code task 3 below...
	unsigned int accum = 0;
	ItemNode *next = this->getHead();
	while (next) {
		printf(
			"Item %i data:\nName:\t%s (%s Item)\nPrice:%f Quantity: %i\n",
			++accum,
			next->itemName.c_str(),
			next->priorityItem ? "Priority" : "Non-priority",
			next->price, next->qty
		);
		next = next->next;
	}
}

/*
	Tasks 4: Find the most expensive item

    The function will look for the most expensive item in the linked list.
	Once the most expensive is found, the function will return a pointer pointing
	to the most expensive item.

	After this task, a pointer to the most expensive item will be returned.
*/
ItemNode* InventoryList::findMostExpensiveItem(){
	//code task 4 below...
	ItemNode *max = this->head;
	ItemNode *next = this->head->next;

	while (next) {
		if (max->price < next->price)
			max = next;
		next = next->next;
	}

	return max;
}


/*
	Tasks 5: Forming a linked list with only priority item

    The function will perform the following tasks:
	1) Create a head pointer (ItemNode*) for the priority item list
	2) Look for all priority items in the original linked list.
	3) If any priority item found, append the priority item to the head pointer for the priority list
	4) Return the head pointer to the priority item

	After this task, a head pointer (ItemNode*) to priority item list is returned.
*/
ItemNode* InventoryList::retrievePriorityItemList(){
	//code task 5 below...
	ItemNode *priorityList = nullptr;
	ItemNode *next = this->head;

	while (next) {
		if (next->priorityItem)
			// we could probably do something like copy()?
			priorityList = new ItemNode(next->priorityItem, next->itemName, next->price, next->qty, priorityList);
		next = next->next;
	}

	return priorityList;
}

// As head is set to public, this is totally an option for you to use
void InventoryList::setHead(ItemNode* n){
	head = n;
}

// As head is set to public, this is totally an option for you to use
ItemNode* InventoryList::getHead(){
	return head;
}

InventoryList::~InventoryList() {
	// for (ItemNode *thing = this->head ; thing; thing = thing->next) {
	// 	delete thing;
	// }
	ItemNode *current = this->head;
	ItemNode *next = this->head->next;
	while (next) {
		delete current;
		current = next;
		next = current->next;
	}
}
