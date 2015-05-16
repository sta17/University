#include "signatures2.h"

/*
 * Print method
 */
void printobserver(struct observer *head){   
    struct observer *observers;
    observers = head;
    printf("====================================================\n");
    printf("OBSERVER ID   LATITUDE   LONGTITUDE\n");
    printf("====================================================\n");
    while( observers!=NULL ){
        printf(" %-9s %10.3f %11.3f \n",
            observers->ID,
            observers->loc.lat,
            observers->loc.lng
            );
        observers = observers->next;   // tranfer the address of 'temp->next' to 'temp'
    }
    printf("====================================================\n\n");
}