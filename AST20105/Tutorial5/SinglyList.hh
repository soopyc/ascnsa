// provided template

#ifndef SINGLYLIST_H
#define SINGLYLIST_H

#include "Node.hh"

class SinglyList
{
   private:
     Node* head; // a pointer to the first node in the list
   public:
	 SinglyList();	// constructor
     ~SinglyList();	// destructor
     // isEmpty determines whether the list is empty or not
     bool isEmpty();
     // insertNode inserts a new node at position "index"
     Node* insertNode(int index, double x);
     // findNode finds the position of the node with a given value
     int findNode(double x);
     // deleteNode deletes a node with a given value
     int deleteNode(double x);
     // displayList prints all the nodes in the list
     void displayList() const;
	 friend class MySinglyList;
};

#endif
