% ##############################
% # BY: STEVEN AANETSEN(STA17) #
% ##############################

% This is a University Assignment, where it was demanded i make a Prolog 
% program that can count the number of instances of the words of first 
% and second in a file, thus a rule have been provided called main, 
% which has this hardcoded in.

% ##############################
% #         Sources:           #
% ##############################
%http://www.learnprolognow.org/lpnpage.php?pagetype=html&pageid=lpn-htmlse54
%http://stackoverflow.com/questions/9088062/count-the-number-of-occurrences-of-a-number-in-a-list

% ##############################
% # General code section/Rules #
% ##############################

% main - a simple version that does all that is required of the assignment
%        in on line with a hard coded file in it.
%
main :-
    filehandling(LinesL,'myFile2.txt'), %calls the list maker
	
	write('Here is the list from which the words where counted from: '), nl, nl,
	write(LinesL), nl, nl,
	% prints out the words in the list so that it can be seen what words passed the
	% illegal character remover and what the final output is.
	
	wordcounter('first','myFile2.txt'),
	wordcounter('second','myFile2.txt').


% main(Filename3) - This is a second version, for easier handling of testing, and 
%                   free selection of files
%
main(Filename3):-
    filehandling(LinesL,Filename3), %calls the list maker
	
	write('Here is the list from which the words where counted from: '), nl, nl,
	write(LinesL), nl, nl,
	% prints out the words in the list so that it can be seen what words passed the
	% illegal character remover and what the final output is.
	
	wordcounter('first',Filename3),
	wordcounter('second',Filename3).

	
% wordcounter(Word,Filename2):- searches the file and prints numbers of instances of 'Word'
%
wordcounter(Word,Filename2):-
	filehandling(Lines2,Filename2),
	
	countall(Lines2,Word,Y), 
	
    write('Number of '),write(Word),write(' found are: '),write(Y), nl.

	
% filehandling(Lines,Filename) - reads in a file(Filename), and outputs a list(Lines)
% 								 calls for the reading of the file, creation of the 
%								 input stream(Stream) in additon to opening and closing 
% 								 mentioned stream. Due to issues of backtracking, so is a
%								 cut also in here, as the Stream will not exist past the close.
%
filehandling(Lines,Filename):-
	open(Filename, read, Stream2),
    readfile(Stream2,Lines),
	close(Stream2),!.

% readfile(Stream,[]) - Checks if the input stream(Stream) is at the end, succeds if stream is 
% 						empty, fails otherwise.
%
readfile(Stream,[]) :-
    at_end_of_stream(Stream).

	
% readfile(Stream,[X|L]) - makes sure that the stream is not empty, then takes head of provided list and
%						   reads in a word from the input stream, if that dont fail, then it continues 
%						   doing the same with the tail of the provided list.
%
readfile(Stream,[X|L]) :-
    \+ at_end_of_stream(Stream),
	readInWord(Stream,X),
    readfile(Stream,L).

	
% readInWord(InStream,W) - reads in characters from the stream, checks them to see if they are legal, if
%						 it is a legal character, then it will continue, doing recursion inside the
%						 checkCharAndReadRest(Char,Chars,InStream) part until it hit an illegal character
%						 which can be end of stream or end of file, for example. it then converts the 
%						 list of characters it got from checkCharAndReadRest and makes that a string.
%
readInWord(InStream,W):-
    get_code(InStream,Char),
    checkCharAndReadRest(Char,Chars,InStream),
    atom_codes(W,Chars).

	
% checkCharAndReadRest(X,[],_):-  !. - this reacts and does a cut if the character X is matching what 
%									   character it is provided. If it does, it cuts, if not it falls
%									   thought going thought the different characters. The number is a
%									   character or symbols ASCII code. Listed below is what each 
%									   instances code and thus what is considered illegal and thus cut.
%
checkCharAndReadRest(46,[],_):-  !. %Dot 
checkCharAndReadRest(10,[],_):-  !. %New line / Enter
checkCharAndReadRest(32,[],_):-  !. %Space
checkCharAndReadRest(44,[],_):-  !. %Comma
checkCharAndReadRest(37,[],_):-  !. %Percentage
checkCharAndReadRest(33,[],_):-  !. %Eclemation Mark
checkCharAndReadRest(63,[],_):-  !. %Question Mark
checkCharAndReadRest(-1,[],_):-  !. %end of the Stream.
checkCharAndReadRest(end_of_file,[],_):-  !. %end of file as it says.


% checkCharAndReadRest(Char,[Char|Chars],InStream) - if it has reached this point then it is a legal character 
%													 and is this inserted into the list, a new character is 
% 													 gotten and it starts over with the new character.
%
checkCharAndReadRest(Char,[Char|Chars],InStream):-
	get_code(InStream,NextChar),
	checkCharAndReadRest(NextChar,Chars,InStream). 

	
% countall(List,X,C) - this is the counter, it first checks if what is wanted to be counted actually exist in 
%					   the list, before searching the list for instances of the provided word.
%
countall(List,X,C) :-
    sort(List,List1),
    member(X,List1),  % check to see if word is in list
    count(List,X,C).  % start counting.


% count([],_,0) - this is a catch mexanism incase of empty list, and thus to stop the recursion.
%
count([],_,0).


% count([X|T],X,Y) - this is the matching rule, if the head here and the desired word match, 
%					 , then they will unify, thus matching and then increment Y by one, after recursion on the
%					 tail of the list.
%
count([X|T],X,Y):- count(T,X,Z), Y is 1+Z.


% count([X1|T],X,Z) - If the word and head in the above instance fail to unify, then it will check if they are 
%					  different.
%
count([X1|T],X,Z):- X1\=X,count(T,X,Z).