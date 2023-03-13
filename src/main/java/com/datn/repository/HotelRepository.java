package com.datn.repository;

import com.datn.entities.Hotel;
import com.datn.entities.Images;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,Long> {

}
