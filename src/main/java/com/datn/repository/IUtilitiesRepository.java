package com.datn.repository;

import com.datn.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUtilitiesRepository extends JpaRepository<Service,Long> {
}
