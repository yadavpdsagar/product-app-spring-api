package com.collage.bid.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String password;
    private String passwordHash;
    private Role role;
    private String email;
    @Column(unique = true)
    private Long citizenship;
    @Column(unique = true)
    private Long panNo;
    @Column(unique = true)
    private Long vatNo;
    @Column(unique = true ,nullable = false)
    private String username;
    @Column(unique = true , nullable = false)
    private Long  phoneNO;

    @ElementCollection
    @Column(nullable = true)
    private List<String> seller ;

}
