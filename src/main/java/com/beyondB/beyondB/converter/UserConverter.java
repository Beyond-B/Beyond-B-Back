package com.beyondB.beyondB.converter;

import com.beyondB.beyondB.dto.response.UserResponseDTO.UserDetailDTO;
import com.beyondB.beyondB.entity.User;

public class UserConverter {

    public static UserDetailDTO toUserDetailDTO(User user) {
        return UserDetailDTO.builder()
            .id(user.getId())
            .username(user.getUsername())
            .picture(user.getPicture())
            .age(user.getAge())
            .build();
    }
}
