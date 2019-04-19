#! /bin/bash

if [ $1 == "" ]; then
	echo "Please input a file name after the script name."
	exit
else
	if [ -f $1 ]; then
		chmod ug+x $1
		echo "File permissions changed successfully."
	else 
		echo "File is not a valid file. No file permissions were changed."
	fi
fi
