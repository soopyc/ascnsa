//
//  main.cpp
//  Lab Exercise 9
//
//  Created by Yik Hin Garret Lai on 17/3/2022.
//

#include <iostream>
#include <time.h>
#include "Camera.h"
#include "DigitalCamera.h"
#include "FilmCamera.h"

using namespace std;

int main(int argc, const char * argv[]) {
    /* initialize random seed: */
    srand (time(NULL));
    const int camNum = 10;
    Camera* cam[camNum];
    
    for(int i = 0; i < camNum; i++){
        int type = rand() % 2 + 1;
        if(type == 1){
            string camName = "DCam" + to_string(i);
            int weight = rand() % 1100 + 100;
            int cmos = rand() % 2;
            cam[i] = new DigitalCamera(camName, "KCR", weight, cmos);
        }else{
            string camName = "FCam" + to_string(i);
            int filmSize = (rand()%2? 35 : 24);
            cam[i] = new FilmCamera(camName, "KLB", filmSize);
        }
    }
    
    for(int i = 0; i < camNum; i++){
        cam[i]->printCameraInfo();
        cout << endl;
    }
    
    for(int i = 0; i < camNum; i++){
        delete cam[i];
    }
    
    return 0;
}
