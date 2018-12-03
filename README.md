# myRetail
This is a simple RestFul application using Spring boot and MongoDB.
 - MongoDB is a popular NoSQL database where data is stored as JSON.
 - Spring Boot provides a powerful MongoDB connector

##Prerequisites: 

- Java

- Gradle

- An accessible MongoDB instance. For more information on how to install MongoDB refer to [MongoDB]

The instance will have the following:

The database used is my_retail:

```sh
use my_retail;
```

The collection used is products:

```sh
db.createCollection("products");
```

Below is a sample of the data inserted in the collection for this application:
```sh
db.products.insertMany([
  { "id" : 13860428, 
     "current_price" : { 
      "value" : 13.49, 
      "currency_code" : "USD" 
      } 
  },
  { "id" : 13860429, 
     "current_price" : { 
      "value" : 13.49, 
      "currency_code" : "USD" 
      } 
  },
  { "id" : 13860425, 
     "current_price" : { 
      "value" : 13.49, 
      "currency_code" : "USD" 
      } 
  }
]);

```
Update the applications.properties file with the MongoDB instance used:

spring.data.mongodb.host=[host]
spring.data.mongodb.port=[port]
spring.data.mongodb.authentication-database=[authentication_database]
spring.data.mongodb.username=[username]
spring.data.mongodb.password=[password]
spring.data.mongodb.database=products

## Running the app:

To run the application:

```sh

gradle bootRun

```

To run the tests:

```sh
gradle test

```



[MongoDB]: <https://docs.mongodb.com/manual/>
