// provided template

#ifndef NODE_H
#define NODE_H

class Node
{
   public:			// Making all the data members
     // data			// as public is very exceptional.
     double data;			// Since this facilitates rapid
     // pointer to next node	// access of data members
     Node* next;
};

#endif
