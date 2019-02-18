#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define LEN 4

char* strnsub (char *p, int n);

int main()
{
    char line[] = "His textbook was bought from that bookstore";  
    char *p1, *p2;

    //set p1 to the beginning of string line;
    p1 = line;	
   
    while (*p1) 	
    {
        //set p2 to the position immediately after p1
	p2 = p1 + 1;
        while (*p2)	
        {
            //if(p1[p1Index, p1Index + LEN] == p2[p2Index, p2Index + LEN])  //a match is found // use strncmp() to compare
	    if(strncmp(strnsub(p1, LEN), strnsub(p2,LEN), LEN) == 0)
            {
                printf("The original string is:\n%s\n", line);
                printf("The first substring:  %s\n", strnsub(p1, LEN));
                printf("The second substring: %s\n", strnsub(p2, LEN));
                return 0;
            }
				
            //advance p2 to the next positio
	    
   	 *(p2++);
        }

      //advance p1 to the next position
	
   	*(p1++);
    }
    printf("No repeated patterns of length %d in the following string:\n%s\n",
            LEN, line);
    return 0;
}


// returns a string with the first n characters of string p

char* strnsub (char *p, int n)
{
 	char * s = (char *) malloc((n+1) * sizeof(char));
       strncpy(s, p, n);
	return s;       
}	
