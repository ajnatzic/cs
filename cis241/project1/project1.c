#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include "sort.h"
#define ARR_MAX 1000000		// This is the array max that was defined in the project specifications

/**
 * This contains the main method for the project. It includes a helper method to print arrays in C.
 * As well as the main function which accepts input of an array size, and the search times for the middle element
 * using bubble and selection sort.
 * */


/**
 * Helper method used to print an entire array to the command line in nice 5 column format
 * */
void printArray(int arr[], int arrSize){
	int i = 0;
	for(i = 0; i < arrSize; i++){
		if(i % 5 == 0 && i != 0)
			printf("\n");
		printf("%d	", arr[i]);
	}
	printf("\n");
}

/**
 * Main function. Invokes search and bubble search functions from "time.h" to find middle elements and reports the system time it took 
 * to complete each search on a given array size.
 * */
int main(){
	/** the user inputted array size */
	int arrSize;

	/** these two variables hold the system start and end times for timing the sort methods*/
	clock_t start, end;

	/** this double holds the total system time used in milleseconds */
	double cpuTimeUsed;

	system("clear");
	printf("Please input the size of the array: ");
	scanf("%d", &arrSize);
	
	// Error checking. Makes sure that array size is within acceptable parameters.
	if((arrSize < 1) || arrSize > ARR_MAX){
		printf("Inputted array size is trash. Please enter something between 1 and 1,000,000. Exiting Program\n");
		exit(1);
	}
	// If it is an acceptable size, then initialize a new array and input random integers.
	else {
	int randArray[arrSize];
	int i;
	for(i = 0; i < arrSize; i++){
		randArray[i] = rand();	
	}
	
	/***********************************************************************************************************************
	//This part sorts the array using bubble sort. If the array has less than 50 items then all the elements will be printed
	************************************************************************************************************************/
	
	// This block sorts the random array. This is the time for average case scenario
	double avgTime = 0.0;
	start = clock();
	bubbleSort(randArray, arrSize);
	end = clock();
	cpuTimeUsed = (((double) (end - start)) / CLOCKS_PER_SEC) * 1000;	
	avgTime = cpuTimeUsed;

	// If the array is less than or equal to 50, print the sorted array in ascending order
	if(arrSize <= 50){
		printf("Here is the sorted array in ascending order: \n");
		printArray(randArray, arrSize);		
	}
	
	// This block sorts the already sorted array. This is the time for best case scenario
	double bestTime = 0.0;
	start = clock();
	bubbleSort(randArray, arrSize);
	end = clock();
	cpuTimeUsed = (((double) (end - start)) / CLOCKS_PER_SEC) * 1000;
	bestTime = cpuTimeUsed;

	// This block is just formatting the print lines to show results for bubble sort
	printf("Array_Size  Algorithm    Average    Best\n");
	printf("    %d      Bubble        %.3f      %.3f\n", arrSize, avgTime, bestTime);

	// Now, the same process is repeated except this time with selection sort
	// We will reinitialize the array so that we have a fresh set of random numbers
	for(i = 0; i < arrSize; i++){
		randArray[i] = rand();	
	}

	/**************************************************************************************************************************
	//This part sorts the array using selection sort. If the array has less than 50 items then all the elements will be printed
	***************************************************************************************************************************/
	
	// This block sorts the random array. This is the time for average case scenario
	avgTime = 0.0;
	start = clock();
	selectSort(randArray, arrSize);
	end = clock();
	cpuTimeUsed = (((double) (end - start)) / CLOCKS_PER_SEC) * 1000;	
	avgTime = cpuTimeUsed;

	// If the array is less than or equal to 50, print the sorted array in ascending order
	if(arrSize <= 50){
		printf("Here is the sorted array in ascending order: \n");
		printArray(randArray, arrSize);		
	}
	
	// This block sorts the already sorted array. This is the time for best case scenario
	 bestTime = 0.0;
	start = clock();
	selectSort(randArray, arrSize);
	end = clock();
	cpuTimeUsed = (((double) (end - start)) / CLOCKS_PER_SEC) * 1000;
	bestTime = cpuTimeUsed;

	// This block is just formatting the print lines to show results for selection sort
	printf("    %d      Selection        %.3f      %.3f\n", arrSize, avgTime, bestTime);

	}
	return 0;
}
