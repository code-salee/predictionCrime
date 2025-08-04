package com.prediction_crime.services;

import com.prediction_crime.dto.RoleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface RoleService {

    Page<RoleDto> findAll(Pageable pageable);

    RoleDto save(RoleDto roleDto);

    RoleDto findById(Long id);

    RoleDto findByLibelle(String libelle);

    RoleDto update(RoleDto roleDto, Long id);

    void delete(Long id);
}