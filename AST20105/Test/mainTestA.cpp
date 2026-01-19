#include "InventoryList.h"
#include "ItemNode.h"
#include <string>
#include <iostream>

int main() {

	// DO NOT CHANGE THIS FILE AS THIS IS YOUR TEST CASE!!!

	// Refer to task 2 in Inventory.cpp
	InventoryList inventL;

	// Refer to task 3 in Inventory.cpp
	cout << "==================================" << endl;
	cout << "       Print Inventory List       " << endl;
	cout << "==================================" << endl << endl;
	inventL.printInventoryList();
	cout << endl;

	// Refer to task 4 in Inventory.cpp
	cout << "==================================" << endl;
	cout << "     Find Most Expensive Item     " << endl;
	cout << "==================================" << endl << endl;

	if(!inventL.isEmpty()){
		ItemNode* mostExpensive = inventL.findMostExpensiveItem();
		cout << "The most expensive item is: " << mostExpensive->itemName << "(" << mostExpensive->price << ")" << endl;
	}else{
		cout << "The list is empty" << endl;
	}
	cout<< endl << endl;

	// Refer to task 5 in Inventory.cpp
	cout << "==================================" << endl;
	cout << "      Retrieve Priority Items     " << endl;
	cout << "==================================" << endl << endl;
	
	InventoryList priorityL;
	if(!inventL.isEmpty()){
		priorityL.head = inventL.retrievePriorityItemList();
		priorityL.printInventoryList();
	}else{
		cout << "The list is empty";
	}
	cout << endl;

	return 0;
}