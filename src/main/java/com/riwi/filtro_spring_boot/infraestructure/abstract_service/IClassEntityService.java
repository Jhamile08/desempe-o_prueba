package com.riwi.filtro_spring_boot.infraestructure.abstract_service;

import com.riwi.filtro_spring_boot.api.dto.request.ClassEntityRequest;
import com.riwi.filtro_spring_boot.api.dto.response.ClassEntityResponse;

public interface IClassEntityService extends CrudService<ClassEntityRequest,ClassEntityResponse, Long> {
    public String FIELD_BY_SORT = "ClassName";
}