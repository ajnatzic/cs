#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>
/**
 * This is where the functions from "functions.h" will be implemented.
 * */
	/** used for iteration and for loops */
	int i;
	
	/** the alphabet reversed. used when creating the cipher */
	char* reverseAlpha = "ZYXWVUTSRQPONMLKJIHGFEDCBA";

	/** used as a char variable for several functions below */
	char ch;

/** 
 * This function actually does the encrypting. This was taken from lab 3.
 * */
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
	
 /**
 *  Remove duplicate characters in array word and return the resulting string. This is how
 *  the cipher is created, so it should not be more than 26 characters.
 *
 *  Some code snippets taken from: https://www.geeksforgeeks.org/remove-duplicates-from-a-given-string/
 *  */
char * removeDuplicates(char word []){
   // Used as index in the modified string 
   int index = 0;    
     
   // Traverse through all characters 
   for (i = 0; word[i] != 0; i++) { 
     // Check if word[i] is present before it   
     int j;   
     for (j=0; j<i; j++)  
        if (word[i] == word[j]) 
           break; 
       
     // If not present, then add it to 
     // result. 
     if (j == i) 
        word[index++] = word[i]; 
   } 
   
   // We know that the cipher must only have 26 letters. So if there are more than remove the extra characters
   for(i = 26;i < strlen(word); i++)
	   word[i] = 0;

   return word; 
}

// search the first num characters in array charArray for character target
// return a non-zero integer if found, otherwise, return 0
int targetFound(char charArray[], int num, char target){
	for(i = 0; i < num; i++){
		if(charArray[i] == (target - 'A'))
			return 1;
	}

	return 0;
}

// initialize the encrypt array with appropriate cipher letters according to the given key
void initializeEncryptArray(char key[], char encrypt[]){
	for(i = 0; i < (strlen(key)); i++){
		encrypt[i] = key[i];
	}	
	int j;
	for(j = 0;j < strlen(reverseAlpha); j++){
		encrypt[i] = reverseAlpha[j];
		i++;
	}
	removeDuplicates(encrypt);
}

// initialize decrypt array with appropriate substitute letters based on the encrypt array
void initializeDecryptArray(char encrypt[], char decrypt[]){
	for(i = 0; i < (strlen(encrypt)); i++){
		decrypt[i] = encrypt[i];
	}	
	int j;
	for(j = 0;j < strlen(reverseAlpha); j++){
		decrypt[i] = reverseAlpha[j];
		i++;
	}
	removeDuplicates(decrypt);

}

// process data from the input file and write the result to the output file
// pass the encrypt array to parameter substitute if encryption is intended
// pass the decrypt array to parameter substitute if decryption is intended
void processInput(FILE * inf, FILE * outf, char substitute[]){
	int n = 0;
	int len = 26;
	if (inf ==  NULL || outf == NULL) 
	{
		printf("File could not be opened\n");
		exit(1);
	}

	while ( fscanf(inf, "%c", &ch) != EOF )
	{
		// if we have reached the end of the key_arr and n != 0, then set it to zero
		if(n % len == 0 && n != 0)
			n = 0;
		
		fprintf(outf, "%c", encrypt(ch, substitute[n]));
				
		n++;
		
	}

	fclose(inf);
	fclose(outf);
}


