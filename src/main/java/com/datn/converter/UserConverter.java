package com.datn.converter;

import com.datn.dto.UserDTO;
import com.datn.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserConverter {
    public User toEntity(UserDTO dto,String pass){
        User entity = new User();
        entity.setUserName(dto.getUserName());
        entity.setPassword(pass);
        entity.setFullName(dto.getFullName());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setAddress(dto.getAddress());
        entity.setCreatedDate(new Date());
        return entity;
    }
    public UserDTO toDTO(User user){
        UserDTO dto = new UserDTO();
        if(user.getId() != null){
            dto.setId(user.getId());
        }
        dto.setUserName(user.getUserName());
        dto.setPassword(user.getPassword());
        dto.setFullName(user.getFullName());
        dto.setPhone(user.getPhone());
        dto.setEmail(user.getEmail());
        dto.setAddress(user.getAddress());
        return dto;
    }
    public User toEntity(UserDTO dto,User user){
        user.setUserName(dto.getUserName());
        user.setPassword(dto.getPassword());
        user.setFullName(dto.getFullName());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setEmail(dto.getEmail());
        return  user;
    }

}
