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

import com.riwi.filtro_spring_boot.api.dto.request.LessonRequest;
import com.riwi.filtro_spring_boot.api.dto.response.LessonResponse;
import com.riwi.filtro_spring_boot.infraestructure.abstract_service.ILessonService;
import com.riwi.filtro_spring_boot.utils.enums.SortType;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/lessons")
@AllArgsConstructor
public class LessonController {
    @Autowired
    private final ILessonService lessonService;

    @Operation(summary = "Delete class", description = "Retrieve a list of all users")
    @GetMapping
    public ResponseEntity<Page<LessonResponse>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType
    ) {
        if (Objects.isNull(sortType))
            sortType = SortType.NONE;
        return ResponseEntity.ok(this.lessonService.getAll(page - 1, size, sortType));
    }

    @Operation(summary = "Delete class", description = "Retrieve a list of all users")
    @GetMapping(path = "/{id}")
    public ResponseEntity<LessonResponse> get(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(this.lessonService.get(id));
    }

    @Operation(summary = "Delete class", description = "Retrieve a list of all users")
    @PostMapping
    public ResponseEntity<LessonResponse> insert(
            @Validated @RequestBody LessonRequest request) {
        System.out.println(request);
        return ResponseEntity.ok(this.lessonService.create(request));
    }

    @Operation(summary = "Delete class", description = "Retrieve a list of all users")
    @PutMapping(path = "/{id}")
    public ResponseEntity<LessonResponse> update(
            @Validated @RequestBody LessonRequest request,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(this.lessonService.update(request, id));
    }

    @Operation(summary = "Delete class", description = "Retrieve a list of all users")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ){
        this.lessonService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
