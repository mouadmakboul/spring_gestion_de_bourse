package com.example.ens.controllers;

import com.example.ens.dto.BourseDTO;
import com.example.ens.dto.DepenceDTO;
import com.example.ens.exception.BourseException;
import com.example.ens.exception.DepenceException;
import com.example.ens.reposetory.BourseRepo;
import com.example.ens.reposetory.DepenceRepo;
import com.example.ens.service.BourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CalculeCont {
    BourseService bourseService;

    @RequestMapping("/solde")
    public String solde(ModelMap modelMap,BourseRepo bourseRepo) throws BourseException, DepenceException {

        String sumBourse= String.valueOf(bourseRepo.getSumBourse());
        modelMap.addAttribute("sumB", sumBourse);
        //modelMap.addAttribute("sum", sumDepence);
        return "/sold";
    }
}
