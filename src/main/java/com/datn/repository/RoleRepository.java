package com.datn.repository;

import com.datn.entities.Role;
import com.datn.enumerable.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
