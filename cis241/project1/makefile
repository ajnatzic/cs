project1: sort.h sort.o project1.o 
	gcc -o project1 sort.o project1.o

sort.o: sort.h sort.c 
	gcc -c sort.c

project1.o: sort.h sort.c project1.c
	gcc -c project1.c

run: 
	./project1

clean: 
	rm -f sort.o project1.o project1
