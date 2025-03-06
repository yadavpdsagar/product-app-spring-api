package com.collage.bid.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "sub_category")
@Data
@JsonIgnoreProperties()
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String specification;

    @Column(nullable = false)
    private Date deliveryTime;

    @Column(nullable = false)
    private Long price;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "categoryid")
    private Bid bid;


}
