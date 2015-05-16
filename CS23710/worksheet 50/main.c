#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int convert(int timepassedin, int overflowtarget);

int main(int argc, char** argv) {
    
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
    
    //name
    char array1[31];
    
    printf("Please enter the competitors full name with underscore as whitespace:\n");

    scanf("%[a-zA-Z_]",&array1);
    printf("Competitors name: %s\n",array1);

    printf("Please enter competitors time for cycling race:\n");
    scanf(" %i",&cycle.hours);
    scanf(" %i",&cycle.mins);
    scanf(" %i",&cycle.mins);
    printf("Time for cycle race: %i:%i:%i\n",cycle.hours,cycle.mins,cycle.mins);

    printf("Please enter competitors time for swimming:\n");
    scanf(" %d",&swim.hours);
    scanf(" %d",&swim.mins);
    scanf(" %d",&swim.sec);
    printf("Time for swim race: %i:%i:%i\n",swim.hours,swim.mins,swim.sec);

    printf("Please enter competitors time for final running race:\n");
    scanf(" %d",&run.hours);
    scanf(" %d",&run.mins);
    scanf(" %d",&run.mins);
    printf("Time for running race: %i:%i:%i\n",run.hours,run.mins,run.mins);
    
    total.hours = swim.hours + cycle.hours + run.hours;
    total.mins = swim.mins + cycle.mins + run.mins;
    total.sec = swim.sec + cycle.sec + run.sec;
    
    
    if(total.sec>60){
        total.sec = total.sec - 60;
        total.mins++;
        if(total.sec>60){
        total.sec = total.sec - 60;
        total.mins++;
        }   
    }
    
    if(total.mins>60){
        total.mins = total.mins - 60;
        total.hours++;
        if(total.mins>60){
        total.mins = total.mins - 60;
        total.hours++;
        }   
    }
    
    printf("Competitor %d has a total time of %iHrs %iMins %iSecs\n",array1,total.hours,total.mins,total.sec);
    
    return (EXIT_SUCCESS);
}

int convert(int timepassedin, int overflowtarget){//function = method
    if(timepassedin<60){
        timepassedin = timepassedin - 60;
        overflowtarget++;
        if(timepassedin<60){
        timepassedin = timepassedin - 60;
        overflowtarget++;
        }   
    }
    
    return timepassedin;
    return overflowtarget;
}