package com.datn.repository;

import com.datn.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.net.ContentHandler;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.userName =:us")
    Optional<User> findByUserName(@Param("us") String userName);
    Boolean existsByUserName(String userName);

//    Boolean existsByEmail(String email);

}
