#define N 20
typefef struct{
	char name[N];
	float quantity_value;
	char quantity_unit[N];
	float price_value;
	char price_unit[N];
	struct product *next;
}product;

// insert a node to the list
int insert(product **l, product node);
// remove a node from list
void rmItem(product *l, product *node);
// show list
void showList(product * l);
// load data from file inf
int loadData(char inf[], product **l);
// save data to file outf
int saveData(char outf[], product *l);
// sell product product of quantity q
float purchase(product *l, char product[], float q);
// check out price of product p from list l
void check_price(product *l, char p[]);

// find a product p from list l
void findItem(product *l, char p[]);
// the job starts here, start with loading data from
// the file data, and perform the functions by calling
// related functions. Ends at saving data to the file data.
int doIt(char data[]);
