#include <stdio.h>
#include <dirent.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <errno.h>
#include <stdlib.h>

int main()
{
    DIR *dirPtr;
    struct dirent *entryPtr;
    struct stat statBuf;

    dirPtr = opendir(".");

    while ((entryPtr = readdir(dirPtr))){
	if (stat(entryPtr->d_name, &statBuf) < 0) {
	  perror("huh?  there is ");
	  exit(1);
    	}
	printf("%-20s", entryPtr->d_name);
	printf("size: %u\n", statBuf.st_size);

    }
   
    closedir(dirPtr);
    return 0;
}
