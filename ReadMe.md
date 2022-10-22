# Eyoel's Connect Four Game

Runs a simple, text-based game of connect four in the terminal that can 
be played by two players on the same keyboard

## Description

For Connect Four's rules see: https://www.gamesver.com/the-rules-of-connect-4-according-to-m-bradley-hasbro/
<br><br>
In this implementation two players play on the same keyboard and are labled player 1 and 2 (player 1 being the player that plays first). The current board can be saved and the game quitted during any player's turn. At the beginning of the game, you are given the choice of loading a saved game from a user-specified file. 

### Dependencies

* JDK 11
* Gradle 7.5.1
* JUnit 4.13



### Executing program

* ```cd``` into the directory within the project folder containing build.gradle in the case of this game it should be in the A2 directory
* run ```gradle clean build```
* if errors are encountered, refer to gradle documentation
* if no errors are encountered run ```java -cp build/classes/java/main connectfour.ConnectFour```
* the first line of out put should be "Welcome to Eyoel's Connect Four game!" 

## Limitations

Unfortunately, there is no functionality to undo moves or to clear the game board without restarting the game with a new board. It is also impossible to load a saved board in the middle of a game.

## Author Information

Author: Eyoel Matiwos  
Email: ematiwos@ouguelph.ca


