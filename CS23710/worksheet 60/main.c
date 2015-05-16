/* 
 * File:   main.c
 * Author: sta17
 *
 * Created on 17 November 2014, 09:07
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * 
 */
int main(int argc, char** argv) {

    struct competitor{
        char name_competitor[31];
        int cycle_secs;
        int swim_secs;
        int run_secs;   
        int total_secs;
    };
    
    struct time{
    int hours;
    int mins;
    int sec;   
    };
    
    //races structures
    struct time cycle; //
    struct time swim; //
    struct time run; //
    struct time total; //
    struct competitor racer;
    
    //name
    char name[31];
    
    printf("Competitor's name: ");

    scanf(" %[a-zA-Z_]",&name);

    printf("Time for cycle race (hours minutes seconds):");
    scanf(" %i",&cycle.hours);
    scanf(" %i",&cycle.mins);
    scanf(" %i",&cycle.sec);

    printf("Time for swim (hours minutes seconds):");
    scanf(" %d",&swim.hours);
    scanf(" %d",&swim.mins);
    scanf(" %d",&swim.sec);

    printf("Time for the running race (hours minutes seconds):");
    scanf(" %d",&run.hours);
    scanf(" %d",&run.mins);
    scanf(" %d",&run.sec);
    
    total.hours = swim.hours + cycle.hours + run.hours;
    total.mins = swim.mins + cycle.mins + run.mins;
    total.sec = swim.sec + cycle.sec + run.sec;
    
    total.mins = total.mins + total.sec/60 ;
    total.sec = total.sec % 60 ;
    total.hours = total.hours + total.mins/60 ;
    total.mins = total.mins % 60 ;
    
    racer.run_secs = run.sec + (run.mins+(run.hours*60)*60);
    racer.cycle_secs = cycle.sec + (cycle.mins+(cycle.hours*60)*60);
    racer.swim_secs = swim.sec + (swim.mins+(swim.hours*60)*60);
    racer.total_secs = total.sec + (total.mins+(total.hours*60)*60);
    strcpy(racer.name_competitor, name);
    
    
    printf("Competitor %s has a total time of %iHrs %iMins %iSecs.\n",name,total.hours,total.mins,total.sec);
    
    return (EXIT_SUCCESS);
}

