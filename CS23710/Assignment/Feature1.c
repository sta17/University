#include "signatures2.h"

// Static/Private Function declearations

static struct observer * newobservernode(char ID[IDSIZE], double lat, double lng);//linked list
static void addobserver(struct observer *newobserver, struct observer ***head2);
static void addcetaceans(struct cetaceans *newcetaceans, struct cetaceans ***head2);
static void removecetacean( struct cetaceans **head2, struct cetaceans *removetarget);

//Display

/*
 * Print method
 * 
 * @param head cetacean linked list head
 */
void printcetaceans(struct cetaceans *head){ 
    struct cetaceans *creature;
    creature = head;
    printf("\n====================================================\n");
    printf("OBSERVER ID   CETACEANS TYPE   LATITUDE   LONGTITUDE\n");
    printf("====================================================\n");
    while( creature!=NULL ){
        printf(" %-13s %-13c %9.3f %12.3f \n",
            creature->ID,
            creature->cetaceantype,
            creature->loc.lat,
            creature->loc.lng);
        creature = creature->next;   // tranfer the address of 'temp->next' to 'temp'
    }
    printf("====================================================\n\n");
}

//File load, read

/*
 * reads in the values from file, first what file to open, then read in values.
 *
 * @param tad2 the date in the file
 * @param obshead2 observer linked list head
 */
void readobserver(struct timeanddate *tad2, struct observer *obshead2){
    char file_name_observer[FILENAME_SIZE];
    FILE *ofp;
    
    printf("\nEnter the name of file over the list observers:\n");
    scanf(" %s",file_name_observer);
    //strcpy(file_name_observer,"/aber/dap/cetaceans/data/observers_5.txt");
    ofp = fopen(file_name_observer,"r"); // read mode
    //fgets(,250,file_name_observer);
    
    if( ofp == NULL ){
        perror("Error while opening the file.\n");
        exit(EXIT_FAILURE);
    }
    
    fscanf(ofp," %d %d %d %d %d %d", &tad2->day, &tad2->month, &tad2->year, &tad2->hour, &tad2->min, &tad2->sec);
    
    struct observer temp1;
    while(fscanf(ofp," %s %lf %lf",temp1.ID, &temp1.loc.lat, &temp1.loc.lng) != EOF){
        struct observer *temp2 = newobservernode(temp1.ID, temp1.loc.lat, temp1.loc.lng);
        addobserver(temp2, &obshead2);
    }
    
    fclose(ofp);
}

/*
 * reads in the values from file, first what file to open, then read in values.
 * 
 * @param sighthead sightings/cetaceans linked list head
 */
void readsightings(struct cetaceans *sighthead){
    char file_name_sightings[FILENAME_SIZE];
    FILE *sfp;
    
    printf("\nEnter the name of file over the list sightings:\n");
    scanf(" %s",file_name_sightings);
    //strcpy(file_name_sightings,"/aber/dap/cetaceans/data/sightings_5.txt");
    sfp = fopen(file_name_sightings,"r"); // read mode
    //fgets(file_name_sightings);
 
    if( sfp == NULL ){
        perror("Error while opening the file.\n");
        exit(EXIT_FAILURE);
    }
    
    struct cetaceans temp1;
    while(fscanf(sfp," %s %c %lf %lf",temp1.ID, &temp1.cetaceantype ,&temp1.degrees ,&temp1.nauticalmiles) != EOF){
        struct cetaceans *temp2 = newcetaceannode(temp1.ID, temp1.cetaceantype, temp1.degrees, temp1.nauticalmiles);
        addcetaceans(temp2, &sighthead);
    }
    fclose(sfp);
}

//calculations, data interaction, "main functions" 

/*
 * Checks to see if the creature coordinates are within the range desired.
 * 
 * @param head2 sightings/cetaceans linked list head
 */
void rangeevaluation(struct cetaceans *head2){
    struct cetaceans *current;
    current = head2;
    while (current!= NULL) {
        if(current->loc.lat < SOUTHRANGE || current->loc.lat > NORTHRANGE || current->loc.lng > EASTRANGE || current->loc.lng < WESTRANGE){
            removecetacean(&head2,current);
        }
        current = current->next;
    }
}

