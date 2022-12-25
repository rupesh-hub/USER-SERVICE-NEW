package com.rupesh.service;

import com.rupesh.clients.AddressClient;
import com.rupesh.entity.User;
import com.rupesh.model.AddressDTO;
import com.rupesh.model.UserDTO;
import com.rupesh.repository.UserRepository;
import com.rupesh.util.global.GlobalResponse;
import com.rupesh.util.global.GlobalUtil;
import com.rupesh.util.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.rupesh.util.helper.UserConstants.*;
import static com.rupesh.util.helper.UserHelper.generatePassword;
import static com.rupesh.util.helper.UserHelper.generateUserId;
import static com.rupesh.util.mapper.UserMapper.toEntity;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final AddressClient addressClient;

    @Autowired
    public UserService(UserRepository userRepository,
                       AddressClient addressClient) {
        this.userRepository = userRepository;
        this.addressClient = addressClient;
    }

    @Override
    @Transactional
    public GlobalResponse<UserDTO> saveUser(final UserDTO userDTO) {
        final AddressDTO addressDTO = userDTO.getAddress();
        final String userId = generateUserId();
        userDTO.setUserId(userId);
        userDTO.setPassword(generatePassword());
        userDTO.setCreatedDate(LocalDateTime.now());
        userDTO.setCreatedBy(userId);

        final User savedUser = userRepository.save(toEntity(userDTO));
        addressDTO.setUserId(savedUser.getUserId());
        saveAddress(addressDTO);

        return GlobalUtil
                .globalResponse(
                        String.format(USER_SAVED_SUCCESS, userDTO.getEmail()),
                        HttpStatus.CREATED,
                        Optional.ofNullable(savedUser)
                                .map(UserMapper::toDto)
                                .orElse(null)
                );
    }


    @Override
    @Transactional
    public GlobalResponse<UserDTO> updateUser(final UserDTO userDTO, final String userId) {
        final LocalDateTime updatedDate = LocalDateTime.now();
        final Long updatedBy = 1L;

        return GlobalUtil
                .globalResponse(
                        String.format(USER_UPDATED_SUCCESS, userDTO.getEmail()),
                        HttpStatus.CREATED,
                        Optional.ofNullable(userRepository.save(UserMapper.toEntity(userDTO)))
                                .map(UserMapper::toDto)
                                .orElse(null)
                );
    }

    @Override
    public GlobalResponse<UserDTO> getUserByUsername(final String username) {

        final UserDTO userDTO = userRepository.findByUsername(username)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new RuntimeException(String.format(USER_NOT_FOUND_BY_USERNAME, username)));

        userDTO.setAddress(getAddress(userDTO.getUserId()));

        return GlobalUtil
                .globalResponse(
                        String.format(USER_UPDATED_SUCCESS, username),
                        HttpStatus.OK,
                        userDTO
                );
    }

    @Override
    public GlobalResponse<UserDTO> getUserByUserId(final String userId) {

        final UserDTO userDTO = userRepository.findByUserId(userId)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new RuntimeException(String.format(USER_NOT_FOUND_BY_USER_ID, userId)));
        userDTO.setAddress(getAddress(userId));

        return
                GlobalUtil
                        .globalResponse(
                                String.format(USER_FOUND_BY_USER_ID, userId),
                                HttpStatus.OK,
                                userDTO
                        );

    }

    @Override
    public GlobalResponse<?> getAllUser() {
        final List<UserDTO> userList = Optional.ofNullable(userRepository.findAll())
                .map(UserMapper::toDtoList)
                .orElse(null);

        userList.forEach(user -> {
            user.setAddress(getAddress(user.getUserId()));
        });

        return GlobalUtil
                .globalResponse(
                        String.format(ALL_USER_FOUND),
                        HttpStatus.OK,
                        userList);
    }

    @Override
    public GlobalResponse<Boolean> deleteUser(final String userId) {
        final User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException(String.format(USER_NOT_FOUND_BY_USER_ID, userId)));

        userRepository.delete(user);

        return GlobalUtil
                .globalResponse(
                        String.format(USER_FOUND_BY_USER_ID, userId),
                        HttpStatus.OK,
                        null);
    }

    private AddressDTO getAddress(final String userId) {
        return addressClient.getAddressByUserId(userId).getBody().getData();
    }

    private void saveAddress(final AddressDTO addressDTO) {
        addressClient.saveAddress(addressDTO);
    }

}
