#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/stat.h>
#include <sys/sem.h>


#define SIZE 16

/**
 * Lab 6 CIS452, Semaphores
 * Created by AJ Natzic and Grant Iversen
 */
int main (int argc, char* argv[]) {
    int status;
    long int i, loop, temp, *shmPtr;
    int shmId;
    pid_t pid;


    // sem variables
    int semId;
    

    // Create sembuf, used like wait and signal (wait is p, signal is v)
    struct sembuf p = {0,-1,SEM_UNDO};
    struct sembuf v = {0,+1,SEM_UNDO};
    /*
     * TODO: get value of loop variable(from command - line
     * argument
     */
    loop = atol(argv[1]);
    //printf("loop var = %li", loop);
    
    // Create shared memory and attach
    if ((shmId = shmget (IPC_PRIVATE, SIZE, IPC_CREAT | S_IRUSR | S_IWUSR)) < 0) {
        perror ("i can't get no..\n");
        exit (1);
    }
    if ((shmPtr = shmat (shmId, 0, 0)) == (void *) -1) {
        perror ("can't attach\n");
        exit (1);
    }

    // Put values in shared memory
    shmPtr[0] = 0;
    shmPtr[1] = 1;


    // Create 1 semaphore
    if ((semId = semget (IPC_PRIVATE,1,IPC_CREAT | S_IRUSR | S_IWUSR)) < 0) {
        perror ("i can't get no..\n");
        exit (1);
    }
    // Set value of semaphore
    if(semctl(semId, 0, SETVAL, 1) < 0){
	perror("semctl setval\n");
	exit(1);
    }
    

    if (!(pid = fork ())) {
        for (i = 0; i < loop; i++) {

            /*
             * TODO: swap the contents of shmPtr[0] and  shmPtr[1]
             */

	    // Decrement the buffer (wait)
	    if(semop(semId, &p, 1) < 0)
            {
                perror("semop p"); 
		exit(13);
            }
	    // Swap values, critical section
	    temp = shmPtr[0];
	    shmPtr[0] = shmPtr[1];
	    shmPtr[1] = temp;
	    // Increment the buffer (signal)
	    if(semop(semId, &v, 1) < 0)
            {
                perror("semop v"); 
		exit(13);
            }

	    
        }
	// Detach shared memory
        if (shmdt (shmPtr) < 0) {
            perror ("just can 't let go\n");
            exit (1);
        }
        exit (0);
    }
    else {


        for (i = 0; i < loop; i++) {

            /*
             * TODO: swap the contents of shmPtr[1] and shmPtr[0]
             */
	    // Decrement the buffer (wait) 
	    if(semop(semId, &p, 1) < 0)
            {
                perror("semop p"); 
		exit(13);
            }
	    // Swap values, critical section
	    temp = shmPtr[0];
	    shmPtr[0] = shmPtr[1];
	    shmPtr[1] = temp;
	    // Increment the buffer (signal)
	    if(semop(semId, &v, 1) < 0)
            {
            	perror("semop v"); 
	    	exit(14);
            }
        }
    }

    wait (&status);
    printf ("values: %li\t%li\n", shmPtr[0], shmPtr[1]);

    // Detach shared memory
    if (shmctl (shmId, IPC_RMID,0) < 0) {
        perror ("just can't let go\n");
        exit (1);
    }
    // Deallocate semaphore
    if (semctl (semId, 0, IPC_RMID) < 0) {
        perror ("can't deallocate\n");
        exit (1);
    }
    return 0;
}

