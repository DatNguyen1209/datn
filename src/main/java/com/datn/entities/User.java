package com.datn.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter

public class User extends BaseEntities{
    @NotNull
    @Column(name = "user_name")
    private  String userName;
    @NotNull
    @Column(name = "password")
    private String password;
    @NotNull
    @Column(name = "full_name")
    private String fullName;
    @NotNull
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @OneToMany(mappedBy = "user")
    private List<OrderHotelDetail> orderHotelDetails;
}
