/* Spamventure! */
%% starter file by Ran Libeskind-Hadas
%% and modified by Z Dodds
%% Author(s): Shannon Lin, Akhil Bagaria


/*
For clarification, this is the map of the locations in this game:

    Frosh World               Yekland               Floppyland
    Spam World                SuperSpam Land        CS60 Lab

In order to win, we need to retrieve Pass/Fail from the CS60 Lab. We
begin at Frosh World and travel to various lands to collect the items
necessary to successfully enter and escape from the CS60 Lab.

** SPOILER **
Item Locations & Function:
spam - Spam World, used to obtain the key... DO NOT EAT!
superspam - SuperSpam Land, used for fun
key - Yekland, used to enter the CS60 Lab
floppydisk - Floppyland, used to escape the CS60 Lab

From Frosh World, go to Spam World and take spam. This is needed in
order to obtain the key from Yekland. The superspam can be picked up in
SuperSpam Land, but this item is not required to win the game. Obtaining
the key from Yekland is not enough. Afterwards, it is vital that we
travel to Floppyland and obtain the floppydisk. This floppydisk is used
to maneuver the spacecraft found in the CS60 Lab. Once you have arrived
at the CS60 Lab, there are a few possible endings: two for when you have
retrieve both the floppydisk and the key, and one for when you have
retrieved only the key. Without the key, you cannot enter the CS60 Lab
and will instead be asked to go back.
*/



% some "nice" prolog settings...  see assignment 8's
% description for the details on what these do
% -- but it's not crucial to know the details of these Prolog internals
:- set_prolog_flag( prompt_alternatives_on, groundness ).
:- set_prolog_flag(toplevel_print_options, [quoted(true),
     portray(true), attributes(portray), max_depth(999), priority(699)]).




%% thing_at(X, Y) will be true iff thing X is at location Y.
%% player_at(X) will be true iff player is at location X.
%% The use of dynamic should be at the beginning of the file when
%% we plan to mix static and dynamic facts with the same names.

:- dynamic thing_at/2, player_at/1.

%% The player is initially at Jacobs
player_at(froshworld).

%% thing_at(X, Y) is true iff thing X is at location Y.
%% note that the key is not described at jacobs and
%% the spam is described along with west_dorm
%% both of these are problems -- you might start by considering
%% how you might fix them... (see west_dorm for a similar note)
thing_at(spam, spamworld).
thing_at(superspam, superspamland).
thing_at(key, yekland).
thing_at(floppydisk, floppyland).

%% path(X, Y, Z) is true iff there is a path from X to Z via direction Y.
% lands: froshworld, spamworld, superspamland, yekland,
%        floppyland, cs60lab
path( froshworld, south, spamworld).
path( froshworld, east, yekland).
path( spamworld, north, froshworld).
path( spamworld, east, superspamland).
path( superspamland, west, spamworld).
path( superspamland, north, yekland).
path( yekland, west, froshworld).
path( yekland, south, superspamland).
path( yekland, east, floppyland).
path( floppyland, west, yekland).
path( floppyland, south, cs60lab).
path( cs60lab, north, floppyland).


%% This is how one can take an object.
take(X) :-
    thing_at(X, in_hand),
    nl, write('You are already holding that.'),
    nl.

take(_) :-
    setof(X, thing_at(X, in_hand), Y),
    length(Y, N),
    N >= 2,
    nl, write('You can only hold two items!').

take(X) :-
    player_at(Place),
    thing_at(X, Place),
    retract(thing_at(X, Place)),
    assert(thing_at(X, in_hand)),
    nl, write('OK you now have '),
    write(X),
    nl.

%% This is how one can drop an object.
drop(X) :-
   player_at(Place),
   thing_at(X, in_hand),
   retract(thing_at(X, in_hand)),
   assert(thing_at(X, Place)),
   nl, write('OK you have dropped '),
   write(X),
   nl.

%% This is how we move.
north :- go(north).
south :- go(south).
east :- go(east).
west :- go(west).

go(Direction) :-
    player_at(Here),
    path(Here, Direction, There),
    retract(player_at(Here)),
    assert(player_at(There)),
    nl, write('You are now traveling to '),
    write(There), nl,
    look.

go(Direction) :-
    player_at(Here),
    \+path(Here, Direction, _),
    write('You cannot go that way.'), nl.


%% The predicates used to describe a place
look :- nl, player_at(Place), describe(Place), nl.

describe(froshworld) :-
    write('You are in Frosh World.'), nl,
    write('You see a barren desert landscape.'), nl,
    write('Unhappy Freshman are walking around aimlessly.'), nl,
    write('You can go to Spam World by heading south and '), nl,
    write('Yekland by heading east.').

describe(spamworld) :-
    thing_at(spam, spamworld),
    write('You are in Spam World.'), nl,
    write('The place has been ravaged by three- '), nl,
    write('eyed aliens, in search of spam.'),nl,
    write('You can go to Frosh World by heading north and '), nl,
    write('SuperSpam Land by heading east.'), nl,
    write('There is spam on the floor.').

