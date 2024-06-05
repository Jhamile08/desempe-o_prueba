package com.riwi.filtro_spring_boot.domain.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "classEntity")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;
    @Column(nullable = false)    
    private String name;
    @Column(nullable = false)    
    private String description;
    @Column(nullable = false)    
    private LocalDateTime created_at;
    @Column(nullable = false)    
    private boolean active;

    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "classEntity",
        cascade = CascadeType.ALL,
        orphanRemoval = false  
    )
    private List<Student> students;

    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "classEntity",
        cascade = CascadeType.ALL,
        orphanRemoval = false  
    )
    private List<Lesson> lessons;
}
