package com.example.ens;

import com.example.ens.exception.BourseException;
import com.example.ens.reposetory.BourseRepo;
import com.example.ens.reposetory.DepenceRepo;
import com.example.ens.service.BourseService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@RestController


@AllArgsConstructor
public class EnsApplication {


    BourseRepo bourseRepo;
    DepenceRepo depenceRepo;
    BourseService bourseService;
    /*@Bean
    public double sumBoursee(){
        System.out.println("le montant des bourse est "+bourseRepo.getSumBourse());
        return bourseRepo.getSumBourse();
    }
    @Bean
    public double sumDepencee(){
        System.out.println("le montant de depance est "+depenceRepo.getSumDepense());
        return bourseRepo.getSumBourse();
    }
    @Bean
    public double solde(){
        System.out.println("le solde est : "+ bourseService.solde());
        return bourseService.solde();
    }
*/
    public static void main(String[] args) throws BourseException {

        SpringApplication.run(EnsApplication.class, args);

    }




}
