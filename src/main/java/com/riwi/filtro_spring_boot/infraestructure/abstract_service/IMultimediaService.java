package com.riwi.filtro_spring_boot.infraestructure.abstract_service;

import com.riwi.filtro_spring_boot.api.dto.request.MultimediaRequest;

public interface IMultimediaService extends CrudService<MultimediaRequest,MultimediaRequest, Long> {
    public final String FIELD_BY_SORT = "title";
}