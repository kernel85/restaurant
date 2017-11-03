## How to run [![Build Status](https://travis-ci.org/kernel85/sales-taxes.svg?branch=master)](https://travis-ci.org/kernel85/restaurant)

### Step 1. Project checkout
```$ git checkout```

### Step 2. Build and package jar
```$ mvn package```

### Step 3. Create docker image
```$ mvn install dockerfile:build```

### Step 4. Run
```$ docker run -p 8080:8080 -t kernel/restaurant-rest-service```