/*
 * Goes though linked list of creatures and checks removetarget against the list
 * If match, then  it removes.
 * 
 * @param head2 sightings/cetaceans linked list head
 * @param removetarget the node to be removed
 */
static void removecetacean( struct cetaceans **head2, struct cetaceans *removetarget){
    struct cetaceans *previous = NULL;
    struct cetaceans *current  = NULL;
    struct cetaceans *next  = NULL;
    
    if(*head2 == NULL){
        return;
    } else {
        struct cetaceans * current = *head2;
        
        if(current->next == NULL){
            return;
        }
        
        previous = current;
        
        while (current->next != NULL) {
            next = current->next;
            
            if(removetarget == current){
                previous->next = next;
                free(current);
                removetarget = previous;
                break;
            }
            
            previous = current;
            current = current->next;
        }
        return;
    }  
    
}

/*
 *Calculates out the coordinates for the creature.
 * 
 * @param observer the observer location need for calculations
 * @param bearings the bearing/degree
 * @param RANGE the nautrical miles
 * @return 
 */
location calculatelocation(location observer,double bearings, double RANGE){
    // Bearings = Directions = degrees
    // RANGE = Distances = nautical miles
    // OLATR = represents an observer’s own latitude converted into radians
    // BGR = represents the bearing of the cetacean mammal from True North converted into radians
    location loc;
    double BGR = bearings * M_PI /180.0;
    double OLATR = observer.lat * M_PI /180.0;
    loc.lat = observer.lat + (RANGE * cos(BGR)) / 60.0;
    loc.lng = observer.lng + (RANGE * sin(BGR) / cos(OLATR)) / 60.0;
    return loc;
}

/*
 * Matches observers and creatures against one another and then makes pointer 
 * from creature to its observer if ID matches.
 *
 * @param sighthead sightings/cetaceans linked list head
 * @param obs the observer linked list head
 */
void matchlists(struct cetaceans *sighthead,struct observer *obs){
    struct cetaceans *temp2;
    struct observer *temp3;
    temp2 = sighthead;
    temp3 = obs;
    
    while( temp2!=NULL ){
        while( temp3!=NULL ){
            if(strcmp(temp2->ID, temp3->ID)){
                temp2->obs = temp3;
            }
            temp3 = temp3->next;   // tranfer the address of 'temp->next' to 'temp'
        }
        temp3 = obs;
        temp2 = temp2->next;   // tranfer the address of 'temp->next' to 'temp'
    }
}

//Linked list setup

/*
 * creates a observer node
 * 
 * @param ID observers ID
 * @param lat latitude of the observers location
 * @param lng tongtitude of the observers location
 * @return 
 */
static struct observer * newobservernode(char ID[IDSIZE], double lat, double lng){
    struct observer *tmp;
    tmp = malloc(sizeof(struct observer));     //allocate space for node
    tmp->loc.lat = lat;                         // start adding details
    tmp->loc.lng = lng;
    strcpy(tmp->ID, ID);
    tmp->next  = NULL;                          // set next pointer to null
    return tmp;
}

/*
 * basically just add a observer node to the linked list.
 * 
 * @param newobserver linked list node to add
 * @param head2 observers linked list head
 */
static void addobserver(struct observer *newobserver, struct observer ***head2){
    if(**head2 == NULL){
        **head2 = newobserver;
    } else {
        struct observer * current = **head2;
        while (current->next != NULL) {
            current = current->next;
        }
         current->next = newobserver;
    }    
}

/*
 * basically just add a cetacean node to the linked list.
 * 
 * @param newcetaceans the node to be added to the linked list
 * @param head2  sightings/cetaceans linked list head
 */
static void addcetaceans(struct cetaceans *newcetaceans, struct cetaceans ***head2){
    if(**head2 == NULL){
        **head2 = newcetaceans;
    } else {
        struct cetaceans * current = **head2;
        while (current->next != NULL) {
            current = current->next;
        }
         current->next = newcetaceans;
    }    
}
