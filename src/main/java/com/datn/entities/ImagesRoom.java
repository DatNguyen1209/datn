package com.datn.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "image_room")
@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class ImagesRoom extends BaseEntities{
    @Column(name = "image_name")
    private String imageName;
    @Column(name = "url")
    private String url;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "roomImg",referencedColumnName = "id")
    private Room room;
}
