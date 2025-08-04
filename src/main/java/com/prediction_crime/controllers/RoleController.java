package com.prediction_crime.controllers;

import com.prediction_crime.dto.RoleDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @GetMapping("")
    List<RoleDto> getAllRoles() {
        return null;
    }

    @PostMapping("/create")
    RoleDto createRole(@RequestBody RoleDto roleDto){
        return null;
    }

    @GetMapping("/id/{id}")
    RoleDto getRoleById(@PathVariable Long id) {
        return null;
    }

    @GetMapping("/Roles/{libelle}")
    RoleDto getRoleByLibelle(@PathVariable String libelle) {
        return null;
    }

    @PutMapping("/id/{id}")
    RoleDto updateRole(@RequestBody RoleDto roleDto, @PathVariable Long id) {
        return null;
    }

    @DeleteMapping("/id/{id}")
    void delete(@PathVariable Long id) {

    }
}
