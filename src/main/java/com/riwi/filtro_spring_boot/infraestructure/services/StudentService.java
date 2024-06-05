package com.riwi.filtro_spring_boot.infraestructure.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.filtro_spring_boot.api.dto.request.StudentRequest;
import com.riwi.filtro_spring_boot.api.dto.response.ClassEntityBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.StudentBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.StudentResponse;
import com.riwi.filtro_spring_boot.domain.entities.ClassEntity;
import com.riwi.filtro_spring_boot.domain.entities.Student;
import com.riwi.filtro_spring_boot.domain.repositories.ClassEntityRepository;
import com.riwi.filtro_spring_boot.domain.repositories.StudentRepository;
import com.riwi.filtro_spring_boot.infraestructure.abstract_service.IStudentService;
import com.riwi.filtro_spring_boot.utils.enums.SortType;
import com.riwi.filtro_spring_boot.utils.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService implements IStudentService{

    @Autowired
    private final StudentRepository studentRepository;
    @Autowired
    private final ClassEntityRepository classEntityRepository;


    @Override
    public StudentResponse create(StudentRequest request) {
        Student student =  this.requestToEntity(request);
        student.setCreated_at(LocalDateTime.now());

        ClassEntity classEntity = this.classEntityRepository.findById(request.getClassEntityId()).orElseThrow(()-> new BadRequestException("class"));

        student.setClassEntity(classEntity);
        StudentResponse studentResponse = new StudentResponse();

        studentResponse = this.entityToResponse(this.studentRepository.save(student));
        return studentResponse;
    }

    @Override
    public StudentResponse get(Long id) {
        return this.entityToResponse(this.find(id));
    }

    @Override
    public StudentResponse update(StudentRequest request, Long id) {
        Student student = this.find(id);
        ClassEntity classEntity = this.classEntityRepository.findById(request.getClassEntityId()).orElseThrow(()-> new BadRequestException("class"));

        Student studentUpdate = this.requestToEntity(request);
        studentUpdate.setId(id);
        studentUpdate.setClassEntity(classEntity);

        StudentResponse studentResponse = new StudentResponse();
        studentResponse = this.entityToResponse(this.studentRepository.save(studentUpdate));
        return studentResponse;
    }

    @Override
    public void delete(Long id) {
        Student student = this.find(id);
        this.studentRepository.delete(student);
    }

    public Page<StudentResponse> getAll(int page, int size, SortType sort) { 
        if(page<0) page = 0;
        PageRequest pagination = null;

        switch (sort){
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page,size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page,size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.studentRepository.findAll(pagination).map(this::entityToResponse);
    }

    private StudentResponse entityToResponse(Student entity){
        StudentResponse studentResponse = new StudentResponse();
        BeanUtils.copyProperties(entity, studentResponse);

        ClassEntityBasicResponse classEntityBasicResponse = new ClassEntityBasicResponse();
        BeanUtils.copyProperties(entity.getClassEntity(), classEntityBasicResponse);

        studentResponse.setClassEntity(classEntityBasicResponse);
        return studentResponse;
    }

    public List<StudentBasicResponse> listToBasic(List<Student> list) {
        List<StudentBasicResponse> studentBasicResponses = new ArrayList<>();
        studentBasicResponses = list.stream()
                .map(student -> {
                    StudentBasicResponse studentBasicResponse = new StudentBasicResponse();
                    BeanUtils.copyProperties(student, studentBasicResponse);
                    return studentBasicResponse;
                })
                .collect(Collectors.toList());
         return studentBasicResponses;
    }

    private Student requestToEntity(StudentRequest request){
        Student student = new Student();
        BeanUtils.copyProperties(request,student);
        return student;
    }

    
    private Student find(Long id){
        return this.studentRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("estudiante"));
    }




    @Override
    public StudentResponse findName(String name) {
        StudentResponse student = this.studentRepository.findByName(name);
        return student;
    }


    
}
