package com.datn.serviceimpl;

import com.datn.converter.UserConverter;
import com.datn.dto.PageDto;
import com.datn.dto.UserDTO;
import com.datn.entities.User;
import com.datn.repository.UserRepository;
import com.datn.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
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
        if (dto.getId() != null) {
            Optional<User> user = userRepository.findById(dto.getId());
            entity = converter.toEntity(dto, user.get());
        } else {
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
    public PageDto<UserDTO> findAllWithPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id").descending());
        Page<User> userList = userRepository.findAll(pageable);

        return PageDto.of(userList, userList.stream().map(user -> converter.toDTO(user)).collect(Collectors.toList()));

//        List<UserDTO> users = ;
//        int start = Math.min((int) pageable.getOffset(), users.size());
//        int end = Math.min((start + pageable.getPageSize()), users.size());
//        Page<UserDTO> page1 = new PageImpl<>(users,pageable, pageable.);
//        return page1;
    }

//    @Override
//    public Page<UserDTO> findAllWithPageable(Pageable pageable) {
//        List<UserDTO> results = new ArrayList<>();
//        Page<User> page = userRepository.findAll(pageable);
//        List<UserDTO> users = userRepository.findAll().stream().map(user -> converter.toDTO(user)).collect(Collectors.toList());
////        List<User> users = page.toList();
////        List<UserDTO> userDTOS = users.stream().map((i)-> converter.toDTO(i)).collect(Collectors.toList());
////        return  userDTOS;
//
//        int start = Math.min((int) pageable.getOffset(), users.size());
//        int end = Math.min((start + pageable.getPageSize()), users.size());
//        Page<UserDTO> page1 = new PageImpl<>(users.subList(start, end), pageable, users.size());
//
//        return  page1;
//
//        //return userRepository.findAll(pageable).toList().stream().map((i)->converter.toDTO(i)).collect(Collectors.toList());
//    }

}
