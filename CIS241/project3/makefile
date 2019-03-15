project3: main.o operations.o 
	gcc -o project3 main.o operations.o

main.o: operations.h operations.c main.c
	gcc -c main.c

operations.o: operations.h operations.c
	gcc -c operations.c

all:
	make project3
	./project3
run:
	./project3

clean:
	rm -f *.o project3

