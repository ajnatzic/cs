#include <stdio.h>

int main(){
	
	int int_size, short_size, long_size;

	int_size = sizeof(int);
	short_size = sizeof(short);
	long_size = sizeof(long);

	printf("Size of int in bytes: %d\n", int_size);
	printf("Size of short in bytes: %d\n", short_size);
	printf("Size of long in bytes: %d\n", long_size);

	return 0;
}

