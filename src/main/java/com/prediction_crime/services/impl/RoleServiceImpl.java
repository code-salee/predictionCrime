package com.prediction_crime.services.impl;

import com.prediction_crime.dto.RoleDto;
import com.prediction_crime.exceptions.EntityNotFoundException;
import com.prediction_crime.models.Role;
import com.prediction_crime.models.enums.RoleList;
import com.prediction_crime.repositories.RoleRepository;
import com.prediction_crime.services.RoleService;
import com.prediction_crime.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



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
        Page<Role> rolePage = roleRepository.findAll(pageable);
        return PageUtils.mapPage(rolePage, role -> modelMapper.map(role, RoleDto.class));
    }

    @Override
    public RoleDto save(RoleDto roleDto) {
            roleDto.setLibelle(String.valueOf(RoleList.valueOf(roleDto.getLibelle())));
            return modelMapper.map(roleRepository.save(modelMapper.map(roleDto, Role.class)), RoleDto.class);
    }

    @Override
    public RoleDto findById(Long id) {
        return roleRepository.findById(id).map(role -> modelMapper.map(role, RoleDto.class)).orElse(null);
    }

    @Override
    public RoleDto findByLibelle(String libelle) {
        Role role =  roleRepository.findByLibelle(RoleList.valueOf(libelle)).orElse(null);
        return modelMapper.map(role, RoleDto.class);
    }

    @Override
    public RoleDto update(RoleDto roleDto, Long id) {
        RoleDto oldRole = roleRepository.findById(id).map(role -> modelMapper.map(role, RoleDto.class)).orElse(null);
        if (oldRole != null) {
            oldRole.setLibelle(roleDto.getLibelle());
            return modelMapper.map(roleRepository.save(modelMapper.map(oldRole, Role.class)), RoleDto.class);
        } else {
            throw  new EntityNotFoundException("Role not found");
        }
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw  new EntityNotFoundException("Role not found");
        }
        roleRepository.deleteById(id);
    }
}
