//
//  FilmCamera.cpp
//  Lab Exercise 9
//
//  Created by Yik Hin Garret Lai on 17/3/2022.
//

#include "FilmCamera.h"
#include <iostream>

FilmCamera::FilmCamera():Camera("000000", "XXX"){
    filmSize = 35;
}

FilmCamera::FilmCamera(const FilmCamera& fc){
    setSerialNum(fc.getSerialNum());
    setBrand(fc.getBrand());
    filmSize = fc.filmSize;
}

FilmCamera::FilmCamera(int filmSize):Camera("000000", "XXX"){
    this->filmSize = filmSize;
}

FilmCamera::FilmCamera(std::string serialNum, std::string brand, int filmSize):Camera(serialNum, brand){
    this->filmSize = filmSize;
}

void FilmCamera::setFilmSize(int filmSize){
    this->filmSize = filmSize;
}

int FilmCamera::getFilmSize() const{
    return filmSize;
}

void FilmCamera::printCameraInfo(){
    std::cout << "This is a camera." << std::endl;
    std::cout << "Brand: " << getBrand() << std::endl;
    std::cout << "Serial Number: " << getSerialNum() << std::endl;

    // Do not remove the following statement as it's owned by FilmCamera.
    std::cout << "Film Size: " << getFilmSize() << " mm" << std::endl;
}
