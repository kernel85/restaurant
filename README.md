## OS requirements

- Java SE 1.8
- Maven 3.5.2
- Docker 17.09.0-ce

## Main adopted technologies

- Java SE 1.8
- Spring Boot 1.5.8.RELEASE
- JUnit 4.12
- Maven 3.5.2
- Travis-CI (continuos integration)
- Docker 17.09.0-ce

## How to run [![Build Status](https://travis-ci.org/kernel85/restaurant.svg?branch=master)](https://travis-ci.org/kernel85/restaurant)

#### 1. Clone project
```
$ git clone git@github.com:kernel85/restaurant.git
$ cd restaurant/
```

#### 2. Project configuration
Under ```resources/``` directory you can find a json file ```restaurant.json``` which contais the startup configuration.

#### 3. Build and package jar
```$ mvn package```

#### 4. Create docker image
```$ mvn install dockerfile:build```

#### 5. Run
```$ docker run -p 8080:8080 -t kernel/restaurant-rest-service```