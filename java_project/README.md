# README

This gradle project was generated via [spring initializr](https://start.spring.io/) using Spring Boot version 2.4.3 and Java 11. To allow for Java version flexibility, we're using [gradle toolchains](https://docs.gradle.org/current/userguide/toolchains.html#sec:consuming). If you do not have JDK 11, simply update the toolchain block in `build.gradle` to the appropriate version:

```
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(11)
    }
}
```

Run `./gradlew tasks` to see what targets you can run.

Default target to build the project is `./gradlew assemble`
Default target to launch the app is `./gradlew bootRun`

# To Set Up the Project

You will first need to verify that you have both Java and Gradle installed on your machine. You can do this by running
the commands `java --version` and `gradle --version`. If you do not have a global version of Gradle installed, you 
can also run the necessary Gradle commands using Gradle shortcuts in your IDE, if your IDE supplies these shortcuts (IntelliJ does).

In your terminal window, from the location of this project (e.g. C:\Users\{yourname}\IdeaProjects\interview-services\java_project),
type the command `./gradlew clean build`. 

Hit enter. Your project is now built.

# To Run the Project

1. In a terminal window, from the location of this project (e.g. C:\Users\{yourname}\IdeaProjects\interview-services\java_project), 
type `./gradlew bootRun`. Hit enter.
2. Open a browser. Hit the url localhost:3001/api/tree to fetch your tree.
3. To add a node to your tree, you will need to use an API platform tool like Postman or Swagger (or HTTP Client, if using IntelliJ Ultimate).

# To Run the Tests
In a terminal window, from the location of this project, type `./gradlew test`. Hit enter.