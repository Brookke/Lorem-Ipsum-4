E: Unit Tests
========================

Below is a table of the unit tests included within this project.

The unit tests are associated with an appropriate requirement to allow
for traceability, and the tests aim to check that the code works for any
associated requirements. Not all requirements have associated tests, and
vice versa - this is because some requirements cannot be explicitly unit
tested, and some tests do not link up directly to a requirement, but are
still needed to ensure the code functions as intended.

There is a criticality measure against each test, for both acceptance
and unit tests - this is to represent how important the test is to the
overall function of the code. Criticality is on a scale - high
criticality means that if that test fails, the project will not function
at all; low criticality means that if the test fails, the project will
still mostly function as intended.

+------------+------------+------------+------------+------------+------------+------------+
| ID         | Test Name  | Purpose    | Criticalit | Class      | Req ID     | Result     |
|            |            |            | y          |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.1        | constructor| Verifies   | High       | GUIControl | 1.1.4      | Passed     |
|            |            | GUI is     |            | ler\_Test  |            |            |
|            |            | constructe |            |            |            |            |
|            |            | d          |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.2        | screenCanB | Verifies   | Medium     | GUIControl | 1.1.4      | Passed     |
|            | eSet       | that a     |            | ler\_Test  |            |            |
|            |            | screen     |            |            |            |            |
|            |            | from the   |            |            |            |            |
|            |            | game can   |            |            |            |            |
|            |            | be set as  |            |            |            |            |
|            |            | the        |            |            |            |            |
|            |            | current    |            |            |            |            |
|            |            | screen     |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.3        | screenCanB | Verifies   | Medium     | GUIControl | 1.1.4      | Passed     |
|            | eChanged   | screen can |            | ler\_Test  |            |            |
|            |            | be changed |            |            |            |            |
|            |            | to a       |            |            |            |            |
|            |            | different  |            |            |            |            |
|            |            | screen     |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.4        | getTime    | Verifies   | Medium     | GameSnapsh | 6.1.2      | Passed     |
|            |            | the game   |            | ot\_Test   |            |            |
|            |            | returns    |            |            |            |            |
|            |            | the        |            |            |            |            |
|            |            | correct    |            |            |            |            |
|            |            | time value |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.5        | getRooms   | Verifies   | High       | GameSnapsh | 4.1.1      | Passed     |
|            |            | the game   |            | ot\_Test   |            |            |
|            |            | returns    |            |            |            |            |
|            |            | the        |            |            |            |            |
|            |            | correct    |            |            |            |            |
|            |            | list of    |            |            |            |            |
|            |            | rooms      |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.6        | getClues   | Verifies   | High       | GameSnapsh | 5          | Passed     |
|            |            | the game   |            | ot\_Test   |            |            |
|            |            | returns    |            |            |            |            |
|            |            | the        |            |            |            |            |
|            |            | correct    |            |            |            |            |
|            |            | list of    |            |            |            |            |
|            |            | clues      |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.7        | getClue    | Verifies   | Low        | Journal\_T | 5.2.1      | Passed     |
|            |            | that the   |            | est        |            |            |
|            |            | list held  |            |            |            |            |
|            |            | by the     |            |            |            |            |
|            |            | journal    |            |            |            |            |
|            |            | contains   |            |            |            |            |
|            |            | clues in   |            |            |            |            |
|            |            | the right  |            |            |            |            |
|            |            | format     |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.8        | addClue    | Verifies   | Medium     | Journal\_T | 5.2.1      | Passed     |
|            |            | that clues |            | est        |            |            |
|            |            | are added  |            |            |            |            |
|            |            | to the     |            |            |            |            |
|            |            | journal    |            |            |            |            |
|            |            | properly   |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.9        | addConvers | Verifies   | Low        | Journal\_T | 7          | Passed     |
|            | ation      | that       |            | est        |            |            |
|            |            | conversati |            |            |            |            |
|            |            | on         |            |            |            |            |
|            |            | snippets   |            |            |            |            |
|            |            | are added  |            |            |            |            |
|            |            | in the     |            |            |            |            |
|            |            | correct    |            |            |            |            |
|            |            | format     |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.10       | getConvers | Verifies   | High       | Journal\_T | 7          | Passed     |
|            | ation      | that       |            | est        |            |            |
|            |            | getConvers |            |            |            |            |
|            |            | ation      |            |            |            |            |
|            |            | returns    |            |            |            |            |
|            |            | all the    |            |            |            |            |
|            |            | conversati |            |            |            |            |
|            |            | on         |            |            |            |            |
|            |            | snippets   |            |            |            |            |
|            |            | that it is |            |            |            |            |
|            |            | currently  |            |            |            |            |
|            |            | holding    |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.11       | SetRoomGet | Verifies   | Medium     | MapEntity\ | 4          | Passed     |
|            | Room       | that a     |            | _Test      |            |            |
|            |            | room, once |            |            |            |            |
|            |            | set, is    |            |            |            |            |
|            |            | returned   |            |            |            |            |
|            |            | in the     |            |            |            |            |
|            |            | correct    |            |            |            |            |
|            |            | format.    |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.12       | getName    | Verifies   | Low        | MapEntity\ | -          | Passed     |
|            |            | that a map |            | _Test      |            |            |
|            |            | entity     |            |            |            |            |
|            |            | returns    |            |            |            |            |
|            |            | its name   |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.13       | genDescrip | Verifies   | Low        | MapEntity\ | -          | Passed     |
|            | tion       | that a map |            | _Test      |            |            |
|            |            | entity     |            |            |            |            |
|            |            | returns    |            |            |            |            |
|            |            | its        |            |            |            |            |
|            |            | descriptio |            |            |            |            |
|            |            | n          |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.14       | getTexture | Verifies   | Medium     | MapEntity\ | -          | Passed     |
|            |            | that a map |            | _Test      |            |            |
|            |            | entity     |            |            |            |            |
|            |            | sets its   |            |            |            |            |
|            |            | texture    |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.15       | setSpeech  | Verifies   | Low        | NarratorSc | 7.1.7      | Passed     |
|            |            | that the   |            | reen\_Test |            |            |
|            |            | narrator   |            |            |            |            |
|            |            | screen     |            |            |            |            |
|            |            | text can   |            |            |            |            |
|            |            | be set     |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.16       | updateSpee | Verifies   | Low        | NarratorSc | 7.1.7      | Passed     |
|            | ch         | that the   |            | reen\_Test |            |            |
|            |            | narrator   |            |            |            |            |
|            |            | screen     |            |            |            |            |
|            |            | text can   |            |            |            |            |
|            |            | be updated |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.17       | getTransit | Verifies   | High       | Room\_Test | 2.1.4      | Passed     |
|            | ion        | that the   |            |            |            |            |
|            |            | player     |            |            |            |            |
|            |            | transition |            |            |            |            |
|            |            | s          |            |            |            |            |
|            |            | between    |            |            |            |            |
|            |            | rooms      |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.18       | addTransit | Verifies   | High       | Room\_Test | 2.1.4      | Passed     |
|            | ion        | that       |            |            |            |            |
|            |            | adding new |            |            |            |            |
|            |            | transition |            |            |            |            |
|            |            | s          |            |            |            |            |
|            |            | between    |            |            |            |            |
|            |            | rooms is   |            |            |            |            |
|            |            | correctly  |            |            |            |            |
|            |            | implemente |            |            |            |            |
|            |            | d          |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.19       | walkable   | Verifies   | High       | Room\_Test | -          | Passed     |
|            |            | that       |            |            |            |            |
|            |            | walkable   |            |            |            |            |
|            |            | tiles are  |            |            |            |            |
|            |            | walkable   |            |            |            |            |
|            |            | and tiles  |            |            |            |            |
|            |            | that       |            |            |            |            |
|            |            | aren’t     |            |            |            |            |
|            |            | walkable   |            |            |            |            |
|            |            | aren’t     |            |            |            |            |
|            |            | walkable   |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.20       | trigger    | Verifies   | High       | Room\_Test | -          | Passed     |
|            |            | that a     |            |            |            |            |
|            |            | trigger    |            |            |            |            |
|            |            | tile is    |            |            |            |            |
|            |            | triggerabl |            |            |            |            |
|            |            | e,         |            |            |            |            |
|            |            | and one    |            |            |            |            |
|            |            | that isn’t |            |            |            |            |
|            |            | triggerabl |            |            |            |            |
|            |            | e          |            |            |            |            |
|            |            | isn’t      |            |            |            |            |
|            |            | triggerabl |            |            |            |            |
|            |            | e          |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.21       | matRotatio | Verifies   | Low        | Room\_Test | 2.1.4      | Passed     |
|            | n          | that       |            |            |            |            |
|            |            | doormats   |            |            |            |            |
|            |            | are        |            |            |            |            |
|            |            | orientated |            |            |            |            |
|            |            | the        |            |            |            |            |
|            |            | correct    |            |            |            |            |
|            |            | way        |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.22       | distribute | Verifies   | Medium     | ScenarioBu | 5.1.1      | Passed     |
|            | RoomsGiveC | that the   |            | ilder\_Tes |            |            |
|            | lues       | distribute |            | t          |            |            |
|            |            | Clues()    |            |            |            |            |
|            |            | method     |            |            |            |            |
|            |            | distribute |            |            |            |            |
|            |            | s          |            |            |            |            |
|            |            | clues so   |            |            |            |            |
|            |            | that there |            |            |            |            |
|            |            | is at      |            |            |            |            |
|            |            | minimum 1  |            |            |            |            |
|            |            | clue per   |            |            |            |            |
|            |            | room       |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.23       | distribute | Verifies   | Medium     | ScenarioBu | 5.1.1      | Passed     |
|            | CluesDiffR | that the   |            | ilder\_Tes |            |            |
|            | ooms       | clues      |            | t          |            |            |
|            |            | aren’t     |            |            |            |            |
|            |            | being      |            |            |            |            |
|            |            | given to   |            |            |            |            |
|            |            | the same   |            |            |            |            |
|            |            | room       |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.24       | generateMo | Verifies   | Low        | ScenarioBu | 5.1.3      | Passed     |
|            | tives      | that the   |            | ilder\_Tes |            |            |
|            |            | motive     |            | t          |            |            |
|            |            | clue is    |            |            |            |            |
|            |            | split into |            |            |            |            |
|            |            | 3 equal    |            |            |            |            |
|            |            | parts      |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.25       | modifyScor | Verifies   | Low        | Scoring\_T | 6          | Passed     |
|            | eAddition  | that the   |            | est        |            |            |
|            |            | correct    |            |            |            |            |
|            |            | score is   |            |            |            |            |
|            |            | added to   |            |            |            |            |
|            |            | the        |            |            |            |            |
|            |            | current    |            |            |            |            |
|            |            | score      |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.26       | modifyScor | Verifies   | Low        | Scoring\_T | 6          | Passed     |
|            | eSubtracti | that the   |            | est        |            |            |
|            | on         | correct    |            |            |            |            |
|            |            | score is   |            |            |            |            |
|            |            | subtracted |            |            |            |            |
|            |            | from the   |            |            |            |            |
|            |            | current    |            |            |            |            |
|            |            | score      |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.27       | updateScor | Verifies   | Low        | Scoring\_T | 6.1.2      | Passed     |
|            | eNoDecrease| that the   |            | est        |            |            |
|            |            | score      |            |            |            |            |
|            |            | doesn’t    |            |            |            |            |
|            |            | change if  |            |            |            |            |
|            |            | the time   |            |            |            |            |
|            |            | passing is |            |            |            |            |
|            |            | less than  |            |            |            |            |
|            |            | 5 seconds  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.28       | updateScor | Verifies   | Low        | Scoring\_T | 6.1.2      | Passed     |
|            | eHasDecrea | that the   |            | est        |            |            |
|            | se         | the score  |            |            |            |            |
|            |            | decreases  |            |            |            |            |
|            |            | by 1 after |            |            |            |            |
|            |            | 5 seconds  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.29       | getInfo    | Verifies   | High       | Clue\_Test | 5          | Passed     |
|            |            | that all   |            |            |            |            |
|            |            | all parts  |            |            |            |            |
|            |            | of the     |            |            |            |            |
|            |            | clue are   |            |            |            |            |
|            |            | stored     |            |            |            |            |
|            |            | corretly   |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.30       | isMotive   | Verifies   | High       | Clue\_Test | 5.1.3      | Passed     |
|            |            | that       |            |            |            |            |
|            |            | motive     |            |            |            |            |
|            |            | clues      |            |            |            |            |
|            |            | return     |            |            |            |            |
|            |            | true as a  |            |            |            |            |
|            |            | motive     |            |            |            |            |
|            |            | clue and   |            |            |            |            |
|            |            | false if   |            |            |            |            |
|            |            | they are   |            |            |            |            |
|            |            | not motive |            |            |            |            |
|            |            | clues      |            |            |            |            |
|            |            |            |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.31       | isMeans    | Verifies   | High       | Clue\_Test | 5          | Passed     |
|            |            | that means |            |            |            |            |
|            |            | clues      |            |            |            |            |
|            |            | return     |            |            |            |            |
|            |            | true as a  |            |            |            |            |
|            |            | motive     |            |            |            |            |
|            |            | clue and   |            |            |            |            |
|            |            | false if   |            |            |            |            |
|            |            | they are   |            |            |            |            |
|            |            | not means  |            |            |            |            |
|            |            | clues.     |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.32       | constructo | Verifies   | Low        | Dialogue\_ | 7          | Passed     |
|            | rValidatio | that given |            | Test       |            |            |
|            | nFail      | a false    |            |            |            |            |
|            |            | .JSON      |            |            |            |            |
|            |            | file, the  |            |            |            |            |
|            |            | game       |            |            |            |            |
|            |            | encounters |            |            |            |            |
|            |            | “JSON not  |            |            |            |            |
|            |            | being      |            |            |            |            |
|            |            | verified”  |            |            |            |            |
|            |            | error.     |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.33       | constructo | Verifies   | High       | Dialogue\_ | 7          | Passed     |
|            | r2Validati | that given |            | Test       |            |            |
|            | onPass     | a valid    |            |            |            |            |
|            |            | .JSON file |            |            |            |            |
|            |            | no error   |            |            |            |            |
|            |            | is thrown. |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.34       | getUsingCl | Verifies   | High       | Dialogue\_ | 7          | Passed     |
|            | ue         | that when  |            | Test       |            |            |
|            |            | given a    |            |            |            |            |
|            |            | clue, the  |            |            |            |            |
|            |            | dialogue   |            |            |            |            |
|            |            | getter     |            |            |            |            |
|            |            | returns a  |            |            |            |            |
|            |            | valid      |            |            |            |            |
|            |            | response.  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.35       | getUsingSt | Verifies   | High       | Dialogue\_ | 7          | Passed     |
|            | ring       | that when  |            | Test       |            |            |
|            |            | given a    |            |            |            |            |
|            |            | string,    |            |            |            |            |
|            |            | the        |            |            |            |            |
|            |            | dialogue   |            |            |            |            |
|            |            | getter     |            |            |            |            |
|            |            | returns a  |            |            |            |            |
|            |            | valid      |            |            |            |            |
|            |            | response.  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.36       | aStar      | Verifies   | Low        | Player\_Te | 2          | Passed     |
|            |            | that the   |            | st         |            |            |
|            |            | A-Star     |            |            |            |            |
|            |            | algorithm  |            |            |            |            |
|            |            | produces   |            |            |            |            |
|            |            | the        |            |            |            |            |
|            |            | correct    |            |            |            |            |
|            |            | result.    |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.37       | testPlayer | Verifies   | Low        | Player\_Te | -          | Passed     |
|            | Name       | the player |            | st         |            |            |
|            |            | name is    |            |            |            |            |
|            |            | returned   |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.38       | init       | Verifies   | High       | Suspect\_T | 3          | Passed     |
|            |            | that the   |            | est        |            |            |
|            |            | NPCs holds |            |            |            |            |
|            |            | data about |            |            |            |            |
|            |            | themselves |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.39       | hasBeenAcc | Verifies   | Low        | Suspect\_T | 7.1.9      | Passed     |
|            | used       | that once  |            | est        |            |            |
|            |            | a NPC who  |            |            |            |            |
|            |            | isn’t the  |            |            |            |            |
|            |            | murderer   |            |            |            |            |
|            |            | has been   |            |            |            |            |
|            |            | accused,   |            |            |            |            |
|            |            | the NPC    |            |            |            |            |
|            |            | records    |            |            |            |            |
|            |            | that they  |            |            |            |            |
|            |            | have been  |            |            |            |            |
|            |            | accused.   |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.40       | setPositio | Verifies   | High       | Suspect\_T | 3          | Passed     |
|            | n          | that the   |            | est        |            |            |
|            |            | position   |            |            |            |            |
|            |            | of a NPC   |            |            |            |            |
|            |            | can be     |            |            |            |            |
|            |            | successful |            |            |            |            |
|            |            | ly         |            |            |            |            |
|            |            | set.       |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.41       | setKiller  | Verifies   | High       | Suspect\_T | 3.1.5      | Passed     |
|            |            | that the   |            | est        |            |            |
|            |            | setKiller  |            |            |            |            |
|            |            | method     |            |            |            |            |
|            |            | successful |            |            |            |            |
|            |            | ly         |            |            |            |            |
|            |            | sets the   |            |            |            |            |
|            |            | NPC as the |            |            |            |            |
|            |            | killer.    |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.42       | setVictim  | Verifies   | High       | Suspect\_T | 3.1.5      | Passed     |
|            |            | that the   |            | est        |            |            |
|            |            | setKiller  |            |            |            |            |
|            |            | method     |            |            |            |            |
|            |            | successful |            |            |            |            |
|            |            | ly         |            |            |            |            |
|            |            | sets the   |            |            |            |            |
|            |            | NPC as the |            |            |            |            |
|            |            | victim.    |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
