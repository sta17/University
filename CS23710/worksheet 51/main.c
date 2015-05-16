#include <stdio.h>
#include <stdlib.h>
#include <string.h>

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
    
    struct time fastest;
    char name_fastest[31];
    struct time slowest;
    char name_slowest[31];
    
    fastest.hours = 0;
    fastest.mins = 0;
    fastest.sec = 0;
    
    //name
    char name[31];
    
    //number of competitors;
    int num_competitor;

    printf("Please enter number of competitors:\n");
    scanf(" %i",&num_competitor);
    
    int i;
    i = 0;
    while(i < num_competitor){
    //int i;
    //for (i = 0; i < num_competitor; i++){
    
    printf("Please enter the competitors full name with underscore as whitespace:\n");

    scanf(" %[a-zA-Z_]",&name);
    printf("Competitors name: %s\n",name);

    printf("Please enter competitors time for cycling race:\n");
    scanf(" %i",&cycle.hours);
    scanf(" %i",&cycle.mins);
    scanf(" %i",&cycle.sec);
    printf("Time for cycle race: %i:%i:%i\n",cycle.hours,cycle.mins,cycle.sec);

    printf("Please enter competitors time for swimming:\n");
    scanf(" %i",&swim.hours);
    scanf(" %i",&swim.mins);
    scanf(" %i",&swim.sec);
    printf("Time for swim race: %i:%i:%i\n",swim.hours,swim.mins,swim.sec);

    printf("Please enter competitors time for final running race:\n");
    scanf(" %i",&run.hours);
    scanf(" %i",&run.mins);
    scanf(" %i",&run.sec);
    printf("Time for running race: %i:%i:%i\n",run.hours,run.mins,run.sec);
    
    total.hours = swim.hours + cycle.hours + run.hours;
    total.mins = swim.mins + cycle.mins + run.mins;
    total.sec = swim.sec + cycle.sec + run.sec;
    
    /*
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
    */
    
    total.mins = total.mins + total.sec/60 ;
    total.sec = total.sec % 60 ;
    total.hours = total.hours + total.mins/60 ;
    total.mins = total.mins % 60 ;
    
    printf("Competitor %s has a total time of %iHrs %iMins %iSecs.\n",name,total.hours,total.mins,total.sec);
    
    // comperaing for biggest and slowest here.
    
    if(i == 0){
        fastest.mins = total.mins;
        fastest.sec = total.sec;
        fastest.hours = total.hours;
        strcpy(name_fastest, name);
        //name_fastest = name;
        slowest.mins = total.mins;
        slowest.sec = total.sec;
        slowest.hours = total.hours;
        strcpy(name_slowest, name);
        //name_slowest = name;
    }else if(fastest.hours>total.hours){
        if(fastest.mins>total.mins){
            if(fastest.sec>total.sec){
                fastest.mins = total.mins;
                fastest.sec = total.sec;
                fastest.hours = total.hours;
                strcpy(name_fastest, name);
                //name_fastest = name;
            }
        }
    }else{
        slowest.mins = total.mins;
        slowest.sec = total.sec;
        slowest.hours = total.hours;
        strcpy(name_slowest, name);
        //name_slowest = name;
    }
   
    i++;
    }
    
    printf("Fastest competitor %s has a total time of %iHrs %iMins %iSecs.\n",name_fastest,fastest.hours,fastest.mins,fastest.sec);
    
    printf("Slowest Competitor %s has a total time of %iHrs %iMins %iSecs.\n",name_slowest,slowest.hours,slowest.mins,slowest.sec);
    
    return (EXIT_SUCCESS);
}