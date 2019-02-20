#include <ctype.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include "functions.h"

/**
 * Main function that takes the users input and encrypts or decrpyts messages. Also checks for typos in user input
 * */

char encrypt(char ch, int k);

int main(int argc, char* argv[])
{ 	
	/** used for "for" loops */
	int i;

	char* reverseAlpha = "ZYXWVUTSRQPONMLKJIHGFEDCBA";
	
	char* rawKey = argv[2];

		
	/** char pointer that holds the entire input string key. */
	char keyRawArr[100];	
	for(i = 0; i < (strlen(rawKey)); i++){
		keyRawArr[i] = rawKey[i];
	}	
	int j;
	for(j = 0;j < strlen(reverseAlpha); j++){
		keyRawArr[i] = reverseAlpha[j];
		i++;
	}

	
	char* key = removeDuplicates(keyRawArr);
	
	/** this integer value holds the length of the input string */
	int len = 26;
	

	char ch;
	FILE *fin, *fout;
	
	/** initialize a big array so that the user can input a large amount of letters for the cipher */
	int key_arr[100];	
	
	/** the number that will keep track of the number of characters to be processed */
	int n = 0;

	
	// if the first argument equals 'e' for encryption, then we will make a key array for encryption
	if(*argv[1] == 'e'){
		for(i = 0; i < len; i++){
			key_arr[i]= key[i] - 'A';
		}
	}

	
	// otherwise, we will treat this array as a decryption array, and set all integer values to a negative values
	else if(*argv[1] == 'd'){
		for(i = 0; i < len; i++){	
			key_arr[i] = key[i] - 'A';
			// times each element by -1 so that every value is negative. This will make it so we can decrypt the message
			key_arr[i] *= -1;
		}
	}
	//TODO test remove later
	//printf("%c KEYARR = ", key_arr);

	fin = fopen(argv[3], "r");
	fout = fopen(argv[4], "w");
    
    	if (fin ==  NULL || fout == NULL) 
	{
		printf("File could not be opened\n");
		exit(1);
	}

	while ( fscanf(fin, "%c", &ch) != EOF )
	{
		// if we have reached the end of the key_arr and n != 0, then set it to zero
		if(n % len == 0 && n != 0)
			n = 0;
		
		fprintf(fout, "%c", encrypt(ch, key_arr[n]));
				
		n++;
		
	}

	fclose(fin);
	fclose(fout);

	return 0;
}

char encrypt(char ch, int k)
{
	if ( k < 0 )
		k = k + 26;

	if ( isupper(ch) )
		return (ch - 'A' + k) % 26 + 'A';
	
	if ( islower(ch) )
		return (ch - 'a' + k) % 26 + 'a';
	
	return ch;
}
