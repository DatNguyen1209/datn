package com.datn.controller;

import com.datn.converter.UserConverter;
import com.datn.dto.UserDTO;
import com.datn.entities.User;
import com.datn.repository.UserRepository;
import com.datn.serviceimpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")

public class UserController {
    @Autowired
    private UserConverter converter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id){
        return userRepository.findById(id);
    }
    @GetMapping("/")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    @PostMapping("/register")
    public UserDTO registration(@RequestBody UserDTO dto){
        return  userService.save(dto);
    }
    @PutMapping("/update/{id}")
    public  UserDTO updateUser(@RequestBody UserDTO dto,@PathVariable("id") Long id){
        dto.setId(id);
        return userService.save(dto);
    }
    @DeleteMapping("/delete/{ids}")
    public void deleteUserById(@PathVariable("ids") Long ids){
        userService.delete(ids);
    }
}
