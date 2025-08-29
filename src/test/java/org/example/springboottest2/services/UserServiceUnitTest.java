package org.example.springboottest2.services;

import org.example.springboottest2.dto.UserDTO;
import org.example.springboottest2.entity.User;
import org.example.springboottest2.enums.GenderEnum;
import org.example.springboottest2.exceptions.ResourceNotFoundException;
import org.example.springboottest2.mappers.UserMapper;
import org.example.springboottest2.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    private UserDTO validUserDTO;
    private User validUser;

    @BeforeEach
    void setUp() {
        validUserDTO = new UserDTO(
                "jean_dupont",
                LocalDate.of(1990, 5, 15),
                "France",
                "0123456789",
                GenderEnum.Male
        );

        validUser = User.builder()
                .id(1L)
                .userName("jean_dupont")
                .birthdate(LocalDate.of(1990, 5, 15))
                .countryOfResidence("France")
                .phoneNumber("0123456789")
                .gender(GenderEnum.Male)
                .build();
    }

    @Test
    void createUser_Success() {
        when(userMapper.toEntity(validUserDTO)).thenReturn(validUser);
        when(userRepository.save(any(User.class))).thenReturn(validUser);
        when(userMapper.toDto(validUser)).thenReturn(validUserDTO);

        UserDTO result = userService.createUser(validUserDTO);

        assertNotNull(result);
        assertEquals("jean_dupont", result.userName());
        assertEquals(LocalDate.of(1990, 5, 15), result.birthdate());
        assertEquals("France", result.countryOfResidence());

        verify(userMapper).toEntity(validUserDTO);
        verify(userRepository).save(any(User.class));
        verify(userMapper).toDto(validUser);
    }

    @Test
    void findUserById_Success() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(validUser));
        when(userMapper.toDto(validUser)).thenReturn(validUserDTO);

        UserDTO result = userService.findUserById(userId);

        assertNotNull(result);
        assertEquals("jean_dupont", result.userName());

        verify(userRepository).findById(userId);
        verify(userMapper).toDto(validUser);
    }

    @Test
    void findUserById_NotFound() {
        Long userId = 999L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> userService.findUserById(userId)
        );

        assertEquals("No found user with this ID", exception.getMessage());
        verify(userRepository).findById(userId);
        verifyNoInteractions(userMapper);
    }

    @Test
    void findAllUsers_Success() {
        User user1 = User.builder().id(1L).userName("user1").build();
        User user2 = User.builder().id(2L).userName("user2").build();
        List<User> users = Arrays.asList(user1, user2);

        UserDTO dto1 = new UserDTO("user1", LocalDate.of(1990, 1, 1), "France", null, null);
        UserDTO dto2 = new UserDTO("user2", LocalDate.of(1985, 1, 1), "France", null, null);

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.toDto(user1)).thenReturn(dto1);
        when(userMapper.toDto(user2)).thenReturn(dto2);

        List<UserDTO> result = userService.findAllUsers();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("user1", result.get(0).userName());
        assertEquals("user2", result.get(1).userName());
        verify(userRepository).findAll();
    }

    @Test
    void findAllUsers_EmptyList() {
        when(userRepository.findAll()).thenReturn(Arrays.asList());

        List<UserDTO> result = userService.findAllUsers();

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(userRepository).findAll();
        verifyNoInteractions(userMapper);
    }

    @Test
    void deleteUser_Success() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(validUser));
        when(userMapper.toDto(validUser)).thenReturn(validUserDTO);
        when(userMapper.toEntity(validUserDTO)).thenReturn(validUser);

        userService.deleteUser(userId);

        verify(userRepository).findById(userId);
        verify(userRepository).delete(validUser);
        verify(userMapper).toDto(validUser);
        verify(userMapper).toEntity(validUserDTO);
    }

    @Test
    void deleteUser_NotFound() {
        Long userId = 999L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> userService.deleteUser(userId)
        );

        assertEquals("No found user with this ID", exception.getMessage());
        verify(userRepository).findById(userId);
        verify(userRepository, never()).delete(any(User.class));
    }
}