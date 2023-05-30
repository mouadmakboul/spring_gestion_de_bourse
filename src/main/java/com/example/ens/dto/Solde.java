package com.example.ens.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class Solde {
    private Long id;
    private Date date;
    private Double solde;

}
