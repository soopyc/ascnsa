// provided template

#include <iostream>
#include "MySinglyList.hh"
#include "Node.hh"
using namespace std;

void displayList(Node* l){
   int num = 0;
   Node* currNode = l;
   while(currNode != NULL) {
     cout << currNode->data << endl;
     currNode = currNode->next;
     num++;
   }
   cout << "Number of nodes in the list: " << num << endl;
}

int main()
{
	cout << "---------------------" << endl;
	cout << "Construction of mySLL" << endl;
	cout << "---------------------" << endl;
	MySinglyList mySLL;

	cout << endl;
	cout << "------------" << endl;
	cout << "addValue(10)" << endl;
	cout << "------------" << endl;
	mySLL.addValue(10);
	mySLL.displayList();

	cout << endl;
	cout << "------------------" << endl;
	cout << "checkSortedOrder()" << endl;
	cout << "------------------" << endl;
	if(mySLL.checkSortedOrder())
		cout << "Yes, the list is sorted" << endl;
	else
		cout << "No, the list is not sorted" << endl;

	cout << endl;
	cout << "-----------------------" << endl;
	cout << "insertNodeWith888(14.6)" << endl;
	cout << "-----------------------" << endl;
	mySLL.insertNodeWith888(14.6);
	mySLL.displayList();

	cout << endl;
	cout << "---------------------" << endl;
	cout << "moveLastNodeToBegin()" << endl;
	cout << "---------------------" << endl;
	Node* newList = mySLL.moveLastNodeToBegin();
	displayList(newList);
	// system("pause");
	return 0;
}
