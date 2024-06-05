package com.riwi.filtro_spring_boot.domain.entities;
import java.time.LocalDateTime;

import com.riwi.filtro_spring_boot.utils.enums.MultimediaType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "multimedia")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Multimedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;
    @Column(nullable = false)    
    private MultimediaType type;
    @Column(nullable = false)    
    private String url;
    @Column(nullable = false)    
    private LocalDateTime created_at;
    @Column(nullable = false)    
    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id",referencedColumnName = "id" )
    private Lesson lesson;
}
