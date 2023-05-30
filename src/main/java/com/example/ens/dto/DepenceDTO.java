package com.example.ens.dto;

import com.example.ens.entities.Bourse;
import com.example.ens.entities.TypeDepence;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DepenceDTO {
    private Long id;
    private String refDepence;;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDepence;
    private double montantDepence;
    private String benificiaire;
    private TypeDepenceDTO typeDepence;
    private BourseDTO bourse;

}
