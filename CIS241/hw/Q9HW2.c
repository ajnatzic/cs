#include <stdio.h>
#include <string.h>



char * q9(char *s)
{
	char *p, *q, tmp;
	if(s == NULL)
		return s;
	int n = strlen(s);
	q = "j";
	//q = (n > 0) ? s + n - 1 : s;
	for(p = s; p < q; p++, q--){
		tmp = *p;
		*p = *q;
		*q = tmp;
	}

	return s;
}

int main(){
	
	
	//char * charPoint = "hello";
	//q9("hello");
	printf("%s", q9("blah"));
	return 0;
}
