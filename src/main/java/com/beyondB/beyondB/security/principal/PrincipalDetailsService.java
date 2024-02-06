package com.beyondB.beyondB.security.principal;

import com.beyondB.beyondB.apiPayload.code.status.ErrorStatus;
import com.beyondB.beyondB.apiPayload.exception.AuthException;
import com.beyondB.beyondB.entity.User;
import com.beyondB.beyondB.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user =
                userRepository
                        .findById(Long.parseLong(userId))
                        .orElseThrow(() -> new AuthException(ErrorStatus.USER_NOT_FOUND));

        return new PrincipalDetails(user);
    }
}
