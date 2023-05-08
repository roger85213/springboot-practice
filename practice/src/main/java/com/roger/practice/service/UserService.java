package com.roger.practice.service;

import com.roger.practice.dto.CreateUserRequest;
import com.roger.practice.dto.UserLoginRequest;
import com.roger.practice.model.User;

public interface UserService {
User getUserByIntUserId(Integer userId);

Integer createUser(CreateUserRequest createUserRequest);

User login(UserLoginRequest userLoginRequest);

}
