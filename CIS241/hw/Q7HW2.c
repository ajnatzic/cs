#include <stdio.h>
#include <string.h>


void stringComp(char * string1, char * string2){
	int result;
	
	if(strlen(string1) == strlen(string2)){
		result = strcmp(string1, string2);
		printf("Both string inputs are the same. Result from strcmp: \t");
		printf("%i\n", result);
	}
	if(strlen(string1) > strlen(string2)){
		printf("The first string is greater.\n");
	}
	if(strlen(string1) < strlen(string2))
		printf("The second string is greater.\n");
}

int main(){

	char * string1 = "hello";
	char * string2 = "yesno";
	char * string3 = "absolutely";

	stringComp(string1, string2);
	stringComp(string1, string3);
	stringComp(string3, string2);
	return 0;
}
