# City Temperature

This repository keeps the source code of an API called "City Temperature". It provides information about city temperature around the world.

You can check its documentation [here](https://app.swaggerhub.com/apis-docs/santiagonbernardes/city-temperature/1.0.0).

## Running this project locally
### Requirements

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

Having trouble installing JDK 1.8? [Here's](https://www.guru99.com/install-java.html) a guide for you.

### Running the application
From your OS command line, access the folder where you cloned this project in. Then, run the following command:

- On Windows
```dos
gradlew :bootRun
```

- On OSX/Linus

```shell
./gradlew :bootRun
```

After this, if everything runs smoothly, you will be able to use the API on http://localhost:8080.

From your browser you can test it by accessing http://localhost:8080/api/v1/cities/Brasília/temperature.

### Running unit tests
You can also run all the unit tests implemented for this application. Run the commands below:

- On Windows
```cmd
gradlew test
```

- On OSX/Linus

```shell
./gradlew test
```