/* 
 * File:   signatures2.h
 * Author: sta17
 *
 * Created on 05 December 2014, 11:25
 */

#ifndef SIGNATURES2_H
#define	SIGNATURES2_H

    /* These are the range variables,
     * that creature cordinates are 
     * checked against. 
     */
    #define NORTHRANGE 52.833
    #define SOUTHRANGE 52.00
    #define EASTRANGE -4.000
    #define WESTRANGE -5.500
    #define IDSIZE 5 //10 chars pluss end
    #define FILENAME_SIZE 25 //24 chars pluss end
    #define MOVEMENT_ACCURACY 0.02 
    //presicion used to determin if it is the same mammal.

#ifdef	__cplusplus
extern "C" {
#endif
    
    // general includes
    
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>
#include "navigation.h"
    
    // Structs
    
    struct observer{
        char ID[IDSIZE]; //10 chars pluss end
        location loc;
        struct observer *next;
    };
   
    struct cetaceans {
       char ID[IDSIZE]; //10 chars pluss end
       char cetaceantype;
       double degrees;
       double nauticalmiles;
       location loc;
       struct observer *obs;
       struct cetaceans *next;
    };
    
    struct cetaceangroup {
       location averageloc;
       char cetaceantype;
       struct cetaceans *movementhead;
       struct cetaceangroup *next;
    };
    
    struct pod {
       int cetaceanscount;
       char cetaceantype;
       struct cetaceans *podhead;
       struct cetaceans *next;
    };
    
    struct timeanddate{
        int year;
        int month;
        int day;
        int hour;
        int min;
        int sec;
    };

// Feature 1 : Your Initial Mission functions
void printcetaceans(struct cetaceans *head);// Display
void readobserver(struct timeanddate *tad2, struct observer *obshead);//read,load file
void readsightings(struct cetaceans *sighthead);
location calculatelocation(location observer,double bearing, double range);//calculations
void matchlists(struct cetaceans *sight,struct observer *obs);
void rangeevaluation(struct cetaceans *head2);
struct cetaceans * newcetaceannode(char ID[IDSIZE], char cetaceantype, double degrees, double nauticalmiles);
    
// Feature 2 : Your Main Mission functions
void displaygroup(struct cetaceangroup *grouphead);
void averagelocations(struct cetaceangroup **grouphead);
void groupfinder(struct cetaceans *sighthead, struct cetaceangroup **grouphead);

//extra
void printobserver(struct observer *head);

#ifdef	__cplusplus
}
#endif

#endif	/* SIGNATURES_H */
