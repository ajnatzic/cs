#include <sys/ipc.h>
#include <stdio.h>
#include <sys/types.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <sys/ipc.h>
#include <sys/shm.h>


#define FOO 4096

int main(){


	// Generate key for reader file
	const char* filename = "./blank.txt";
	int keyId = 1;
	const key_t KEY = ftok(filename, keyId);
	
	// Create struct for shm
//	struct shmid_ds{
//		char usrInput[50];
//	}shmStat;

	printf("\nhere is the key: %i\n", KEY);


	// infinite loop, continously accept input
	//while(1){
    int shmId;
    char *shmPtr;

    if ((shmId = shmget (KEY, FOO, IPC_CREAT | S_IRUSR | S_IWUSR)) < 0) {
        perror ("i can't get no..\n");
        exit (1);
    }
    if ((shmPtr = shmat (shmId, 0, 0)) == (void *) -1) {
        perror ("can't attach\n");
        exit (1);
    }
    if (shmdt (shmPtr) < 0) {
        perror ("just can't let go\n");
        exit (1);
    }
    struct shmid_ds {
	    char userInput[20];
    } test;
    //shmctl(shmId, IPC_STAT, &test);
    //printf("\nthis is here%lu\n\n", test.shm_segsz);
    //printf ("value a: %lu\t value b: %lu\n", (unsigned long) shmId, (unsigned long) shmPtr + FOO);
    
  		
		//printf("Enter message to write: \n");
		
		//scanf("%s", test.userInput);
		//printf("Message \'%s\' sent to reader.\n", shmctl);
		//shmctl(KEY, IPC_STAT, &test);
    		//printf("\nthis is here%s\n\n", test.userInput);		
	//}
	//
// shmat to attach to shared memory 
    char *str = (char*) shmat(shmid,(void*)0,0); 
  
    cout<<"Write Data : "; 
    gets(str); 
  
    printf("Data written in memory: %s\n",str); 
      
    //detach from shared memory  
    shmdt(str); 	
	

    	//destroy shared memory
    	//if (shmctl (shmId, IPC_RMID, 0) < 0) {
        //	perror ("can't deallocate\n");
        //	exit (1);
    	//}


	return 0;
}

