package org.example.springboottest2.mappers;

import org.example.springboottest2.dto.UserDTO;
import org.example.springboottest2.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    User toEntity(UserDTO userDTO);
    UserDTO toDto(User user);
}