# Toy Robot Program

Spring Boot Application to resole a toy robot problem.

## What you will need ?

* JDK 1.8 or later
* [Maven 3.0+](https://maven.apache.org/download.cgi)

## How to use

with Bash

    mvn package && java -jar target/toy-robot-0.0.1.jar

Then open a Postman window and enjoy Posting commands to http://localhost:8080/


## Source Code organization
```
toy-robot/src$ find . | sort | sed '1d;s,[^/]*/,|    ,g;s/..//;s/[^ ]*$/|-- &/'
   |-- main
   |    |-- java
   |    |    |-- com
   |    |    |    |-- example
   |    |    |    |    |-- toyrobot
   |    |    |    |    |    |-- Application.java
   |    |    |    |    |    |-- controller
   |    |    |    |    |    |    |-- RobotController.java
   |    |    |    |    |    |-- model
   |    |    |    |    |    |    |-- FacingEnum.java
   |    |    |    |    |    |    |-- Robot.java
   |-- test
   |    |-- java
   |    |    |-- com
   |    |    |    |-- example
   |    |    |    |    |-- toyrobot
   |    |    |    |    |    |-- RobotControllerTest.java
   |    |-- resources
   |    |    |-- test1.in
   |    |    |-- test1.out
   |    |    |-- test2.in
   |    |    |-- test2.out
   |    |    |-- test3.in
   |    |    |-- test3.out
   |    |    |-- test4.in
   |    |    |-- test4.out
   |    |    |-- test5.in
   |    |    |-- test5.out
   |    |    |-- test6.in
   |    |    |-- test6.out
   |    |    |-- test7.in
   |    |    |-- test7.out
   |    |    |-- test8.in
   |    |    |-- test8.out

$ mvn test
... [INFO] Scanning for projects...
```

## License

Licensed under the Apache License, Version 2.0.
