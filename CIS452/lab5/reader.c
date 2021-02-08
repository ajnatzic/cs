#include <sys/ipc.h>
#include <stdio.h>
#include <sys/types.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <sys/ipc.h>
#include <sys/shm.h>

int main(){


	// Generate key for reader file
	const char* filename = "./blank.txt";
	int keyId = 1;
	key_t key = ftok(filename, keyId);
	printf("here is key: %i\n", key);


	while(1){
		
	}

}
