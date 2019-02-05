/* A lab for time and math */
#include<stdio.h>
#include<math.h>
#include<time.h>
#include<stdlib.h>

int main()
{
	clock_t start, end;
	double cpu_time_used;
	
	/* clean up the screen for the results of this program */
	system("clear");
	
	/* Tell user the program is running. Please wait. */
	printf("Program is running ... \n\n");
	
	/* Invoking power method 100000000 million times */
	
	printf("CPU time to invoke pow() 1000000000 times in milleseconds:	");

	start = clock();
	int i = 0;
	while(i < 100000000){
		pow(1000.5, 222.2);
		i++;
	}
	end = clock();

	cpu_time_used = (((double) (end - start)) / CLOCKS_PER_SEC) * 1000;
	printf("%f", cpu_time_used);
	printf("\n");
	
	printf("CPU time to invoke sqrt() 1000000000 times in milleseconds:	");

	start = clock();
	i = 0;
	while(i < 100000000){
		sqrt(1000.5);
		i++;
	}
	end = clock();

	cpu_time_used = (((double) (end - start)) / CLOCKS_PER_SEC) * 1000;
	printf("%f", cpu_time_used);
	printf("\n");

	return 0;
}
