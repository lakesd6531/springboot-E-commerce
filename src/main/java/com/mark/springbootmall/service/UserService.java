package com.mark.springbootmall.service;

import com.mark.springbootmall.dao.UserDao;
import com.mark.springbootmall.dto.UserRegisterRequest;
import com.mark.springbootmall.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final static Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserDao userDao;

    public Integer register(UserRegisterRequest userRegisterRequest) {
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());

        if (user != null) {
            log.warn("該 email {} 已經被註冊", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return userDao.createUser(userRegisterRequest);
    }

    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
