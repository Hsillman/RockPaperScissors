# RockPaperScissors
This is a rock paper scissors game written with Java11 and build using Java Spring Boot v2.5.4. Java Spring Boot (Spring Boot) is a tool that makes developing web application and microservices with Spring Framework faster and easier. The framework provides an embedded tomcat, which will be used to make the requests on its default port 8080. 
The game can be played via command line, however, using Postman will make the experience better.

In this game, the player decides the strategy his/her opponent will use. 
For this, there are two strategies to choose from, **RANDOM** or **ROCKS**.

## Deploy the application locally
Clone the project and go to the target folder. In the folder, you will se the JAR file. Open the bash and execute the following command:

    <YOUR_JAVA_PATH> -jar rpsgame-0.0.1-SNAPSHOT.jar

Your application will start at localhost:8080/api/game

## Deploy the application on Google Cloud
This approach is ideal for playing with people that are not in the same network.
In order to deploy the application on google cloud, please follow the instructions provided by Google: https://codelabs.developers.google.com/codelabs/cloud-app-engine-springboot#0

Please note that, you don't need to follow the **"Create a new Spring Boot web app"** step. Also, the step to update the pom.xml file can be skipped since the project has that dependency already. 

In the **"Add a controller"** step, instead of creating the code that is shown, you will need to upload the folder that contains all the files of the project, by using the cloud shell. Here is a quick tutorial on how to upload a folder/files using the shell: https://cloud.google.com/shell/docs/uploading-and-downloading-files

Once the project has been uploaded, go to the folder that contains the **mvnw** file so you can run the commands shown in the google tutorial. If by any chance, you get the error **"Could not find or load main class org.apache.maven.wrapper.MavenWrapperMain"** just simply execute the following command in the root folder of your project:
 
    mvn -N io.takari:maven:wrapper

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
Before you continue, it is very important that you watch the video tutorial on how to play the game using Postman. The same idea will be applied here, so it is important that you understand how the game works.

If you are not using localhost just simply change the IP to where your application is running.

Here is a list of commands you need to know in order to play the game:

- GET method  to see the game that is going on
 
       curl http://localhost:8080/api/game
       
- POST method to create new game. **"opponentStrategy"** must be RANDOM or ROCKS
                                              
      curl -X POST http://localhost:8080/api/game -H 'Content-Type: application/json' -d '{"name":"hugo","opponentStrategy":"ROCKS","numberOfRounds":"3"}' | json_pp
    
- POST method to join a game with id = {id}. **"opponentStrategy"** must be RANDOM or ROCKS

      curl -X POST http://localhost:8080/api/game/{id}/join -H 'Content-Type: application/json' -d '{"name":"maria","opponentStrategy":"RANDOM","numberOfRounds":"3"}' | json_pp
      
- POST method for player with name = {name} to make a play on the game with id = {id}

      curl -X POST http://localhost:8080/api/game/{id}/{name}/play  | json_pp
      
- POST method to end a game with id = {id}

      curl -X POST http://localhost:8080/api/game/{id}/end
