#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <limits.h>

int main(){
	printf("Page Size %lu\n", sysconf(_SC_PAGESIZE));
	printf("Max # of processes per user: %lu\n", sysconf(_SC_CHILD_MAX));
}
