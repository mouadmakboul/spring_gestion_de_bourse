package com.example.ens.reposetory;

import com.example.ens.entities.TypeDepence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDepenceRepo extends JpaRepository<TypeDepence,Long> {
}
