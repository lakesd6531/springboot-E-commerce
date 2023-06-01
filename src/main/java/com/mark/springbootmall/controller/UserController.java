package com.mark.springbootmall.controller;

import com.mark.springbootmall.dto.UserLoginRequest;
import com.mark.springbootmall.dto.UserRegisterRequest;
import com.mark.springbootmall.model.User;
import com.mark.springbootmall.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(tags = "帳號功能")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(
        value = "註冊使用者",
        notes = "使用電子商城需使用該API進行帳號註冊"
    )
    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        Integer userId = userService.register(userRegisterRequest);

        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @ApiOperation(
        value = "使用者登入",
        notes = "使用電子商城需使用該API進行登入"
    )
    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        User user = userService.login(userLoginRequest);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
