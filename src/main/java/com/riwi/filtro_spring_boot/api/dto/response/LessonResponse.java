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
public class LessonResponse {
    private Long id;    
    private String title;    
    private String content;    
    private LocalDateTime created_at;    
    private boolean active;
    private ClassEntityResponse classEntity;
    private List<MultimediaResponse> multimedias;
}
