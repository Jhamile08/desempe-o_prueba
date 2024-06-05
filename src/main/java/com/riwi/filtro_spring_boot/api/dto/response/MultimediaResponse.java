package com.riwi.filtro_spring_boot.api.dto.response;

import java.time.LocalDateTime;

import com.riwi.filtro_spring_boot.utils.enums.MultimediaType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MultimediaResponse {  
    private Long id; 
    private MultimediaType type; 
    private String url; 
    private LocalDateTime created_at; 
    private boolean active;
}
