#include <stdio.h>
#include <string.h>
/**
 * This is where the functions from "functions.h" will be implemented.
 * */
	/** used for iteration and for loops */
	int i;
/**
 *  remove duplicate characters in array word and return the resulting string
 *  */
char * removeDuplicates(char word []){
   // Used as index in the modified string 
   int index = 0;    
     
   // Traverse through all characters 
   for (i = 0; word[i] != 0; i++) { 
     // Check if str[i] is present before it   
     int j;   
     for (j=0; j<i; j++)  
        if (word[i] == word[j]) 
           break; 
       
     // If not present, then add it to 
     // result. 
     if (j == i) 
        word[index++] = word[i]; 
   } 
   
   // We know that the cipher must only have 26 letters. So if there are more than remove them
   for(i = 26;i < strlen(word); i++)
	   word[i] = 0;

   return word; 
}

// search the first num characters in array charArray for character target
// return a non-zero integer if found, otherwise, return 0
int targetFound(char charArray[], int num, char target);

// initialize the encrypt array with appropriate cipher letters according to the given key
void initializeEncryptArray(char key[], char encrypt[]);

// initialize decrypt array with appropriate substitute letters based on the encrypt array
void initializeDecryptArray(char encrypt[], char decrypt[]);

// process data from the input file and write the result to the output file
// pass the encrypt array to parameter substitute if encryption is intended
// pass the decrypt array to parameter substitute if decryption is intended
void processInput(FILE * inf, FILE * outf, char substitute[]);
