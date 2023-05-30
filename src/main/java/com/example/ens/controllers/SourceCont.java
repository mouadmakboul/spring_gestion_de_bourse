package com.example.ens.controllers;

import com.example.ens.dto.SourceDTO;
import com.example.ens.entities.Source;
import com.example.ens.exception.SourceException;
import com.example.ens.reposetory.SourceRepo;
import com.example.ens.service.BourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("")
public class SourceCont {
    @Autowired
    BourseService bourseService;
    SourceRepo sourceRepo;
    @RequestMapping("/createSource")
    public String createSource(){

        return "source/CreateSource";
    }
    @GetMapping()
    public String index(){

        return "Index";
    }

    @RequestMapping("/index")
    public String menu(){
        return "Index";
    }
    @RequestMapping("/saveSource")
    public String saveSource(@ModelAttribute("source") SourceDTO source, ModelMap modelMap) {


        SourceDTO memo = bourseService.saveSource(source);
        return "source/CreateSource";

    }


    @RequestMapping("/updateSource")
    public String updateSource(@ModelAttribute("source") SourceDTO source, ModelMap modelMap,
                               @RequestParam(name = "page" ,defaultValue = "0") int page,
                               @RequestParam(name = "size" ,defaultValue = "4" )int size) throws SourceException {
        SourceDTO memo = bourseService.updateSource(source);
        Page<Source> sourceController = bourseService.getAllSourceByPage(page,size);
        modelMap.addAttribute("sourceJsp",sourceController);
        modelMap.addAttribute("pages", new int[sourceController.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "source/sourceListe";

    }


    @RequestMapping("/allSource")
    public String allSource(ModelMap modelMap,
                            @RequestParam(name = "page" ,defaultValue = "0") int page,
                            @RequestParam(name = "size" ,defaultValue = "4" )int size){
        Page<Source> sourceController = bourseService.getAllSourceByPage(page,size);
        modelMap.addAttribute("sourceJsp",sourceController);
        modelMap.addAttribute("pages", new int[sourceController.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "source/sourceListe";
    }

    @RequestMapping("/deleteSource")
    public String deleteSource(@RequestParam("id") Long id, ModelMap modelMap,
                               @RequestParam(name = "page" ,defaultValue = "0") int page,
                               @RequestParam(name = "size" ,defaultValue = "10" )int size) throws SourceException {
        bourseService.deleteSource(id);
        Page<Source> sourceController = bourseService.getAllSourceByPage(page,size);
        modelMap.addAttribute("sourceJsp",sourceController);
        modelMap.addAttribute("pages", new int[sourceController.getTotalPages()]);
        modelMap.addAttribute("currentPage", page);
        return "source/sourceListe";
    }


    @RequestMapping("/showSource")
    public String showSource(@RequestParam("id") Long id, ModelMap modelMap) throws SourceException {
        SourceDTO sourceController = bourseService.getSourceById(id);
        modelMap.addAttribute("sourceJsp", sourceController);
        return "source/editeSource";
    }




}