describe(spamworld) :-
    write('You are in Spam World.'), nl,
    write('The place has been ravaged by three- '), nl,
    write('eyed aliens, in search of spam.'),nl,
    write('There is no spam on the floor. :['), nl,
    write('You can go to Frosh World by heading north and '), nl,
    write('SuperSpam Land by heading east.'), nl,
    \+thing_at(spam, spamworld).

describe(superspamland) :-
    thing_at(superspam, superspamland),
    write('You are in SuperSpam Land.'), nl,
    write('This place has also been destroyed by the '), nl,
    write('three-eyed aliens.'), nl,
    write('You see a map on the floor and read it.'), nl,
    write('The map says that a key is required to '), nl,
    write('get into the CS60 Lab'),nl,
    write('There is superspam on the floor!'), nl,
    write('Maybe this will be useful later.'), nl,
    write('You can go to Spam World by heading west, '), nl,
    write('Yekland by heading north, and '), nl.

describe(superspamland) :-
    write('You are in SuperSpam Land.'), nl,
    write('This place has also been destroyed by the '), nl,
    write('three-eyed aliens.'), nl,
    write('There is nothing on the floor but a map, '), nl,
    write('which says that a key is required to get '), nl,
    write('into the CS60 Lab.'), nl,
    write('You can go to Spam World by heading west, '), nl,
    write('Yekland by heading north, and '), nl,
    \+thing_at(superspam, superspamland).

describe(yekland) :-
    thing_at(key, yekland),
    thing_at(spam, in_hand),
    write('You are in Yekland.'), nl,
    write('It is very hot.'), nl,
    write('There is a four-eyed alien standing in the shade.'), nl,
    write('He asks you why you are here, and you tell him that '), nl,
    write('you need a key to enter the CS60 Lab. Surprisingly, '), nl,
    write('he has one. He asks you for spam in exchange.'), nl,
    retract(thing_at(spam, in_hand)),
    assert(thing_at(key, in_hand)),
    retract(thing_at(key, yekland)),
    write('After he eats the spam, he tells you that he '), nl,
    write('was abandoned by the three-eyed aliens for having '), nl,
    write('for having four eyes. He asks you to take revenge '), nl,
    write('for him and warns you that a floppydisk is needed '), nl,
    write('to maneuver the alien spaceship.'), nl,
    write('You can get to Frosh World by heading west, '), nl,
    write('SuperSpam Land by heading south, and '), nl,
    write('Floppyland by heading east. ').

describe(yekland) :-
    thing_at(key, yekland),
    write('You are in Yekland.'), nl,
    write('It is very hot.'), nl,
    write('There is a four-eyed alien standing in the shade.'), nl,
    write('He asks you why you are here, and you tell him that '), nl,
    write('you need a key to enter the CS60 Lab. Surprisingly, '), nl,
    write('he has one. He asks you for spam in exchange.'), nl,
    write('You do not have spam. Maybe you should get some.'), nl,
    write('You can get to Frosh World by heading west, '), nl,
    write('SuperSpam Land by heading south, and '), nl,
    write('Floppyland by heading east. '),
    \+thing_at(spam, in_hand).

describe(yekland) :-
    write('You are in Yekland.'), nl,
    write('It is very hot.'), nl,
    write('The four-eyed alien is full from spam.'), nl,
    write('You can get to Frosh World by heading west, '), nl,
    write('SuperSpam Land by heading south, and '), nl,
    write('Floppyland by heading east. '),
    \+thing_at(key, yekland).

describe(floppyland) :-
    thing_at(floppydisk, floppyland),
    write('You are in Floppyland, the land of the 90s.'), nl,
    write('Eminem music is blasting in the background.'), nl,
    write('You see a floppydisk on the floor.'), nl,
    write('You can get to Yekland by heading west, and '), nl,
    write('CS60 Lab by heading south. ').

describe(floppyland) :-
    write('You are in Floppyland, the land of the 90s.'), nl,
    write('Eminem music is blasting in the background.'), nl,
    write('Nothing is on the floor.'), nl,
    write('You can get to Yekland by heading west, and '), nl,
    write('CS60 Lab by heading south. '),
    \+thing_at(floppydisk, floppyland).

describe(cs60lab) :-
    \+thing_at(key, in_hand),
    write('You are at the entrance of the CS60 Lab.'), nl,
    write('There are barbed wire fences surrounding '), nl,
    write('the room. The only entrance is the locked '), nl,
    write('door. You cannot open the door without a key.'), nl,
    write('You can get to Floppyworld by heading north.').

