package com.riwi.filtro_spring_boot.infraestructure.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.riwi.filtro_spring_boot.api.dto.request.MultimediaRequest;
import com.riwi.filtro_spring_boot.api.dto.response.LessonBasicResponse;
import com.riwi.filtro_spring_boot.api.dto.response.MultimediaResponse;
import com.riwi.filtro_spring_boot.domain.entities.Lesson;
import com.riwi.filtro_spring_boot.domain.entities.Multimedia;
import com.riwi.filtro_spring_boot.domain.repositories.MultimediaRepository;
import com.riwi.filtro_spring_boot.infraestructure.abstract_service.IMultimediaService;
import com.riwi.filtro_spring_boot.utils.enums.SortType;
import com.riwi.filtro_spring_boot.utils.exceptions.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MultimediaService implements IMultimediaService{

    @Autowired
    private IMultimediaService multimediaService;
    @Autowired
    private MultimediaRepository multimediaRepository;
   
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

    private Multimedia requestToEntity(MultimediaRequest request){
        Multimedia multimedia = new Multimedia();
        BeanUtils.copyProperties(request,multimedia);
        return multimedia;
    }

    private Multimedia find(Long id){
        return this.multimediaRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("multimedia"));
    }
}
