#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>

/**
* This file contains the functions that were declared in the file_utils.h file.
*
* @author AJ Natzic
* @date 9-18-2018
*/

/**
 * This function reads a file and returns the size of a given file
 *
 * @param filename  the name of the file
 * @param buffer    the buffer used to read the file
 * @return  size    the size of the file
 */
int read_file(char *filename, char **buffer) {

    //this checks to see how big the file is and assigns it to variable size
    //this block of code was taken from the assignment page
    struct stat st;
    stat(filename, &st);
    int size = st.st_size;

    /** opens the file in read mode */
    FILE *fp = fopen(filename, "r");

    //throws an exception if the file is not able to be opened
    if ( fp == NULL ){
        fprintf(stderr, "ERROR: Unable to open the file");
    }
        //otherwise, reads the contents of the file into the buffer
    else {
        *buffer = malloc(sizeof(char) * (size));
        //throws an exception if no memory given to buffer
        if (*buffer == NULL){
            fprintf(stderr, "ERROR: Memory not given to buffer");
        }
        fread(*buffer, sizeof(char), size, fp);
        fclose(fp);
    }
    return (size);

}

/**
 * This function writes to a file
 *
 * @param filename  the name of the file
 * @param buffer    the buffer used to write to the file
 * @param size          the size of the file
 * @return  0
 */
int write_file(char *filename, char *buffer, int size) {

    /** opens the file in write mode */
    FILE *fp = fopen(filename, "w");

    //throws an exception if the file cannot be opened
    if (fp == NULL){
        fprintf(stderr, "ERROR: This file cannot be opened");
    }
    else{
        fwrite(buffer, sizeof(char), size, fp);
        fclose(fp);
    }

    return 0;

}

