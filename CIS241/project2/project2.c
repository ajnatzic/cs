#include <ctype.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "functions.h"

/**
 * Main function that takes the users input and encrypts or decrpyts messages. Also checks for typos in user input
 * */
int main(int argc, char* argv[])
{ 	
	/** used for "for" loops */
	int i;
	
	/** takes the raw key input from the user */
	char* rawKey = argv[2];

		
	/** this large array should be able to hold the large key input a user may type in */
	char key[100];	

	/** this integer value holds the final length of the input string. it is 26 because the cipher must be the length of the alphabet */
	const int len = 26;
	
	/** file variable names that will be used to read and write to files */
	FILE *fin, *fout;
	
	/** the number that will keep track of the number of characters to be processed */
	int n = 0;

	
	// if the first argument equals 'e' for encryption, then we will make a key array for encryption
	if(*argv[1] == 'e'){
		initializeEncryptArray(rawKey, key);
		for(i = 0; i < len; i++){
			key[i] = key[i] - 'A';
		}
	}

	
	// otherwise, we will treat this array as a decryption array, and set all integer values to a negative values
	else if(*argv[1] == 'd'){
		initializeDecryptArray(rawKey, key);
		for(i = 0; i < len; i++){	
			key[i] = key[i] - 'A';
			// times each element by -1 so that every value is negative. This will make it so we can decrypt the message
			key[i] *= -1;
		}
	}

	fin = fopen(argv[3], "r");
	fout = fopen(argv[4], "w");

	processInput(fin, fout, key);	

	// Testing the target found method
	printf("\nTARGET FOUND? %i \n\n" ,targetFound(key, 3, 'D'));
	printf("\nTARGET FOUND? %i \n\n" ,targetFound(key, 1, 'D'));

	return 0;
}

