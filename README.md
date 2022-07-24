# Getting Started
Football World Cup Score Board that shows matches and scores.

### Reference Documentation
For further reference, please consider the following sections:

* Prerequisites -> Gradle, java 11.
* Run the step './gradlew build'.

### Additional Info
The board supports the following operations:
1. Start a game. When a game starts, it should capture (being initial score 0 – 0):

   a. Home team
   b. Away team
2. Finish game. It will remove a match from the scoreboard.
3. Update score. Receiving the pair score; home team score and away team score
   updates a game score.
4. Get a summary of games by total score. Those games with the same total score will
   be returned ordered by the most recently added to our system.

### Assumption