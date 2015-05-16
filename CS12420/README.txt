 PROJECT TITLE: CS124 
 Assignment
 PURPOSE OF PROJECT: To create a hangman game
 VERSION or DATE: 24.04.2014
 HOW TO START THIS PROJECT: run PirateApp
 AUTHORS: STA17
 USER INSTRUCTIONS:
 The Project has auto save and load upon exit and Start.
 Upon Start so will menu start, which gives access to its features.

 Game Instructions - Readme Line 16.
 Command Line versions Overview - Readme Line 27.
 GUI (version) Menu Overview - Readme Line 37.
 TXT version Menu Overview - Readme Line 112.
 File information - Readme Line 196.
 To Run from Command Line -Readme Line 224.

 Game Instructions:

 You are to input letters and see if it matches the hidden word, you will be
 provided its "censored version" for hints as to what it is. 
 you can also input words if you wish, only remember. 1 wrong letter 
 
 and you lose 1 guess, 1 wrong word and you lose 5 guesses, you 
 start with 10 guesses


 Command Line versions Overview:

 While the game will normally start up to the choice window, which will ask for which 
 way you want to play the game. There have how ever also been programmed in 2 command line 
 arguments which can be used to directly and only start up 1 specific version of the game.
 These arguments are: -TXT and -GUI, thus -TXT PirateApp will start the text version of the 
 game, while -GUI PirateApp will start gui version. For more details about the two versions, 
 see below.


 GUI (version) Menu Overview:
 
 
 Button Menu overview:
 
 Start Text - readme line 59.
 Start GUI - readme line 64.
 Add Phrase - readme line 71.
 Remove Phrase - readme line 77.
 Change List - readme line 83.
 Try Letter - readme line 93.
 Try Phrase - readme line 98.
 New Phrase - readme line 103.
 Instructions - readme line 107.
 
 
 Choice Window: 


 This is the basic window opened during normal start, you will be presented with a choice 
 in regards to which version you wish to play in.

 Pressed Start Text:
 The text Version will start up the game, with only text and typed input, and will be running
 in a console window, depending a little on different OS systems. See TXT version Menu Overview
 for more details.
 
 Pressed Start GUI:
 This will start up a version that includes graphics both in the forms of windows and buttons, 
 but also animations. Input here will take form off input fields. 
 
 
 List Handlings:
 
 Add Phrase:
 When pressed a small pop up window with an input field will appear. The input will be accepted 
 as long as it is not already exist in the list of phrases and you are not trying to enter nothing, 
 in both of these cases you will receive a notification about this and anything else thought popup 
 window.
 
 Remove Phrase:
 When pressed a small pop up window with an input field will appear. The input will be accepted 
 as long as the word trying to be removed already exist in list and you are not trying to enter 
 nothing, in both cases you will receive a notification about this and anything thought popup 
 window.
 
 Change List:
 When pressed a small pop up window with an input field will appear. The input will be accepted 
 as long as the word trying to change to a file that does exist and you are not trying to enter 
 nothing. Then Gamem will first save the list if it can save it, before trying to load a file. 
 The file cannot have duplicates in it or empty lineew.
 
 
 Game Buttons:

 ry Letter:
 When pressed a small pop up window with an input field will appear. The input will be accepted 
 as long as it is not empty input and it is only 1 letter long. If you are correct then, the 
 screen and the details will update to reflect this, if you are wrong then it will update 
 according to that.
 
 Try Phrase:
 When pressed a small pop up window with an input field will appear. The input will be accepted 
 as long as it is not empty input. If you are correct then, the screen and the details will 
 update to reflect this, if you are wrong then it will update according to that.
 
 New Phrase:
 When pressed this will reset most game statistics like how many guesses left, and give a new
 phase to guess, its a new Game. Animation will also be Reseted.
 
 Instructions:
 When pressed it will give a window which will tell you the basics of what you are supposed to 
 in the game. For more Instructions look under Instructions above.


 Txt version Menu Overview:

 Note: Text version will require some form of output display for System.out lines, which can be
 something like either a Operation System(OS) command line or an Eclipse software console window.
 
 The Command lines for the 3 most common Operation Systems are:
 Windows: CMD.exe
 Linux(Unix): Bash
 Mac(intosh): simply known as Terminal
 
 Please Look elsewhere for run instructions.

 Menu input is case insensitive, inregards to letters.
 L - Try a letter - readme line 141.
 W - try a word - readme line 145.
 N - Starts a new game - readme line 149.
 1 - Print Pirate wordlist - readme line 153.
 2 - Print instructions - readme line 158.
 3 - add phrase - readme line 162.
 4 - Remove a phrase - readme line 166.
 5 - Change the list used to pick phrases another one - readme line 171.
 M - print menu - readme line 175.
 Q - quit - readme line 180.

 To access the individual options, input the number or Letter that is before the dash(-)
 in a single line by itself and press enter to shift to next line, the line will be read, 
 and if it matches any of the above or any other inputs asked in the suboptions in their 
 parts it will do as stated. The above list are the options, it also contains which line 
 to find more information in this document.

 Example:

 Like Q or q for quit, 
 M or m for menu, 
 1 to display the wordlist.


 Individual Options details:
 
 L - Try a letter

 After input of L, input the letter you wish to try, then press enter to input.

 W - try a word

 After input of W, input the phrase/word you wish to try, then press enter to input.

 N - Starts a new game

 After input of N, so will everything reset and a new hidden phrase will be displayed.

 1 - Print Pirate wordlist

 After input of 1, so will the entire wordlist print, in format, the size first, then 
 the individual entries below that in alphabetic order.

 2 - Print instructions

 This will short and summarily print the basic game instructions for this game

 3 - add phrase

 the wordlist - used to add words to its internal list, which words are picked from.

 4 - Remove a phrase

 To remove a word or phrase, you simply have to write it, and if it finds a match, 
 then it will remove it if it is a match.

 5 - Change the list used to pick phrases another one

 This option is for changing the txt file used to pick words or phrases from, 

 M - print menu

 This will print the menu for, it does not auto print to prevent spam of the 
 screen and to make it more manageble.

 Q - quit

 This exits the program, saves all lists into their respective files, before exit.
 
 
 File Information:
 
 The Assignment uses 1 file that you can find in the folder containing the content for 
 this program. They do not need to be in their own folder, as long as they are in the 
 same folder as the program PirateApp.
 
 The 1 file is:
 piratewords.txt
 
 The piratewords.txt contains your list of words and phrases that the program uses to 
 as basis to pick a word for hangman from.
 
 It has a format like this:
 
 (Total Number of words/phrases in file)
 word/phrase here
 another word/phrase here if more than 1, etc
 
 This is one word/phrase, and will continue downwards after number, reading lines depending on top number.

 Note, the loader will consider empty lines, duplicate lines to be error lines, and thus loading will fail.

 Note regarding the files, the total number at top of the file, tells how many times the 
 program is supposed to read file, thus any extra lines after this, will not be read.
 
 Note how ever that JUnitLists folder are for files used in with the JUnit and are generally not intended for gameplay use.
 
 
 To Run from Command Line:
 
 This game can be started from command line and has several unique abilities there.
 These unique abilities are the ability to start the game in only 1 version, without the ability to choose a 
 version at the start. What you have to type to get this in command line is below:
 
 To start, run: 
 java -jar Assigment Sta17.jar
 
 To start txt version only, run: 
 java -jar Assigment Sta17.jar -TXT
 
 To start gui version only, run:
 java -jar Assigment Sta17.jar -GUI