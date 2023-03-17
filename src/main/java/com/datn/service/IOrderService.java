package com.datn.service;

import com.datn.dto.OrderDTO;
import com.datn.entities.OrderHotelDetail;

import java.util.List;

public interface IOrderService {
    OrderDTO save(OrderDTO dto);
    OrderDTO update(OrderDTO dto,Long id);
    void Accept(Long id);
    List<OrderHotelDetail> getAllOrder();
 }
