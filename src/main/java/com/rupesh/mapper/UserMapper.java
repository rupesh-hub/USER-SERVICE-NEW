package com.rupesh.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rupesh.model.UserResponseDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    Page<UserResponseDTO> allUsers(Page<UserResponseDTO> page);

}
