package com.prediction_crime.services;

import com.prediction_crime.dto.RoleDto;
import com.prediction_crime.models.Role;
import com.prediction_crime.models.enums.RoleList;
import com.prediction_crime.repositories.RoleRepository;
import com.prediction_crime.services.impl.RoleServiceImpl;
import com.prediction_crime.utils.PageUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private RoleServiceImpl roleService; // Injecter le service à tester, pas le mocker


    private MockMvc mockMvc;

    private final Logger logger = Logger.getLogger(RoleServiceTest.class.getName());


    @Test
    void shouldReturnAllRoles() {

        Pageable pageable = PageRequest.of(0, 10);

        Role role1 = new Role();
        role1.setId(1L);
        role1.setLibelle(RoleList.USER);

        Role role2 = new Role();
        role2.setId(2L);
        role2.setLibelle(RoleList.AGENT);

        List<Role> roles = List.of(role1, role2);
        Page<Role> rolePage = new PageImpl<>(roles, pageable, roles.size());

        RoleDto roleDto1 = new RoleDto();
        roleDto1.setId(1L);
        roleDto1.setLibelle(String.valueOf(RoleList.USER));

        RoleDto roleDto2 = new RoleDto();
        roleDto2.setId(2L);
        roleDto2.setLibelle(String.valueOf(RoleList.AGENT));

        List<RoleDto> roleDtos = List.of(roleDto1, roleDto2);
        Page<RoleDto> expectedPage = new PageImpl<>(roleDtos, pageable, roleDtos.size());



        // Setup mocks
        given(roleRepository.findAll(pageable)).willReturn(rolePage);

        // Mock PageUtils.mapPage
        try (MockedStatic<PageUtils> pageUtilsMock = mockStatic(PageUtils.class)) {
            pageUtilsMock.when(() -> PageUtils.mapPage(eq(rolePage), any(Function.class)))
                    .thenReturn(expectedPage);

            // When
            Page<RoleDto> result = roleService.findAll(pageable);

            // Debug
            System.out.println("Result: " + result);
            if (result != null) {
                System.out.println("Content size: " + result.getContent().size());
                result.getContent().forEach(dto ->
                        System.out.println("DTO: " + dto.getId() + " - " + dto.getLibelle()));
            }

            // Then
            assertThat(result).isNotNull();
            assertThat(result.getContent()).hasSize(2);
            assertThat(result.getContent().get(0).getId()).isEqualTo(1L);
            assertThat(result.getContent().get(0).getLibelle()).isEqualTo(String.valueOf(RoleList.USER));
            assertThat(result.getContent().get(1).getId()).isEqualTo(2L);
            assertThat(result.getContent().get(1).getLibelle()).isEqualTo(String.valueOf(RoleList.AGENT));
            assertThat(result.getContent()).containsExactly(roleDto1, roleDto2);
        }
    }

    @Test
    void shouldCreateRole() {

        Role role = new Role();
        role.setId(1L);
        role.setLibelle(RoleList.USER);

        RoleDto roleDto = new RoleDto();
        roleDto.setId(1L);
        roleDto.setLibelle(String.valueOf(RoleList.USER));


        // Mock modelMapper
        given(modelMapper.map(Mockito.eq(roleDto), Mockito.eq(Role.class))).willReturn(role);
        given(modelMapper.map(Mockito.eq(role), Mockito.eq(RoleDto.class))).willReturn(roleDto);

        // Mock repository
        given(roleRepository.save(Mockito.eq(role))).willReturn(role);
            // When
            RoleDto result = roleService.save(roleDto);

            logger.info("Result: " + result);


            assertThat(result).isNotNull();
            assertThat(result.getId()).isEqualTo(1L);
            assertThat(result.getLibelle()).isEqualTo(String.valueOf(RoleList.USER));
    }

    @Test
    void shouldTestFailedCausedValidation() {

        RoleDto roleDto = new RoleDto();
        roleDto.setId(1L);
        roleDto.setLibelle("Test");

        logger.info("Result roleDto: " + roleDto);


        assertThrows(IllegalArgumentException.class, () -> {
            roleService.save(roleDto);
        });
    }

    @Test
    void shouldFindRoleById() {

        Role role = new Role();
        role.setId(1L);
        role.setLibelle(RoleList.USER);

        RoleDto dto = new RoleDto();
        dto.setId(1L);
        dto.setLibelle(String.valueOf(RoleList.USER));

        // Setup mocks
        given(modelMapper.map(Mockito.eq(role), Mockito.eq(RoleDto.class))).willReturn(dto);

        // Mock repository
        given(roleRepository.findById(1L)).willReturn(Optional.of(role));


            // When
            RoleDto result = roleService.findById(1L);

            // Debug
            System.out.println("Result: " + result);


            // Then
            assertThat(result).isNotNull();
            assertThat(result.getId()).isEqualTo(1L);
            assertThat(result.getLibelle()).isEqualTo(String.valueOf(RoleList.USER));
    }

    @Test
    void shouldFindRoleByLibelle() {

        Role role = new Role();
        role.setId(1L);
        role.setLibelle(RoleList.USER);

        RoleDto dto = new RoleDto();
        dto.setId(1L);
        dto.setLibelle(String.valueOf(RoleList.USER));

        // Setup mocks
        given(modelMapper.map(Mockito.eq(role), Mockito.eq(RoleDto.class))).willReturn(dto);

        // Mock repository
        given(roleRepository.findByLibelle(RoleList.USER)).willReturn(Optional.of(role));


        // When
        RoleDto result = roleService.findByLibelle("USER");

        // Debug
        System.out.println("Result: " + result);


        // Then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getLibelle()).isEqualTo(String.valueOf(RoleList.USER));
    }

    @Test
    void shouldDeleteRole() {

        RoleDto roleDto = new RoleDto();
        roleDto.setId(1L);
        roleDto.setLibelle(String.valueOf(RoleList.USER));

        logger.info("Result roleDto: " + roleDto);

        // Mock repository
        doNothing().when(roleRepository).deleteById(1L);

        roleService.delete(1L);

        verify(roleRepository, times(1)).deleteById(1L);

    }
}