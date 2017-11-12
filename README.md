# jwt-java-rest-backend
This project is a boilerplate/example which covers how to create an application using the next technologies.

- REST API using Spring Boot for the backend
- Reactjs for the front end using semantic ui and axios

The main interesting points of this code are:

## Backend
- Spring boot REST app
- Postgres as database
- JPA/Hibernate as ORM, spring data repositories
- Jsonb fields in entities
- REST controllers, using JSON
- Flyway as the database migration tool
- Custom exception handling in controllers 
- Automatic tests for services and controllers (MVC)

## Frontend
- Reactjs
- Semantic UI as the

## To start the app

### Start the database

From the project root folder type

```
docker-compose up
```

You'll have to install previously [docker](https://www.docker.com/).
This will start the postgres database. You'll need to create a database and schema called `crazy`for the API to work.

### Start the API
From the project root folder type

```
./gradlew bootRun
```

### Start the UI
From the project root folder type

```
cd ui/ui-react
npm start
```

You'll be able to use the application at `http://localhost:3000`
 
## TODO list

### Features
- Form validation
- Add scores feature
- Add some D3 charts for scores using react-d3

### Architecture
- Add spring security with JWT tokens. Config is done, need to do the login page, and manage the tokens in the UI and the API.
- Use PropTypes in the UI
- Create database and schema automatically if not existing