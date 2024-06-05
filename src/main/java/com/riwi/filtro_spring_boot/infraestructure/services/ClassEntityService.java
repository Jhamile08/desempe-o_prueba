package com.riwi.filtro_spring_boot.infraestructure.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.type.descriptor.java.LocalDateTimeJavaType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.filtro_spring_boot.api.dto.request.ClassEntityRequest;
import com.riwi.filtro_spring_boot.api.dto.response.ClassEntityBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.ClassEntityResponse;
import com.riwi.filtro_spring_boot.domain.entities.ClassEntity;
import com.riwi.filtro_spring_boot.domain.repositories.ClassEntityRepository;
import com.riwi.filtro_spring_boot.infraestructure.abstract_service.IClassEntityService;
import com.riwi.filtro_spring_boot.utils.enums.SortType;
import com.riwi.filtro_spring_boot.utils.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClassEntityService implements IClassEntityService{

    @Autowired
    private ClassEntityRepository classEntityRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private LessonService lessonService;

    @Override
    public ClassEntityResponse create(ClassEntityRequest request) {

        ClassEntity classEntity = this.requestToEntity(request);

        classEntity.setLessons(new ArrayList<>());
        classEntity.setStudents(new ArrayList<>());
        classEntity.setCreated_at(LocalDateTime.now());

        return  this.entityToResp(this.classEntityRepository.save(classEntity));

    }

    @Override
    public ClassEntityResponse get(Long id) {
        return this.entityToResp(this.find(id));
    }

    @Override
    public ClassEntityResponse update(ClassEntityRequest request, Long id) {
        ClassEntity classEntity = this.find(id);
        ClassEntity classUpdate = this.requestToEntity(request);
        classUpdate.setId(id);
        return this.entityToResp(this.classEntityRepository.save(classUpdate));
    }

    @Override
    public void delete(Long id) {
        ClassEntity classEntity = this.find(id);
        this.classEntityRepository.delete(classEntity);
    }

    @Override
    public Page<ClassEntityResponse> getAll(int page, int size, SortType sort) {
        if(page<0) page = 0;
        PageRequest pagination= null;

        switch (sort){
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page,size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page,size, Sort.by(FIELD_BY_SORT).descending());
        }
        return this.classEntityRepository.findAll(pagination).map(this::entityToResp);
    }

    private ClassEntityResponse entityToResp(ClassEntity entity){
        ClassEntityResponse classResponse = new ClassEntityResponse();
        BeanUtils.copyProperties(entity, classResponse);

        classResponse.setLessons(lessonService.listToBasic(entity.getLessons()));
        classResponse.setStudents(studentService.listToBasic(entity.getStudents()));
        return classResponse;
    }

    private ClassEntity requestToEntity(ClassEntityRequest request){
        ClassEntity classEntity = new ClassEntity();
        BeanUtils.copyProperties(request,classEntity);
        return classEntity;
    }

    public List<ClassEntityBasicResponse> listToBasic(List<ClassEntity> list) {
        List<ClassEntityBasicResponse> classEntityBasicResponses = new ArrayList<>();
        classEntityBasicResponses = list.stream()
                .map(classes -> {
                    ClassEntityBasicResponse classEntityBasicResponse = new ClassEntityBasicResponse();
                    BeanUtils.copyProperties(classes, classEntityBasicResponse);
                    return classEntityBasicResponse;
                })
                .collect(Collectors.toList());
         return classEntityBasicResponses;
    }
    private ClassEntity find(Long id) {
        return this.classEntityRepository.findById(id)
        .orElseThrow(()-> new BadRequestException("No hay clases con el id suministrado"));
    }
}
