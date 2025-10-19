// SPDX-License-Identifier: CC0-1.0
// Author: hwc39103 (H10007740)

#include "MySinglyList.hh"
#include "SinglyList.hh"
#include <cmath>

// ideally we could create an iterable thing that allow us to use the
// for-each syntax, but that's too much effort.

// make our own double comparison method as simply comparing with ==
// will result in a non-quality due to some sort of precision loss.
// basically

bool isDoubleEqual(double x, double y) {
	if (std::abs(x - y) < std::numeric_limits<double>::epsilon()) return true;
	else return false;
}

// create and initalize a singly linked list with default values as
// prescribed.
MySinglyList::MySinglyList () {
	this->sLL = SinglyList();
	this->sLL.insertNode(0, 3.8);
	this->sLL.insertNode(1, 4.6);
	this->sLL.insertNode(2, 0.2);
	this->sLL.insertNode(3, 9.1);

	// display the list as prescribed in the guideline.
	this->sLL.displayList();
}

// SinglyList already provides a displayList method, reuse that.
void MySinglyList::displayList() const {
	this->sLL.displayList();
}

// self-explanatory -- just check if the SLL is sorted in asc. order.
//
// O(n) time complexity as in the worst case we need to loop though
// all the elements, such is the case with an already sorted list.
bool MySinglyList::checkSortedOrder() const {
	Node* current = this->sLL.head;
	// if current is a nullptr it'd be an UB, though i believe it's
	// technically a sorted list?
	//
	// sidenote: there is an `isEmpty` method in SinglyList but it's not
	// a const function, so we can't use that.
	// ... apparently it's not even implemented? either way, it should
	// still be a const method as it doesn't modify anything in the inst.
	if (!current) return true;

	while (current && current->next) {
		Node* next = current->next;
		// return early if we know it's not sorted.
		if (current->data > next->data)	return false;

		// shift the index to the next item.
		current = current->next;
	}

	// if we made it through the entire list without returning it should
	// be sorted.
	return true;
}

// make the last node the first node basically.
// according to the instructions i am supposed to return a ptr to an
// sLL, but the signature is actually a Node*...
//
// I suppose that means i return the first node in the list.
Node* MySinglyList::moveLastNodeToBegin() {
	// first, find the second-last and the last node.
	// then, make the former point to a nullptr,
	// afterwards make the last node point to the current HEAD.
	// finally make sLL's HEAD point to the "last" node.
	//
	// we can avoid allocating new memory locations if we just reuse the
	// existing node instead of using insertNode.

	Node *previousNode, *lastNode;
	Node *currentNode = this->sLL.head;
	while (currentNode) {
		if (!currentNode->next) { // is this the last node?
			lastNode = currentNode;
			previousNode->next = nullptr;
		}

		previousNode = currentNode;
		currentNode = currentNode->next; // shift to the next node
	};

	previousNode->next = this->sLL.head;
	this->sLL.head = previousNode;

	return this->sLL.head;
}

// this function is a misnomer - it actually adds `value` to every single node
// in the list. this should really be renamed to addValueToAll or something,
// but i can't not follow the guidelines.
//
// i guess this comment will have to do.
void MySinglyList::addValue(double value) {
	Node* current = this->sLL.head;
	while (current) {
		current->data += value; // add the value to the current index
		current = current->next; // move to the next item
	};
}

// add a new node with data 888 _after_ every node that contains $value.
void MySinglyList::insertNodeWith888(double value) {
	// i was going to use something like this but it doesn't... do
	// what i need it to do. also it finds only one of them, not all.
	//Node* targetNode = this->sLL.findNode(value);

	Node *current = this->sLL.head;
	while (current) {
		if (isDoubleEqual(current->data, value)) {
			Node* newNode = new Node;
			newNode->data = 888;

			// make the new node point to what was supposed to the next
			newNode->next = current->next;
			// ... then make the current one point to the new one, effectively
			// inserting a new node without using insertNode.
			current->next = newNode;
		}

		// shift the index to the next node.
		current = current->next;
	}
}
