package com.riwi.filtro_spring_boot.infraestructure.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.riwi.filtro_spring_boot.api.dto.request.LessonRequest;
import com.riwi.filtro_spring_boot.api.dto.response.LessonBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.LessonResponse;
import com.riwi.filtro_spring_boot.domain.entities.Lesson;
import com.riwi.filtro_spring_boot.domain.repositories.LessonRepository;
import com.riwi.filtro_spring_boot.infraestructure.abstract_service.ILessonService;
import com.riwi.filtro_spring_boot.utils.enums.SortType;
import com.riwi.filtro_spring_boot.utils.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LessonService implements ILessonService {

    @Autowired
    private MultimediaService multimediaService;
    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public LessonResponse create(LessonRequest request) {
        Lesson lesson = this.requestToEntity(request);
        // Multimedia multimedia = this.multimediaService.create(request);
        return this.entityToResponse(this.lessonRepository.save(lesson));
    }

    @Override
    public LessonResponse get(Long id) {
         return this.entityToResponse(this.find(id));
    }

    @Override
    public LessonResponse update(LessonRequest request, Long id) {
        Lesson lesson = this.find(id);
        Lesson lessonUpdate = this.requestToEntity(request);
        lessonUpdate.setId(id);
        return this.entityToResponse(this.lessonRepository.save(lessonUpdate));
    }

    @Override
    public void delete(Long id) {
        Lesson lesson = this.find(id);
        this.lessonRepository.delete(lesson);
    }

    @Override
    public Page<LessonResponse> getAll(int page, int size, SortType sort) {
        if(page<0) page = 0;
        PageRequest pagination= null;

        switch (sort){
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page,size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page,size, Sort.by(FIELD_BY_SORT).descending());
        }
        return this.lessonRepository.findAll(pagination).map(this::entityToResponse);
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

    private Lesson requestToEntity(LessonRequest request){
        Lesson lesson = new Lesson();
        BeanUtils.copyProperties(request,lesson);
        return lesson;
    }

    private Lesson find(Long id){
        return this.lessonRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("lesson"));
    }
    
}
