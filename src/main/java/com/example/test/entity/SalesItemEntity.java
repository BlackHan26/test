package com.example.test.entity;

import jakarta.persistence.*;
import lombok.Data;
@Table(name = "sale")
@Data
@Entity
public class SalesItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String writer;
    private Integer minPrice;
    private String content;
    private String title;
    private String password;
    private String imgUrl;
    private String status;


}
