package com.rupesh.service;

import com.rupesh.model.UserDTO;
import com.rupesh.util.global.GlobalResponse;

public interface IUserService {

    GlobalResponse<UserDTO> saveUser(final UserDTO userDTO);

    GlobalResponse<UserDTO> updateUser(final UserDTO userDTO, final String userId);

    GlobalResponse<UserDTO> getUserByUsername(final String username);

    GlobalResponse<UserDTO> getUserByUserId(final String userId);

    GlobalResponse<?> getAllUser();

    GlobalResponse<Boolean> deleteUser(final String userId);

}
