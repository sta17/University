------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all he/she needs to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: Assignment
PURPOSE OF PROJECT: To create a translator and to generate name and phrases
VERSION or DATE: 30.11.2013
HOW TO START THIS PROJECT: run Pirateapp
AUTHORS: STA17
USER INSTRUCTIONS:
The Project has auto save and load upon exit and Start.
Upon Start so will menu start, which gives access to its features.

Menu Overview - Readme Line 19.
File information - Readme Line 133.

Menu Overview:

Menu input is case insensitive, inregards to letters.
 1 - add phrace - readme line 50
 2 - add a pirate name - readme line 56
 3 - translate to Pirate - readme line 67
 4 - translate to English - readme line 73
 5 - random phrace generation - readme line 79
 6 - random pirate name generator - readme line 84
 7 - print Pirate Name List - readme line 89
 8 - print Pirate Dictonary - readme line 99
 9 - remove a word - readme line 104
10 - remove a name - readme line 110
 M - print menu - readme line 120
 Q - quit - readme line 125

To access the individual options, input the number or Letter that is before the dash(-)
in a single line by itself and press enter to shift to next line, the line will be read, 
and if it matches any of the above or any other inputs asked in the suboptions in their 
parts it will do as stated. The above list are the options, it also contains which line 
to find more information in this document.

Eksample:

Like Q or q for quit, 
M or m for menu, 
8 to display the dictonary list.
 

Add Functions:

The project has an add function for its 3 internal lists, it will pretty much
accept any input! 

 1 - add phrace:

the dictonary - used to add words to its internal translation list.
Input the english version of word or phrase and then opposite pirate version, 
so it can translate inbetween these 2.

 2 - add a pirate name:

The firstname - used for providing firstnames for the name generator.
After pressing 2, input firstname as your choice when asked, and then 
input the firstname to be added.

The lastname - used for providing lastname for the name generator.
After pressing 2, input lastname as your choice when asked, and then 
input the lastname to be added.


 3 - translate to Pirate
 
 This will accept writing of upto 35 lines and translate it into piratespeak from english.
 It requires a single capital Q to exit the lineinput and to see the translation.
 It used its internal dictonary, which can be enlarged with the add function.
 
 4 - translate to English
 
 This will accept writing of upto 35 lines and translate it into English from piratespeak.
 It requires a single capital Q to exit the lineinput and to see the translation.
 It used its internal dictonary, which can be enlarged with the add function.
 
 5 - random phrace generation
 
 This option will print out a 3 phrase (or word) long sentance in piratespeak based on 
 dictonary.
 
 6 - random pirate name generator
 
 This option generates a piratename for the user, based on the 2 name lists(firstname and 
 lastname)
 
 7 - print Pirate Name List
 
The firstname - used for providing firstnames for the name generator.
After inputing 7, input firstname as your choice when asked, and then 
the list will be generated.

The lastname - used for providing lastname for the name generator.
After inputing 7, input lastname as your choice when asked, and then 
the list will be generated.
 
 8 - print Pirate Dictonary
 
after input, so will the entire dictonary print, in format, english in 
one line, then its piratespeak version in next line.
 
 9 - remove a word
 
 To remove a word, you simply have to write, either the english version or 
 piratespeak version, and if it finds a match, then it will remove it if it
 finds a match.
 
10 - remove a name

The firstname - used for providing firstnames for the name generator.
After inputing 10, input firstname as your choice when asked, and then 
input the name you wish to have removed.

The lastname - used for providing lastname for the name generator.
After inputing 10, input lastname as your choice when asked, and then 
input the name you wish to have removed.

 M - print menu
 
 This will print the menu for, it does not auto print to prevent spaming of the 
 screen and to make it more manageble.
 
 Q - quit
 
 This exits the program, saves all lists into their respective files, before exit.
 
 
 File Information:
 
 The Assignment uses 3 files that you can find in the folder containing the content for 
 this program. They do not need to be in their own folder, as long as they are in the 
 same folder as the program Pirateapp.
 
 The 3 files are:
 pirate.txt
 Firstnames.txt
 Lastnames.txt
 
 The pirate.txt contains your dictonary of words and phrases that the program uses to 
 translate the program.
 
 It has a format like this:
 
 (Total Number of words/phrases in file)
 Pirate version
 English version
 
 This is one word/phrase, and will continue downwards after number, interchangebly with 
 pirate version and english version.
 
 The  Firstnames.txt and Lastnames.txt have the same format and contains the firstname 
 and lastname used for the name generator.
 
 Format:
 (Total Number of words/phrases in file)
 (And the names downwards)
 
 Note regarding the files, the total number at top of the file, tells how many times the 
 program is supposed to read file, thus any exstra lines after this, will not be read.