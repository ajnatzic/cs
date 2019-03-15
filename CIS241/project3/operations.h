#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define N 20
typedef struct product{
	char name[N];
	float quantity_value;
	char quantity_unit[N];
	float price_value;
	char price_unit[N];
	struct product *next;
}product;


/*
 * This function must be used when created a new list with no other nodes. Just using makeNode() will not work.
 */
product * create();

/*
 * This function creates a new node from scratch. It accepts user input one at a time, for each variable, and then returns the new list.
 */
product * makeNode();

/*
 * This function inserts a product into the list. It is essentially the same as appending something to a list
 */
product * insert(product *l, product *node);

/*
 * This function builds a node from raw text. This is useful when using the loadData() function.
 */
product * buildNode(char name[], float quantity_value, char quantity_unit[], float price_value, char price_unit[]);

/*
 * This function removes a node from a list and frees the memory used by that node. 
 */
void rmItem(product **l, char name[]);

/*
 * This function prints out all of the current items on the list. 
 */
void showList(product * l);

/*
 * This function simply loads all the data from a given filename. It builds the list given the raw text from the file, and then returns
 * a new list of type (product) that can be used in the program.
 */
product *  loadData(char inf[], product *l);

/*
 * This fucntion simply saves the info of all the products in the list.
 */
void saveData(char outf[], product *l);

/*
 * This function sells a product. It first instantiates variables that are useful for a print statement explaining the profit made later. 
 * Then, it sorts through the temporary list made, comparing each node (in tmp list) to name[] (this is passed to us). Finally, 
 * the function creates corresponding print statements depending on how much the consumer bought, and returns the profit made from
 * the purchase as a float value.
 */
float purchase(product *l, char product[], float q);
product * addQuantity(product * l, char name[], float q);
/**
 * This function returns the quantity of a product. If it cannot find the quantity it will return 0.0 and assume that product does not exist 
 */
float getQuantity(product *l, char name[]);

/*
 *This function takes a list and a product name as input and find it. Once it is found the product name and price are printed. If it
 is not found, then a message will print saying it cannot be found.
 */
void check_price(product *l, char p[]);

/*
 * This function takes a list and a product name as input and finds it. It uses strcmp() to compare every item on the list, 
 * if it is found it will be printed, otherwise a message will be printed saying it cannot be found.
 */
char * findItem(product *l, char p[]);

