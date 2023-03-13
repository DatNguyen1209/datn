package com.datn.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_hotel_detail")
@Getter
@Setter
public class OrderHotelDetail extends BaseEntities{

    @Column(name = "user_name")
    private String userName;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "hotel_name")
    private String hotelName;
    @Column(name = "room_name")
    private String roomName;
    @Column(name = "price")
    private String price;
    @Column(name = "capacity")
    private String capacity;
    @Column(name = "images")
    private String images;
    @ManyToOne
    @JoinColumn(name = "user_order",referencedColumnName = "id")
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id",referencedColumnName = "id")
    private Room room;
}
