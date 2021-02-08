#include <stdio.h>
#include <dirent.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

/**
 * Lab 10 Extra credit, mini ls
 * 
 * Created by AJ Natzic
 */
int main(int argc, char *argv[])
{
    DIR *dirPtr;
    struct dirent *entryPtr;
    struct stat statBuf;
    struct stat dirBuf;

    // Error handling for creating stats for user specified directory
    if (stat(argv[2], &dirBuf) < 0) {
	perror("dir stat problem");
	exit(1);
    }
    // Checks if the user specified directory is a directory
    if (S_ISDIR(dirBuf.st_mode) == 0){
	perror("File specified is not directory.");
	exit(1);
    }
    // If the user specified file is a directory, open a directory stream 
    dirPtr = opendir(argv[2]);

    // Change our working directory to wherever specified
    // This helps prevent errors that may be caused when using stat(entryPtr->d_name, &statBuf);
    chdir(argv[2]);

    while ((entryPtr = readdir(dirPtr))){
	if (stat(entryPtr->d_name, &statBuf) < 0) {
		perror("huh?  there is ");
		exit(1);
    	}
	// If user uses '-i' option, display inode information
	if(strcmp(argv[1],"-i") == 0){
		printf("INODE:%lu ", entryPtr->d_ino);
	}
	// If user uses '-n' option, display user and group ID for each file
	else if(strcmp(argv[1],"-n") == 0){
		printf("USER-ID:%u GROUP-ID:%u ", statBuf.st_uid, statBuf.st_gid);
	}
	// If no option specified, end program
	else{
		perror("Invalid option specified. Use -i or -n. Exiting\n");
		exit(1);
	}
	// Print filename 
	printf("%-20s\n", entryPtr->d_name);	
	
    }
   
    // Close directory stream
    closedir(dirPtr);
    return 0;
}
