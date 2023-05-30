package com.example.ens.reposetory;

import com.example.ens.entities.Depence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DepenceRepo extends JpaRepository<Depence,Long> {

    List<Depence> findAllByBourse_Id(Long id);
    List<Depence> findAllByTypeDepence_Id(Long id);

    @Query("select sum(d.montantDepence) from Depence d")
    double getSumDepense();

}
