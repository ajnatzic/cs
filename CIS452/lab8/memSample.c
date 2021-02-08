#include <stdio.h>
#include <stdlib.h>

int global = 0;
int main(){

	int local = 0;
	int init = 0;
	int unInit;
	int array[10000];
	char* pointer = "blah";

	printf("Global var add: %x type: %p\n", &global, &global);
	printf("Local var add: %x type: %p\n", &local, &local);
	printf("Init var add: %x type: %p\n", &init, &init);
	printf("unInit var add: %x type: %p\n", &unInit, &unInit);
	printf("Array var add: %x type: %p\n", &array, &array);
	printf("Pointer var add: %x type: %p\n", &pointer, &pointer);


	return 0;
}
