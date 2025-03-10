package com.github.payment_manager.controller;

import com.github.payment_manager.dto.authentication.AuthenticationDTO;
import com.github.payment_manager.dto.user.CreateUserDTO;
import com.github.payment_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody CreateUserDTO data) {

        if (userService.findUserByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(userService.save(data));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationDTO data) {

        return ResponseEntity.ok(userService.login(data));
    }
}
