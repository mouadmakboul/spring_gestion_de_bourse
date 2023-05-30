package com.example.ens.dto.req;

import com.example.ens.dto.BourseDTO;
import com.example.ens.dto.DepenceDTO;
import com.example.ens.dto.TypeDepenceDTO;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Getter
@Setter
@ToString
public class DepenceReq  {
    private Long id;
    private String refDepence;;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDepence;
    private double montantDepence;
    private String benificiaire;
    private Long typeDepenceId;
    private Long bourseId;
}
