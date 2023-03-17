package com.datn.repository;

import com.datn.entities.OrderHotelDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderHotelDetail,Long> {
}
