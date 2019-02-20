p2: functions.o project2.o
	gcc -o p2 functions.o project2.o
functions.o: functions.c

project2.o: project2.c

test: encrypt decrypt compare

encrypt:
	./p2 e "GOODMORNING" datap cipher
decrypt:
	./p2 d "GOODMORNING" cipher rdata
compare:
	diff -s datap rdata
clean:
	rm -f *.o p2 *.out *.bak

view:
	vim ./datap
	vim ./cipher
	vim ./rdata
