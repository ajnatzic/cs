#include "operations.h"

/*
 * This function must be used when created a new list with no other nodes. Just using makeNode() will not work.
 */
product * create(){
	return makeNode();
}

/*
 * This function creates a new node from scratch. It accepts user input one at a time, for each variable, and then returns the new list.
 */
product * makeNode(product * l){
    int nodeExists = 0;
    product * searchList = l;
    product * tmp = (product *) malloc(sizeof(product));
    if (tmp == NULL)
    {
        puts("InMakeNode: Memory allocation failed");
        return NULL;
    }
    fflush(stdin);
    printf("Please input product name: ");
    scanf("%s", tmp->name);
    fflush(stdin);
    printf("Please input quantity value: ");
    scanf("%f", &(tmp->quantity_value));
    fflush(stdin);
    printf("Please input quantity unit: ");
    scanf("%s", tmp->quantity_unit);
    fflush(stdin);
    printf("Please input price value: ");
    scanf("%f", &(tmp->price_value));
    fflush(stdin);
    printf("Please input price unit: ");
    scanf("%s", tmp->price_unit);
    fflush(stdin);

    tmp->next = NULL;
    return tmp;
}

/*
 * This function inserts a product into the list. It is essentially the same as appending something to a list
 */
product * insert(product *l, product *node){
	// create temporary cursor
        product * tmp = l;

	// advance cursor to end of list where it is null
	while(tmp->next != NULL)
		tmp = tmp->next;

	// initialize new person 
	product * newProduct = node;

	// assign it to the next node in the linked list
	tmp->next = newProduct;

	// return the updated list
	return l;
	
	
}
/*
 * This function builds a node from raw text. This is useful when using the loadData() function.
 */
product * buildNode(char name[], float quantity_value, char quantity_unit[], float price_value, char price_unit[])
{
    product * p = (product *) malloc(sizeof(product));
    if(p == NULL) {
        puts("InBuildNode: Memory Allocation Failed!");
        return NULL;
    }
    strcpy(p->name, name);
    p->quantity_value = quantity_value;
    strcpy(p->quantity_unit, quantity_unit);
    p->price_value = price_value;
    strcpy(p->price_unit, price_unit);

    return p;
}
/*
 * This function removes a node from a list and frees the memory used by that node. 
 */
void rmItem(product **l, char name[]){
 product * current = *l;
    product * previous = current;
    while(current != NULL) {
        if (strcmp(current->name, name) == 0) {
            if(previous == current)  // the first node
                *l = (current->next);
            else // not the first one
                previous->next = current->next;
            
            free (current);
            return;
        }
        previous = current;
        current = current->next;
    }
}
/*
 * This function prints out all of the current items on the list. 
 */
void showList(product * l){
	//Display all the node on the list l.
	//for each node, diplay first name, last name and id
	product * tmp = l;

	if(tmp == NULL)
		printf("\nNo list contents found\n");
	else{
		printf("\nHere is the contents of the current list:\n");
		while(1){
			if(tmp == NULL)
				break;
			else{
				printf("%s %f %s %f %s\n", 
                			tmp->name, tmp->quantity_value, tmp->quantity_unit, tmp->price_value, tmp->price_unit);
				tmp = tmp->next;
			}
		}
	}
}
/*
 * This function simply loads all the data from a given filename. It builds the list given the raw text from the file, and then returns
 * a new list of type (product) that can be used in the program.
 */
product * loadData(char inf[], product *l){
    
    int rt;	
    char name[20], quantity_unit[20], price_unit[20];
    float quantity_value, price_value;
    product * head = l;
    FILE * fin = fopen(inf, "r");
    if(fin == NULL) {
        printf("InLoad: File open failed (%s)\n", inf);
        return NULL;
    }

    while (1) {
        rt = fscanf(fin, "%s %f %s %f %s\n", name, &quantity_value, quantity_unit, &price_value, price_unit);
        if (rt < 5)
            break;
        if (head == NULL)
            head = buildNode(name, quantity_value, quantity_unit, price_value, price_unit);
        else
            insert(head, buildNode(name, quantity_value, quantity_unit, price_value, price_unit));
    }
    fclose(fin);
    return head;
}
/*
 * This fucntion simply saves the info of all the products in the list.
 */
