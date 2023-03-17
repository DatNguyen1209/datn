package com.datn.dto;

import com.datn.entities.BaseEntities;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO extends BaseEntities {
    private Long userId;
    private String userName;
    private String fullName;
    private String phone;
    private String hotelName;
    private String roomName;
    private String price;
    private int capacity;
    private boolean status;
    private String images;
    private Long roomId;
    private Long hotelId;

}
