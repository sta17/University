/* 
 * File:   main.c
 * Author: sta17
 *
 * Created on 24 November 2014, 09:29
 */

struct book{
    char book_title[51];
    char author_name[31];
    int ISBN;
    struct book *next_book;
};

//struct book *head = NULL;

//int nodes_to_add = 3;

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
//#include "signatures.h" 

struct book * newbooknode(char book_title[51], char author_name[31],int ISBN);
void addbook(struct book *newbook, struct book **head2);
static void sort(struct book *head2);
void display(struct book *head);
struct book * selectnode(int index,struct book *head);

int main(int argc, char** argv) {
    
    //variables
    char author_name[31];
    char book_title[51];
    int ISBN;
    struct book *head = NULL;
    
    struct book book1;
    book1.ISBN = 321;
    strcpy(book1.author_name, "author1");
    strcpy(book1.book_title,"book1");
    struct book book2;
    book2.ISBN = 231;
    strcpy(book2.author_name, "author2");
    strcpy(book2.book_title,"book2");
    struct book book3;
    book3.ISBN = 123;
    strcpy(book3.author_name, "author3");
    strcpy(book3.book_title,"book3");
    
    struct book *temp1 = newbooknode(book1.book_title,book1.author_name,book1.ISBN);
    addbook(temp1, &head);
    
    struct book *temp2 = newbooknode(book2.book_title,book2.author_name,book2.ISBN);
    addbook(temp2, &head);
    
    struct book *temp3 = newbooknode(book3.book_title,book3.author_name,book3.ISBN);
    addbook(temp3, &head);
    //sort(head);
    
    printf(" display content of a list \n");
    display(head);
    
    printf(" display selected node from list \n");
    struct book *temp4 = selectnode(1, head);
    printf("Book has title %s, authors name is %d, ISBN %d.\n",temp4->book_title,temp4->author_name,temp4->ISBN);
    
    return (EXIT_SUCCESS);
}

struct book * 
newbooknode(char book_title[51], char author_name[31],int ISBN){
    struct book *tmpbook;                         //create a temporary node 
    tmpbook = malloc(sizeof(struct book));; //allocate space for node
    tmpbook->ISBN = ISBN;
    strcpy(tmpbook->book_title, book_title);
    strcpy(tmpbook->author_name, author_name);
    tmpbook->next_book  = NULL;
    return tmpbook;
}

void addbook(struct book *newbook, struct book **head2){
    if(*head2==NULL){
        *head2 = newbook;
    } else {
//        struct book *temp1;
//        *temp1 = head2;
//        while( temp1 != NULL ){
//        *temp1 = temp1->next_book;   // tranfer the address of 'temp->next_book' to 'temp'
//        }
//        temp1->next_book = newbook;
        
       struct book * current = *head2;
    while (current->next_book != NULL) {
        current = current->next_book;
    }
         current->next_book = newbook;
    }
    
    //sort(head2);
    /*
    struct book *temp1;                  // create a temporary node
    temp1 = malloc(sizeof(struct book));; //allocate space for node
    struct book *temp2;                  // create a temporary node
    temp2 = malloc(sizeof(struct book));; //allocate space for node
 
    struct book tempbook;
 
    for( temp1 = head2 ; temp1!=NULL ; temp1 = temp1->next_book ){
        for( temp2 = temp1->next_book ; temp2!=NULL ; temp2 = temp2->next_book ){
            if( temp1->ISBN > temp2->ISBN ){
                  tempbook.ISBN = temp1->ISBN;
                  temp1->ISBN = temp2->ISBN;
                  temp2->ISBN = tempbook.ISBN;
                  
                  strcpy(tempbook.book_title, temp1->book_title);
                  strcpy(tempbook.author_name, temp1->author_name);
                  
                  strcpy(temp1->author_name,temp2->author_name);
                  strcpy(temp1->book_title,temp2->book_title);
                  
                  strcpy(temp2->author_name, tempbook.author_name);
                  strcpy(temp2->book_title, tempbook.author_name);
            }
        }
    }
    free(temp1);
    temp1 = NULL;
    free(temp2);
    temp2 = NULL;
    */
}

static void sort(struct book *head2){
    struct book *temp1;                  // create a temporary node
    temp1 = malloc(sizeof(struct book));; //allocate space for node
    struct book *temp2;                  // create a temporary node
    temp2 = malloc(sizeof(struct book));; //allocate space for node
 
    struct book tempbook;
 
    for( temp1 = head2 ; temp1!=NULL ; temp1 = temp1->next_book ){
        for( temp2 = temp1->next_book ; temp2!=NULL ; temp2 = temp2->next_book ){
            if( temp1->ISBN > temp2->ISBN ){
                  tempbook.ISBN = temp1->ISBN;
                  temp1->ISBN = temp2->ISBN;
                  temp2->ISBN = tempbook.ISBN;
                  
                  strcpy(tempbook.book_title, temp1->book_title);
                  strcpy(tempbook.author_name, temp1->author_name);
                  
                  strcpy(temp1->author_name,temp2->author_name);
                  strcpy(temp1->book_title,temp2->book_title);
                  
                  strcpy(temp2->author_name, tempbook.author_name);
                  strcpy(temp2->book_title, tempbook.author_name);
            }
        }
    }
    free(temp1);
    temp1 = NULL;
    free(temp2);
    temp2 = NULL;
}

void display(struct book *head){
    struct book *temp6;
    temp6 = head;
    printf("--------------------------------------------------\n");
    while( temp6!=NULL ){
    printf("Book has title %s, authors name is %s, ISBN %d.\n",temp6->book_title,temp6->author_name,temp6->ISBN);
    printf("--------------------------------------------------\n");
    temp6 = temp6->next_book;   // tranfer the address of 'temp->next' to 'temp'
    }
}

struct book * selectnode(int index, struct book *head){
    struct book *temp = head;
    int i = 0;
    while( temp!=NULL ){
        if(i == index){
            return temp;
        }
    temp = temp->next_book;   // tranfer the address of 'temp->next' to 'temp'
    i++;
    }
    return NULL;
}