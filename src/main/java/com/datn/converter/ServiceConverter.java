package com.datn.converter;

import com.datn.dto.ServiceDTO;
import com.datn.entities.Service;
import org.springframework.stereotype.Component;

@Component
public class ServiceConverter {
    public Service toEntity(ServiceDTO dto){
        Service service = new Service();
        service.setUrl(dto.getUrl());
        service.setStatus(dto.isStatus());
        return service;
    }
    public ServiceDTO toDTO(Service service){
        ServiceDTO dto = new ServiceDTO();
        if(service.getId() != null){
            dto.setHotelId(service.getId());
        }
        dto.setHotelId(service.getId());
        dto.setUrl(service.getUrl());
        dto.setStatus(service.isStatus());
        return dto;
    }
    public Service toEntities(ServiceDTO dto , Service service){
        service.setUrl(dto.getUrl());
        service.setStatus(dto.isStatus());
        return service;
    }
}
