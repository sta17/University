/* 
 * File:   signatures.h
 * Author: sta17
 *
 * Created on 17 November 2014, 09:55
 */

#ifndef SIGNATURES_H
#define	SIGNATURES_H

#ifdef	__cplusplus
extern "C" {
#endif

    struct competitor{
        char name_competitor[31];
        int cycle_secs;
        int swim_secs;
        int run_secs;   
        int total_secs;
    };
    
    struct time{
    int hours;
    int mins;
    int sec;   
    };

#ifdef	__cplusplus
}
#endif

#endif	/* SIGNATURES_H */

