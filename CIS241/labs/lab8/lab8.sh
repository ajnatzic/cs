#!/bin/bash

numElem=0

echo "Please input integer numbers. Ctrl-D to end input."
numArr=()
while read x
do
	numArr[numElem]=$x
	numElem=$((numElem + 1))
done

echo "The array of numbers is: ${numArr[*]}"

if [ $numElem -gt 7 ] || [ $numElem -lt 1 ]; then
	echo "Input must be between 1 and 7 numbers. Input is $numElem numbers."
	exit
else
	declare -i tot=0
	declare -i max=0
	for var in ${numArr[*]}
	do
		((tot+=$var))
		if [ $var -gt $max ]; then 
			max=$var
		fi
	done
	echo "The sum is: $tot"
	tot=$((tot/numElem))
	echo "The average is: $tot"
	echo "The maximum is: $max"
fi



