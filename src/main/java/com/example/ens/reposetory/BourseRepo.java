package com.example.ens.reposetory;

import com.example.ens.entities.Bourse;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BourseRepo extends JpaRepository<Bourse,Long> {


    List<Bourse> findAllBySource_Id(Long id);



    @Query("select sum(b.montantBourse) from Bourse b")
    double getSumBourse();
}
