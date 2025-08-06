package com.prediction_crime.controllers;

import com.prediction_crime.dto.RoleDto;
import com.prediction_crime.exceptions.EntityNotFoundException;
import com.prediction_crime.services.RoleService;
import org.apache.tomcat.util.http.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.net.URI;


@RestController
@RequestMapping("/roles")
public class RoleController {

    private static final Logger log = LoggerFactory.getLogger(RoleController.class);
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("")
    Page<RoleDto> getAllRoles(Pageable pageable) {
        log.info("Request to retrive all roles role: {}", pageable.getPageNumber());
        return roleService.findAll(pageable);
    }

    @PostMapping("")
    ResponseEntity<RoleDto> createRole(@Valid @RequestBody RoleDto roleDto){
        log.info("Request to create role: {}", roleDto);
        try {
            if (roleDto.getLibelle() == null || roleDto.getLibelle().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Libelle cannot be empty");
            }
            RoleDto result = roleService.save(roleDto);
            return ResponseEntity.created(new URI("/api/roles/" + result.getId())).body(result);
        } catch (Exception e) {
            log.error("Une erreur est survenue {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    RoleDto getRoleById(@PathVariable Long id) {
        log.info("Request to get role by id: {}", id);
        return roleService.findById(id);
    }

    @GetMapping("/libelle/{libelle}")
    RoleDto getRoleByLibelle(@PathVariable String libelle) {
        log.info("Request to get role by libelle: {}", libelle);
        return roleService.findByLibelle(libelle);
    }

    @PutMapping("/{id}")
    RoleDto updateRole(@RequestBody RoleDto roleDto, @PathVariable Long id) {
        log.info("Request to update role: {}", roleDto);
        return roleService.update(roleDto, id);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id) {
        try {
            log.info("Request to delete role ID No: {}", id);
            roleService.delete(id);
        }  catch (Exception e) {
            log.error("Une erreur est survenue {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
