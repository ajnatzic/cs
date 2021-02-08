#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/stat.h>
#include <sys/sem.h>

int main(){
    
    long int *shmPtr;
    int shmId;
   
    // sem variables
    int semId;
       
    
    // This will fail to create a memory segment as it's one above the max size
    // of 25241919488
    if ((shmId = shmget (IPC_PRIVATE, 25241919489, IPC_CREAT | S_IRUSR | S_IWUSR)) < 0) {
        perror ("i can't get no..\n");
        exit (1);
    }
    if ((shmPtr = shmat (shmId, 0, 0)) == (void *) -1) {
        perror ("can't attach\n");
        exit (1);
    }

    
    // Create 1 semaphore
    if ((semId = semget (IPC_PRIVATE,1,IPC_CREAT | S_IRUSR | S_IWUSR)) < 0) {
        perror ("i can't get no..\n");
        exit (1);
    }
    // Set value of semaphore one above max. This should fail to set a value
    // because it is one above the max setval size of 327677
    // NOTE: This section will not run if shared memory cannot be created
    if(semctl(semId, 0, SETVAL, 32768) < 0){
	perror("semctl setval\n");
	exit(1);
    }


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
