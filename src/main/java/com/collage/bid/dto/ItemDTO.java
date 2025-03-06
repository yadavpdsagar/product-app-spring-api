package com.collage.bid.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class ItemDTO {

    private String name;
    private int quantity;
    private String specification;
    private Date deliveryTime;
    private double price;

    // Getters and Setters
}
