#include <pthread.h>
#include <stdio.h>
#include <errno.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <signal.h>

/** 
	program that will process multiple filenames in paralell using multithreading
*/
int filesServed = 0;	//keeps track of files processed

int averageTime = 0;	// running total of total sleep time. Used later to get total time.

pthread_t thread1;     //thread ID holder

void sigEnd(int sig);	//used to capture ^C signals 

void *worker_thread(void *arg);

int main()
{
	signal(SIGINT, sigEnd);

	// Constantly loop and accept filenames as input
	while(1){


	char filenameStr[20];
	
	scanf("%[^\n]%*c", filenameStr);
	
	srand(time(NULL)); 
    	

	int status;            //captures any error code

   	
    	// create and start a thread executing the "worker_thread" function
	// pass filenameStr as a parameter of the thread
    	if ((status = pthread_create(&thread1, NULL, worker_thread, filenameStr)) != 0) {
        	fprintf(stderr, "Thread create error %d: %s\n", status, strerror(status));

        exit(1);
    	}
	


	}
	
    return 0;
}

int getFilesServed(){
	return filesServed;
}


void sigEnd(int sig){	
	pthread_join(thread1, NULL);
	printf("\nEnding program. Number of files serviced: %d\n", getFilesServed());	// Display files served
	printf("Average time slept: %i total sleep time / %i files served = %f avg sleep time (seconds)\n", averageTime, filesServed, (float)((float)averageTime / (float)filesServed));	// Calculate average time slept
	pthread_exit(NULL);
}

void *worker_thread(void *filename)
{
	// create array that will hold filename string. Use memcpy to copy the filename string to the new saveFile string
	char saveFile[20];
	memcpy(saveFile, filename, 20);
   	
	// Generate a random number and use it to determine how long to sleep
	// 1-8 means the thread will sleep for 1 second
	// 9-10 means the thread will sleep between 7-10 seconds 
	int rando = ((rand() % 10)+1);
	
	if(rando < 9){
		averageTime++;
		sleep(1);
		printf("Found file	Filename: \"%s\"\n", saveFile);
		averageTime++;

	}
	else{
			int newRando = ((rand() %4) + 7);
			averageTime += newRando;
			sleep(newRando);
			printf("Sorry for the wait...	Filename: \"%s\"\n", saveFile);
	}
   
       filesServed++;

   
    return NULL;
}
