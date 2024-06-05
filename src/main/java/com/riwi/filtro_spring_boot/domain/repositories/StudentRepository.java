package com.riwi.filtro_spring_boot.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.riwi.filtro_spring_boot.domain.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
    
}

