#include <sys/ipc.h> 
#include <sys/shm.h> 
#include <stdio.h>
#include <string.h>

typedef struct Messages {
	char str[50];
	int flag;
} Message;


int main() 
{ 

    Message msg;
    Message *p_msg;

    p_msg = &msg;
    // ftok to generate unique key 
    key_t key = ftok("blank.txt",1); 
  
    // shmget returns an identifier in shmid 
    int shmid;
    if((shmid = shmget(key,sizeof(msg),0666|IPC_CREAT)) < 0){
	perror("SHMGET");
	//exit(1);
    }

    // shmat to attach to shared memory 
    if((p_msg = shmat(shmid,NULL,0)) == (Message*) -1){
	perror("SHMAT");
	//exit(1);
    }

    char saveStr[50] = "";
    //strcpy(saveStr, str);
    while(1){
	strcpy(saveStr, p_msg->str);
	if(strcmp(saveStr, "quit") == 0){
		break;
	}
	if(strcmp(saveStr, p_msg->str) == 0){	
		continue;
	}
	
  	//if(strcmp() != 0){
    		printf("Data read from memory: %s\n",p_msg->str); 
		
	//}
	
	}
     
    //detach from shared memory  
    shmdt(p_msg); 
    
    // destroy the shared memory 
    shmctl(shmid,IPC_RMID,NULL); 
     
    return 0; 
} 
