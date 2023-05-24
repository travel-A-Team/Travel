package com.travelproject.travelproject.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Banner")
@Table(name = "Banner")
public class BannerEntity {
    @Id
    private int bannerNumber;
    private String imageUrl;
    private String content;
}
