package com.example.ens.controllers;

import com.example.ens.dto.*;
import com.example.ens.dto.req.BourseReq;
import com.example.ens.dto.req.DepenceReq;
import com.example.ens.entities.Bourse;
import com.example.ens.entities.Depence;
import com.example.ens.exception.BourseException;
import com.example.ens.exception.DepenceException;
import com.example.ens.exception.SourceException;
import com.example.ens.exception.TypeDepenceException;
import com.example.ens.mapper.BourseMapper;
import com.example.ens.reposetory.BourseRepo;
import com.example.ens.service.BourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("")

public class DepenceCont {

    @Autowired
    BourseService bourseService;
    BourseRepo bourseRepo;
    //create
    @GetMapping("/createDepence")
    public String createDepence(ModelMap modelMap){

            List<TypeDepenceDTO> listTypeDepence = bourseService.getAllTypeDepence();
            List<BourseDTO> listBourse = bourseService.getAllBourse();
            modelMap.addAttribute("listTypeDepence",listTypeDepence);
            modelMap.addAttribute("listBourse",listBourse);
            modelMap.addAttribute("typedep",new TypeDepenceDTO());
            modelMap.addAttribute("listeBourse",new BourseDTO());


        return "depence/createDepence";
    }
    //save
//    @RequestMapping("/saveDepence")
//    public String saveDepence(@ModelAttribute("depence") DepenceReq req) throws BourseException, TypeDepenceException {
//
//            DepenceDTO memo = bourseService.saveDepence(req);
//
//
//        return "depence/createDepence";
//    }
    @RequestMapping("/saveDepence")
    public String saveDepence(@ModelAttribute("depence") DepenceReq req )throws BourseException, DepenceException, TypeDepenceException{
        TestDepenceDTO testDepenceDTO = bourseService.saveDepence(req);
        if(testDepenceDTO.isMoney())
        {
            return "depence/createDepence";
        }
        else
        {
            return "/money";
        }
    }

//    public String saveBourse(@ModelAttribute("bourse") BourseReq req ) throws SourceException {
//        BourseDTO memo = bourseService.saveBourse(req);
//        return "bourse/createBourse";
//    }
    //update
    @RequestMapping("/updateDepence")
    public String updateDepence(@ModelAttribute("depence") DepenceReq req, ModelMap modelMap,
                                @RequestParam(name = "page" ,defaultValue = "0") int page,
                                @RequestParam(name = "size" ,defaultValue = "10" )int size) throws DepenceException, BourseException {
        System.out.println(req.toString());
        TestDepenceDTO memo = bourseService.updateDepence(req);
        Page<Depence> depenceController = bourseService.getAllDepenceByPage(page,size);
        modelMap.addAttribute("depence",depenceController);
        modelMap.addAttribute("pages", new int[depenceController.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        if(memo.isMoney())
        {
            return "depence/createDepence";
        }
        else
        {
            return "/money";
        }

    }

    //show for edit

    @RequestMapping("/showDepence")
    public String showDepence(@RequestParam("id") Long id, ModelMap modelMap) throws DepenceException {
        DepenceDTO depenceController = bourseService.getDepenceById(id);
        modelMap.addAttribute("depence", depenceController);

        List<TypeDepenceDTO> listTypeDepence = bourseService.getAllTypeDepence();
        List<BourseDTO> listBourse = bourseService.getAllBourse();
        modelMap.addAttribute("listTypeDepence",listTypeDepence);
        modelMap.addAttribute("listBourse",listBourse);
        modelMap.addAttribute("typedep",new TypeDepenceDTO());
        modelMap.addAttribute("listeBourse",new BourseDTO());
        return "depence/editeDepence";
    }


    //lister
    @RequestMapping("/allDepence")
    public String allDepence(ModelMap modelMap,
                             @RequestParam(name = "page" ,defaultValue = "0") int page,
                             @RequestParam(name = "size" ,defaultValue = "10" )int size){
        Page<Depence> depenceController = bourseService.getAllDepenceByPage(page,size);
        modelMap.addAttribute("depence",depenceController);
        modelMap.addAttribute("pages", new int[depenceController.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "depence/depenceListe";
    }


    @RequestMapping("/all")
    public String allDepenceBourse(ModelMap modelMap) throws BourseException {

        List<DepenceDTO> depenceController = bourseService.getAllDepence();
        double solde =bourseService.sumBourse();

        modelMap.addAttribute("depence",depenceController);
        modelMap.addAttribute("total",solde);
        return "bdListe";
    }
    @GetMapping("/solde")
    public double solde(ModelMap modelMap) throws BourseException {

        double sumBourse = bourseService.sumBourse();
        modelMap.addAttribute("sum", sumBourse);
        return sumBourse;
    }

    //delete
    @RequestMapping("/deleteDepence")
    public String deleteDepence(@RequestParam("id") Long id, ModelMap modelMap,
                                @RequestParam(name = "page" ,defaultValue = "0") int page,
                                @RequestParam(name = "size" ,defaultValue = "10" )int size) throws DepenceException {
        bourseService.deletDepence(id);
        Page<Depence> depenceController = bourseService.getAllDepenceByPage(page,size);
        modelMap.addAttribute("depence",depenceController);
        modelMap.addAttribute("pages", new int[depenceController.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "depence/depenceListe";
    }


}
