package com.beyondB.beyondB.service.impl;

import com.beyondB.beyondB.apiPayload.code.status.ErrorStatus;
import com.beyondB.beyondB.apiPayload.exception.UserException;
import com.beyondB.beyondB.dto.UserSignupDTO;
import com.beyondB.beyondB.dto.request.UserRequestDTO.PatchAgeDTO;
import com.beyondB.beyondB.entity.User;
import com.beyondB.beyondB.entity.enums.Role;
import com.beyondB.beyondB.repository.UserRepository;
import com.beyondB.beyondB.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User findUserById(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new UserException(ErrorStatus.USER_NOT_FOUND));
    }


    public void signUp(UserSignupDTO userSignUpDto) throws Exception {

        if (userRepository.findByEmail(userSignUpDto.getEmail()).isPresent()) {
            throw new Exception("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .email(userSignUpDto.getEmail())
                .password(userSignUpDto.getPassword())
                .username(userSignUpDto.getUsername())
                .age(userSignUpDto.getAge())
                .role(Role.USER)
                .build();

        user.passwordEncode(passwordEncoder);
        userRepository.save(user);

    }

    @Transactional
    public User patchAge(User user, PatchAgeDTO patchAgeDTO) {
        user.updateUserAge(patchAgeDTO.getAge());
        return user;
    }

    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
