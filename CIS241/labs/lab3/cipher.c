#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char encrypt(char ch, int k);

int main(int argc, char* argv[])
{ 	
	/** char pointer that holds the entire input string key */
	char* key = argv[1];
	
	/** this integer value holds the length of the input string */
	int len = strlen(argv[1]);
	

	char ch;
	FILE *fin, *fout;
	
	/** initialize a big array so that the user can input a large amount of letters for the cipher */
	int key_arr[100];
	
	/** the number that will keep track of the number of characters to be processed */
	int n = 0;

	/** used for "for" loops */
	int i;

	if (argc != 5)
	{
		printf ("Usage: cipher option key infile, outfile\n");
		printf ("Option 1 for encryption and 2 for decryption");
		exit(1);
	}

	/**
	 * The way that this works is that the user can input the string of characters to be used for encryption (for example "FORK") and then 
	 * for the second part of decryption the user can enter the same word but with a '-' sign in front of it (so "-FORK"). The below "for" loops
	 * check the front of the string array and see if there is a '-' sign.
	 **/

	// checks to see if there is a '-' sign at the front of the user string array
	if(key[0] != '-'){
		// if there is NOT, then we will loop through the length of the string and assign integer values of chars to the appropriate key_arr indices
		for(i = 0; i < len; i++){
			key_arr[i]= key[i] - 'A';
		}
	}
	// otherwise, we will treat this array as a decryption array, and set all integer values to a negative values
	else{
		// we set i = 1 so that it skips the '-' in the string
		for(i = 1; i < len; i++){
			// also note, I use "i - 1" here because we need to "shift" the entire array to the left, since we are skipping the '-' char
			key_arr[i - 1] = key[i] - 'A';
			// times each element by -1 so that every value is negative. This will make it so we can decrypt the message
			key_arr[i - 1] *= -1;
		}
		
		// we must decrement the variable len. We do this so in the later for loop when we are dencrypting the program does not try to access an element that is not there
		len = len - 1;
	}
	
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
