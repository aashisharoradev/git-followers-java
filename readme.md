# Git Hub Follower

Project to use follow api for the github

### Prerequisites

What things you need to install the software and how to install them

```
setup gradle
java version 1.12
```
## Getting Started

This project is a pass through to get git hub user followers. To use this project update your API_TOKEN in common/constants.js. Github allows you to use the api without token. But there is a limit to use apis for more information refer https://developer.github.com/v3/#rate-limiting

### Installing

A step by step series to run the test casee

Say what the step will be

```
cd %PROJECT_HOME%/
./gradlew clean build

```
### How to use API
run
```
./gradlew run
```

Use any http client such as curl, postman etc
http://localhost:3000/followers/:githubuser?level<level>
API gives the flexibility to change the level from 3 to any level. API response depends on the response from github follow api.