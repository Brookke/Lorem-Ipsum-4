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
also has a status bar overlay at the top which allows the player to
switch between the map and journal screen, and also shows the player's
current score and personality.

When you click on a clue, the clue is shown and described. When you
click on a detective the screen changes to the dialogue screen.

**Related requirements**: 2.1.4,2.2.1,3,4(Map,clues and BPC sections)
**Realisation**:  Appendix F:2

Dialogue screen
---------------
The dialogue screen allows communication with suspects/NPCs. The GUI
contains an image of the suspect and contains the interview flow. This
involves the player first selecting whether to question, accuse or
ignore the suspect, followed buttons to select a clue to question the
suspect about, and dialogue style choices.

**Related requirements**: See dialogue sections of requirements.
**Realisation**: Appendix F:3

Journal
-------
The Journal is a collection of information that the player has obtained
so far in the game. It is layed out like a notebook and contains buttons
that allow the player to view clues, see the interview log of
conversations and write in a notepad. It contains the following
sections:

-  Clues list - shows the player the clues that they have collected so
   far
-  Conversation history - provides a list of the interview dialogue that
   have occurred with suspects so far in the game
-  Notepad - allows the player to enter any notes they feel appropriate

**Related Requirements**:5.2.1, 5.2.2
**Realisation**: Appendix F:4

Find Clue Screen
----------------
When the player clicks on a clue within the map, the find clue screen is
shown. It has a large graphic of the clue that has been found, and on
the right hand side is a text box describing the clue. The name of the
clue is at the top and there is a button at the bottom to continue and
go back to the map, the map can be seen in the background.

This screen is used to inform the player about the clue they have found
and ensures they are aware of the implications of it. We use an
animation to “fly out” the clue to the journal, this is intended to
increase user awareness of the journal so that they can use it to guide
their thought processes when deducting the killer in the game.

**Related requirements**:5.1.2
**Realisation**: Appendix F:5

Narrator Screen
---------------
The narrator screen has a narrator character, Sir Heslington (the duck),
with a speech box containing the text which he is saying. This screen is
used when you first load up the game, and the speech box text is used to
explain the premise. It is also used when you have found all 3 parts of
the motive clue. Finally it is used for when the player wins or loses.

**Related requirements**:7.1.7,8.1.1,8.1.2
**Realisation**: Appendix F:6
