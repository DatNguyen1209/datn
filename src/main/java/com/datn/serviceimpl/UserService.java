package com.datn.serviceimpl;

import com.datn.converter.UserConverter;
import com.datn.dto.UserDTO;
import com.datn.entities.User;
import com.datn.repository.UserRepository;
import com.datn.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter converter;

    @Override
    public UserDTO save(UserDTO dto) {
        User entity = new User();
        if(dto.getId() != null){
            Optional<User> user = userRepository.findById(dto.getId());
            entity = converter.toEntity(dto,user.get());
        }else {
            entity = converter.toEntity(dto);
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

    @Override
    public Optional<User> findById(Long id) {
       return  userRepository.findById(id);
    }


}
