package com.datn.controller;

import com.datn.dto.OrderDTO;
import com.datn.entities.OrderHotelDetail;
import com.datn.serviceimpl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {
    @Autowired
    private OrderService service;
    @PostMapping("/create")
    public  OrderDTO  CreateOrder(@RequestBody OrderDTO dto){
        var result = service.save(dto);
        return result;
    }
    @PutMapping("/update/{id}")
    public OrderDTO updateOrder(@RequestBody OrderDTO dto,@PathVariable("id") Long id){
        dto.setId(id);
        return service.update(dto,id);
    }
    @PutMapping("/accept/{id}")
    public void accept(@PathVariable("id") Long id){
        service.Accept(id);
    }
    @GetMapping("/getAll")
    public List<OrderHotelDetail> getAllOrder(){
        return service.getAllOrder();
    }

}
