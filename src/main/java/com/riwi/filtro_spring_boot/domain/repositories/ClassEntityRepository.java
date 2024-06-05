package com.riwi.filtro_spring_boot.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.filtro_spring_boot.domain.entities.ClassEntity;

@Repository
public interface ClassEntityRepository extends JpaRepository<ClassEntity, Long>{
    
}
