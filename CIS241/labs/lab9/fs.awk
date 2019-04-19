#!/bin/gawk -f
BEGIN	{
	dirs = 0	# count for number of directory
	files = 0
	links = 0
	total = 0
	storage = 0
	}
$1 ~ /^d[rwxts-]{9}/	{dirs += 1}	# directory
$1 ~ /^-[rwxts-]{9}/	{files += 1}	# files

$10 ~ /->/		{links += 1}
storage += $5
total = dirs + files + links
END	{print ""
	 print "Here is the summary of files under your home directory:"
	 print ""
	 print "directories   files     links      total       storage(Bytes)"
	 print "============================================================="
	 printf "%-13d %-9d %-11d %-10d %-10d\n\n", dirs, files, links, total, storage # more coming
	} 
