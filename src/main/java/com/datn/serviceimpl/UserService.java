package com.datn.serviceimpl;

import com.datn.converter.UserConverter;
import com.datn.dto.UserDTO;
import com.datn.entities.User;
import com.datn.repository.UserRepository;
import com.datn.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("userSV")
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private IUserService service;
    @Autowired
    private UserConverter converter;

    @Override
    public UserDTO save(UserDTO dto) {
        User entity = new User();
        if(dto.getId() != null){
            Optional<User> user = userRepository.findById(dto.getId());
            entity = converter.toEntity(dto,user.get());
        }else {
            dto.setPassword(new String(Base64.getEncoder().encode(dto.getPassword().getBytes())));
            entity = converter.toEntity(dto, (new String(Base64.getEncoder().encode(dto.getPassword().getBytes()))));
        }
        entity = userRepository.save(entity);
        return converter.toDTO(entity);
    }

    @Override
    public void delete(Long ids) {
            userRepository.deleteById(ids);
    }
    @Override
    public List<UserDTO> findAllWithPageable(Pageable pageable) {
        List<UserDTO> results = new ArrayList<>();
        Page<User> page = userRepository.findAll(pageable);
//        List<User> users = page.toList();
//        List<UserDTO> userDTOS = users.stream().map((i)-> converter.toDTO(i)).collect(Collectors.toList());
//        return  userDTOS;

        return userRepository.findAll(pageable).toList().stream().map((i)->converter.toDTO(i)).collect(Collectors.toList());
    }

}
