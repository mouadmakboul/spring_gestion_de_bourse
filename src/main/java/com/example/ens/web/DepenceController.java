package com.example.ens.web;

import com.example.ens.dto.BourseDTO;
import com.example.ens.dto.DepenceDTO;
import com.example.ens.dto.TestDepenceDTO;
import com.example.ens.dto.req.BourseReq;
import com.example.ens.dto.req.DepenceReq;
import com.example.ens.entities.Bourse;
import com.example.ens.entities.Depence;
import com.example.ens.exception.BourseException;
import com.example.ens.exception.DepenceException;
import com.example.ens.exception.SourceException;
import com.example.ens.exception.TypeDepenceException;
import com.example.ens.service.IBourceService;
import jakarta.websocket.DecodeException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/Depence")
public class DepenceController {
    IBourceService service;


    @PostMapping()
    public DepenceDTO saveDepence(@RequestBody DepenceReq req) throws BourseException, TypeDepenceException, DepenceException {
            //DepenceDTO depenceDTO = service.saveDepence(req);
            //return service.saveDepence(req);
        return null;
    }

    @PutMapping("/'{id}")
    public TestDepenceDTO updateDepence(@PathVariable(name = "id") Long id, @RequestBody DepenceReq depenceDTO) throws DepenceException, BourseException {
        depenceDTO.setId(id);
        return service.updateDepence(depenceDTO);
    }
    @GetMapping("/{id}")
    public DepenceDTO getDepenceById(@PathVariable(name = "id") Long id) throws DepenceException{
        return service.getDepenceById(id);
    }
    @GetMapping("AllDepence")
    public List<DepenceDTO> getAllDepence(){
        return service.getAllDepence();
    }
    @DeleteMapping("/{id}")
    public void deletDepence(@PathVariable(name = "id") Long idDeprnce) throws DepenceException{
        service.deletDepence(idDeprnce);
    }

    @GetMapping("/Bourse/{id}")
    public List<DepenceDTO> getAllDepenceByBourse(@PathVariable(name="id") Long id) throws BourseException {
        return service.findAllByBourse_Id(id);
    }
    @GetMapping("/Type/{id}")
    public List<DepenceDTO> findAllByTypeDepence_Id(@PathVariable(name = "id") Long id) throws TypeNotPresentException, TypeDepenceException {
        return service.findAllByTypeDepence_Id(id);
    }
    @GetMapping("/sum")
    public double sumDepence() throws DepenceException{
        return service.sumDepence();
    }
}
