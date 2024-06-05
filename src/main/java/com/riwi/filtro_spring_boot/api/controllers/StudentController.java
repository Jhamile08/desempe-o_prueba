package com.riwi.filtro_spring_boot.api.controllers;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.filtro_spring_boot.api.dto.request.StudentRequest;
import com.riwi.filtro_spring_boot.api.dto.response.StudentResponse;
import com.riwi.filtro_spring_boot.infraestructure.abstract_service.IStudentService;
import com.riwi.filtro_spring_boot.utils.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/students")
@AllArgsConstructor
public class StudentController {

    @Autowired
    private IStudentService studentService;

    @Operation(summary = "Get all students", description = "Retrieve a list of all users")
    @GetMapping
    public ResponseEntity<Page<StudentResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType
    ){
        if(Objects.isNull(sortType))
            sortType = SortType.NONE;
        
        return ResponseEntity.ok(this.studentService.getAll(page -1, size, sortType));
    }

    @Operation(summary = "Get a student", description = "Retrieve a list of all users")
    @GetMapping(path = "/{id}")
    public ResponseEntity<StudentResponse> get(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(this.studentService.get(id));
    }

    @Operation(summary = "Get a student by name", description = "Retrieve a list of all users")
    @GetMapping(path = "/{name}")
    public ResponseEntity<StudentResponse> findByName(
            @PathVariable String name
    ) {
        return ResponseEntity.ok(this.studentService.findName(name));
    }

    @Operation(summary = "Create student", description = "Retrieve a list of all users")
    @PostMapping
    public ResponseEntity<StudentResponse> insert(
            @Validated @RequestBody StudentRequest request
            ){
        return ResponseEntity.ok(this.studentService.create(request));
    }

    @Operation(summary = "Modify a student by id", description = "Retrieve a list of all users")
    @PutMapping(path = "/{id}")
    public ResponseEntity<StudentResponse> update(
            @Validated @RequestBody StudentRequest request,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(this.studentService.update(request, id));
    }

    @Operation(summary = "Disable a student", description = "Retrieve a list of all users")
    @PatchMapping(path = "/{id}/disable")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ){
        this.studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
