package com.example.ens.entities;


import lombok.ToString;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.*;



//@OneToMany one class for many attribut
//@ManyToMany many class for many attribut
//@ManyToOne many class for one attribut
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Depence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String refDepence;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDepence;
    private double montantDepence;
    private String benificiaire;
    @ManyToOne
    @JoinColumn(name = "TypeDepence_id")
    private TypeDepence typeDepence;
    @ManyToOne
    @JoinColumn(name = "bourse_id")
    private Bourse bourse;


}