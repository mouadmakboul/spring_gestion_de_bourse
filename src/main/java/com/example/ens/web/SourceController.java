package com.example.ens.web;

import com.example.ens.dto.SourceDTO;
import com.example.ens.exception.SourceException;
import com.example.ens.service.IBourceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/Source")
public class SourceController {
    IBourceService service;


    @PostMapping()
    public SourceDTO saveSource(@RequestBody SourceDTO sourceDTO)throws SourceException {
        return service.saveSource(sourceDTO);
    }
    @PutMapping("/{id}")
    public SourceDTO updateSource(@PathVariable(name = "id") Long id,@RequestBody SourceDTO sourceDTO) throws SourceException{
        sourceDTO.setId(id);
        return service.updateSource(sourceDTO);
    }
    @GetMapping("/{id}")
    public SourceDTO getSourceById(@PathVariable(name = "id") Long id) throws SourceException{
        try
        {
            return service.getSourceById(id);
        }catch (SourceException e)
        {

           throw  new SourceException("Source not found");
        }
    }
    @GetMapping("AllSource")
    public List<SourceDTO> getAllSource(){

        return service.getAllSource();
    }
    @DeleteMapping("/{id}")
    public void deleteSource(@PathVariable(name = "id") Long id) throws SourceException{
        service.deleteSource(id);
    }

}
