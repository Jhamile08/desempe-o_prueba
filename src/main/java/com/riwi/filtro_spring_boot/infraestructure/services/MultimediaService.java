package com.riwi.filtro_spring_boot.infraestructure.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import com.riwi.filtro_spring_boot.api.dto.request.MultimediaRequest;
import com.riwi.filtro_spring_boot.api.dto.response.LessonBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.MultimediaResponse;
import com.riwi.filtro_spring_boot.domain.entities.Lesson;
import com.riwi.filtro_spring_boot.domain.entities.Multimedia;
import com.riwi.filtro_spring_boot.infraestructure.abstract_service.IMultimediaService;
import com.riwi.filtro_spring_boot.utils.enums.SortType;

public class MultimediaService implements IMultimediaService{

    @Override
    public MultimediaRequest create(MultimediaRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public MultimediaRequest get(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public MultimediaRequest update(MultimediaRequest request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Page<MultimediaRequest> getAll(int page, int size, SortType sort) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }
    
    public List<MultimediaResponse> listToBasic(List<Multimedia> list) {
        List<MultimediaResponse> multimediaResponses = new ArrayList<>();
        multimediaResponses = list.stream()
                .map(multimedia -> {
                    MultimediaResponse multimediaResponse = new MultimediaResponse();
                    BeanUtils.copyProperties(multimedia, multimediaResponse);
                    return multimediaResponse;
                })
                .collect(Collectors.toList());
         return multimediaResponses;
    }
}
