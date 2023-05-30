package com.example.ens.mapper;

import com.example.ens.dto.*;
import com.example.ens.entities.*;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BourseMapper implements IBourseMapper {

    @Override
    public BourseDTO fromBourse(Bourse bourse) {
        BourseDTO bourseDTO=new BourseDTO();
        BeanUtils.copyProperties(bourse,bourseDTO);
        bourseDTO.setSource(this.fromSource(bourse.getSource()));
        return bourseDTO;
    }

    @Override
    public Bourse fromBourseDTO(BourseDTO bourseDTO){
        Bourse bourse=new Bourse();
        BeanUtils.copyProperties(bourseDTO,bourse);
        bourse.setSource(this.fromSourceDTO(bourseDTO.getSource()));
        return bourse;
    }
    @Override
    public DepenceDTO fromDepence(Depence depence){
        DepenceDTO depenceDTO=new DepenceDTO();
        BeanUtils.copyProperties(depence,depenceDTO);
        depenceDTO.setTypeDepence(this.fromTypeDepence(depence.getTypeDepence()));
        depenceDTO.setBourse(this.fromBourse(depence.getBourse()));
        return depenceDTO;
    }
    @Override
    public Depence fromDepenceDTO(DepenceDTO depenceDTO){
        Depence depence=new Depence();
        BeanUtils.copyProperties(depenceDTO,depence);
        depence.setTypeDepence(this.fromTypeDepenceDTO(depenceDTO.getTypeDepence()));
        depence.setBourse(this.fromBourseDTO(depenceDTO.getBourse()));
        return depence;
    }

    @Override
    public SourceDTO fromSource(Source source){
        SourceDTO sourceDTO=new SourceDTO();
        BeanUtils.copyProperties(source,sourceDTO);
        return sourceDTO;
    }

    @Override
    public Source fromSourceDTO(SourceDTO sourceDTO){
        Source source=new Source();
        BeanUtils.copyProperties(sourceDTO,source);
        return source;
    }

    @Override
    public TypeDepenceDTO fromTypeDepence(TypeDepence typeDepence){
        TypeDepenceDTO typeDepenceDTO=new TypeDepenceDTO();
        BeanUtils.copyProperties(typeDepence,typeDepenceDTO);
        return typeDepenceDTO;
    }
    @Override
    public TypeDepence fromTypeDepenceDTO(TypeDepenceDTO typeDepenceDTO){
        TypeDepence typeDepence=new TypeDepence();
        BeanUtils.copyProperties(typeDepenceDTO,typeDepence);
        return typeDepence;
    }





}
