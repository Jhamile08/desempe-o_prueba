package com.riwi.filtro_spring_boot.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassEntityRequest {
    @NotBlank(message = "Name is required")
    @Size(
        min = 1, 
        max = 100, 
        message = "Name must have between 1 and 100 letters"
    )
    private String name;
    @NotBlank(message = "Description is required")
    @Size(
        min = 1,
        message = "Description must have more than 1 letter"
    )
    private String description; 
    private boolean active;
}