describe(cs60lab) :-
    thing_at(key, in_hand),
    \+thing_at(floppydisk, in_hand),
    write('You are at the entrance of the CS60 Lab.'), nl,
    write('There are barbed wire fences surrounding '), nl,
    write('the room. The only entrance is the locked '), nl,
    write('door. You put the key in the keyhole... '), nl,
    write('and it opens!'), nl,
    write('Inside, three-eyed aliens are swarming around '), nl,
    write('Prof Lewis, who is being forced to play the drums for '), nl,
    write('them. Maybe she was actually abducted for their personal '), nl,
    write('entertainment! You feel pass/fail calling to you in the '), nl,
    write('alien spaceship. You dash to the spaceship and start slamming '), nl,
    write('the buttons in the cockpit in an attempt to get the spaceship '), nl,
    write('flying. Nothing works. You have disrupted their party with Prof Lewis. '), nl,
    write('The aliens slither to you and eat you alive. Nice try, kiddo!'), nl,
    write('Game Over. To end the game, type halt.').

describe(cs60lab) :-
    thing_at(key, in_hand),
    thing_at(floppydisk, in_hand),
    write('You are at the entrance of the CS60 Lab.'), nl,
    write('There are barbed wire fences surrounding '), nl,
    write('the room. The only entrance is the locked '), nl,
    write('door. You put the key in the keyhole... '), nl,
    write('and it opens!'), nl,
    write('Inside, three-eyed aliens are swarming around '), nl,
    write('Prof Lewis, who is being forced to play the drums for '), nl,
    write('them. Maybe she was actually abducted for their personal '), nl,
    write('entertainment! You feel pass/fail calling to you in the '), nl,
    write('alien spaceship. You notice spaceship can only hold one '), nl,
    write('human being. You can save Prof Lewis and die yourself, or '), nl,
    write('you can be scumbag and survive! Who do you sacrifice?'), nl,
    write('yourself or lewis?').

% alternate endings
lewis :- sacrifice(lewis, cs60lab).
yourself :- sacrifice(yourself, cs60lab).

sacrifice(lewis, cs60lab) :-
	player_at(cs60lab),
	write('You dash past the three-eyed aliens, who are '), nl,
	write('dancing to the beat of the drums. Prof Lewis notices '), nl,
	write('you and asks you to save her. The three-eyed aliens realize '), nl,
	write('that you are there. They begin to slither towards you. '), nl,
	write('You are at the entrance of the alien spaceship. You ignore '), nl,
	write('her pleas, and close the doors. Using the FLOPPYDISK, you '), nl,
	write('control the spaceship and hurl at (3/5)c back to Froshworld. '), nl,
	write('You are true hero!'), nl,
	write('Game Over. To end the game, type halt.').

sacrifice(yourself, cs60lab) :-
	player_at(cs60lab),
	write('You dash past the three-eyed aliens, who are '), nl,
	write('dancing to the beat of the drums. Prof Lewis notices '), nl,
	write('you and asks you to save her. The three-eyed aliens realize '), nl,
	write('that you are there. They begin to slither towards you. '), nl,
	write('You decide to give yourself to the aliens and tell Prof Lewis '), nl,
	write('to escape. She runs to the spaceship. As the doors close, she chuckles '), nl,
	write('and says "So long, sucker!" You are eaten alive by the furious aliens.'), nl,
	write('Game Over. To end the game, type halt.').

% additional feature
eat(X) :-
       thing_at(X, in_hand),
       (X = spam; X = superspam),
       retract(thing_at(X, in_hand)),
       nl, write('You have eaten '),
       write(X), write(' .It was delicious! You feel rejuvenated.'),
       nl.

eat(X) :-
       thing_at(X, in_hand),
       (X \= spam; X\= superspam),
       nl, write('You cannot eat that! '),
       nl.

%% The predicates used to start the game
%% You should customize these instructions to reveal
%% the object of your adventure along with any special
%% commands the user might need to know.
start :- instructions, look.

instructions :- nl,
    write('Welcome to Spamventure!'), nl,
    nl,
    write('Objective: '), nl,
    write('Prof Lewis and her gang of three-eyed aliens '), nl,
    write('have stolen pass/fail from the innocent '), nl,
    write('Freshman and have absconded to the CS60 Lab '), nl,
    write('(a land in which Eclipse works) in their '), nl,
    write('spaceship! In order to ensure the best '), nl,
    write('experience for the incoming Freshman class, '), nl,
    write('we must locate the CS60 Lab and return '), nl,
    write('pass/fail to its rightful place.'), nl,
    nl,
    write('Instructions: '), nl,
    write('Type east, west, north, or south to move.'), nl,
    write('Type take(item) to take item.'), nl,
    write('Type drop(item) to drop the item.'), nl,
    write('Type eat(item) to eat an item.'), nl,
    write('Type instructions to see possible commands.'), nl,
    write('Type inventory to check the items in hand. '), nl,
    write('You can only hold two objects at once!. '), nl,
    write('Type halt to end the game.'), nl,
    write('Typing start will NOT restart the game.'), nl.

% inventory
inventory :- nl,
    thing_at( X, in_hand ),
    write('You are currently holding '), nl,
    write(X), nl, fail.

inventory :- nl,
    \+thing_at( _, in_hand),
    write('You are currently holding nothing').
