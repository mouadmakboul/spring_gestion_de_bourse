package com.example.ens.dto.req;

import com.example.ens.dto.BourseDTO;
import com.example.ens.dto.SourceDTO;
import com.example.ens.entities.Source;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BourseReq {

    private Long id;
    private String refBourse;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateBourse;
    private double montantBourse;
    private Long sourceId;
}
