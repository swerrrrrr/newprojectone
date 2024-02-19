package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserMapper userMapprer;
    @PostMapping
    public Result <?> save(@RequestBody User user) {
         userMapprer.insert(user);
        return Result.success();
    }
}
