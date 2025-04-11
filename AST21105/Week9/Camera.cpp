//
//  Camera.cpp
//  Lab Exercise 9
//
//  Created by Yik Hin Garret Lai on 17/3/2022.
//

#include "Camera.h"
#include <iostream>

Camera::Camera(){
    serialNum = "";
    brand = "";
}

Camera::Camera(std::string serialNum, std::string brand){
    this->serialNum = serialNum;
    this->brand = brand;
}

void Camera::setSerialNum(std::string serialNum){
    this->serialNum = serialNum;
}

std::string Camera::getSerialNum() const{
    return serialNum;
}

void Camera::setBrand(std::string brand){
    this->brand = brand;
}

std::string Camera::getBrand() const{
    return brand;
}

Camera::~Camera(){}

// Step 3: Remove the following function as printCameraInfo() becomes a pure virtual function.
//         printCameraInfo() is not supposed to be implemented here since it is a pure virtual
//         function.  printCameraInfo() should be implemented by its derived class(es).
