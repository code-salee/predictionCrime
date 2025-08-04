package com.prediction_crime.services.impl;

import com.prediction_crime.dto.RoleDto;
import com.prediction_crime.models.Role;
import com.prediction_crime.repositories.RoleRepository;
import com.prediction_crime.services.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<RoleDto> findAll(Pageable pageable) {
        List<Role> roles = roleRepository.findAll(pageable).getContent();
        Page<RoleDto> roleDtoPage = modelMapper.map(roles, Page.class);
        return roleDtoPage;
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
        return null;
    }

    @Override
    public RoleDto findById(Long id) {
        return null;
    }

    @Override
    public RoleDto findByLibelle(String libelle) {
        return null;
    }

    @Override
    public RoleDto update(RoleDto roleDto, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
