package com.example.ens.controllers;

import com.example.ens.dto.BourseDTO;
import com.example.ens.dto.SourceDTO;
import com.example.ens.dto.TypeDepenceDTO;
import com.example.ens.entities.TypeDepence;
import com.example.ens.exception.BourseException;
import com.example.ens.exception.TypeDepenceException;
import com.example.ens.service.BourseService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class TypeCont {
    BourseService bourseService;

    @RequestMapping("/createTypeDepence")
    public String createType(){

        return "typeDepence/createType";
    }
    //save
    @RequestMapping("/saveType")
    public String saveType(@ModelAttribute("typeDepence") TypeDepenceDTO typeDepenceDTO, ModelMap modelMap) {
        TypeDepenceDTO memo = bourseService.saveTypeDepence(typeDepenceDTO);
        return "typeDepence/createType";
    }
    //update
    @RequestMapping("/updateType")
    public String updateType(@ModelAttribute("typeDepence") TypeDepenceDTO typeDepenceDTO, ModelMap modelMap,
                             @RequestParam(name = "page" ,defaultValue = "0") int page,
                             @RequestParam(name = "size" ,defaultValue = "10" )int size) throws TypeDepenceException {
        TypeDepenceDTO memo = bourseService.updateTypeDepence(typeDepenceDTO);
        Page<TypeDepence> typeController = bourseService.getAllTypeByPage(page, size);
        modelMap.addAttribute("typeDepence",typeController);
        modelMap.addAttribute("pages", new int[typeController.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "typeDepence/typeListe";

    }

    //show for edit

    @RequestMapping("/showType")
    public String showType(@RequestParam("id") Long id, ModelMap modelMap) throws TypeDepenceException {
        TypeDepenceDTO bourseController = bourseService.getTypeDepenceById(id);
        modelMap.addAttribute("typeDepence", bourseController);
        return "typeDepence/editeType";
    }


    //lister
    @RequestMapping("/allType")
    public String allType(ModelMap modelMap,
                          @RequestParam(name = "page" ,defaultValue = "0") int page,
                          @RequestParam(name = "size" ,defaultValue = "10" )int size){
        Page<TypeDepence> typeController = bourseService.getAllTypeByPage(page, size);
        modelMap.addAttribute("typeDepence",typeController);
        modelMap.addAttribute("pages", new int[typeController.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "typeDepence/typeListe";
    }

    //delete
    @RequestMapping("/deleteType")
    public String deleteType(@RequestParam("id") Long id, ModelMap modelMap,
                             @RequestParam(name = "page" ,defaultValue = "0") int page,
                             @RequestParam(name = "size" ,defaultValue = "10" )int size) throws TypeDepenceException {
        bourseService.deletTypeDepence(id);
        Page<TypeDepence> typeController = bourseService.getAllTypeByPage(page, size);
        modelMap.addAttribute("typeDepence",typeController);
        modelMap.addAttribute("pages", new int[typeController.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "typeDepence/typeListe";
    }

}
