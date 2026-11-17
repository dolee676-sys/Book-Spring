package com.example.borrow.controller;

import com.example.borrow.entity.User;
import com.example.borrow.service.UserService;
import com.example.borrow.common.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

        @GetMapping
        public List<User> list(){
        return userService.list();
        }

        @PostMapping
        public User create(@RequestBody User user){
        return userService.create(user);
        }

        @GetMapping("/{id}/borrows")
        public List<Map<String, Object>> borrows(@PathVariable Long id) throws NotFoundException {
        return userService.borrows(id);
        }



}



































