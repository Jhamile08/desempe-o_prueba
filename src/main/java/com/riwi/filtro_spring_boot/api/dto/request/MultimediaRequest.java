package com.riwi.filtro_spring_boot.api.dto.request;

import com.riwi.filtro_spring_boot.utils.enums.MultimediaType;

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
public class MultimediaRequest {
    @NotBlank(message = "Type is required")
    private MultimediaType type; 
    @NotBlank(message = "Url is required")
    @Size(
        min = 1,
        message = "Url must have more than 1 letter"
    )
    private String url; 
    @NotNull(message = "Active is required")  
    private boolean active;
}
