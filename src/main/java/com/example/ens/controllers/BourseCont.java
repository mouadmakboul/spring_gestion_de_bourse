package com.example.ens.controllers;

import com.example.ens.dto.BourseDTO;
import com.example.ens.dto.DepenceDTO;
import com.example.ens.dto.SourceDTO;
import com.example.ens.dto.req.BourseReq;
import com.example.ens.entities.Bourse;
import com.example.ens.exception.BourseException;
import com.example.ens.exception.SourceException;
import com.example.ens.service.BourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("")
public class BourseCont {
    @Autowired
    BourseService bourseService;
    //create
    @GetMapping("/createBourse")
    public String createBourse(ModelMap modelMap){
        List<SourceDTO> listSource = bourseService.getAllSource();
        System.out.println(listSource);
        modelMap.addAttribute("listesource",listSource);
        modelMap.addAttribute("source",new SourceDTO());
        return "bourse/createBourse";
    }
    //save
    @RequestMapping("/saveBourse")
    public String saveBourse(@ModelAttribute("bourse") BourseReq req ) throws SourceException {
        BourseDTO memo = bourseService.saveBourse(req);
        return "bourse/createBourse";
    }
    //update
    @RequestMapping("/updateBourse")
    public String updateBourse(@ModelAttribute("bourse") BourseReq req, ModelMap modelMap,
                               @RequestParam(name = "page" ,defaultValue = "0") int page,
                               @RequestParam(name = "size" ,defaultValue = "10" )int size) throws BourseException, SourceException {
        System.out.println(req.toString());
        BourseDTO memo = bourseService.updateBourse(req);
        Page<Bourse> bourseController = bourseService.getAllBourseByPage(page, size);
        modelMap.addAttribute("bourse",bourseController);
        modelMap.addAttribute("pages", new int[bourseController.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "bourse/bourseListe";

    }

    //show for edit

    @RequestMapping("/showBourse")
    public String showBourse(@RequestParam("id") Long id, ModelMap modelMap) throws BourseException {


        BourseDTO bourseController = bourseService.getBourseById(id);
        modelMap.addAttribute("bourse", bourseController);
        List<SourceDTO> listSource = bourseService.getAllSource();
        modelMap.addAttribute("listesource",listSource);
        modelMap.addAttribute("source",new SourceDTO());
        return "bourse/editBourse";
    }


    //lister
    @RequestMapping("/allBourse")
    public String allBourse(ModelMap modelMap ,
                            @RequestParam(name = "page" ,defaultValue = "0") int page,
                            @RequestParam(name = "size" ,defaultValue = "10" )int size){
        Page<Bourse> bourseController = bourseService.getAllBourseByPage(page, size);
        modelMap.addAttribute("bourse",bourseController);
        modelMap.addAttribute("pages", new int[bourseController.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "bourse/bourseListe";
    }

    //delete
    @RequestMapping("/deleteBourse")
    public String deleteBourse(@RequestParam("id") Long id, ModelMap modelMap,
                               @RequestParam(name = "page" ,defaultValue = "0") int page,
                               @RequestParam(name = "size" ,defaultValue = "10" )int size) throws BourseException {
        bourseService.deletBourse(id);
        Page<Bourse> bourseController = bourseService.getAllBourseByPage(page, size);
        modelMap.addAttribute("bourse",bourseController);
        modelMap.addAttribute("pages", new int[bourseController.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "bourse/bourseListe";
    }


}