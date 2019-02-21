#include <stdio.h>

typedef unsigned long ul;

int main(){
	
	char 		*pc = NULL;
	int 		*pi = NULL;

	double 		*pd = NULL;
	long double 	*pld = NULL;

	printf("\n%5lu %5lu\n%5lu %5lu\n%5lu %5lu\n\n", 
			(ul)(pc + 1), (ul)(pi + 1),
			(ul)(pd + 1), (ul)(pld + 1),
			(ul)(pc + 3), (ul)(pld + 3));
	
	return 0;
}
