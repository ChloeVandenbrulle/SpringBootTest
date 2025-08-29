# Test Spring Boot Project
## Introduction
This project consists to develop a Springboot API that exposes several services :

* One that allows to register a user
* One that allows to update a registered user
* One that allows to delete a registered user
* One that displays the details of a registered user
* One that displays the list of all registered users.

Users are defined by a username, a birthdate and a country of residence. They can also have a phone number and a gender, but those aren't mandatory.
Only adults french residents are accepted.
A user's birthdate has to meet the correct format for a date : yyyy-mm-dd, has to be in the past and has to be so that the users is 18+.
A user's country of residence has to be 'France' or a derived such as 'FR', 'Fr', 'FRANCE', ...
A phone number has to meet the correct format for a phone number.
