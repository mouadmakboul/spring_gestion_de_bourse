package com.example.ens.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TypeDepence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String objDepence;
    /*@OneToMany(mappedBy = "typeDepence")
    List<Depence> depences = new ArrayList<>();*/


}
