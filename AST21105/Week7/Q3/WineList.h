#ifndef WINELIST_H
#define WINELIST_H

#include <string>

class WineList {
	private:
		std::string *wList;
		int wCount;

	public:
		WineList();
		WineList(std::string list[], int count);
		WineList(const WineList& data);
		~WineList();

		void removeWine(int index);
		void printWineList();
};

#endif
