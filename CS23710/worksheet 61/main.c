/* 
 * File:   main.c
 * Author: sta17
 *
 * Created on 17 November 2014, 09:29
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "signatures.h"

void print(struct competitor *racer2, int racer_number);
struct time timeFix(int secs);

/*
 * 
 */
int main(int argc, char** argv) {
    
    //races structures
    struct time cycle; //
    struct time swim; //
    struct time run; //
    struct time total; //
    struct competitor racer;
    
    //name
    char name[31];
    
    //number of competitors;
    int num_competitor;
    num_competitor = 2;
    
    // the array of racers
    struct competitor competitors[num_competitor];
    
    int i;
    i = 0;
    while(i < num_competitor){
    
    printf("Competitor's name: ");

    scanf(" %[a-zA-Z_]",&name);

    printf("Time for cycle race (hours minutes seconds):");
    scanf(" %i",&cycle.hours);
    scanf(" %i",&cycle.mins);
    scanf(" %i",&cycle.sec);

    printf("Time for swim (hours minutes seconds):");
    scanf(" %i",&swim.hours);
    scanf(" %i",&swim.mins);
    scanf(" %i",&swim.sec);

    printf("Time for the running race (hours minutes seconds):");
    scanf(" %i",&run.hours);
    scanf(" %i",&run.mins);
    scanf(" %i",&run.sec);
    
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
    
    competitors[i] = racer;
    
    printf("Competitor %s has a total time of %iHrs %iMins %iSecs.\n",name,total.hours,total.mins,total.sec);
    
    i++;
    }
    
    int j;
    j = 0;
    struct competitor *current_competitor;
    printf("NAME        competitor  cycle time  swim time   run time    total time\n");
    printf("======================================================================\n");
    while(j < num_competitor){
        current_competitor = &competitors[j];
        print(current_competitor,j);
        j++;
    }
    
    return (EXIT_SUCCESS);
}

void print(struct competitor *racer2, int racer_number){

    //races structures
    struct time cycle; //
    struct time swim; //
    struct time run; //
    struct time total; //
    racer_number = racer_number + 1;
    
    cycle = timeFix(racer2->cycle_secs);
    swim = timeFix(racer2->swim_secs);
    run = timeFix(racer2->run_secs);
    total = timeFix(racer2->total_secs);
    
    printf(" %s %i %iH %iM %iS %iH %iM %iS %iH %iM %iS %iH %iM %iS\n",racer2->name_competitor,racer_number,cycle.hours,cycle.mins,cycle.sec,swim.hours,swim.mins,swim.sec,run.hours,run.mins,run.sec,total.hours,total.mins,total.sec);
    
}

struct time timeFix(int secs){

    struct time temp_time;
    
    temp_time.mins = secs/60 ;
    temp_time.sec = secs % 60 ;
    temp_time.hours = temp_time.mins/60 ;
    temp_time.mins = temp_time.mins % 60 ;
    
    return temp_time;
}