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

- On OSX/Linux

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

- On OSX/Linux

```shell
./gradlew test
```

## API interface decisions
### Why did you chose this request interface?
In this domain, cities became a resource. In the long run, this decision makes possible to add more services regarding cities, such as fetching their population.
An example would be the route GET /api/v1/cities/{cityName}/population.

That said, I set city name as a resource identifier and this is the reason cityName is a path param.

I know that might be a city with the same name as another and, in that case, we can use other feature like query strings.

The action on the only route of this API is retrieval of a resource representation. This is why the http method is GET.

### Why did you chose this response interface?
To begin with, I tried to make the best use possible of http status code. 200 on success, 404 on not found and 500 on internal server error.

In this first version, I have decided to return a date field to make clear for the user when that temperature might happen.

Also, since its possible that the user inputs characters that does not make a complete city name, I have decided to make clear what is the city returned.

The temperature fields are a simplification since they represent the average temperature of that day. 
