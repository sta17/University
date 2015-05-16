/* 
 * File:   signatures.h
 * Author: sta17
 *
 * Created on 24 November 2014, 09:32
 */

#ifndef SIGNATURES_H
#define	SIGNATURES_H

#ifdef	__cplusplus
extern "C" {
#endif

   
struct book{
    char book_title[51];
    char author_name[31];
    int ISBN;
    struct book *next_book;
};

//struct book *head = NULL;

int nodes_to_add = 3;

#ifdef	__cplusplus
}
#endif

#endif	/* SIGNATURES_H */

