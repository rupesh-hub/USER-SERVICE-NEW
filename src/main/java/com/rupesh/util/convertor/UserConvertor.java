package com.rupesh.util.convertor;

import com.rupesh.entity.User;
import com.rupesh.model.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserConvertor {

    public static User toEntity(final UserDTO source) {

        final User user = new User();

        user.setUserId(source.getUserId());
        user.setFirstName(source.getFirstName());
        user.setMiddleName(source.getMiddleName());
        user.setLastName(source.getLastName());
        user.setEmail(source.getEmail());
        user.setDateOfBirth(source.getDateOfBirth());
        user.setPhone(source.getPhone());
        user.setPassword(source.getPassword());
        user.setCreatedDate(source.getCreatedDate());
        user.setCreatedBy(source.getCreatedBy());
        user.setModifiedBy(source.getModifiedBy());
        user.setModifiedDate(source.getModifiedDate());
        user.setEnabled(user.isEnabled());

        return user;
    }

    public static UserDTO toDto(final User source) {
        final UserDTO userDTO = new UserDTO();

        userDTO.setUserId(source.getUserId());
        userDTO.setFirstName(source.getFirstName());
        userDTO.setMiddleName(source.getMiddleName());
        userDTO.setLastName(source.getLastName());
        userDTO.setEmail(source.getEmail());
        userDTO.setDateOfBirth(source.getDateOfBirth());
        userDTO.setPhone(source.getPhone());
        userDTO.setPassword(source.getPassword());
        userDTO.setCreatedDate(source.getCreatedDate());
        userDTO.setCreatedBy(source.getCreatedBy());
        userDTO.setModifiedBy(source.getModifiedBy());
        userDTO.setModifiedDate(source.getModifiedDate());
        userDTO.setEnabled(source.isEnabled());

        return userDTO;
    }

    public static List<UserDTO> toDtoList(final List<User> userList){
        return userList.stream().map(UserConvertor::toDto).collect(Collectors.toList());
    }

}
