/* main function for lab1 */
#include "lab1.h"

int main()
{
	int i;
	float diff, epslon = 0.0001;
	int n = 0;

	printf("\nRAND_MAX = %d\n", RAND_MAX);

	random_char(200000);
	i = 100;
	printf("	Runs	Difference\n");
	while(1){
		diff = (float) coin_toss(i) / (i / 2);
		printf("%11d %10.7f\n", i, diff);

		if(diff < epslon)
			break;

		i += i * 2;
	}

	return 0;
}
