# Test Spring Boot Project
## Introduction
This project consists to develop a Springboot API that exposes several services :

* One that allows to register a user
* One that allows to delete a registered user
* One that displays the details of a registered user
* One that displays the list of all registered users.

Users are defined by a username, a birthdate and a country of residence. They can also have a phone number and a gender, but those aren't mandatory.  
Only adults french residents are accepted.  
A user's birthdate has to meet the correct format for a date : yyyy-mm-dd, has to be in the past and has to be so that the users are 18+.  
A user's country of residence has to be 'France' or a derived such as 'FR', 'Fr', 'FRANCE', ...  
A phone number has to meet the correct format for a phone number.  
A gender has to be either Male, Female or Other.  

## Features
* Validation for French adult residents only
* Comprehensive input validation with detailed error messages
* Automatic logging of all API calls with execution times using AOP
* Embedded H2 database for easy deployment
* Exception handling with appropriate HTTP status codes
* Unit and integration tests

## Technical Stack
* IntelliJ IDEA 2024.3.4
* Java 24
* Spring Boot 3.5.5
* Spring Data JPA - Database operations
* Spring Validation - Input validation
* Spring AOP - Logging aspect
* H2 Database 2.3.232 - Embedded database
* MapStruct 1.6.3 - Object mapping
* Lombok 1.18.38 - Boilerplate code reduction
* Maven 3.6+ - Build tool

## Installation
1. Clone the repository 
```console
git clone https://github.com/ChloeVandenbrulle/SpringBootTest
cd SpringBootTest
```

2. Build the project
```console
mvn clean install
```
or "Build Module SpringBootTest2"  
<img width="300" alt="IntelliJ Build Module" src="https://github.com/user-attachments/assets/94ef08e8-9c7b-4b76-86df-da42fcccbda4" />  
3. Run the application
```console
mvn spring-boot:run
```
or run SpringBootTest2Application :  
<img width="500" alt="IntelliJ Run Application" src="https://github.com/user-attachments/assets/e527921c-cf5a-4f02-b039-88d351a4488f" />  
The application will start on http://localhost:8080.

4. Access H2 Console :  
URL: http://localhost:8080/h2-console  
JDBC URL: jdbc:h2:mem:testdb  
Username: sa  
Password: (leave empty)  

## API Documentation
Base URL : http://localhost:8080/api/users  
Endpoints :  
```POST http://localhost:8080/api/users``` -> Create a new user  
### Create User (POST)
```json
{
    "userName": "jean_dupont",
    "birthdate": "1990-05-15",
    "countryOfResidence": "France",
    "phoneNumber": "0123456789",
    "gender": "Male"
}
```
```GET http://localhost:8080/api/users``` -> Get the list of all registered users  
```GET http://localhost:8080/api/users/{id}``` -> Get the details of a registered user   
Example : ```GET http://localhost:8080/api/users/1```  
```DELETE http://localhost:8080/api/users/{id}``` -> Delete a registered user  
Example : ```DELETE http://localhost:8080/api/users/1```  

## Validation Rules
### Username
Required  
Must be unique  
Cannot be blank  

### Birthdate
Required  
Format: yyyy-MM-dd  
Must be in the past  
User must be 18 years or older  

### Country of Residence
Required  
Must be France or variations: "France", "france", "FRANCE", "FR", "fr", ...  

### Phone Number
Optional  
Format: 10-12 digits with optional + prefix  
Valid examples: "0123456789", "+33123456789", "123456789012"  

### Gender
Optional  
Valid values: "Male", "Female", "Other"  

