#!/bin/bash

echo "Usage: 
	Type 'f' and enter to count number of files in this directory
	Type 'd' and enter to count number of directories in this directory
	Type 'q' and enter to exit program"

while read x
do
		
	# Echo commands inputted 	
	if [ $1 $x ]; then
		echo $x
	fi
	
	# The command "d" will display the number of files in the current directory
	if [ "$x" = "f" ]; then
	numFiles=$( ls -p | grep -v / | wc -l)
	echo "There are $numFiles files in this current directory."
	fi

	# The command "d" will display the number of directories in the current directory
	if [ "$x" = "d" ]; then
	numDir=$(ls -ld -- */ | wc -l)
	echo "There are $numDir directories in this current directory."
	fi
	
	# The command "q" will safely exit the program
	if [ "$x" = "q" ]; then
		exit
	fi
done
