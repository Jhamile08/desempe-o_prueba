package com.riwi.filtro_spring_boot.api.dto.request;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonRequest {
    @NotBlank(message = "title is required")
    @Size(
        min = 1, 
        max = 100, 
        message = "Title must have between 1 and 100 letters"
    )
    private String title;   
    @NotBlank(message = "content is required")
    @Size(
        min = 1,
        message = "content must have more than 1 letter"
    )
    private String content;  
    @NotNull(message = "Date is required")  
    private LocalDateTime created_at;   
    @NotNull(message = "Active is required") 
    private boolean active;
    private List<MultimediaRequest> multimedia;
}
