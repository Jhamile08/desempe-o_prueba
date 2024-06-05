package com.riwi.filtro_spring_boot.api.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riwi.filtro_spring_boot.api.dto.request.ClassEntityRequest;
import com.riwi.filtro_spring_boot.api.dto.response.ClassEntityResponse;
import com.riwi.filtro_spring_boot.infraestructure.abstract_service.IClassEntityService;
import com.riwi.filtro_spring_boot.utils.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/classEntity")
@AllArgsConstructor
public class ClassEntityController {

    @Autowired
    private final IClassEntityService classEntityService;

    @Operation(summary = "Get all class", description = "Retrieve a list of all users")
    @GetMapping
    public ResponseEntity<Page<ClassEntityResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType
    ) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;
        return ResponseEntity.ok(this.classEntityService.getAll(page - 1, size, sortType));
    }

    @Operation(summary = "Get a class", description = "Retrieve a list of all users")
    @GetMapping(path = "/{id}")
    public ResponseEntity<ClassEntityResponse> get(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(this.classEntityService.get(id));
    }

    @Operation(summary = "Create a class", description = "Retrieve a list of all users")
    @PostMapping
    public ResponseEntity<ClassEntityResponse> insert(
            @Validated @RequestBody ClassEntityRequest request
            ){
            System.out.println(request);
        return ResponseEntity.ok(this.classEntityService.create(request));
    }

    @Operation(summary = "Modify a class", description = "Retrieve a list of all users")
    @PutMapping(path = "/{id}")
    public ResponseEntity<ClassEntityResponse> update(
            @Validated @RequestBody ClassEntityRequest request,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(this.classEntityService.update(request, id));
    }

    @Operation(summary = "Delete class", description = "Retrieve a list of all users")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ){
        this.classEntityService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
