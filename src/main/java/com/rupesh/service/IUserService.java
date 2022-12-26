package com.rupesh.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rupesh.model.UserDTO;
import com.rupesh.model.UserResponseDTO;
import com.rupesh.pagination.PageRequest;
import com.rupesh.util.global.GlobalResponse;

import java.util.List;

public interface IUserService {

    GlobalResponse<UserDTO> saveUser(final UserDTO userDTO);

    GlobalResponse<UserDTO> updateUser(final UserDTO userDTO, final String userId);

    GlobalResponse<UserDTO> getUserByUsername(final String username);

    GlobalResponse<UserDTO> getUserByUserId(final String userId);

    GlobalResponse<?> getAllUser();

    GlobalResponse<Boolean> deleteUser(final String userId);

    GlobalResponse<Page<UserResponseDTO>> filterUser(final int page, final int size);

}
