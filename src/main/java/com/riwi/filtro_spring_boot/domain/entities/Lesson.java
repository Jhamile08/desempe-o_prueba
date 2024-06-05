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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "lessons")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;
    @Column(nullable = false)    
    private String title;
    @Column(nullable = false)    
    private String content;
    @Column(nullable = false)    
    private LocalDateTime created_at;
    @Column(nullable = false)    
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id",referencedColumnName = "id" )
    private ClassEntity classEntity;

    @OneToMany(
        fetch = FetchType.EAGER,
        mappedBy = "lesson",
        cascade = CascadeType.ALL,
        orphanRemoval = false  
    )
    private List<Multimedia> multimedias;
}
