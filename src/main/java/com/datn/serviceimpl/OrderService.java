package com.datn.serviceimpl;

import com.datn.converter.OrderConverter;
import com.datn.dto.OrderDTO;
import com.datn.entities.OrderHotelDetail;
import com.datn.repository.OrderRepository;
import com.datn.repository.RoomRepository;
import com.datn.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    OrderConverter converter;

    @Autowired
    private RoomRepository roomRepository;
    @Override
    public OrderDTO save(OrderDTO dto) {
        OrderHotelDetail orderHotelDetail = null;
        try {
            var found =roomRepository.findById(dto.getRoomId());
            if(found == null){
                throw new RuntimeException("Not Found!!!");
            }else {
                orderHotelDetail = converter.toEntity(dto);
            }
            orderRepository.save(orderHotelDetail);
        }catch (Exception e){
            throw new RuntimeException("Faild!!!");
        }
        return converter.toDTO(orderHotelDetail);
    }

    @Override
    public OrderDTO update(OrderDTO dto,Long id) {
        OrderHotelDetail result = new OrderHotelDetail();
        if(dto.getId() != null){
            Optional<OrderHotelDetail> orderHotelDetail
                    = orderRepository.findById(dto.getId());
            result = converter.toEntities(dto,orderHotelDetail.get());
        } else {
            throw new RuntimeException("Not Found!!!");
        }
        result = orderRepository.save(result);
        return converter.toDTO(result);
    }

    @Override
    public void Accept(Long id) {
        OrderHotelDetail orderHotelDetail = orderRepository.findById(id).orElseThrow(()->{
            throw new RuntimeException("Not Found");
        });
        orderHotelDetail.setStatus(true);
        orderRepository.save(orderHotelDetail);
    }

    @Override
    public List<OrderHotelDetail> getAllOrder() {
        return orderRepository.findAll();
    }

}