void saveData(char outf[], product *l){
    FILE * fout = fopen(outf, "w");
    if(fout == NULL) {
        printf("InSave: File open failed (%s)\n", outf);
        return;
    }
    product * current = l;
    while (current != NULL) {
        fprintf(fout, "%s %f %s %f %s\n", 
                current->name, current->quantity_value, current->quantity_unit, current->price_value, current->price_unit);
        current = current->next;
    }
    fclose(fout);
}
/*
 * This function sells a product. It first instantiates variables that are useful for a print statement explaining the profit made later. 
 * Then, it sorts through the temporary list made, comparing each node (in tmp list) to name[] (this is passed to us). Finally, 
 * the function creates corresponding print statements depending on how much the consumer bought, and returns the profit made from
 * the purchase as a float value.
 */
float purchase(product *l, char name[], float q){
	
	float profit;
	product * tmp = l;
	char * profitUnit = tmp->price_unit;
	if(tmp == NULL)
		printf("\nNo list contents found\n");
	else{
		while(1){
			if(tmp == NULL){
				printf("\nProduct not found! We'll need to get some more of that!\n");
				break;
			}
			if(strcmp(tmp->name, name) == 0){
				profit = q * tmp->price_value;
				printf("\nBefore Sale... %s %f %s %f %s\n", 
                			tmp->name, tmp->quantity_value, tmp->quantity_unit, tmp->price_value, tmp->price_unit);
				
				tmp->quantity_value = tmp->quantity_value - q;	// Updating the quantity of the product after sale
				if(tmp->quantity_value < 1)	// If there is less than 1 of the product, tell shopkeeper we are all out
					printf("\nAfter Sale... All out! Will need to get some more!\n");
				else	// otherwise, print out the new details of the product after the sale
					printf("\nAfter Sale... %s %f %s %f %s\n", 
                			tmp->name, tmp->quantity_value, tmp->quantity_unit, tmp->price_value, tmp->price_unit);
				printf("\nWe made %f %s from that sale!\n", profit, profitUnit);
				return profit;	// return the profit made from the purchase
			}
			tmp = tmp->next;
		}
	}
	return 0.0;	// if the program reaches here, we can assume no such product exists and that we made 0 profit from this "purchase"	
}
product * addQuantity(product * l, char name[], float q){
	product * tmp = l;
	
	while(1){
			if(tmp == NULL){
				break;
			}
			if(strcmp(tmp->name, name) == 0){
				tmp->quantity_value += q;
				return l;
			}
			tmp = tmp->next;
		}

}
/**
 * This function returns the quantity of a product. If it cannot find the quantity it will return 0.0 and assume that product does not exist 
 */
float getQuantity(product *l, char name[]){
	product * tmp = l;
	
	if(tmp == NULL)
		printf("\nNo list contents found\n");
	else{
		while(1){
			if(tmp == NULL){
				break;
			}
			if(strcmp(tmp->name, name) == 0){
				return tmp->quantity_value;
			}
			tmp = tmp->next;
		}
	printf("\nCouldn't find the quantity of that product! Maybe it doesn't exist?\n");
	return 0.0;
	}
}
/*
 *This function takes a list and a product name as input and find it. Once it is found the product name and price are printed. If it
 is not found, then a message will print saying it cannot be found.
 */
void check_price(product *l, char p[]){
	product * tmp = l;

	if(tmp == NULL)
		printf("\nNo list contents found\n");
	else{
		while(1){
			if(tmp == NULL){
				printf("\nProduct not found! We'll need to get some more of that!\n");
				break;
			}
			if(strcmp(tmp->name, p) == 0){
				printf("\nProduct: %s Price: %f %s\n", 
                			tmp->name, tmp->price_value, tmp->price_unit);
				break;
			}
			tmp = tmp->next;
		}
	}

}
/*
 * This function takes a list and a product name as input and finds it. It uses strcmp() to compare every item on the list, 
 * if it is found it will be printed, otherwise a message will be printed saying it cannot be found.
 */
char * findItem(product *l, char p[]){
	product * tmp = l;

	if(tmp == NULL)
		printf("\nNo list contents found\n");
	else{
		while(1){
			if(tmp == NULL){
				printf("\nProduct not found!\n");
				return "";
			}
			if(strcmp(tmp->name, p) == 0){
				printf("\n%s %f %s %f %s\n", 
                			tmp->name, tmp->quantity_value, tmp->quantity_unit, tmp->price_value, tmp->price_unit);
				return tmp->name;
			}
			tmp = tmp->next;
		}
	}
	return "";

}

