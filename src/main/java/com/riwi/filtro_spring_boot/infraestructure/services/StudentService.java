package com.riwi.filtro_spring_boot.infraestructure.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.riwi.filtro_spring_boot.api.dto.request.StudentRequest;
import com.riwi.filtro_spring_boot.api.dto.response.StudentBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.StudentResponse;
import com.riwi.filtro_spring_boot.domain.entities.Student;
import com.riwi.filtro_spring_boot.infraestructure.abstract_service.IStudentService;
import com.riwi.filtro_spring_boot.utils.enums.SortType;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService implements IStudentService{

    @Override
    public StudentResponse create(StudentRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public StudentResponse get(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public StudentResponse update(StudentRequest request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    public Page<StudentResponse> getAll(int page, int size, SortType sort) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
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

    
    
}
