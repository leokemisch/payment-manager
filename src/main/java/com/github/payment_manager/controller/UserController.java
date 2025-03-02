package com.github.payment_manager.controller;

import com.github.payment_manager.dto.AuthenticationDTO;
import com.github.payment_manager.dto.CreateUserDTO;
import com.github.payment_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody CreateUserDTO data) {

        if (userService.findUserByLogin(data.login()) != null) return ResponseEntity.badRequest().build();

        userService.save(data);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody AuthenticationDTO data) {

        return ResponseEntity.ok(userService.login(data));
    }
}
