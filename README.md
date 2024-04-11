# javahetic

javahetic is a Java project developed using Maven for dependency management and build automation.

## Prerequisites

Before you begin, ensure you have met the following requirements:
- Java JDK 21 installed on your machine
- Apache Maven 3.6.0 or higher

## Building the Project

To build the project, run the following command from the root of the project:

```bash
mvn clean install
```

## Running the Application
You have two options for running the application, depending on your needs:

### For Development
If you are developing and need to quickly test changes:

```bash
mvn exec:java -Dexec.mainClass="org.example.javahetic.Main"
```

This method uses Maven to run the application directly from the compiled classes, which is faster for iterative development since it doesn't require rebuilding the JAR file after every change.

### For Production Simulation
To run the application in a way that simulates the production environment:

```bash
java -jar target/javahetic-1.0-SNAPSHOT.jar
```

This method involves running the application from an executable JAR file. This ensures you are testing the application under conditions that closely resemble the production environment, using a single distributable that includes all necessary dependencies.