package com.datn.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @Column(name = "hotel_name")
    private String hotelName;
    @Column(name = "room_name")
    private String roomName;
    @Column(name = "price")
    private String price;
    @Column(name = "email")
    private String email;
    @Column(name = "capacity")
    private int capacity;
    @Column(name = "images")
    private String images;
    @Column(name = "status")
    private boolean status = false;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_order",referencedColumnName = "id")
    private User user;

    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id",referencedColumnName = "id")
    private Room room;
    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel",referencedColumnName = "id")
    private Hotel hotel;
}
