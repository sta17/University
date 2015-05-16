/* 
 * File:   main.c
 * Author: sta17
 *
 * Created on 17 November 2014, 10:27
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "signatures.h"

void print(struct competitor *racer);
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
    
    // the array of racers
    struct competitor competitors[num_competitor];
    
    int i;
    i = 0;
    while(i < num_competitor){
    
    printf("Competitor's name: ");

    scanf(" %s",name);

    printf("Time for cycle race (hours minutes seconds):");
    scanf(" %d",&cycle.hours);
    scanf(" %d",&cycle.mins);
    scanf(" %d",&cycle.sec);

    printf("Time for swim (hours minutes seconds):");
    scanf(" %d",&swim.hours);
    scanf(" %d",&swim.mins);
    scanf(" %d",&swim.sec);

    printf("Time for the running race (hours minutes seconds):");
    scanf(" %d",&run.hours);
    scanf(" %d",&run.mins);
    scanf(" %d",&run.sec);
    
    /*
    total.hours = swim.hours + cycle.hours + run.hours;
    total.mins = swim.mins + cycle.mins + run.mins;
    total.sec = swim.sec + cycle.sec + run.sec;
    
    total.mins = total.mins + total.sec/60 ;
    total.sec = total.sec % 60 ;
    total.hours = total.hours + total.mins/60 ;
    total.mins = total.mins % 60 ;
    */
    
    racer.run_secs = run.sec + (run.mins+run.hours*60)*60;
    racer.cycle_secs = cycle.sec + (cycle.mins+cycle.hours*60)*60;
    racer.swim_secs = swim.sec + (swim.mins+swim.hours*60)*60;
    racer.total_secs = racer.run_secs + racer.cycle_secs + racer.swim_secs;
    
    total = timeFix(racer.total_secs);
    
    strcpy(racer.name_competitor, name);
    
    competitors[i] = racer;
    competitors[i].competitor_number = i;
    competitors[i].competitor_number++;
    
    printf("Competitor %s has a total time of %iHrs %iMins %iSecs.\n",name,total.hours,total.mins,total.sec);
    
    i++;
    }
    
    struct competitor *array_pointer;
    array_pointer = &competitors;
    sort(array_pointer);
    
    int b;
    b = 0;
    struct competitor *current_competitor;
    printf("NAME        competitor  cycle time  swim time   run time    total time\n");
    printf("======================================================================\n");
    while(b < num_competitor){
        current_competitor = &competitors[b];
        print(current_competitor);
        b++;
    }
    return (EXIT_SUCCESS);
}

void print(struct competitor *racer){

    //races structures
    struct time cycle; //
    struct time swim; //
    struct time run; //
    struct time total; //
    
    cycle = timeFix(racer->cycle_secs);
    swim = timeFix(racer->swim_secs);
    run = timeFix(racer->run_secs);
    total = timeFix(racer->total_secs);
    
    printf(" %-11s %8i %2iH %02iM %02iS %2iH %02iM %02iS %2iH %02iM %02iS %2iH %02iM %02iS\n",
            racer->name_competitor,
            racer->competitor_number,
            
            cycle.hours,
            cycle.mins,
            cycle.sec,
            
            swim.hours,
            swim.mins,
            swim.sec,
            
            run.hours,
            run.mins,
            run.sec,
            
            total.hours,
            total.mins,
            total.sec
            );
    //man 3 printf for man page on print details.
}

struct time timeFix(int secs){

    struct time temp_time;
    
    temp_time.mins = secs/60 ;
    temp_time.sec = secs % 60 ;
    temp_time.hours = temp_time.mins/60 ;
    temp_time.mins = temp_time.mins % 60 ;
    
    return temp_time;
}

void sort(struct competitor *temp_competitors){
    int i, j;
    struct competitor tmp;

        for (i = 0; i < num_competitor; ++i){
        for (j = i + 1; j < num_competitor; ++j){
            if (temp_competitors[i].total_secs > temp_competitors[j].total_secs){
                tmp =  temp_competitors[i];
                temp_competitors[i]= temp_competitors[j];
                temp_competitors[j] = tmp;
            }
        }
    }
}