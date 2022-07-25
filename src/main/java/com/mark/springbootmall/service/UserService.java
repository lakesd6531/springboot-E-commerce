package com.mark.springbootmall.service;

import com.mark.springbootmall.dao.UserDao;
import com.mark.springbootmall.dto.UserRegisterRequest;
import com.mark.springbootmall.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }

    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
