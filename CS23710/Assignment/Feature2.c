#include "signatures2.h"

// Static/Private Function declearations

static struct cetaceans *copy(struct cetaceans *tobecopied);
static void addcetaceans(struct cetaceans *newcetaceans, struct cetaceans *head2);
static struct cetaceangroup * newcetaceangroupnode();
static void addcetaceanTOgroup( struct cetaceans *tobeadded, struct cetaceangroup *cetaceangroup);
static void addcetaceangroup(struct cetaceangroup *newcetaceangroup, struct cetaceangroup **head2);

//Display

/*
 * Print method
 
 * @param grouphead linked list head for groups of sightings.
 */
void displaygroup(struct cetaceangroup *grouphead){
    struct cetaceangroup *group;
    group = grouphead;
    struct cetaceans *creature;
    creature = grouphead->movementhead;
        printf("\n=====================================================\n");
    while(group!=NULL){
        printf("CETACEANS TYPE   AVREAGE LATITUDE   AVERAGE LONGTITUDE\n");
        printf("======================================================\n");
        printf(" %-13c %9.3f %12.3f \n"
                ,group->cetaceantype
                ,group->averageloc.lat
                ,group->averageloc.lng);
        printf("======================================================\n");
        printf("OBSERVER ID   CETACEANS TYPE   LATITUDE   LONGTITUDE\n");
        printf("======================================================\n");
        
        creature = group->movementhead;
        while( creature!=NULL) {
            printf(" %-13s %-13c %9.3f %12.3f \n",
                creature->ID,
                creature->cetaceantype,
                creature->loc.lat,
                creature->loc.lng);
                creature = creature->next;   // tranfer the address of 'temp->next' to 'temp'
        };
        printf("======================================================\n");
        group = group->next;
    }
}

//calculations, data interaction, "main functions" 

/**
 *  Groups togheter cetaceans sightings, partially working, increase MOVEMENT_ACCURACY from 0.02 to 0.2 and its 
 *  grouping function will work on its most basic. It lacks cross comparrison at this stage and thus will appear
 *  to be rather lacking.
 * @param sighthead linked list head for sightings
 * @param grouphead linked list head for groups of sightings.
 */
void groupfinder(struct cetaceans *sighthead, struct cetaceangroup **grouphead){
    double distance = 0;
    struct cetaceans *temp2;
    struct cetaceans *temp3;
    temp2 = sighthead;
    temp3 = sighthead->next;
    
    while( temp2!=NULL ){

        struct cetaceangroup *temp5 = newcetaceangroupnode();
        addcetaceangroup(temp5, grouphead);
        struct cetaceans * temp1, *temp7;
        temp1 = copy(temp2);
        temp7 = temp5->movementhead;
        //addcetaceans2(temp1, &temp7);
        addcetaceanTOgroup(temp1, temp5);
        
        while( temp3!=NULL ){
            distance = great_circle(temp3->loc, temp2->loc);
            if((distance <= MOVEMENT_ACCURACY) && (temp3->cetaceantype == temp2->cetaceantype) && (temp2 != temp3)){
                struct cetaceans * temp6, *temp8;
                temp6 = copy(temp3);
                //addcetaceans2(temp6, temp7);
                addcetaceanTOgroup(temp6, temp5);
            }
            temp3 = temp3->next;   // tranfer the address of 'temp->next' to 'temp'
        }
        temp3 = sighthead;
        temp2 = temp2->next;   // tranfer the address of 'temp->next' to 'temp'
    }
}

/*
 * copies a cetacean node
 * 
 * @param tobecopied the cetacean to be copied
 * @return the new cetacean.
 */
static struct cetaceans *copy(struct cetaceans *tobecopied) {
    struct cetaceans *temp2 = newcetaceannode(tobecopied->ID, tobecopied->cetaceantype, tobecopied->degrees, tobecopied->nauticalmiles);
    temp2->loc = tobecopied->loc;
    temp2->obs = tobecopied->obs;
    return temp2;
}

/*
 * calculates the average distance for all the groups in the group header.
 * 
 * @param grouphead linked list head for groups of sightings.
 */
void averagelocations(struct cetaceangroup **grouphead){
    double longtitude = 0, latitude = 0, a = 0, b = 0;
    int divisioncounter = 0;
    struct cetaceangroup *temp3 = *grouphead;
    struct cetaceans *temp1;
    
    //here i start the first check and group creation.
    while (temp3!= NULL) {
        temp1 = temp3->movementhead;
        
        while (temp1!= NULL){
            divisioncounter++;
            double a,b;
            a = temp1->loc.lng;
            b = temp1->loc.lat;
            longtitude = longtitude + a;
            latitude = latitude + b;
            temp3->cetaceantype = temp1->cetaceantype;
               temp1 = temp1->next;
        };
        
    temp3->averageloc.lng = longtitude / divisioncounter;
    temp3->averageloc.lat = latitude / divisioncounter;
    longtitude = 0.0;
    latitude = 0.0;
    divisioncounter = 0;
    a = 0.0;
    b = 0.0;
    
    temp3 = temp3->next;   
    }   
}

//Linked list setup

/*
 * basically just add a observer node to the linked list.
 * 
 * @param newcetaceans
 * @param head2
 */
static void addcetaceans(struct cetaceans *newcetaceans, struct cetaceans *head2){
    if(head2 == NULL){
        head2 = newcetaceans;
    } else {
        struct cetaceans * current = head2;
        while (current->next != NULL) {
            current = current->next;
        }
         current->next = newcetaceans;
    }    
}

/*
 * creates a cetaceangroup node
 */
static struct cetaceangroup * newcetaceangroupnode(){
    struct cetaceangroup *tmp;
    tmp = malloc(sizeof(struct cetaceangroup));     //allocate space for node
    tmp->next  = NULL;                               // set next pointer to null
    tmp->movementhead = NULL;
    tmp->averageloc.lat = 0;
    tmp->averageloc.lng = 0;
    return tmp;
}

/*
 * adds a cetacean to the groups local header
 *
 * @param tobeadded new node to be added to linked list
 * @param cetaceangroup linked list head for groups of sightings.
 */
static void addcetaceanTOgroup( struct cetaceans *tobeadded, struct cetaceangroup *cetaceangroup){
    if(cetaceangroup->movementhead == NULL){
        cetaceangroup->movementhead = tobeadded;
    } else {
        struct cetaceans * current = cetaceangroup->movementhead;
        while (current->next != NULL) {
            current = current->next;
        }
         current->next = tobeadded;
         current = current->next;
         current->next = NULL;
    }
}

/*
 * basically just add a cetaceangroup node to the linked list.
 * 
 * @param newcetaceangroup new node to be added to linked list
 * @param head2 linked list head for groups of sightings.
 */
static void addcetaceangroup(struct cetaceangroup *newcetaceangroup, struct cetaceangroup **head2){
    if(*head2 == NULL){
        *head2 = newcetaceangroup;
    } else {
        struct cetaceangroup * current = *head2;
        while (current->next != NULL) {
            current = current->next;
        }
        
         current->next = newcetaceangroup;
    }    
}