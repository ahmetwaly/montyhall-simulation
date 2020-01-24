<h1 align="center"> MontyHall Simulation </h1> <br>

## Table of Contents

- [Introduction](#introduction)
- [Assumptions](#assumptions)
- [run application](#run)
- [test application](#test)



## Introduction

  - Montyhall game simulation as described in http://en.wikipedia.org/wiki/Monty_Hall_problem .
  - the game accpets number of rounds as parameter and has 3 simulation moods :-
    - STICK --> which means that player will always stick to his choice
    - SWAP --> which means that player will always swap to his choice.
    - ALTERNATE --> which means that player will change his mind one stick and one swap.
 - the API endpoint is documented with swagger on URL http://localhost:8080/swagger-ui.html 	
  
## Assumptions  
- Every single round, the player is decided to be winner or not.
- This API is configured to run many game rounds per single call based on the user input.
- User can request to run as many game rounds as he wants with a specific game mode. For example, user can send a request to simulate 1000 game rounds with STICK mode.

## run
**run tests using maven**
```console
cd montyhall-simulation
mvn test
```

**run the application using maven**
```console
cd montyhall-simulation
mvn spring-boot:run
```


## test from console
**ALTERNATE Mode**

```console
curl -X POST "http://localhost:8080/game/start" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"simulationMode\": \"ALTERNATE\", \"simulationRounds\": 1000}"
```
**SWAP Mode**

```console
curl -X POST "http://localhost:8080/game/start" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"simulationMode\": \"SWAP\", \"simulationRounds\": 1000}"
```
**STICK Mode**

```console
curl -X POST "http://localhost:8080/game/start" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"simulationMode\": \"STICK\", \"simulationRounds\": 1000}"
```
