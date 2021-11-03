# kotlin-clean-arch

This is a simple project applying `Clean Architecture` using `Kotlin` and `Micronaut Framework`.

For now, the application is only structure by packages (the next step is to modularize the project):
* configuration
* core
* dataproviders
* entrypoints

## Stack

* Language: [`Kotlin`](https://kotlinlang.org)
* Build: [`Gradle`](https://gradle.org/)
* Framework: [`Micronaut`](https://micronaut.io/)
* Integration: 
  * Events: [`Kafka`](https://docs.confluent.io/platform/current/platform.html)
  * API: `Rest API` and [`gRPC`](https://grpc.io/)
* Database: [`Postgresql`](https://www.postgresql.org/)
* Test
  * Unit Tests: [`junit5`](https://junit.org/junit5/)
  * Integration Tests: [`Test Containers`](https://www.testcontainers.org/)
  * Mock: [`mockk`](https://mockk.io/)
  * Assert: [`assertk`](https://github.com/willowtreeapps/assertk)  
  * Mutation: [`pitest`](https://pitest.org/)  

## Build and execute

### Pre-requisites
* JDK 11+
* docker and docker-compose
* gradle

### Build
```bash
gradle clean build
```

### Up the infrastructure (database, kafka, etc)
In the root folder, execute:
```bash
docker-compose up -d
```

### Start the application 
Start the app from the IDE using the Application main class (in configuration package)

## Package Structure

`entrypoints`:
* gRPC
* rest
* Kafka Consumer

`dataproviders`:
* Postgresql
* Kafka Producer

`configuration`:
* Framework bean configurations
* Main class

`core`:
* `models`: business entities
* `usecases`: business logic
* `exceptions`

