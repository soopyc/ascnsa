#include <iostream>
#include "SinglyList.hh"
using namespace std;

SinglyList::SinglyList() {
   head = NULL;
}

SinglyList::~SinglyList() {
   Node* currNode = head;
   Node* nextNode = NULL;
   while(currNode != NULL) {
     nextNode = currNode->next;
     delete currNode;
     currNode = nextNode;
   }
}

Node* SinglyList::insertNode(int index, double x) {
  if(index < 0)
     return NULL;
  int currIndex = 1;
  Node* currNode = head;
  while(currNode && index > currIndex) {
    currNode = currNode->next;
    currIndex++;
  }
  if(index > 0 && currNode == NULL)
    return NULL;
  Node* newNode = new Node;
  newNode->data = x;
  if(index == 0) {
    newNode->next = head;
    head = newNode;
  }
  else {
    newNode->next = currNode->next;
    currNode->next = newNode;
  }
  return newNode;
}

int SinglyList::findNode(double x) {
   Node* currNode = head;
   int currIndex = 1;
   while(currNode && currNode->data != x)
   {
     currNode = currNode->next;
     currIndex++;
   }
   if(currNode)
     return currIndex;
   return 0;
}

int SinglyList::deleteNode(double x) {
  Node* prevNode = NULL;
  Node* currNode = head;
  int currIndex = 1;
  while(currNode &&         currNode->data != x)
  {
    prevNode = currNode;
    currNode = currNode->next;
    currIndex++;
  }
  if(currNode) {
    if(prevNode) {
      prevNode->next =       currNode->next;
      delete currNode;
    }
    else {
      head = currNode->next;
      delete currNode;
    }
    return currIndex;
  }
  return 0;
}

void SinglyList::displayList() const{
   int num = 0;
   Node* currNode = head;
   while(currNode != NULL) {
     cout << currNode->data << endl;
     currNode = currNode->next;
     num++;
   }
   cout << "Number of nodes in the list: " << num << endl;
}
