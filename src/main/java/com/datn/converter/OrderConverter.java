package com.datn.converter;

import com.datn.dto.OrderDTO;
import com.datn.entities.OrderHotelDetail;
import com.datn.repository.HotelRepository;
import com.datn.repository.RoomRepository;
import com.datn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class OrderConverter {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private UserRepository userRepository;
    public OrderHotelDetail toEntity(OrderDTO dto){
        OrderHotelDetail order = new OrderHotelDetail();
        order.setUser(userRepository.findByUserName(dto.getUserName()).orElseThrow(()->
             new RuntimeException("User Not Found!!!")));
        order.setUserName(order.getUser().getUserName());
        order.setFullName(order.getUser().getFullName());
        order.setEmail(order.getUser().getEmail());
        order.setPhone(order.getUser().getPhone());
        order.setCreatedDate(new Date());
        order.setStatus(dto.isStatus());
        order.setHotel(hotelRepository.getReferenceById(dto.getHotelId()));
        order.setHotelName(order.getHotel().getHotelName());
        order.setRoom(roomRepository.getReferenceById(dto.getRoomId()));
        order.setRoomName(dto.getRoomName());
        order.setPrice(order.getRoom().getPrice());
        order.setCapacity(order.getRoom().getCapacity());
        return order;
    }
    public OrderDTO toDTO(OrderHotelDetail orderHotelDetail){
        OrderDTO dto = new OrderDTO();
        if(orderHotelDetail.getId() != null){
            dto.setId(orderHotelDetail.getId());
        }
        dto.setUserId(orderHotelDetail.getId());
        dto.setUserName(orderHotelDetail.getUserName());
        dto.setFullName(orderHotelDetail.getFullName());
        dto.setHotelId(orderHotelDetail.getId());
        dto.setRoomId(orderHotelDetail.getId());
        dto.setCapacity(orderHotelDetail.getCapacity());
        dto.setPrice(orderHotelDetail.getPrice());
        dto.setPhone(orderHotelDetail.getPhone());
        dto.setRoomName(orderHotelDetail.getRoomName());
        return dto;
    }
    public OrderHotelDetail toEntities(OrderDTO dto,OrderHotelDetail orderHotelDetail){
        orderHotelDetail.setUserName(dto.getUserName());
        orderHotelDetail.setFullName(dto.getFullName());
        orderHotelDetail.setPrice(dto.getPrice());
        orderHotelDetail.setModifiedDate(new Date());
        orderHotelDetail.setPhone(dto.getPhone());
        orderHotelDetail.setCapacity(dto.getCapacity());
        return orderHotelDetail;
    }
}
