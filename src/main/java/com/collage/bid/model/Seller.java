package com.collage.bid.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Entity
@Table(name = "product")
@Data
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, length = 75)
    private String title;

    @Column(nullable = false)
    private LocalDate createdAt;
    @Column(unique = true)
    private Long citizenship;
    @Column(unique = true)
    private Long panNo;
    @Column(unique = true)
    private Long vatNo;

    private String filePath;
    @Column(nullable = false)
    private Long price;



    @Lob
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Bid bid;
}
