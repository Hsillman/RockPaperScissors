# RockPaperScissors
This is a rock paper scissors game build using Java Spring Boot v2.5.4. Java Spring Boot (Spring Boot) is a tool that makes developing web application and microservices with Spring Framework faster and easier. The framework provides an embedded tomcat, which will be used to make the requests on its default port 8080. 
The game can be played via command line, however, using Postman will make the experience better.

In this game, the player decides the strategy his/her opponent will use. 
For this, there are two strategies to choose from, **RANDOM** or **ROCKS**.

## Deploy the application locally

## Deploy the application on Google Cloud
This approach is ideal for playing with people that are not in the same network. 


## How to play the game using Postman
After you deployed the application, please refer to my quick tutorial on how to play the game using Postman : https://www.youtube.com/watch?v=5FXob-goDxQ

The tutorial won't cover some cases. For example: 
- What happens when you try to join a game that does not exist? 
- Can a player with the attribute **"numberOfRounds":3** join a game that has the number of rounds set to 2? 
- Can a player make a play twice in a row? 
- Can you end a game before it is actually finished?
- Can a player named John make a play in a game he is not part of?
- Can you create another game without finishing one?

These and other cases are covered by the code. So, go ahead and try to simulate them to see what happens.


## How to play the game using Git Bash
