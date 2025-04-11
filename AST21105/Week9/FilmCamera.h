//
//  FilmCamera.hpp
//  Lab Exercise 9
//
//  Created by Yik Hin Garret Lai on 17/3/2022.
//

#ifndef FilmCamera_h
#define FilmCamera_h

#include <stdio.h>
#include "Camera.h"

class FilmCamera : public Camera{
private:
    int filmSize;

public:
    FilmCamera();
    FilmCamera(const FilmCamera&);
    FilmCamera(int);
    FilmCamera(std::string, std::string, int);
    void setFilmSize(int);
    int getFilmSize() const;

    //Step 6: Add the keyword "virtual" to the following function to override the base class
    //        printCameraInfo().  Despite of the keyword is "automatically" added to the function
    //        It's still preferable to add the keyword "virtual" to remind the programmers.
    virtual void printCameraInfo();
};
#endif /* FilmCamera_h */
