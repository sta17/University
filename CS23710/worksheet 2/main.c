/* 
 * File:   main.c
 * Author: sta17
 *
 * Created on 06 October 2014, 09:40
 */

#include <stdio.h>
#include <stdlib.h>

double convert(int fp,int ip);

int main(int argc, char** argv) {

    printf("Please enter your height for conversion to meter:\nPlease enter feet:\n");

    int f;

    scanf(" %d",&f); //note the & in front of i

    printf("Please enter inches:\n");

    int i;

    scanf(" %d",&i); //note the & in front of i

    double m;

    m = convert(f,i);

    printf("Your heigh in meters is: %f\n",m);

    return (EXIT_SUCCESS);
}

double convert(int fp,int ip){//function = method

    fp = fp * 12;

    ip = ip + fp;

    double mp;

    mp = 2.54 * ip;

    mp = mp / 100;

    return mp;

}