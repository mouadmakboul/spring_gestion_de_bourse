package com.example.ens.web;

import com.example.ens.dto.BourseDTO;
import com.example.ens.dto.req.BourseReq;
import com.example.ens.entities.Bourse;
//import com.example.ens.controller.*;
import com.example.ens.exception.BourseException;
import com.example.ens.exception.SourceException;
import com.example.ens.service.IBourceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/Bourse")
@Slf4j
public class BourseController {

    IBourceService service;


    @PostMapping()
    public ResponseEntity<BourseDTO> saveBourse(@RequestBody BourseReq req) throws  SourceException {
        BourseDTO bourseDTO = service.saveBourse(req);
        return new ResponseEntity<>(bourseDTO, HttpStatus.OK);
   }
//    @PutMapping("/{id}")
//    public BourseDTO updateBourse(@PathVariable(name = "id") Long id,@RequestBody BourseReq bourseDTO) throws BourseException, SourceException {
//        bourseDTO.setId(id);
//        return service.updateBourse(bourseDTO);
//    }
    @GetMapping("/{id}")
    public BourseDTO getBourseById(@PathVariable(name = "id") Long id) throws BourseException{
        return service.getBourseById(id);
    }
    @GetMapping("AllBourse")
    public List<BourseDTO> getAllBourse(){
        return service.getAllBourse();
    }
    @DeleteMapping("/{id}")
    public void deletBourse(@PathVariable(name = "id") Long id) throws BourseException{
         service.deletBourse(id);
    }
    @GetMapping("/BySource/{id}")
    public List<BourseDTO> findAllBysource_id(@PathVariable(name = "id") Long id) throws SourceException{
        return service.findAllBysource_id(id);
    }
    @GetMapping("sum")
    public double sumBourse() throws BourseException {
        return service.sumBourse();
    }

    /*@GetMapping("/source/id")
    public List<BourseDTO> getAllBourseBySource(@PathVariable Long id) throws SourceException, BourseException {
        return (List<BourseDTO>) service.getAllBourseBySource(id);
    }*/



}


