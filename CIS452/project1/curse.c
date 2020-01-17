#include <stdio.h>
#include <curses.h>
#include <term.h>
#include <unistd.h>

/* Created by AJ Natzic 
 * CIS 452
 * */
int main(){	

	// Initialize the terminal info	
	setupterm((char *)0, 1, (int *)0);
	
	// Get total rows and columns in the terminal
	int maxRows = tigetnum("lines");
	int maxColumns = tigetnum("cols");

	printf("Supported Rows: %d\nSupported Columns: %d\n", maxRows, maxColumns);
	sleep(5);
	putp(tparm(tigetstr("clear")));	// Change terminal paramaters to clear the screen

	// These integers will be used to store which row and column the user wants to home their cursor to
	int numRows = 0;
	int numColumns = 0;

	// While number of rows is not negative, keep accepting commands	
	while(numRows >= 0){
		printf("Please enter number of rows and columns: ");
		scanf("%d,%d", &numRows, &numColumns);
		// Error checking for input
		if(numRows > maxRows || numColumns > maxColumns){
			printf("ERROR: Please enter a row between 0 and %d and a column between 0 and %d\n", maxRows, maxColumns);
			continue;
		}
		// If no errors, move cursor
		else{
		printf("\033[%d;%dH", numRows, numColumns);
		}
	}	
	return 0;
}
