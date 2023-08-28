package com.aixu.async.controller;

import com.aixu.async.model.User;
import com.aixu.async.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author 冠旭
 * @Date 2023/8/28 9:45
 */
@RestController
public class AsyncController {


    @Resource
    private UserService userService;

    @GetMapping("/test")
    public String test(){
        return "Test";
    }


    @GetMapping("/{userId}")
    public ResponseEntity<User> queryUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.queryUserById(userId));
    }
}
