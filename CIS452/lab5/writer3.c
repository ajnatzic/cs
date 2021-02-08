#include <string.h> 
#include <sys/ipc.h> 
#include <sys/shm.h> 
#include <stdio.h> 
/**
 * Lab 5 Writer
 * AJ Natzic, Grant Iversen
 */

struct dataStruct{
	//* str[255] will make 255 strings.
	char str[255];
	int flag1;
	int flag2;
	int quit;

}dataStruct;

// Main for Writer portion of lab 5
int main() {

struct dataStruct *shared_stuff;


    // ftok to generate unique key 
    // NOTE: MUST have file name "blank.txt" in directory
    key_t key = ftok("blank.txt",1); 
 
    // shmget returns an identifier in shmid 
    int shmid = shmget(key,1024,0666|IPC_CREAT);


   perror("from writer"); 
   shared_stuff =  shmat(shmid,(void*)0,0);
   
   while(1){
	// Accept user input and write to shared memory struct
   	printf("Write Data : \n"); 
   	scanf("%s", shared_stuff->str); 
  
	// Display what was written and change flags for both readers to 1
   	printf("Data written in memory: %s\n",shared_stuff->str);
        shared_stuff->flag1 = 1;
	shared_stuff->flag2 = 1;

	// Quit sequence   
   	if(strcmp(shared_stuff->str, "quit") == 0){
		shared_stuff->flag1 = 0;
		shared_stuff->flag2 = 0;
		shared_stuff->quit = 1;
		printf("we got to quit\n");
		break;
   	}

   }
    //detach from shared memory  
    shmdt(shared_stuff);
     
  
    return 0; 
} 


