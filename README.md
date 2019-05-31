# NCDC Java assessment

This is a simple web app realized as an answer to Java assessment task for NCDC company.
It may be used as a simple storage of books records.

## Getting started

### Prerequisites

```
Gradle build tool
alternatively: IDE, f.ex. IntelliJ IDEA
```

### Building the application

In order to build the application easily please install Gradle.

```
In the command line shell:

1. Go to the directory with "gradlew" file of the Project folder.
2. Insert: ./gradlew -v
3. Insert: ./gradlew build

The Project should have built automatically.

4. Insert: gradle bootRun. 

The application should run and be ready to use on localhost:8080 port of your browser.
```

## Using the application

You are able to save, retrieve and delete the data, using dedicated forms and buttons.

```
The acceptable record format is:

1. Author – Author’s forename and surname separated by a space. One of these names must start with “A” letter.
2. Title – can not be empty or consist of only white characters.
3. ISBN – number according to ISBN-13 standard.
```
