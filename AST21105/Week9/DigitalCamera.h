//
//  DigialCamera.hpp
//  Lab Exercise 9
//
//  Created by Yik Hin Garret Lai on 17/3/2022.
//

#ifndef DigitalCamera_h
#define DigitalCamera_h

#include <stdio.h>
#include "Camera.h"

class DigitalCamera : public Camera{
private:
    int pixelInM;
    bool isCMOS;

public:
    DigitalCamera();
    DigitalCamera(const DigitalCamera&);
    DigitalCamera(int);
    DigitalCamera(std::string, std::string, int, bool);
    void setPixelInM(int);
    int getPixelInM() const;
    void setIsCMOS(bool);
    bool getIsCMOS() const;

    //Step 4: Add the keyword "virtual" to the following function to override the base class
    //        printCameraInfo().  Despite of the keyword is "automatically" added to the function
    //        It's still preferable to add the keyword "virtual" to remind the programmers.
    virtual void printCameraInfo();
};

#endif /* DigialCamera */
