package org.example.springboottest2.services;

import org.example.springboottest2.dto.UserDTO;
import org.example.springboottest2.entity.User;
import org.example.springboottest2.enums.GenderEnum;
import org.example.springboottest2.exceptions.ResourceNotFoundException;
import org.example.springboottest2.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.ConstraintViolationException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void createValidUser() {
        LocalDate birthdate = LocalDate.of(1990, 5, 15);
        UserDTO userDTO = new UserDTO("jean_dupont", birthdate, "France", null, null);

        UserDTO result = userService.createUser(userDTO);

        assertNotNull(result);
        assertEquals("jean_dupont", result.userName());
        assertEquals(birthdate, result.birthdate());
        assertEquals("France", result.countryOfResidence());
    }

    @Test
    void minorUser() {
        LocalDate birthdate = LocalDate.of(2010, 5, 15);
        UserDTO minorUserDTO = new UserDTO("minor_user", birthdate, "France", "0736256941", GenderEnum.Male);

        ConstraintViolationException exception = assertThrows(
                ConstraintViolationException.class,
                () -> userService.createUser(minorUserDTO)
        );
        assertTrue(exception.getMessage().contains("User should be an adult"));
    }

    @Test
    void foreignUser() {
        LocalDate birthdate = LocalDate.of(1990, 5, 15);
        UserDTO foreignUserDTO = new UserDTO("foreign_user", birthdate, "Allemagne", null, null);

        ConstraintViolationException exception = assertThrows(
                ConstraintViolationException.class,
                () -> userService.createUser(foreignUserDTO)
        );
        assertTrue(exception.getMessage().contains("Only French Residents are accepted"));
    }

    @Test
    void usernameAlreadyExists() {
        LocalDate birthdate = LocalDate.of(1990, 5, 15);
        UserDTO firstUser = new UserDTO("existing_user", birthdate, "France", null, null);
        userService.createUser(firstUser);

        UserDTO duplicateUser = new UserDTO("existing_user", birthdate, "France", "0736256941", GenderEnum.Female);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userService.createUser(duplicateUser)
        );
        assertTrue(exception.getMessage().contains("Username already exists"));
    }

    @Test
    void wrongPhoneNumberFormat() {
        LocalDate birthdate = LocalDate.of(1990, 5, 15);
        UserDTO wrongPhoneUserDTO = new UserDTO("wrong_phone_user", birthdate, "France", "123", null);

        ConstraintViolationException exception = assertThrows(
                ConstraintViolationException.class,
                () -> userService.createUser(wrongPhoneUserDTO)
        );
        assertTrue(exception.getMessage().contains("Invalid phone number format"));
    }

    @Test
    void usernameMissing() {
        LocalDate birthdate = LocalDate.of(1990, 5, 15);
        UserDTO usernameMissingDTO = new UserDTO("", birthdate, "France", "0736256941", GenderEnum.Female);

        ConstraintViolationException exception = assertThrows(
                ConstraintViolationException.class,
                () -> userService.createUser(usernameMissingDTO)
        );
        assertTrue(exception.getMessage().contains("Username is required"));
    }

    @Test
    void birthdateMissing() {
        UserDTO birthdateMissingDTO = new UserDTO("birthdate_missing_user", null, "France", "0736256941", GenderEnum.Other);

        ConstraintViolationException exception = assertThrows(
                ConstraintViolationException.class,
                () -> userService.createUser(birthdateMissingDTO)
        );
        assertTrue(exception.getMessage().contains("Birthdate is required"));
    }

    @Test
    void countryOfResidenceMissing() {
        LocalDate birthdate = LocalDate.of(1990, 5, 15);
        UserDTO countryMissingDTO = new UserDTO("country_missing_user", birthdate, "", "0736256941", GenderEnum.Male);

        ConstraintViolationException exception = assertThrows(
                ConstraintViolationException.class,
                () -> userService.createUser(countryMissingDTO)
        );
        assertTrue(exception.getMessage().contains("Country of residence is required"));
    }

    @Test
    void findUserByID() {
        LocalDate birthdate = LocalDate.of(1990, 5, 15);
        UserDTO userDTO = new UserDTO("test_user", birthdate, "France", null, null);
        UserDTO createdUser = userService.createUser(userDTO);

        User savedUser = userRepository.findAll().get(0);
        UserDTO result = userService.findUserById(savedUser.getId());

        assertNotNull(result);
        assertEquals("test_user", result.userName());
    }

    @Test
    void tryFindUserThatDoesNotExist() {
        Long nonExistentId = 999L;

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> userService.findUserById(nonExistentId)
        );
        assertEquals("No found user with this ID", exception.getMessage());
    }
}