package com.example.ens.mapper;

import com.example.ens.dto.*;
import com.example.ens.entities.Bourse;
import com.example.ens.entities.Depence;
import com.example.ens.entities.Source;
import com.example.ens.entities.TypeDepence;

public interface IBourseMapper {
    BourseDTO fromBourse(Bourse bourse);
    Bourse fromBourseDTO(BourseDTO bourseDTO);

    DepenceDTO fromDepence(Depence depence);
    Depence fromDepenceDTO(DepenceDTO depenceDTO);

    SourceDTO fromSource(Source source);
    Source fromSourceDTO(SourceDTO sourceDTO);

    TypeDepenceDTO fromTypeDepence(TypeDepence typeDepence);
    TypeDepence fromTypeDepenceDTO(TypeDepenceDTO typeDepenceDTO);

}
