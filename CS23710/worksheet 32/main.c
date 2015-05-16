/* 
 * File:   main.c
 * Author: sta17
 *
 * Created on 06 October 2014, 09:40
 */

#include <stdio.h>
#include <stdlib.h>
#include "Signatures.h"



int main(int argc, char** argv) {

    doloop();
    
    return (EXIT_SUCCESS);
}

int doloop(){
    int runs = 1;
    do{
    printf("Please enter your height for conversion to meter:\nPlease enter feet:\n");

    int f;
    scanf(" %d",&f); //note the & in front of i

    if(f < 0){
    break;
    }

    printf("Please enter inches:\n");

    int i;
    scanf(" %d",&i); //note the & in front of i

    if(i < 0){
    break;
    }

    double m;
    m = convert(f,i);

    printf("Your heigh in meters is: %f\n",m);

    printf("Do you want to exit? Enter 1 for yes, 0 for no.\n");
    int times = 1;
    scanf(" %d",&times); //note the & in front of i
    
    if(times == 0){
    times = runs;
    }

    } while(runs != 0);
}

int whileloop(){

printf("Please enter the number of times you wish to run this conversion program:\n");

    int times;
    scanf(" %d",&times); //note the & in front of i

    int i;
    for (i = 0; i < times; i++){ /* ------- the loop start --------*/

    printf("Please enter your height for conversion to meter:\nPlease enter feet:\n");

    int f;
    scanf(" %d",&f); //note the & in front of i

    if(f < 0){
    break;
    }

    printf("Please enter inches:\n");

    int i;
    scanf(" %d",&i); //note the & in front of i

    if(i < 0){
    break;
    }

    double m;
    m = convert(f,i);

    printf("Your heigh in meters is: %f\n",m);
    }
}

double convert(int fp,int ip){//function = method

    fp = fp * 12;

    ip = ip + fp;

    double mp;

    mp = 2.54 * ip;

    mp = mp / 100;

    return mp;

}