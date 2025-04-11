//
//  Camera.h
//  Lab Exercise 9
//
//  Created by Yik Hin Garret Lai on 17/3/2022.
//

#ifndef Camera_h
#define Camera_h

#include <stdio.h>
#include <string>

class Camera{
private:
    std::string serialNum, brand;
public:
    Camera();
    Camera(std::string, std::string);
    void setSerialNum(std::string);
    std::string getSerialNum() const;
    void setBrand(std::string);
    std::string getBrand() const;

    // Step 1: Change void printCameraInfo() as a pure virtual function
    virtual void printCameraInfo() = 0;

    // Step 2: Make the destructor virtual as well so that all its derived classes' destructor will
    //         also be invoked if ~Camera() is invoked
    virtual ~Camera() = 0;
};

#endif /* Camera_h */
