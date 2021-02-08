#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>


void sigHandler(int);

int main(){
	time_t t;
	//srand((unsigned) time(&t));
	
	
	signal(SIGINT, sigHandler);
	printf("waiting...");
	pause();
	signal(SIGUSR2, sigHandler);
	printf("waiting...");

	pid_t who = fork();
	
	if(who == 0){	// If process is the child
		
		printf("Child spawned, process ID# %d", getpid());	
	}	
	else{

		printf("do something");
	}

	//printf("Created child PID# %d", getpid());	
	while(1){
		printf("Waiting for signal...	");
		//pause();
		int randNum = rand() % 5 + 1;
		sleep(randNum);
		printf("SIGNAL");
	}
	
	return 0;
}

void sigHandler(int sigNum){
	if(sigNum == SIGINT){
		printf("User signal recieved, shutting down...");
		exit(0);	// TODO, don't use exit
	}
	if(sigNum == SIGUSR1){
		printf("recieved a SIGUSR1 signal\n");
	}
	if(sigNum == SIGUSR2){
		printf("recieved a SIGUSR2 signal\n");
	}
}


