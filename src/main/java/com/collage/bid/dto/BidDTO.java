package com.collage.bid.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class BidDTO {

    private Long id;
    private String username;
    private String title;
    private Long bankAccNo;
    private Date validDate;
    private Date closeDate;
    private String bankName;
    private List<ItemDTO> items;  // List of ItemDTOs

    // Getters and Setters
}
