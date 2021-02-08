#include <sys/ipc.h> 
#include <sys/shm.h> 
#include <stdio.h>
#include <string.h>
/**
 * Reader for lab 5
 * AJ Natzic, Grant Iversen
 */
struct dataStruct{
	char str[255];
	int flag1;
	int flag2;
	int quit;
	

}dataStruct;

// Reader for lab 5 
int main() 
{ 
    // Instantiate data structure
    struct dataStruct *shared_stuff;

    // ftok to generate unique key 
    key_t key = ftok("blank.txt",1); 
  
    // shmget returns an identifier in shmid 
    int shmid = shmget(key,1024,0666);



    perror("debug");
  
    // shmat to attach to shared memory 
    shared_stuff =  shmat(shmid,(void*)0,0);
    
    // Instantiate shmid structure to get memory id info 
    // We need to know which terminal is printing, so we will use IPC_STAT and shm_nattch to see
    // which process in memory this reader is attached to
    struct shmid_ds test;
    shmctl(shmid, IPC_STAT, &test);
    while(1){
	// If both write flags are 0 and quit flag is 1, then quit
	if (shared_stuff->flag1 == 0 && shared_stuff->flag2 == 0 && shared_stuff->quit == 1){	
		break;
	} 
	// If this is reader terminal 1 (nattch 2) and the flag is set to 1 (write), then print what was written
	if(shared_stuff->flag1 == 1 && test.shm_nattch == 2){
    		printf("Data read from memory: %s\n",shared_stuff->str); 
		
		
		// Set write flag to 0
		shared_stuff->flag1 = 0;
		printf("This is nattch 2\n");
	}

	// If this is reader terminal 2 (nattch 3) and the flag is set to 1 (write), then print what was written
	if(shared_stuff->flag2 == 1 && test.shm_nattch == 3){
		printf("Data read from memory: %s\n",shared_stuff->str); 

		// Set write flag to 0
		shared_stuff->flag2 = 0;
		printf("This is nattch 3\n");
	}	
    }
     
    //detach from shared memory  
    shmdt(shared_stuff->str); 
    
    // destroy the shared memory 
    shmctl(shmid,IPC_RMID,NULL); 
     
    return 0; 

   
}


