#include <iostream>
#include "WineList.h"

WineList::WineList() {
	wCount = 0;
	wList = nullptr;
}

WineList::WineList(std::string list[], int count) {
	wList = list;
	wCount = count;
}

WineList::WineList(const WineList &data) {
	wCount = data.wCount;

	// deep copy
	wList = new std::string[data.wCount];
	for (int i = 0; i < wCount; i++) {
		wList[i] = data.wList[i];
	}
}

WineList::~WineList() {
	delete[] wList;
}

void WineList::removeWine(int index) {
	wList[index] = "removed";
}

void WineList::printWineList() {
	// for (auto wine : wList) { didn't work because c++
	for (int i = 0; i < wCount; i++) {
		std::cout << i+1 << ": " << wList[i] << std::endl;
	}
}
