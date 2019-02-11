#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>

char encrypt(char ch, int k);

int main(int argc, char* argv[])
{  
	int choice;
	int* key_arr;
	int len = 4;
	key_arr = (int *) malloc (len * sizeof(int));

	char ch;
	FILE *fin, *fout;

	if (argc != 5)
	{
		printf ("Usage: cipher option key infile, outfile\n");
		printf ("Option 1 for encryption and 2 for decryption");
		exit(1);
	}
	
	choice = atoi(argv[1]);
	
	//int i;
	
	key_arr = atoi(argv[2]);

	//if (choice == 2)
	//	key = -key;
	
    	fin = fopen(argv[3], "r");
	fout = fopen(argv[4], "w");
    
    	if (fin ==  NULL || fout == NULL) 
	{
		printf("File could not be opened\n");
		exit(1);
	}

	while ( fscanf(fin, "%c", &ch) != EOF )
	{
		fprintf(fout, "%c", encrypt(ch, key_arr));
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
