#!/usr/bin/env python

###################################################
# Script to count number of movies in a directory #
#                 By Devon Kerai                  #
###################################################

# Imports
import os

# Setup variables
directory = '/Users/devon/Documents/Scripts/movieCounting'
# directory = '/Volumes/My Passport/Movies'
extensions = [".mkv", ".avi", ".mp4", ".wmv", ".mpg"]
count = 0

with open("movielist.txt", "w") as movieListFile:

	# For movies not in a subfolder
	for name in os.listdir(directory):
		movieExt = name[-4:]
		if movieExt in extensions:
			count += 1
			movieListFile.write(name + "\n")

		# For movies in a subfolder
		if os.path.isdir(name):
			newDir = os.path.join(directory, name)
			for newName in os.listdir(newDir):
				movieExt = newName[-4:]
				if movieExt in extensions:
					count += 1
					movieListFile.write(newName + "\n")
	
	movieListFile.write("\nTotal number of movies: %s" % count)


print "Total number of movies: %s" % count