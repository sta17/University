#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char** argv) {
    
    char array1[30];
    char array2[30];
    char array3[60];

    printf("Please enter first part of text:\n");

    scanf("%[a-zA-Z ]",&array1);
    printf("Your first scan: %s\n",array1);

    strcpy(array2, array1);
    //printf("array2: %s",array2);
    printf("Please enter second part of text:\n");

    scanf(" %[a-zA-Z ]",&array1);
    printf("Your second scan: %s\n",array1);

    //printf("array2: %s",array2);
    //printf("array1: %s",array1);
    strcat(array2, array1);

    printf("Your text: %s\n",array2);

    int s;
    s = strlen(array2);

    printf("length is: %d\n",s);

    return (EXIT_SUCCESS);
}

