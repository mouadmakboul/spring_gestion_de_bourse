package com.example.ens.reposetory;

import com.example.ens.entities.Source;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepo extends JpaRepository<Source,Long> {

}
