package com.datn.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "feedback")
@Getter
@Setter
public class Feedback extends BaseEntities{

    @Column(name = "email")
    private String email;
    @Column(name = "content")
    private String content;
    @Column(name = "vote")
    private int vote;
    private  String serviceName;
}
