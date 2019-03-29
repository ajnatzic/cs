#!/bin/bash 

####################################
#
# Linux backup bash script
#
# Author: AJ Natzic
#
####################################


# What to backup. 
backup_files=""

# Where to backup to.
dest="/$HOME/.backup"

# These are option switches. They are set to true if the user inputs these options
listBool=false
numBool=false
helpBool=false

# This is the help menu
helpMenu="The usage of this command is: backup [options] source-files
This backs up inputted source-files to ~/.backup
-l: List files in ~/.backup
-n: Display number of files in ~/.backup and the storage they consume
--help: Display this menu"

# If no options or filenames inputted display a message and exit
if [ -z "$1" ]; then
	echo "The usage of this command is: backup [options] source-files"
	exit
fi

# Check input line for options and files
while [ -n "$1" ]; do 
	case "$1" in
		-l) listBool=true;;
		-n) numBool=true;;
		-ln) listBool=true numBool=true;;
		-nl) numBool=true listBool=true;;
		--help) helpBool=true;;
		-*) echo "	ERROR: Option [$1] not recognized!";;

		
	*) if [ -e $1 ]; then
		backup_files+="$1 "
	else
		echo "	ERROR: Directory/File [$1] does not exist."
	fi
	esac
	shift
done

# If backup directory does not exist, create it
if [ ! -e "$dest" ]; then
	echo "Creating backup directory."
	mkdir $dest
fi

# Print start status message
if [ ! -z "$backup_files" ]; then
echo "Backing up $backup_files to $dest..."
echo

#Turn backup_files string into an array we can sort through
backup_files_arr=($backup_files)

# Sort through backup files and back them up by copying them to $dest
for i in ${backup_files_arr[@]}; do
	echo "Backing up: $i"
	if [[ -d $i ]]; then
		yes | cp -rf /$i/ /$dest/
	fi	
	if [[ -f $i ]]; then
		cp /$i /$dest/
	fi
done

echo "Backup Complete"
else
	echo "No files to backup."
fi

# Long listing of files in $dest to check file sizes.
if [ "$listBool" = true ]; then
	echo
	echo "=========================================================="
	echo "Files/Directories in $dest:"
	ls -lh $dest
	echo "=========================================================="
	echo
fi

# States how many files are in $dest and how much space they use
if [ "$numBool" = true ]; then
	echo
	echo "=========================================================="
	numFiles=$(ls -1 | wc -l)
	echo "Number of files in $dest: $numFiles"
	
	du -sc $dest
	echo "=========================================================="
	echo
fi

# If the user inputted help option, display the help menu lastly.
if [ "$helpBool" = true ]; then
	echo
	echo "========================================================="
	echo "$helpMenu"
	echo "========================================================="
	echo
fi
