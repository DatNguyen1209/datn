package com.datn.service;

import com.datn.dto.PageDto;
import com.datn.dto.UserDTO;
import com.datn.entities.User;
import com.datn.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    UserDTO save(UserDTO dto);
    void delete(Long ids);
//    Page<UserDTO> findAllWithPageable(Pageable pageable);

    PageDto<UserDTO> findAllWithPageable(int page, int size);
}
