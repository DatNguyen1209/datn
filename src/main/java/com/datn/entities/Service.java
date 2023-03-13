package com.datn.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "service")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Service extends BaseEntities {
//    private String serviceName;
    @Column(name = "url")
    private String url;
    @Column(name = "is_status")
    private boolean isStatus = true;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "hotel_service", referencedColumnName = "id")
    private Hotel hotel;
}
