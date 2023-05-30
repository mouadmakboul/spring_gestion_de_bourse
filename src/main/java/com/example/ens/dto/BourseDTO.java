package com.example.ens.dto;


import com.example.ens.entities.Source;
import jakarta.persistence.*;
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
public class BourseDTO {
    private Long id;
    private String refBourse;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateBourse;
    private double montantBourse;
    private SourceDTO source;
}
