#include <stdio.h>
#include <math.h>
/**
 * This is the function that finds the fourth root of the function
 * */
double root4(int k){
	double root4;
       	root4 = sqrt(sqrt((double)k));	// finds the fourth root. the input is cast as a double here	
	return root4;
}

/**
 * Main method that creates the list of inputs and invokes root3 to get the outputs. It is printed in table format
 * */
int main(void) {
	int k[20];	// create integer array of inputs
	int i;
	for(i = 0; i < 20; i++){	// initialize the array with numbers 1 through 20 to the fourth power
		k[i] = pow((i + 1), 4);
	}

	printf("Numbers\t4th Root\n");	// labels for table

	for(i = 0; i < 20; i++){	
		printf("%d\t", k[i]);	// prints input
		printf("%g\n", root4(k[i]));	// prints output, using root4 function and "%g" format to avoid trailing zeroes	
	}
	return 0;
}
