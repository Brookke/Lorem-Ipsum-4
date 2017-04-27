GUI Report
=====================

Design processes
----------------

Our design process for the game was based on some simple principles, we
wanted to make sure that all GUI elements in the game were clear,
intuitive and easy to use. As a team we were clear that we wanted the
GUI design to be centered around the user and making sure that a user
would find it enjoyable to use, this in turn would hopefully lead to an
enjoyable experience playing the game which is another high priority for
us as a team. This is a game so it should be fun. We also aimed to have
a game that has a high level of usability. One example would be the
click to move option which uses an A\* search algorithm and makes
movement very usable.

Player interactions
-------------------

We designed the user interaction in the game using the principles
designed above. We were focused on usability, so we took the decision to
use mouse control for all user interactions. This was because we felt
the mouse is the most common and familiar method of interaction with a
computer and will be obvious to the user. It also removes the need for a
mental transition between keyboard control and mouse control, as jumping
between the two can be jarring, as we found in the last assessment.

At first we thought it was best to interact with clues and NPC’s by
walking up and pressing the Enter key, however we realised that this was
not intuitive, not to mention walking across rooms was boring and also
could have an unfair effect on the player's score. For this reason we
switched to just clicking on the clue or suspect instead, as this option
addresses these issues.

Main menu
----------

The main menu will be the first screen that a player will see upon
loading up the game. It will provide the user with the following
options:

-  **New game button** - this starts the game
-  **Exit button** - this closes the game
-  **Settings button** - go to settings screen
-  **Multiplayer** - go to number of players select screen

The buttons are all designed in the same way throughout the game. They
are designed to be easy to distinguish from the background and for all
text displayed on them to be easy to read.

**Related requirements**: 1.1.1
**Realisation**:  Appendix F:1

Main navigation screen
----------------------
The main navigation screen contains two elements - a map and a status
bar.

While the player is playing the game they will be able to see their
character on the map and move around from room to room. The character is
displayed in the middle of the map for good playability. This screen
also has a status bar overlay at the bottom, which allows the player to
switch between the map and Inventory screen, and also shows the player's
current score and personality. You can also go to the pause menu. Also
shows which player turn it is in multiplayer.

**Related requirements**: 2.1.4,2.2.1,3,4(Map,clues and BPC sections)
**Realisation**:  Appendix F:2

Dialogue screen
---------------
The dialogue screen allows communication with suspects/NPCs. The GUI is
an overlay over the main navigation screen and displays the text in a
box which is being spoken by the NPC and player.

**Related requirements**: See dialogue sections of requirements.
**Realisation**: Appendix F:3

Inventory
----------
The Inventory is a collection of information that the player has
obtained so far in the game. It contains a clues list which shows the
player the clues that they have collected so far

**Related Requirements**:5.2.1, 5.2.2
**Realisation**: Appendix F:4

Settings screen
----------------
The settings screen contains a tick box to mute music, it also will have
volume sliders.

**Related Requirements**:1.3.2
**Realisation**: Appendix F:5

Pause screen
-------------
The pause screen will let the player quit, go to the settings menu or
resume the game.

**Related Requirements**:1.2.1
**Realisation**: Appendix F:6

Number of Players Select Screen
--------------------------------
The number of players select screen allows the player/players to decide
how many players you want in your multiplayer game. The screen features
a very simple and intuitive slider which sets the number of players.

**Related requirements**: 2.1.5
**Realisation**: Appendix F:7

Player Switch Screen
----------------------
The player switch screen is shown every time the game switches from one
player to another in multiplayer mode. It says which player goes next
and also shows a leaderboard table detailing who is currently winning at
this stage in the game.

**Related requirements**: 2.1.5
**Realisation**: Appendix F:8

Puzzle Screen
---------------
The puzzle screen is the screen which lets the player attempt to
complete the puzzle and enter the secret room. The puzzle screen looks
like a bookshelf with book style buttons. These buttons can be clicked
causing them to move. If the player gets the sequence wrong these books
all reset. If the player gets the sequence right this screen is replaced
by the secret room and the main navigation screen as usual.

**Related requirements**: 4.1.5
**Realisation**: Appendix F:9
