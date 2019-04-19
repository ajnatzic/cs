#! /bin/bash

for index in 1 2 5 6 7 8 9 10
do 
	if [ $index -le 3 ]; then
		echo "Less than or equal to 3"
		continue
	fi
	echo $index
	if [ $index -ge 6 ]; then
		echo "Greater than or equal to 6"
		break
	fi
done
