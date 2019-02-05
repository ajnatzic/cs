#include <stdio.h>
#include <stdlib.h>
#include "file_utils.h"

/**
 *Includes a main that takes command line arguments from user and interprets which files to read/write to
 *
 * @author AJ Natzic
 * @date 9-18-2018
 */

 /**
  * this main method calls the functions from file_utils to read/write files
  *
  * @return  0
  */
int main(int argc, char* argv[]){

    /** a buffer used to read files */
    char* buffer;

    /** a temporary buffer used to write to files backwards */
    char* tempBuffer;

    /** an int used to store the size of a file */
    int size;

    //reads the file using read_file function and stores the size into variable size
    size = read_file(argv[1], &buffer);

    //gives memory required to tempbuffer
    tempBuffer = malloc(sizeof(char) * size);

    //throws an exception if memory cannot be given to tempBuffer
    if(tempBuffer == NULL){
        fprintf(stderr, "ERROR: Memory was unable to be given to tempbuffer");
    }

    // this loop starts at the end of the file and adds chars going backwards to the tempbuffer
    //the buffIndex int keeps track of which index in the buffer the char will be added to
    int buffIndex = 0;
    for(int i = size - 1; i >= 0; i--){
        tempBuffer[buffIndex] = buffer[i];
        buffIndex++;
    }

    //frees all memory used by buffer
    free(buffer);

    //puts chars from tempBuffer into the new file specified in the command line by user
    write_file(argv[2], tempBuffer, size);

    //frees all memory used by tempBuffer
    free(tempBuffer);

    return 0;
}
