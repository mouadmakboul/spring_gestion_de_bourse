package com.example.ens.web;

import com.example.ens.dto.SourceDTO;
import com.example.ens.dto.TypeDepenceDTO;
import com.example.ens.exception.SourceException;
import com.example.ens.exception.TypeDepenceException;
import com.example.ens.service.IBourceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/TypeDepence")
public class TypeDepenceController {

    IBourceService service;


    @PostMapping()
    public TypeDepenceDTO saveTypeDepence(@RequestBody  TypeDepenceDTO typeDepenceDTO) {
        return service.saveTypeDepence(typeDepenceDTO);
    }
    @PutMapping("/{id}")
    public TypeDepenceDTO updateTypeDepence(@PathVariable(name = "id") Long id,@RequestBody TypeDepenceDTO typeDepenceDTO) throws TypeDepenceException{
        typeDepenceDTO.setId(id);
        return service.updateTypeDepence(typeDepenceDTO);
    }
    @GetMapping("/{id}")
    public TypeDepenceDTO getTypeDepenceById(@PathVariable(name = "id") Long id) throws TypeDepenceException{
        return service.getTypeDepenceById(id);
    }
    @GetMapping("AllTypeDepence")
    public List<TypeDepenceDTO> getAllTypeDepence(){
        return service.getAllTypeDepence();
    }
    @DeleteMapping("/{id}")
    public void deletTypeDepence(@PathVariable(name = "id") Long id) throws TypeDepenceException{
        service.deletTypeDepence(id);
    }
}
