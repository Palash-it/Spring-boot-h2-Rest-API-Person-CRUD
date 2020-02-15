# Spring-boot-h2-Rest-API-Person-CRUD
as a part of a task i made this REST API

## Getting Started

These instructions will describe in details how to build,test,deploy and run this repository on your local/live machine.
Available REST Endpoints
| Sl  |   API Name    | HTTTP Method |                Path                 |     Status code                       |              Description             |
| --- | ------------- | ------------ | ----------------------------------- | ------------------------------------- | ------------------------------------ |
|  1  | GET persons   |     GET      | /person-service/api/v1/persons      |     200 (OK)                          | all persons resource will be fetched |
|  2  | POST person   |     POST     | /person-service/api/v1/persons      |     201 (Created)                     | new person will be created           |
|  3  | GET person    |     GET      | /person-service/api/v1/persons/{id} |     200 (OK)                          | One person will be fetched           |
|  4  | PUT person    |     PUT      | /person-service/api/v1/persons/{id} |     200 (OK)                          | One person will be updated           |
|  5  | DELETE person |     DELETE   | /person-service/api/v1/persons/{id} |     200 (OK)/404 (Resource Not Found) | One person will be delete if present |

### Prerequisites

```
Java 1.8 and maven
```

### Installing

To create a executable .war file run bellow maven command. Generated war file can be run using java -jar filename and as well as can upload war file inside a tomcat container to run on live server

```
mvn clean install
```

Above command will run all unit test then build the project as .war. To avoid unit test run bellow command

```
mvn clean install -DskipTests=true
```

## Running the tests

You can run only test as well using 

```
mvn test
```
And specific test class or method can be run using mvn specific available commands

Run only PersonControllerIntegrationTest test class using bellow command

```
mvn -Dtest=PersonControllerIntegrationTest test
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Build project using mvn clean install and upload war file to live tomcat container. Then start tomcat server using ssh startup.sh command
Project default port 8080 which can be change using application.properties file property server.port=

## Run with embed tomcat

Build project with mvn clean install and use java -jar to target/person-rest-api-0.0.1-SNAPSHOT.war

## Front end UI

I have build a front end UI using pure HTML/CSS/JS which can be run on any machine without anything installation. Used vue.js to populate html DOM and axios to call REST api
Please download the reposioty from bellow link
[Front End UI Repository](https://github.com/Palash-it/simple-frontend-ui-to-test-person-rest-api)

Front End UI using a js file name common.js inside resources/js/ where API_BASE_URL = "http://localhost:8080/person-service/api/v1/" mentioned. If you change REST API port then this url need to be updated

You can use REST TEST client application Postman as well. use enity like bellow
body json data
{
	"firstName":"Palash Kumar",
	"lastName" : "Nath",
	"age":30,
	"favouriteColour" : "blue",
	"hobby" : ["gardening","programming"]
}

I made firstName as required/mandatory to show some default constrain violation messages

Thants all so far. Thanks for read me

