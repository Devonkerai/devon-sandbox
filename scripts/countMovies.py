#!/usr/bin/env python

###################################################
# Script to count number of movies in a directory #
#                 By Devon Kerai                  #
###################################################

# Imports
import os
from datetime import date

# Setup variables
extensions = [".mkv", ".avi", ".mp4", ".wmv", ".mpg"]
directory = '/Volumes/My Passport/Movies/'
filename = "Movielist-%s.txt" % date.today()
count = 0

# Functions
def writeMovieNamesIntoFile(name):
	global count
	movieExt = name[-4:]
	if movieExt in extensions:
		count += 1
		movieListFile.write(name + "\n")

# Main
# Write all movie names in file.
with open(directory+filename, "w") as movieListFile:

	# For movies not in a subfolder
	for name in os.listdir(directory):
		writeMovieNamesIntoFile(name)

		# For movies in a subfolder
		if os.path.isdir(name):
			newDir = os.path.join(directory, name)
			for newName in os.listdir(newDir):
				writeMovieNamesIntoFile(newName)

	movieListFile.write("\nTotal number of movies: %s" % count)


print "Total number of movies: %s" % count
print "Movie list file: %s" % directory+filename
