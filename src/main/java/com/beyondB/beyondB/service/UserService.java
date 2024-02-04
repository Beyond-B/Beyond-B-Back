package com.beyondB.beyondB.service;

import com.beyondB.beyondB.entity.User;
import java.util.concurrent.Flow;
import org.springframework.data.domain.Page;

public interface UserService {

    User findUserById(Long userId);

}
