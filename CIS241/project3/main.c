#include <time.h>
#include <stdio.h>
#include "operations.h"

/*
 * This function creates a string that welcomes the user with the date and time. Used when displaying the main GUI menu
 */
void putTime(){	
	time_t rawtime;
	struct tm * timeinfo;
	time ( &rawtime );
	timeinfo = localtime ( &rawtime );
	printf ("Today is %d-%d and the time is %d:%d. ", timeinfo->tm_mon + 1, timeinfo->tm_mday, timeinfo->tm_hour, timeinfo->tm_min); 
	if(timeinfo->tm_hour < 12)
		printf("Good morning!");
	else if(timeinfo->tm_hour < 17 && timeinfo->tm_hour >= 12)
		printf("Good afternoon!");
	else if(timeinfo->tm_hour >= 17)
		printf("Good evening.");
	printf("\n");
}

/**
 * This function displays the main GUI of the program, it displays all available commands
 */
void displayMenu()
{
    puts("");
    puts("Welcome to Natzic's grocery store!");
    putTime();
    puts("What would you like to do?");
    puts("============================================================");
    puts("1: Add product to store			2: Purchase product from store");
    puts("3: Check price of a product		4: Show products in store");
    puts("5: Clean up a product from store	6: Find product");
    puts("7: Inventory				8: Done for today");
}
   
// main function to test the list operations
int main(int argc, char *argv[])
{
    product * head = NULL, *l;
    int choice, done = 0;
    double quantity;
    double profit;
    char tmp[20];
    system("clear");
    head = loadData("data", head);
    while (!done) {
        displayMenu();
        puts("What do you want to do?");
        fflush(stdin);
        scanf("%d", &choice);
        fflush(stdin);

        switch (choice)
        {
            case 1:
		fflush(stdin);
		printf("\nInput product to search database for duplicates first: ");
		fflush(stdin);
		scanf("%s", tmp);
		if(head == NULL){
			puts("Previous list does not exist, creating list...");
			head = create();
		}
		else if(strcmp(findItem(head, tmp), tmp) == 0){
			fflush(stdin);
			printf("\nPrevious product found, enter the quantity you would like to add: ");
			fflush(stdin);
			scanf("%lf", &quantity);
			head = addQuantity(head, tmp, quantity);
		}	
		
		else if(head != NULL && strcmp(findItem(head, tmp), tmp) != 0){
			puts("\n\nPrevious list exists, new product does not exist, adding new product... \n(*retype product name*)");
			head = insert(head, makeNode());
		}
                break;
            case 2:
		fflush(stdin);
                printf("Input the product name you would like to buy: ");
                fflush(stdin);
                scanf("%s", tmp);	
                fflush(stdin);
		printf("Input the amount of this product you would like to buy: ");
                fflush(stdin);
		scanf("%lf", &quantity);
		fflush(stdin);
		profit += purchase(head, tmp, quantity);
		if(getQuantity(head, tmp) <= 0)
			rmItem(&head, tmp);
		break;
            case 3:
		fflush(stdin);
                printf("Input the product name you would like to check the price of: ");
                fflush(stdin);
                scanf("%s", tmp);	
                fflush(stdin);
                check_price(head, tmp);
 		break;
            case 4:
		showList(head);
                break;
            case 5:
		fflush(stdin);
                printf("Input the product name you would like to clean up: ");
                fflush(stdin);
                scanf("%s", tmp);	
                fflush(stdin);
                rmItem(&head, tmp);
                break;
            case 6:
		fflush(stdin);
                printf("Input the product name you would like to find: ");
                fflush(stdin);
                scanf("%s", tmp);	
                fflush(stdin);
                findItem(head, tmp);
                break;
            case 7:
		showList(head);
		printf("\nProfit made: %f Dollars\n\n", profit);
                break;
            case 8:
		saveData("data", head);
		puts("All done. Closing up shop!");
		done = 1;
                break;
            default:
                puts("Wrong code. Please try again.");
                break;
        }
    }

    return 0;
}
