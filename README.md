# Test Spring Boot Project

## Introduction
Ce projet consiste à développer une API Spring Boot qui expose plusieurs services :
* Un qui permet d'enregistrer un utilisateur
* Un qui permet de supprimer un utilisateur enregistré
* Un qui affiche les détails d'un utilisateur enregistré
* Un qui affiche la liste de tous les utilisateurs enregistrés.

Les utilisateurs sont définis par un nom d'utilisateur, une date de naissance et un pays de résidence. Ils peuvent également avoir un numéro de téléphone et un genre, mais ceux-ci ne sont pas obligatoires.  
Seuls les résidents français adultes sont acceptés.  
La date de naissance d'un utilisateur doit respecter le format correct pour une date : yyyy-mm-dd, doit être dans le passé et doit faire en sorte que les utilisateurs soient majeurs (18+).  
Le pays de résidence d'un utilisateur doit être 'France' ou une variante telle que 'FR', 'Fr', 'FRANCE', ...  
Un numéro de téléphone doit respecter le format correct pour un numéro de téléphone.  
Un genre doit être soit Homme, Femme ou Autre.

## Fonctionnalités
* Validation pour les résidents français adultes uniquement
* Validation complète des entrées avec messages d'erreur détaillés
* Journalisation automatique de tous les appels API avec temps d'exécution utilisant AOP
* Base de données H2 intégrée pour un déploiement facile
* Gestion des exceptions avec codes de statut HTTP appropriés
* Tests unitaires et d'intégration

## Stack Technique
* IntelliJ IDEA 2024.3.4
* Java 24
* Spring Boot 3.5.5
* Spring Data JPA - Opérations de base de données
* Spring Validation - Validation des entrées
* Spring AOP - Aspect de journalisation
* Base de données H2 2.3.232 - Base de données intégrée
* MapStruct 1.6.3 - Mapping d'objets
* Lombok 1.18.38 - Réduction du code boilerplate
* Maven 3.6+ - Outil de build

## Installation
1. Cloner le dépôt
```console
git clone https://github.com/ChloeVandenbrulle/SpringBootTest
cd SpringBootTest
```

2. Build le projet
```console
mvn clean install
```
ou "Build Module SpringBootTest2" :  
<img width="300" alt="IntelliJ Build Module" src="https://github.com/user-attachments/assets/94ef08e8-9c7b-4b76-86df-da42fcccbda4" /> 

3. Exécuter l'application
```console
mvn spring-boot:run
```
ou lancer SpringBootTest2Application :  
<img width="500" alt="IntelliJ Run Application" src="https://github.com/user-attachments/assets/e527921c-cf5a-4f02-b039-88d351a4488f" />  
L'application démarrera sur http://localhost:8080.  

4. Accéder à la console H2 :
URL: http://localhost:8080/h2-console  
JDBC URL: jdbc:h2:mem:testdb  
Username: sa  
Password: (laisser vide)

## Documentation de l'API
URL de base : http://localhost:8080/api/users  
Endpoints :  
### Créer utilisateur (POST)
```POST http://localhost:8080/api/users```  
#### Exemple :
```json
{
    "userName": "jean_dupont",
    "birthdate": "1990-05-15",
    "countryOfResidence": "France",
    "phoneNumber": "0123456789",
    "gender": "Male"
}
```

### Récupérer tout les utilisateur (GET)
```GET http://localhost:8080/api/users```  

### Récupérer le détail d'un utilisateur (GET/{id})
```GET http://localhost:8080/api/users/{id}```     
#### Exemple :
```GET http://localhost:8080/api/users/1```  

### Supprimer un utilisateur (DELETE/{id})
```DELETE http://localhost:8080/api/users/{id}```  
#### Exemple :
```DELETE http://localhost:8080/api/users/1```  

## Règles de Validation  
### Nom d'utilisateur
Propriété obligatoire.
Il doit être unique. Il ne peut pas être vide.

### Date de naissance
Propriété obligatoire.  
Format: yyyy-MM-dd.  
La date doit être dans le passé.
L'utilisateur doit être âgé de 18 ans ou plus.

### Pays de résidence
Propriété obligatoire.  
La valeur doit être France ou ses variantes : "France", "france", "FRANCE", "FR", "fr", ...

### Numéro de téléphone
Propriété optionnelle.  
Format: 10-12 chiffres avec préfixe + optionnel  
Exemples valides : "0123456789", "+33123456789", "123456789012"

### Genre
Propriété optionnelle.  
Valeurs valides : "Male", "Female", "Other"

***
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
### Create a new user (POST)  
```POST http://localhost:8080/api/users```
#### Example :  
```json
{
    "userName": "jean_dupont",
    "birthdate": "1990-05-15",
    "countryOfResidence": "France",
    "phoneNumber": "0123456789",
    "gender": "Male"
}
```

### Get all registered users (GET)  
```GET http://localhost:8080/api/users```  

### Get the details of a registered user (GET/{id})
```GET http://localhost:8080/api/users/{id}```   
#### Example :
```GET http://localhost:8080/api/users/1```  

### Delete a registered user (DELETE/{id})
```DELETE http://localhost:8080/api/users/{id}``` 
#### Example :  
```DELETE http://localhost:8080/api/users/1```  

## Validation Rules
### Username
Required property.  
The username must be unique. It cannot be blank.  

### Birthdate
Required property.  
Format: yyyy-MM-dd  
The date must be in the past. User must be 18 years or older.  

### Country of Residence
Required property.  
The value must be France or a variation: "France", "france", "FRANCE", "FR", "fr", ...  

### Phone Number
Optional property.  
Format: 10-12 digits with optional + prefix.  
Valid examples: "0123456789", "+33123456789", "123456789012".

### Gender
Optional property.   
Valid values: "Male", "Female", "Other".
