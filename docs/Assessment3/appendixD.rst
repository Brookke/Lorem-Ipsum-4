D: Acceptance Tests
==============================

Below is a table of the acceptance tests included within this project.

These tests need to be manually run using a copy of the game executable.
They involve following a series of steps, verifying each of the
assertions in the steps is true, and that the relevant GUI exists to
allow the steps to be carried out. If all of the steps can be carried
out, and their assertions are true, the test passes. If not, the test
fails.

The acceptance tests are associated with an appropriate requirement to
allow for traceability, and the tests aim to check that the code works
for any associated requirements. Not all requirements have associated
tests, and vice versa - this is because some requirements cannot be
explicitly unit tested, and some tests do not link up directly to a
requirement, but are still needed to ensure the code functions as
intended.

There is a criticality measure against each test, for both acceptance
and unit tests - this is to represent how important the test is to the
overall function of the code. Criticality is on a scale - high
criticality means that if that test fails, the project will not function
at all; low criticality means that if the test fails, the project will
still mostly function as intended.

Test Listing
-------------
+----------------+----------------+----------------+----------------+----------------+
| ID             | Test Steps     | Req ID         | Criticality    | Result         |
+----------------+----------------+----------------+----------------+----------------+
| 2.01           | To test the    | 1.1.1          | Low            | Passed         |
|                | Main Menu:     |                |                |                |
|                |                |                |                |                |
|                | -  Run game    |                |                |                |
|                |    executable. |                |                |                |
|                | -  Check that  |                |                |                |
|                |    the Main    |                |                |                |
|                |    menu is     |                |                |                |
|                |    shown.      |                |                |                |
|                | -  Click on    |                |                |                |
|                |    the New     |                |                |                |
|                |    Game        |                |                |                |
|                |    button.     |                |                |                |
|                | -  Ensure the  |                |                |                |
|                |    screen      |                |                |                |
|                |    changes to  |                |                |                |
|                |    the         |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen.     |                |                |                |
|                | -  Restart the |                |                |                |
|                |    game and    |                |                |                |
|                |    click on    |                |                |                |
|                |    the Quit    |                |                |                |
|                |    button      |                |                |                |
|                | -  Ensure that |                |                |                |
|                |    the game    |                |                |                |
|                |    closes.     |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.02           | To test the    | 1.1.2          | High           | Passed         |
|                | player         |                |                |                |
|                | movement using |                |                |                |
|                | key presses:   |                |                |                |
|                |                |                |                |                |
|                | -  Run game    |                |                |                |
|                |    executable  |                |                |                |
|                | -  On the Main |                |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  Once the    |                |                |                |
|                |    game has    |                |                |                |
|                |    loaded,     |                |                |                |
|                |    press “W”   |                |                |                |
|                |    on the      |                |                |                |
|                |    keyboard.   |                |                |                |
|                | -  Ensure that |                |                |                |
|                |    the player  |                |                |                |
|                |    has moved   |                |                |                |
|                |    upwards.    |                |                |                |
|                | -  Press “S”   |                |                |                |
|                |    on the      |                |                |                |
|                |    keyboards   |                |                |                |
|                | -  Ensure that |                |                |                |
|                |    the player  |                |                |                |
|                |    has moved   |                |                |                |
|                |    downwards.  |                |                |                |
|                | -  Press “A”   |                |                |                |
|                |    on the      |                |                |                |
|                |    keyboard.   |                |                |                |
|                | -  Ensure that |                |                |                |
|                |    the player  |                |                |                |
|                |    has moved   |                |                |                |
|                |    to the      |                |                |                |
|                |    left.       |                |                |                |
|                | -  Press “D”   |                |                |                |
|                |    on the      |                |                |                |
|                |    keyboard.   |                |                |                |
|                | -  Ensure that |                |                |                |
|                |    the player  |                |                |                |
|                |    has moved   |                |                |                |
|                |    to the      |                |                |                |
|                |    right.      |                |                |                |
|                |                |                |                |                |
|                | If any of      |                |                |                |
|                | these          |                |                |                |
|                | movements do   |                |                |                |
|                | not occur as   |                |                |                |
|                | they should or |                |                |                |
|                | the player     |                |                |                |
|                | remains        |                |                |                |
|                | stationary at  |                |                |                |
|                | any point      |                |                |                |
|                | despite the    |                |                |                |
|                | pressing of    |                |                |                |
|                | the WASD       |                |                |                |
|                | buttons or the |                |                |                |
|                | player moving  |                |                |                |
|                | despite the    |                |                |                |
|                | buttons not    |                |                |                |
|                | being pressed, |                |                |                |
|                | then the test  |                |                |                |
|                | has failed.    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.03           | -  Run game    | 1.1.3          | Medium         | Passed         |
|                |    executable. |                |                |                |
|                | -  On the Main |                |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  Play game   |                |                |                |
|                |    to          |                |                |                |
|                |    completion. |                |                |                |
|                | -  Keep a note |                |                |                |
|                |    of the      |                |                |                |
|                |    selected    |                |                |                |
|                |    killer,     |                |                |                |
|                |    victim,     |                |                |                |
|                |    murder      |                |                |                |
|                |    location    |                |                |                |
|                |    and murder  |                |                |                |
|                |    weapon.     |                |                |                |
|                | -  Repeat at   |                |                |                |
|                |    least 3     |                |                |                |
|                |    times.      |                |                |                |
|                | -  Ensure the  |                |                |                |
|                |    combination |                |                |                |
|                |    of selected |                |                |                |
|                |    killer,     |                |                |                |
|                |    victim,     |                |                |                |
|                |    murder      |                |                |                |
|                |    location    |                |                |                |
|                |    and murder  |                |                |                |
|                |    weapon      |                |                |                |
|                |    differ each |                |                |                |
|                |    time.       |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.04           | -  Run game    | 2.1.3          | Low            | Passed         |
|                |    executable. |                |                |                |
|                | -  On the Main |                |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  Take note   |                |                |                |
|                |    of the      |                |                |                |
|                |    location    |                |                |                |
|                |    the Player  |                |                |                |
|                |    starts in.  |                |                |                |
|                | -  Close the   |                |                |                |
|                |    game.       |                |                |                |
|                | -  Run game    |                |                |                |
|                |    executable. |                |                |                |
|                | -  On the Main |                |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  Ensure that |                |                |                |
|                |    the Player  |                |                |                |
|                |    starts in   |                |                |                |
|                |    the same    |                |                |                |
|                |    location    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.05           | -  Run game    | 2.1.4          | High           | Passed         |
|                |    executable. |                |                |                |
|                | -  On the Main |                |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  Use “WASD”  |                |                |                |
|                |    keys to     |                |                |                |
|                |    Navigate    |                |                |                |
|                |    the player  |                |                |                |
|                |    to a        |                |                |                |
|                |    doorway     |                |                |                |
|                |    (marked by  |                |                |                |
|                |    a carpet).  |                |                |                |
|                | -  Move        |                |                |                |
|                |    through the |                |                |                |
|                |    doorway.    |                |                |                |
|                | -  Check that  |                |                |                |
|                |    the Player  |                |                |                |
|                |    is now in a |                |                |                |
|                |    different   |                |                |                |
|                |    room.       |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.06           | -  Run game    | 2.2.1          | Low            | Passed         |
|                |    executable. |                |                |                |
|                | -  On the Main |                |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  Personality |                |                |                |
|                |    meter       |                |                |                |
|                |    displays    |                |                |                |
|                |    the current |                |                |                |
|                |    personality |                |                |                |
|                |    level.      |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.07           | -  Run game    | 3.1.2          | High           | Passed         |
|                |    executable. |                |                |                |
|                | -  On the Main |                |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  Use “WASD”  |                |                |                |
|                |    keys to     |                |                |                |
|                |    Navigate    |                |                |                |
|                |    through the |                |                |                |
|                |    initial     |                |                |                |
|                |    room.       |                |                |                |
|                | -  Note if an  |                |                |                |
|                |    NPC is      |                |                |                |
|                |    found.      |                |                |                |
|                | -  Navigate    |                |                |                |
|                |    through a   |                |                |                |
|                |    doorway     |                |                |                |
|                |    (marked by  |                |                |                |
|                |    a carpet)   |                |                |                |
|                |    to a        |                |                |                |
|                |    different   |                |                |                |
|                |    room.       |                |                |                |
|                | -  Note if an  |                |                |                |
|                |    NPC is      |                |                |                |
|                |    found.      |                |                |                |
|                | -  Continue    |                |                |                |
|                |    this until  |                |                |                |
|                |    you have    |                |                |                |
|                |    navigated   |                |                |                |
|                |    through all |                |                |                |
|                |    10          |                |                |                |
|                |    different   |                |                |                |
|                |    rooms       |                |                |                |
|                | -  Count the   |                |                |                |
|                |    number of   |                |                |                |
|                |    NPCs found  |                |                |                |
|                |    and ensure  |                |                |                |
|                |    that there  |                |                |                |
|                |    are exactly |                |                |                |
|                |    9 NPCs      |                |                |                |
|                |    around the  |                |                |                |
|                |    map.        |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.08           | -  Run game    | 4.1.1          | High           | Passed         |
|                |    executable. |                |                |                |
|                | -  On the Main |                |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  Use “WASD”  |                |                |                |
|                |    keys to     |                |                |                |
|                |    Navigate    |                |                |                |
|                |    through the |                |                |                |
|                |    map.        |                |                |                |
|                | -  Navigate    |                |                |                |
|                |    through a   |                |                |                |
|                |    doorway     |                |                |                |
|                |    (marked by  |                |                |                |
|                |    a carpet)   |                |                |                |
|                |    to a        |                |                |                |
|                |    different   |                |                |                |
|                |    room.       |                |                |                |
|                | -  Note when   |                |                |                |
|                |    you’ve      |                |                |                |
|                |    entered a   |                |                |                |
|                |    new room    |                |                |                |
|                |    (one that   |                |                |                |
|                |    hasn’t been |                |                |                |
|                |    entered     |                |                |                |
|                |    before      |                |                |                |
|                |    during this |                |                |                |
|                |    test).      |                |                |                |
|                | -  Repeat this |                |                |                |
|                |    until you   |                |                |                |
|                |    have        |                |                |                |
|                |    navigated   |                |                |                |
|                |    through all |                |                |                |
|                |    rooms of    |                |                |                |
|                |    the map.    |                |                |                |
|                | -  Check that  |                |                |                |
|                |    there are   |                |                |                |
|                |    10 distinct |                |                |                |
|                |    rooms that  |                |                |                |
|                |    the player  |                |                |                |
|                |    has         |                |                |                |
|                |    traveled    |                |                |                |
|                |    through.    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.09           | -  Run game    | 5.1.1          | High           | Passed         |
|                |    executable. |                |                |                |
|                | -  On the Main |                |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  Use “WASD”  |                |                |                |
|                |    keys to     |                |                |                |
|                |    Navigate    |                |                |                |
|                |    through the |                |                |                |
|                |    map.        |                |                |                |
|                | -  Navigate    |                |                |                |
|                |    through a   |                |                |                |
|                |    doorway     |                |                |                |
|                |    (marked by  |                |                |                |
|                |    a carpet)   |                |                |                |
|                |    to a        |                |                |                |
|                |    different   |                |                |                |
|                |    room.       |                |                |                |
|                | -  Check that  |                |                |                |
|                |    the room    |                |                |                |
|                |    has a clue. |                |                |                |
|                | -  In this     |                |                |                |
|                |    manner,     |                |                |                |
|                |    navigate    |                |                |                |
|                |    through all |                |                |                |
|                |    10 rooms in |                |                |                |
|                |    the map.    |                |                |                |
|                | -  Check that  |                |                |                |
|                |    the Player  |                |                |                |
|                |    can find at |                |                |                |
|                |    least one   |                |                |                |
|                |    clue in     |                |                |                |
|                |    each room.  |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.10           | -  Run game    | 5.1.2          | High           | Passed         |
|                |    executable. |                |                |                |
|                | -  On the Main |                |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  Use “WASD”  |                |                |                |
|                |    keys to     |                |                |                |
|                |    Navigate    |                |                |                |
|                |    through the |                |                |                |
|                |    initial     |                |                |                |
|                |    room.       |                |                |                |
|                | -  Find a clue |                |                |                |
|                |    (can be     |                |                |                |
|                |    recognised  |                |                |                |
|                |    as an       |                |                |                |
|                |    obvious     |                |                |                |
|                |    glowing     |                |                |                |
|                |    glint at    |                |                |                |
|                |    some        |                |                |                |
|                |    location in |                |                |                |
|                |    the room)   |                |                |                |
|                | -  Check that  |                |                |                |
|                |    the player  |                |                |                |
|                |    can         |                |                |                |
|                |    interact    |                |                |                |
|                |    with the    |                |                |                |
|                |    clue by     |                |                |                |
|                |    clicking on |                |                |                |
|                |    it.         |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.11           | -  Run game    | 5.1.3          |                | Passed         |
|                |    executable. |                |                |                |
|                | -  On the Main |                |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  Use “WASD”  |                |                |                |
|                |    keys to     |                |                |                |
|                |    Navigate    |                |                |                |
|                |    through the |                |                |                |
|                |    map.        |                |                |                |
|                | -  Collect     |                |                |                |
|                |    clues until |                |                |                |
|                |    a motive    |                |                |                |
|                |    clue part   |                |                |                |
|                |    is found.   |                |                |                |
|                | -  Continue    |                |                |                |
|                |    finding     |                |                |                |
|                |    clues until |                |                |                |
|                |    all 3       |                |                |                |
|                |    motive clue |                |                |                |
|                |    parts are   |                |                |                |
|                |    found       |                |                |                |
|                |    (these      |                |                |                |
|                |    appear as a |                |                |                |
|                |    glint in a  |                |                |                |
|                |    room, just  |                |                |                |
|                |    like with a |                |                |                |
|                |    normal      |                |                |                |
|                |    clue).      |                |                |                |
|                | -  Check that  |                |                |                |
|                |    the whole   |                |                |                |
|                |    motive clue |                |                |                |
|                |    is provided |                |                |                |
|                |    once all 3  |                |                |                |
|                |    are found.  |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.12           | -  Run game    | 5.1.3          |                | Passed         |
|                |    executable. |                |                |                |
|                | -  On the Main |                |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  Use “WASD”  |                |                |                |
|                |    keys to     |                |                |                |
|                |    Navigate    |                |                |                |
|                |    through the |                |                |                |
|                |    map.        |                |                |                |
|                | -  Find all    |                |                |                |
|                |    the clues   |                |                |                |
|                | -  Question    |                |                |                |
|                |    characters  |                |                |                |
|                |    until the   |                |                |                |
|                |    murder can  |                |                |                |
|                |    be deduced. |                |                |                |
|                | -  Ensure that |                |                |                |
|                |    only one    |                |                |                |
|                |    whole       |                |                |                |
|                |    motive clue |                |                |                |
|                |    and 3       |                |                |                |
|                |    motive clue |                |                |                |
|                |    parts are   |                |                |                |
|                |    obtained    |                |                |                |
|                |    throughout  |                |                |                |
|                |    the entire  |                |                |                |
|                |    game, check |                |                |                |
|                |    using the   |                |                |                |
|                |    Journal     |                |                |                |
|                | -  Accuse the  |                |                |                |
|                |    murderer    |                |                |                |
|                |    and         |                |                |                |
|                |    complete    |                |                |                |
|                |    the game    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.13           | -  Run game    | 7.1.1          | High           | Passed         |
|                |    executable. |                |                |                |
|                | -  On the Main |                |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”       |                |                |                |
|                | -  Use “WASD”  |                |                |                |
|                |    keys to     |                |                |                |
|                |    Navigate    |                |                |                |
|                |    through the |                |                |                |
|                |    initial     |                |                |                |
|                |    room.       |                |                |                |
|                | -  Find the    |                |                |                |
|                |    NPC         |                |                |                |
|                |    assigned to |                |                |                |
|                |    that room.  |                |                |                |
|                | -  Click on    |                |                |                |
|                |    the NPC.    |                |                |                |
|                | -  The screen  |                |                |                |
|                |    should      |                |                |                |
|                |    change and  |                |                |                |
|                |    the         |                |                |                |
|                |    “Question”  |                |                |                |
|                |    and         |                |                |                |
|                |    “Ignore”    |                |                |                |
|                |    buttons     |                |                |                |
|                |    should      |                |                |                |
|                |    appear      |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.14           | -  Run game    | 7.1.2          | High           | Passed         |
|                |    executable. |                |                |                |
|                | -  On the Main |                |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”       |                |                |                |
|                | -  Use “WASD”  |                |                |                |
|                |    keys to     |                |                |                |
|                |    Navigate    |                |                |                |
|                |    through the |                |                |                |
|                |    initial     |                |                |                |
|                |    room.       |                |                |                |
|                | -  Find the    |                |                |                |
|                |    NPC         |                |                |                |
|                |    assigned to |                |                |                |
|                |    that room.  |                |                |                |
|                | -  Click on    |                |                |                |
|                |    that NPC.   |                |                |                |
|                | -  Select      |                |                |                |
|                |    ‘Question’  |                |                |                |
|                |    button.     |                |                |                |
|                | -  The player  |                |                |                |
|                |    can         |                |                |                |
|                |    question    |                |                |                |
|                |    the NPC.    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.15           | -  Run game    | 7.1.3          | Medium         | Passed         |
|                |    executable. |                |                |                |
|                | -  On the Main |                |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”       |                |                |                |
|                | -  Use “WASD”  |                |                |                |
|                |    keys to     |                |                |                |
|                |    Navigate    |                |                |                |
|                |    through the |                |                |                |
|                |    initial     |                |                |                |
|                |    room.       |                |                |                |
|                | -  Find the    |                |                |                |
|                |    NPC         |                |                |                |
|                |    assigned to |                |                |                |
|                |    that room.  |                |                |                |
|                | -  Click on    |                |                |                |
|                |    that NPC.   |                |                |                |
|                | -  Select the  |                |                |                |
|                |    ‘Ignore’    |                |                |                |
|                |    button.     |                |                |                |
|                | -  Ensure that |                |                |                |
|                |    the player  |                |                |                |
|                |    can ignore  |                |                |                |
|                |    the NPC     |                |                |                |
|                |    (cannot     |                |                |                |
|                |    question,   |                |                |                |
|                |    accuse or   |                |                |                |
|                |    ignore the  |                |                |                |
|                |    NPC again   |                |                |                |
|                |    until       |                |                |                |
|                |    another     |                |                |                |
|                |    clue is     |                |                |                |
|                |    found, the  |                |                |                |
|                |    Player      |                |                |                |
|                |    moves to a  |                |                |                |
|                |    different   |                |                |                |
|                |    room or the |                |                |                |
|                |    Player      |                |                |                |
|                |    talks with  |                |                |                |
|                |    a different |                |                |                |
|                |    character). |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.16           | -  Run game    | 2.1.2          | Medium         | Passed         |
|                |    executable. |                |                |                |
|                | -  On the Main | 7.1.4          |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  Use the     |                |                |                |
|                |    “WASD” keys |                |                |                |
|                |    to move the |                |                |                |
|                |    player      |                |                |                |
|                |    until an    |                |                |                |
|                |    NPC is      |                |                |                |
|                |    found       |                |                |                |
|                |    (appears on |                |                |                |
|                |    the         |                |                |                |
|                |    screen).    |                |                |                |
|                | -  Use the     |                |                |                |
|                |    mouse to    |                |                |                |
|                |    click on    |                |                |                |
|                |    the NPC.    |                |                |                |
|                | -  Ensure that |                |                |                |
|                |    the         |                |                |                |
|                |    “accuse”    |                |                |                |
|                |    button is   |                |                |                |
|                |    not         |                |                |                |
|                |    visible.    |                |                |                |
|                | -  Move the    |                |                |                |
|                |    player      |                |                |                |
|                |    until a     |                |                |                |
|                |    clue is     |                |                |                |
|                |    found.      |                |                |                |
|                | -  Click on    |                |                |                |
|                |    the clue to |                |                |                |
|                |    collect the |                |                |                |
|                |    clue.       |                |                |                |
|                | -  Repeat      |                |                |                |
|                |    until the   |                |                |                |
|                |    motive and  |                |                |                |
|                |    means clues |                |                |                |
|                |    are         |                |                |                |
|                |    collected.  |                |                |                |
|                | -  Find an NPC |                |                |                |
|                |    and click   |                |                |                |
|                |    on it.      |                |                |                |
|                | -  Ensure that |                |                |                |
|                |    the         |                |                |                |
|                |    ‘“accuse”   |                |                |                |
|                |    button is   |                |                |                |
|                |    now         |                |                |                |
|                |    visible.    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.17           | -  Run game    | 7.1.5          | High           | Passed         |
|                |    executable. |                |                |                |
|                | -  On the Main |                |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”       |                |                |                |
|                | -  Use “WASD”  |                |                |                |
|                |    keys to     |                |                |                |
|                |    Navigate    |                |                |                |
|                |    through the |                |                |                |
|                |    initial     |                |                |                |
|                |    room.       |                |                |                |
|                | -  Find the    |                |                |                |
|                |    clue        |                |                |                |
|                |    assigned to |                |                |                |
|                |    that room.  |                |                |                |
|                | -  Find the    |                |                |                |
|                |    NPC         |                |                |                |
|                |    assigned to |                |                |                |
|                |    that room.  |                |                |                |
|                | -  Click on    |                |                |                |
|                |    that NPC.   |                |                |                |
|                | -  Select      |                |                |                |
|                |    the ‘Questi |                |                |                |
|                | on’            |                |                |                |
|                |    button.     |                |                |                |
|                | -  The player  |                |                |                |
|                |    should be   |                |                |                |
|                |    able to     |                |                |                |
|                |    select a    |                |                |                |
|                |    clue to     |                |                |                |
|                |    question    |                |                |                |
|                |    the NPC     |                |                |                |
|                |    about       |                |                |                |
|                | -  The player  |                |                |                |
|                |    should be   |                |                |                |
|                |    able to     |                |                |                |
|                |    select a    |                |                |                |
|                |    style of    |                |                |                |
|                |    question to |                |                |                |
|                |    ask the NPC |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.18           | -  Start a     | 1.1.5          | High           | Passed         |
|                |    computer In |                |                |                |
|                |    Windows 10  |                |                |                |
|                | -  Ensure the  |                |                |                |
|                |    game        |                |                |                |
|                |    executable  |                |                |                |
|                | runs.          |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.19           | -  In macOS    | 1.2.2          | High           | Passed         |
|                | -  Game        |                |                |                |
|                |    executable  |                |                |                |
|                |    runs.       |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.20           | -  Run game    | 3.1.4          | Low            | Passed         |
|                |    executable. |                |                |                |
|                | -  On the Main |                |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”       |                |                |                |
|                | -  Use “WASD”  |                |                |                |
|                |    keys to     |                |                |                |
|                |    Navigate    |                |                |                |
|                |    through the |                |                |                |
|                |    initial     |                |                |                |
|                |    room.       |                |                |                |
|                | -  Find and    |                |                |                |
|                |    interact    |                |                |                |
|                |    with the    |                |                |                |
|                |    clue.       |                |                |                |
|                | -  Pick up the |                |                |                |
|                |    clue.       |                |                |                |
|                | -  Find the    |                |                |                |
|                |    NPC         |                |                |                |
|                |    assigned to |                |                |                |
|                |    that room.  |                |                |                |
|                | -  Click on    |                |                |                |
|                |    that NPC.   |                |                |                |
|                | -  Click on    |                |                |                |
|                |    the         |                |                |                |
|                |    “Question”  |                |                |                |
|                |    button.     |                |                |                |
|                | -  Ask the NPC |                |                |                |
|                |    a polite    |                |                |                |
|                |    question    |                |                |                |
|                | -  Ask the NPC |                |                |                |
|                |    an          |                |                |                |
|                |    aggressive  |                |                |                |
|                |    question    |                |                |                |
|                | -  Ensure that |                |                |                |
|                |    the two     |                |                |                |
|                |    responses   |                |                |                |
|                |    from the    |                |                |                |
|                |    NPC include |                |                |                |
|                |    one helpful |                |                |                |
|                |    response,   |                |                |                |
|                |    and one     |                |                |                |
|                |    unhelpful   |                |                |                |
|                |    response    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.21           | -  Run game    | 5.2.2          | High           | Passed         |
|                |    executable. |                |                |                |
|                | -  On the Main |                |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”       |                |                |                |
|                | -  Use “WASD”  |                |                |                |
|                |    keys to     |                |                |                |
|                |    Navigate    |                |                |                |
|                |    through the |                |                |                |
|                |    initial     |                |                |                |
|                |    room.       |                |                |                |
|                | -  Find the    |                |                |                |
|                |    clue        |                |                |                |
|                |    assigned to |                |                |                |
|                |    that room.  |                |                |                |
|                | -  Click on    |                |                |                |
|                |    the clue.   |                |                |                |
|                | -  Click on    |                |                |                |
|                |    the         |                |                |                |
|                |    ‘Journal’   |                |                |                |
|                |    button on   |                |                |                |
|                |    the status  |                |                |                |
|                |    bar at the  |                |                |                |
|                |    top of the  |                |                |                |
|                |    screen.     |                |                |                |
|                | -  Ensure that |                |                |                |
|                |    the clue    |                |                |                |
|                |    appears in  |                |                |                |
|                |    the         |                |                |                |
|                |    Journal.    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.22           | -  Run game    | 5.2.2          | Medium         | Passed         |
|                |    executable. |                |                |                |
|                | -  On the Main |                |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”       |                |                |                |
|                | -  Use “WASD”  |                |                |                |
|                |    keys to     |                |                |                |
|                |    Navigate    |                |                |                |
|                |    through the |                |                |                |
|                |    initial     |                |                |                |
|                |    room.       |                |                |                |
|                | -  Find the    |                |                |                |
|                |    NPC         |                |                |                |
|                |    assigned to |                |                |                |
|                |    that room.  |                |                |                |
|                | -  Click on    |                |                |                |
|                |    the NPC.    |                |                |                |
|                | -  Click on    |                |                |                |
|                |    the         |                |                |                |
|                |    “Question”  |                |                |                |
|                |    button and  |                |                |                |
|                |    pick a      |                |                |                |
|                |    questioning |                |                |                |
|                |    style.      |                |                |                |
|                | -  Return to   |                |                |                |
|                |    Map         |                |                |                |
|                | -  Click the   |                |                |                |
|                |    ‘Journal’   |                |                |                |
|                |    button.     |                |                |                |
|                | -  The journal |                |                |                |
|                |    displays    |                |                |                |
|                |    the         |                |                |                |
|                |    dialogue.   |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.23           | -  Run game    | 6.1.2          | Low            | Passed         |
|                |    executable. |                |                |                |
|                | -  On the Main |                |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”       |                |                |                |
|                | -  Make note   |                |                |                |
|                |    of current  |                |                |                |
|                |    score.      |                |                |                |
|                | -  Wait 5      |                |                |                |
|                |    seconds.    |                |                |                |
|                | -  Confirm the |                |                |                |
|                |    score has   |                |                |                |
|                |    reduced by  |                |                |                |
|                |    1 due to    |                |                |                |
|                |    the passage |                |                |                |
|                |    of time.    |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.24           | -  Run game    | 6.1.3          | Medium         | Passed         |
|                |    executable. |                |                |                |
|                | -  On the Main |                |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”       |                |                |                |
|                | -  Make note   |                |                |                |
|                |    of current  |                |                |                |
|                |    score.      |                |                |                |
|                | -  Use “WASD”  |                |                |                |
|                |    keys to     |                |                |                |
|                |    Navigate    |                |                |                |
|                |    through the |                |                |                |
|                |    initial     |                |                |                |
|                |    room.       |                |                |                |
|                | -  Find the    |                |                |                |
|                |    NPC         |                |                |                |
|                |    assigned to |                |                |                |
|                |    that room.  |                |                |                |
|                | -  Click on    |                |                |                |
|                |    the NPC.    |                |                |                |
|                | -  Accuse the  |                |                |                |
|                |    NPC         |                |                |                |
|                |    wrongly.    |                |                |                |
|                | -  Confirm the |                |                |                |
|                |    score has   |                |                |                |
|                |    decreased.  |                |                |                |
+----------------+----------------+----------------+----------------+----------------+
| 2.25           | -  Run game    | 6.1.4          | Medium         | Passed         |
|                |    executable. |                |                |                |
|                | -  On the Main |                |                |                |
|                |    Menu click  |                |                |                |
|                |    on “New     |                |                |                |
|                |    Game”.      |                |                |                |
|                | -  On the      |                |                |                |
|                |    Narrator    |                |                |                |
|                |    Screen      |                |                |                |
|                |    click on    |                |                |                |
|                |    “Start      |                |                |                |
|                |    Game”       |                |                |                |
|                | -  Make note   |                |                |                |
|                |    of current  |                |                |                |
|                |    score.      |                |                |                |
|                | -  Use “WASD”  |                |                |                |
|                |    keys to     |                |                |                |
|                |    Navigate    |                |                |                |
|                |    through the |                |                |                |
|                |    initial     |                |                |                |
|                |    room.       |                |                |                |
|                | -  Find the    |                |                |                |
|                |    NPC         |                |                |                |
|                |    assigned to |                |                |                |
|                |    that room.  |                |                |                |
|                | -  Click on    |                |                |                |
|                |    the NPC.    |                |                |                |
|                | -  Question    |                |                |                |
|                |    the NPC.    |                |                |                |
|                | -  Confirm the |                |                |                |
|                |    score       |                |                |                |
|                |    has decreas |                |                |                |
|                | ed.            |                |                |                |
+----------------+----------------+----------------+----------------+----------------+