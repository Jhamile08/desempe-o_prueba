package com.riwi.filtro_spring_boot.api.dto.request;


import jakarta.validation.constraints.Email;
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
public class StudentRequest {
    @NotBlank(message = "Name is required")
    @Size(
        min = 1, 
        max = 100, 
        message = "Name must have between 1 and 100 letters"
    )
    private String name;
    @Size(
        min = 2, 
        max = 100, 
        message = "Email must be have 4 and 100 letters"
    )
    @Email
    private String email;
    @NotNull(message = "Active is required")
    private boolean active;
}
