#include <string.h> 
#include <sys/ipc.h> 
#include <sys/shm.h> 
#include <sys/types.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
 

typedef struct Messages {
	char str[50];
	int flag;
} Message;

int main() 
{ 

    Message msg;
    Message *p_msg;

    p_msg = &msg;
    strcpy(p_msg->str, "hello");
    p_msg->flag = 1;
    printf("STR: %s FLAG: %d\n", p_msg->str, p_msg->flag);

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

    

    //p_msg->flag = 1;

    //printf("CURR STR = %s", p_msg->flag);
    

   while(1){ 
	
	
   	printf("Write Data : \n"); 
   	scanf("%s", p_msg->str); 
  
   	printf("Data written in memory: %s\n",p_msg->str);
        
		
   
   	if(strcmp((*p_msg).str, "quit") == 0){
		break;
   	}

   }
    //detach from shared memory  
    shmdt((*p_msg).str);
    //shmdt(wrtFlag); 
  
    return 0; 
} 

