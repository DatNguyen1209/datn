package com.datn.dto;

import com.datn.entities.BaseEntities;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceDTO extends BaseEntities {
    private Long hotelId;
    private String url;
    private boolean isStatus = true;
}
