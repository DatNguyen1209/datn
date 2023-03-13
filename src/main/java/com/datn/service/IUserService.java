package com.datn.service;

import com.datn.dto.UserDTO;
import com.datn.entities.User;
import com.datn.repository.UserRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    UserDTO save(UserDTO dto);
    void delete(Long ids);
    List<UserDTO> findAllWithPageable(Pageable pageable);
    Optional<User> findById(Long id);
}
