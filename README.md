# restaurant

$ git checkout 

$ mvn package
$ mvn install dockerfile:build

$ docker run -p 8080:8080 -t kernel/restaurant-rest-service