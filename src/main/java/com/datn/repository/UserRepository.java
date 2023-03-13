package com.datn.repository;

import com.datn.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.net.ContentHandler;

public interface UserRepository extends JpaRepository<User,Long> {
}
