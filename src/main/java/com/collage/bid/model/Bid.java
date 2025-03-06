package com.collage.bid.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "category")
@Data
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true ,nullable = false)// this will set from sec org name
    private String username;

    @Column(nullable = false, length = 75)
    private String title;

    @Column(nullable = false)
    private Long bankAccNo;

    @Column(nullable = false)
    private Date validDate;
    @Column(nullable = false)
    private Date closeDate;

    @Column(nullable = false)
    private String bankName;

    @OneToMany(mappedBy = "bid")
    @JsonManagedReference
    @JsonIgnore
    private List<Item> subCategories;
    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Bid category;

    @OneToMany(mappedBy = "bid")
    @JsonIgnore
    private List<Seller> sellers;

}
