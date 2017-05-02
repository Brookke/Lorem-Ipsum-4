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
| 1.01       | testName   | Verifies   | Low        | ClueUnitTe | 5          | Passed     |
|            |            | the name   |            | st         |            |            |
|            |            | of a clue  |            |            |            |            |
|            |            | has been   |            |            |            |            |
|            |            | set        |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.02       | testDescri | Verifies   | Low        | ClueUnitTe | 5          | Passed     |
|            | ption      | the        |            | st         |            |            |
|            |            | descriptio |            |            |            |            |
|            |            | n          |            |            |            |            |
|            |            | of the     |            |            |            |            |
|            |            | clue has   |            |            |            |            |
|            |            | been set   |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.03       | testTileCo | Verifies   | High       | ClueUnitTe | 5.1.1      | Passed     |
|            | ordinates  | the        |            | st         |            |            |
|            |            | location   |            |            |            |            |
|            |            | of the     |            |            |            |            |
|            |            | clue has   |            |            |            |            |
|            |            | been set   |            |            |            |            |
|            |            | as         |            |            |            |            |
|            |            | expected   |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.04       | testEquali | Verifies   | Medium     | ClueUnitTe | 5          | Passed     |
|            | ty         | that       |            | st         |            |            |
|            |            | identical  |            |            |            |            |
|            |            | clues are  |            |            |            |            |
|            |            | considered |            |            |            |            |
|            |            | equal      |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.05       | testMurder | Verifies   | High       | ClueUnitTe | 5.1.4      | Passed     |
|            | Weapon     | that a     |            | st         |            |            |
|            |            | murder     |            |            |            |            |
|            |            | weapon has |            |            |            |            |
|            |            | been       |            |            |            |            |
|            |            | chosen     |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.06       | testRedHer | Verifies   | Low        | ClueUnitTe | 5          | Passed     |
|            | ring       | that Red   |            | st         |            |            |
|            |            | Herrings   |            |            |            |            |
|            |            | have been  |            |            |            |            |
|            |            | chosen     |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.07       | testFinish | Verifies   | High       | GameSnapsh | 2.1.5      | Passed     |
|            | Interactio | that the   |            | otTests    |            |            |
|            | n          | game       |            |            |            |            |
|            |            | snapshot   |            |            |            |            |
|            |            | class      |            |            |            |            |
|            |            | keeps      |            |            |            |            |
|            |            | track of   |            |            |            |            |
|            |            | how many   |            |            |            |            |
|            |            | interactio |            |            |            |            |
|            |            | ns         |            |            |            |            |
|            |            | the        |            |            |            |            |
|            |            | associated |            |            |            |            |
|            |            | player has |            |            |            |            |
|            |            | left in    |            |            |            |            |
|            |            | their      |            |            |            |            |
|            |            | current    |            |            |            |            |
|            |            | turn       |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.08       | testGetNPC | Verifies   | High       | GameSnapsh | 3          | Passed     |
|            | s          | that data  |            | otTests    |            |            |
|            |            | stored on  |            |            |            |            |
|            |            | NPCs is    |            |            |            |            |
|            |            | stored and |            |            |            |            |
|            |            | retrieved  |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.09       | testGetNam | Verifies   | Low        | NPCUnitTes | 3          | Passed     |
|            | e          | that the   |            | ts         |            |            |
|            |            | name       |            |            |            |            |
|            |            | stored and |            |            |            |            |
|            |            | retrieved  |            |            |            |            |
|            |            | for the    |            |            |            |            |
|            |            | NPC is     |            |            |            |            |
|            |            | correct    |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.10       | testPerson | Verifies   | Medium     | NPCUnitTes | 3          | Passed     |
|            | ality      | that the   |            | ts         |            |            |
|            |            | personalit |            |            |            |            |
|            |            | y          |            |            |            |            |
|            |            | stored and |            |            |            |            |
|            |            | retrieved  |            |            |            |            |
|            |            | for the    |            |            |            |            |
|            |            | NPC is     |            |            |            |            |
|            |            | correct    |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.11       | testIntera | Verifies   | High       | PlayerUnit | 5.1.2      | Passed     |
|            | ctFindingC | that the   |            | Tests      |            |            |
|            | lues       | correct    |            |            |            |            |
|            |            | clue is    |            |            |            |            |
|            |            | correctly  |            |            |            |            |
|            |            | collected  |            |            |            |            |
|            |            | when       |            |            |            |            |
|            |            | interacted |            |            |            |            |
|            |            | ,          |            |            |            |            |
|            |            | also       |            |            |            |            |
|            |            | correctly  |            |            |            |            |
|            |            | altering   |            |            |            |            |
|            |            | the score  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.12       | testPlayer | Verifies   | Low        | PlayerUnit | 2.1.5      | Passed     |
|            | Name       | that the   |            | Tests      |            |            |
|            |            | playerName |            |            |            |            |
|            |            | is stored  |            |            |            |            |
|            |            | and        |            |            |            |            |
|            |            | returned   |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.13       | testPlayer | Verifies   | Medium     | PlayerUnit | 2.1.1      | Passed     |
|            | Personalit | that the   |            | Tests      |            |            |
|            | y          | players    |            |            |            |            |
|            |            | personalit |            |            |            |            |
|            |            | y          |            |            |            |            |
|            |            | can be     |            |            |            |            |
|            |            | manipulate |            |            |            |            |
|            |            | d          |            |            |            |            |
|            |            | and stored |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.14       | doesPlayer | Verifies   | High       | PlayerUnit | 2.1.4      | Passed     |
|            | Move       | that the   |            | Tests      |            |            |
|            |            | player is  |            |            |            |            |
|            |            | able to    |            |            |            |            |
|            |            | move       |            |            |            |            |
|            |            | correctly  |            |            |            |            |
|            |            | in all     |            |            |            |            |
|            |            | four       |            |            |            |            |
|            |            | cardinal   |            |            |            |            |
|            |            | directions |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.15       | testCanAcc | Verifies   | Low        | PlayerUnit | 7.1.4      | Passed     |
|            | use        | that the   |            | Tests      |            |            |
|            |            | player is  |            |            |            |            |
|            |            | not able   |            |            |            |            |
|            |            | to accuse  |            |            |            |            |
|            |            | without    |            |            |            |            |
|            |            | evidence   |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.16       | testScore  | Verifies   | Medium     | PlayerUnit | 6.1.1      | Passed     |
|            |            | that the   |            | Tests      |            |            |
|            |            | players    |            |            |            |            |
|            |            | score can  |            |            |            |            |
|            |            | be         |            |            |            |            |
|            |            | modified   |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.17       | testPlayTi | Verifies   | Low        | PlayerUnit | 6.1.2      | Passed     |
|            | me         | that how   |            | Tests      |            |            |
|            |            | long a     |            |            |            |            |
|            |            | player has |            |            |            |            |
|            |            | played for |            |            |            |            |
|            |            | is stored  |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.18       | testGetTra | Verifies   | High       | RoomUnitTe | 2.1.4      | Passed     |
|            | nsition    | that the   |            | sts        |            |            |
|            |            | player     |            |            |            |            |
|            |            | transition |            |            |            |            |
|            |            | s          |            |            |            |            |
|            |            | between    |            |            |            |            |
|            |            | rooms      |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.19       | testAddTra | Verifies   | High       | RoomUnitTe | 2.1.4      | Passed     |
|            | nsition    | that new   |            | sts        |            |            |
|            |            | transition |            |            |            |            |
|            |            | s          |            |            |            |            |
|            |            | are added  |            |            |            |            |
|            |            | correctly  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.20       | testWalkab | Verifies   | Medium     | RoomUnitTe | 2.1.4      | Passed     |
|            | le         | certain    |            | sts        |            |            |
|            |            | tiles are  |            |            |            |            |
|            |            | and aren’t |            |            |            |            |
|            |            | walkable   |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.21       | testTrigge | Verifies   | High       | RoomUnitTe | 2.1.4      | Passed     |
|            | r          | if a tile  |            | sts        |            |            |
|            |            | a trigger  |            |            |            |            |
|            |            | tile or    |            |            |            |            |
|            |            | not        |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.22       | testMatRot | Verifies   | Low        | RoomUnitTe | 2.14       | Passed     |
|            | ation      | that mats  |            | sts        |            |            |
|            |            | are        |            |            |            |            |
|            |            | rotated to |            |            |            |            |
|            |            | the        |            |            |            |            |
|            |            | correct    |            |            |            |            |
|            |            | direction  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
| 1.23       | testGenera | Verifies   | High       | ScenarioBu | 1          | Passed     |
|            | teGame     | that the   |            | ilderUnitT |            |            |
|            |            | scenario   |            | est        |            |            |
|            |            | builder    |            |            |            |            |
|            |            | runs       |            |            |            |            |
|            |            | without    |            |            |            |            |
|            |            | generating |            |            |            |            |
|            |            | an         |            |            |            |            |
|            |            | exception  |            |            |            |            |
+------------+------------+------------+------------+------------+------------+------------+
