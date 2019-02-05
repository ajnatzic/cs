#include <stdio.h>

/**
 * This file contains the methods used to conduct Bubble sort and selection sort
 * */

/**
 * helper method for both bubble sort and selection sort methods
 *
 * code snippets taken from: https://www.geeksforgeeks.org/bubble-sort/
 * and 
 * https://www.geeksforgeeks.org/selection-sort/
 * */
void swap(int *xp, int *yp) 
{ 
    int temp = *xp;	// creates a temporary variable to hold the original first value 
    *xp = *yp; 		// swaps the first value with the second value
    *yp = temp; 	// swaps the second value with the original first value, the temp variable we created earlier
} 

/**
 * This function performs bubble sort on an array. It is simple. It loops through the array twice, if the element it is currently on (first loop) is bigger than the next element
 * (second loop) than these two elements are swapped. This is done for every element until the array is sorted.
 * */
void bubbleSort(int randArr[], int arrSize){
	int i, j; 
	for (i = 0; i < arrSize - 1; i++) // loops through array    
		for (j = 0; j < arrSize - i - 1; j++)  // loops through array again. This is necessary to move or "swap" an element if needed
			if (randArr[j] > randArr[j + 1]) // if the current element is larger than the next element, swap them
				swap(&randArr[j], &randArr[j + 1]); 
}

void selectSort(int randArr[], int arrSize){
	int i, j, min_idx; 
  
	// One by one move boundary of unsorted subarray 
	for (i = 0; i < arrSize - 1; i++) 
	{ 
	// Find the minimum element in unsorted array 
	min_idx = i; 
	for (j = i + 1; j < arrSize; j++) 
		if (randArr[j] < randArr[min_idx]) 
			min_idx = j; 
  
	// Swap the found minimum element with the first element 
	swap(&randArr[min_idx], &randArr[i]); 
    } 
}
