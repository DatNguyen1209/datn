package com.datn.dto;

import com.datn.entities.BaseEntities;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends BaseEntities {
    private  String userName;
    private String password;
    private String fullName;
    private String phone;
    private String address;
    private String email;
}
