package com.riwi.filtro_spring_boot.api.dto.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassEntityResponse { 
    private Long id;
    private String name;
    private String description;
    private LocalDateTime created_at;
    private boolean active;
    private List<StudentBasicResponse> students;
    private List<LessonBasicResponse> lessons;
}
