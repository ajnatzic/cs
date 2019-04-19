#! /bin/bash

while read x
do
	if [ -f $x ]; then
		echo "A regular file"
	fi
	if [ -d $x ]; then
		echo "A directory"
	fi
	if [ $x == 'q' ]; then
		exit
	fi
done
