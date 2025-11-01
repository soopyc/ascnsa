**AST20105 Data Structures & Algorithms**

**Lab 6 -- Stack and Queue**

## Submission Details

In this lab, you are required to submit **TWO** C++ programmes to solve
the given problem shown in the section "Exercise". To make the program
implementation easier, you are suggested to use Visual Studio 2017 or
later version. After you have completed the task, submit your program
files (i.e. StackMain.cpp & QueueMain.cpp for this lab) to Moodle by
**31 October 2025**. For details, please refer to the following:

## Objective

The objective of this lab is to give you a revision on Stack and Queue
data structure. Please note that all the concepts reviewed during this
lab are very useful for your further study. So, you are highly
encouraged to pay attention to what your lab instructor tells you. Apart
from this, you will be asked to work on a lab question so as to help you
get familiar with Stack and Queue applications.

**Exercise**

**Part 1: Stack**

1.  Create a New Project and give your project a name, say Lab6a.

2.  Download the given source files StackArr.h and StackArr.cpp from
    Moodle and save them to your Lab6a folder. Also import them to your
    project.

3.  Add a source file to your project, called StackMain.cpp and
    implement your program according to the following:

    a.  Prompt the user to input a program filename.

    b.  Open the file and check if every right brace (i.e. ``}``), bracket
        (i.e. ``]``), and parenthesis (i.e. ``)``) in the file correspond to
        its left counterpart or not.

    c.  If the program file passed the checking, output "The code is
        correct". Otherwise, output "The code is incorrect".

The algorithm for checking is as follows:

1.  Make an empty stack

2.  Read characters until the end of program file

    a.  if the character is an opening symbol, push it onto the stack;

    b.  if it is a closing symbol and if the stack is empty, output
        "Error: Empty stack";

    c.  otherwise, pop the stack. If the symbol popped is not the
        corresponding opening symbol, output "Error: Not equal".

At the end of the file, if the stack IS NOT EMPTY, output "The code is
incorrect". Otherwise, output "The code is correct".

Use the provided files, testfile1.txt and testfile2.txt to test your
program. The code file testfile1.txt is correct, while the code file
testfile2.txt is incorrect.

**Useful code:**

```cpp
#include <fstream>
using namespace std;

string readFile() {
	string tempString = "";
	string filename;
	cout << "Input file name: ";
	cin >> filename;
	ifstream inputFile(filename);

	// Prompt user again if wrong filename received
	while (!inputFile.good()) {
		cout << "Wrong file name, input again please: ";
		cin >> filename;
		inputFile.open(filename);
	}

	// Append all lines from the file into a long string
	while ((!inputFile.eof())) {
		string str;
		getline(inputFile, str);
		tempString += str;
	}
	return tempString;
}
```

**Part 2: Queue**

1.  Create a New Project and give your project a name, say Lab6b.

2.  Download the given source files StackArr.h, StackArr.cpp, QueueArr.h
    and QueueArr.cpp from Canvas and save them to your Lab6b folder.
    Also import them to your project.

3.  Add a source file to your project, called QueueMain.cpp and
    implement your program according to the following:

    a.  prompt the user to input a string;

    b.  change each uppercase letter to lowercase;

    c.  place each letter both in a queue and onto a stack;

    d.  verify whether the input string is a palindrome (i.e. a set of
        letters or numbers that is the same whether read from left to
        right or right to left).

The following shows a number of program's sample input / output
sessions.

> Input a string: [aibohphobia]{.underline}
>
> aibohphobia is a palindrome
>
> Input a string: [level]{.underline}
>
> level is a palindrome
>
> Input a string: [desmond]{.underline}
>
> desmond is not a palindrome

-End-

> Adopted from California State University (Long Beach)
>
> http://www.csulb.edu/colleges/coe/cecs/views/programs/undergrad/grade_prog.shtml
