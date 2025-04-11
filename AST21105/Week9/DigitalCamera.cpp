//
//  DigialCamera.cpp
//  Lab Exercise 9
//
//  Created by Yik Hin Garret Lai on 17/3/2022.
//

#include "DigitalCamera.h"
#include <iostream>

DigitalCamera::DigitalCamera():Camera("000000", "XXX"){
    pixelInM = 1;
    isCMOS = false;
}

DigitalCamera::DigitalCamera(const DigitalCamera& dc){
    setSerialNum(dc.getSerialNum());
    setBrand(dc.getBrand());
    pixelInM = dc.pixelInM;
    isCMOS = dc.isCMOS;
}

DigitalCamera::DigitalCamera(int pixelInM):Camera("000000", "XXX"){
    this->pixelInM = pixelInM;
    isCMOS = false;
}

DigitalCamera::DigitalCamera(std::string serialNum, std::string brand, int pixelInM, bool isCMOS):Camera(serialNum, brand){
    this->pixelInM = pixelInM;
    this->isCMOS = isCMOS;
}

void DigitalCamera::setPixelInM(int pixelInM){
    this->pixelInM = pixelInM;
}

int DigitalCamera::getPixelInM() const{
    return pixelInM;
}

void DigitalCamera::setIsCMOS(bool isCMOS){
    this->isCMOS = isCMOS;
}

bool DigitalCamera::getIsCMOS() const{
    return isCMOS;
}

void DigitalCamera::printCameraInfo(){
    std::cout << "This is a camera." << std::endl;
    std::cout << "Brand: " << getBrand() << std::endl;
    std::cout << "Serial Number: " << getSerialNum() << std::endl;

    // Do not remove the following statements as they're owned by DigitalCamera.
    std::cout << "Pixel: " << getPixelInM() << " (M)" << std::endl;
    std::cout << "CMOS: " << (getIsCMOS()? "Yes" : "No") << std::endl;
}
