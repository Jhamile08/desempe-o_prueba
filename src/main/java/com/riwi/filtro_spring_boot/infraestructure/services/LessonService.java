package com.riwi.filtro_spring_boot.infraestructure.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.riwi.filtro_spring_boot.api.dto.request.LessonRequest;
import com.riwi.filtro_spring_boot.api.dto.response.LessonBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.LessonResponse;
import com.riwi.filtro_spring_boot.domain.entities.Lesson;
import com.riwi.filtro_spring_boot.infraestructure.abstract_service.ILessonService;
import com.riwi.filtro_spring_boot.infraestructure.abstract_service.IMultimediaService;
import com.riwi.filtro_spring_boot.utils.enums.SortType;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LessonService implements ILessonService {

    @Autowired
    private MultimediaService multimediaService;

    @Override
    public LessonResponse create(LessonRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public LessonResponse get(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public LessonResponse update(LessonRequest request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Page<LessonResponse> getAll(int page, int size, SortType sort) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    private LessonResponse entityToResponse(Lesson entity){
        LessonResponse lessonResponse = new LessonResponse();
        BeanUtils.copyProperties(entity, lessonResponse);
        lessonResponse.setMultimedias(this.multimediaService.listToBasic(entity.getMultimedias()));
        return lessonResponse;
    }

    public List<LessonBasicResponse> listToBasic(List<Lesson> list) {
        List<LessonBasicResponse> lessonBasicResponses = new ArrayList<>();
        lessonBasicResponses = list.stream()
                .map(lessons -> {
                    LessonBasicResponse lessonBasicResponse = new LessonBasicResponse();
                    BeanUtils.copyProperties(lessons, lessonBasicResponse);
                    return lessonBasicResponse;
                })
                .collect(Collectors.toList());
         return lessonBasicResponses;
    }
    
}
