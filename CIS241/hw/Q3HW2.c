#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int main(){
int a[] = {1,2,3,4,5}, i = 3, b, c, d;
int *p = &i, *q = a;
char *format = "\n%d\n%d\n%d\n%d\n%d\n\n";

b = * (q + 2);
c = (q + 2) - q;
d = (long unsigned) (q + 1) - (long unsigned) q;

printf(format, *p + 7, 3 * **&p + 1, b, c, d);


return 0;
}
