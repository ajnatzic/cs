cipher: cipher.o
	gcc cipher.o -o cipher
cipher.o: cipher.c

test: test1 test2
test1:
	./cipher FORK 5 data.txt data.out
test2:
	./cipher -FORK 5 data.out data.bak
clean:
	rm -f *.o cipher *.out *.bak
compare:
	diff -s data.txt data.bak
view:
	vim ./data.txt
	vim ./data.bak
	vim ./data.out
