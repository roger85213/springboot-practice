package com.roger.practice.service.Impl;

import com.roger.practice.dao.UserDao;
import com.roger.practice.dto.CreateUserRequest;
import com.roger.practice.dto.UserLoginRequest;
import com.roger.practice.model.User;
import com.roger.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.net.HttpRetryException;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByIntUserId(Integer userId) {
        return userDao.getUserByIntUserId(userId);
    }

    @Override
    public Integer createUser(CreateUserRequest createUserRequest) {
        Integer userId = userDao.createUser(createUserRequest);

        return userId;
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());
        if (user == null){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
//
       // //String hashPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());
//
        if (userLoginRequest.getPassword().equals(user.getPassword())){
           return user;
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
