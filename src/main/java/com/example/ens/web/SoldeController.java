package com.example.ens.web;

import com.example.ens.service.BourseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/Solde")
public class SoldeController {
    BourseService service;

    @GetMapping("/solde")
    public double solde(){
        return service.solde();
    }
}
