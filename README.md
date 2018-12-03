# myRetail
This is a simple RestFul application using Spring boot and MongoDB.
 - MongoDB is a popular NoSQL database where data is stored as JSON.
 - Spring Boot provides a powerful MongoDB connector

##Prerequisites: 

- Java

- Gradle

- An accessible MongoDB instance. For more information on how to install MongoDB refer to [MongoDB]

The instance will have the following db:

```sh
use my_retail;

db.createCollection("products");

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

## Running the app:

To run the application:

```sh

gradle bootRun

```



[MongoDB]: <https://docs.mongodb.com/manual/>
