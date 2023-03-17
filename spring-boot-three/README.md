# Bootiful Spring Boot 3

Code based on the youtube talk https://www.youtube.com/watch?v=Y2gZz8-yK7Y given by Josh Long.

Github examples provided by him can be found here: https://github.com/joshlong/bootiful-spring-boot-3

This project involves implementation of a client and a server, the server exposes a REST endpoint to 
get a list of customers and for a given name; the client implements a web client which call mentioned
REST endpoints; and it configures a `/proxy` route in order to forward the call to the customers list endpoint and
also exposes a graphql endpoint `http://localhost:9090/graphiql?path=/graphql` to run queries over two methods to 
retrieve a list of customers and also by name.

## Technologies involved

- Java 17
- Spring boot 3
- Spring Web
- Spring Cloud Gateway
- Spring Reactive
- Spring Graphql

## Requirements
- Java 17

## Modules

- server
- client

## Installation

Each module contains a `build.gradle` with listed gradle dependencies, it can be built with `./gradlew build`.