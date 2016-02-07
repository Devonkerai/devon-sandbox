#!/usr/bin/env python

#####################################################
# Script to rename all files by consecutive numbers #
#                   By Devon Kerai                  #
#####################################################

# Imports
import os

# Setup variables
# directory = '/Users/devon/Documents/Scripts/renamePics'
# directory = '/Volumes/Storage/Pictures/iPhone Pics/Pics 1'
extensions = [".jpg", ".png", ".mp4", ".mov"]
namePrefix = "IMG_"
count = 0


for name in os.listdir(directory):
	ext = name[-4:].lower()
	if ext in extensions:
		print name
		count += 1
		newName = str(count) + ".jpg"
		if name.startswith(namePrefix):
			print newName
			os.rename(name, newName)




print "\nAll \"%s\" files have been renamed." % namePrefix