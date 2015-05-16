/* 
 * File:   main.c
 * Author: sta17
 *
 * Created on 10 November 2014, 10:02
 */

#include "signatures2.h"

int main(int argc, char** argv) {
    
    struct timeanddate tad;
    struct observer *obshead = NULL;
    struct cetaceans *sighthead = NULL;
    struct pod *podhead = NULL;
    struct cetaceangroup *grouphead = NULL;
    
    readobserver(&tad,&obshead);
    
    readsightings(&sighthead);
    
    matchlists(sighthead,obshead);
    
    struct cetaceans *current;
    current = sighthead;
    while (current->next != NULL) {
            current->loc = calculatelocation(current->obs->loc,current->degrees,current->nauticalmiles);
            current = current->next;
    }
    
    rangeevaluation(sighthead);

    printcetaceans(sighthead);
    
    groupfinder(sighthead,&grouphead);
    
    averagelocations(&grouphead);
    
    displaygroup(grouphead);
    
    return 0;
}

// Shared functions

/*
 * creates a cetacean node
 * 
 * @param ID the id of the observer
 * @param cetaceantype type of cetacean
 * @param degrees the degrees
 * @param nauticalmiles nautrical miles
 * @return 
 */
struct cetaceans * newcetaceannode(char ID[IDSIZE], char cetaceantype, double degrees, double nauticalmiles){
    struct cetaceans *tmp; 
    tmp = malloc(sizeof(struct cetaceans));;     //allocate space for node
    tmp->cetaceantype = cetaceantype;          // start adding details
    tmp->degrees = degrees;
    tmp->nauticalmiles = nauticalmiles;
    tmp->loc.lat = 0;
    tmp->loc.lng = 0;
    strcpy(tmp->ID, ID);
    tmp->next  = NULL;                          // set next pointer to null
    return tmp;
}