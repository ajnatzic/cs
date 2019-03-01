//#include "operations.h"
#include <time.h>
#include <stdio.h>
void putTime(){	
	time_t rawtime;
	struct tm * timeinfo;
	time ( &rawtime );
	timeinfo = localtime ( &rawtime );
	printf ("Today is %d-%d and the time is %d:%d. ", timeinfo->tm_mon, timeinfo->tm_mday, timeinfo->tm_hour, timeinfo->tm_min);
	if(timeinfo->tm_hour < 12)
		printf("Good morning!");
	else if(timeinfo->tm_hour < 5 && timeinfo->tm_hour >= 12)
		printf("Good afternoon!");
	else if(timeinfo->tm_hour >= 5)
		printf("Good evening.");
	printf("\n");
}
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
    //puts("9: Exit				10: Create a list"); //FIXME remove this later if needed
}
   
// main function to test the list operations
int main(int argc, char *argv[])
{
    //person * head = NULL, *p;
    //int choice, tmp, done = 0;
    //system("clear");
    displayMenu();
	/**
    while (!done) {
        displayMenu();
        puts("What do you want to do?");
        fflush(stdin);
        scanf("%d", &choice);
        fflush(stdin);

        switch (choice)
        {
            case 1:
                head = load(head, "data");
                break;
            case 2:
                save(head, "data");
                break;
            case 3:
                head = append(head, makeNode());
                break;
            case 4:
                fflush(stdin);
                printf("Input the person ID you want to insert after: ");
                fflush(stdin);
                scanf("%d", &tmp);
                fflush(stdin);
                insertAfter(head, tmp, makeNode());
                break;
            case 5:
                fflush(stdin);
                printf("Input the person ID you want to delete: ");
                fflush(stdin);
                scanf("%d", &tmp);
                fflush(stdin);
                deleteNode(&head, tmp);
                break;
            case 6:
                destroy(&head);
                break;
            case 7:
                printf("\nNumber of nodes on list: %d\n", count(head));
                break;
            case 8:
                display(head);
                break;
            case 9:
                puts("Thank you. Buy.");
                done = 1;
                break;
            case 10:
                if (head == NULL)
                    head = create();
                else
                    puts("\nCase 10: A list already created.");
                break;
            default:
                puts("Wrong code. Please try again.");
                break;
        }
	************************TODO */
    ///////////////////////////}

    return 0;
}
