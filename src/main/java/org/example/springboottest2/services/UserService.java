package org.example.springboottest2.services;

import jakarta.validation.Valid;
import org.example.springboottest2.dto.UserDTO;
import org.example.springboottest2.entity.User;
import org.example.springboottest2.exceptions.ResourceNotFoundException;
import org.example.springboottest2.mappers.UserMapper;
import org.example.springboottest2.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Transactional
    public UserDTO createUser(@Valid UserDTO userDTO) {
        if (userRepository.existsByUserName(userDTO.userName())) {
            throw new IllegalArgumentException("Username already exists");
        }
        User user = userMapper.toEntity(userDTO);
        User newUser = userRepository.save(user);
        UserDTO newUserDTO = userMapper.toDto(newUser);
        return newUserDTO;
    }

    public UserDTO findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No found user with this ID"));
        return userMapper.toDto(user);
    }

    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userMapper.toDto(user);
            userDTOs.add(userMapper.toDto(user));
        }
        return userDTOs;
    }

    @Transactional
    public UserDTO updateUser(Long id, @Valid UserDTO user) {
        UserDTO oldUserDTO = findUserById(id);
        User oldUser = userMapper.toEntity(oldUserDTO);
        User newUser = userMapper.toEntity(user);
        BeanUtils.copyProperties(newUser, oldUser, "id");
        return userMapper.toDto(newUser);
    }

    @Transactional
    public void deleteUser(Long id) {
        UserDTO userDTO = findUserById(id);
        User user = userMapper.toEntity(userDTO);
        userRepository.delete(user);
    }
}
