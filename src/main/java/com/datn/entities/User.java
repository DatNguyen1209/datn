package com.datn.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

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
    @JsonManagedReference
    private List<OrderHotelDetail> orderHotelDetails;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_roles",referencedColumnName = "id")
    private Set<Role> roles;
}
