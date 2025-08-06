package com.prediction_crime.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prediction_crime.dto.RoleDto;
import com.prediction_crime.repositories.RoleRepository;
import com.prediction_crime.services.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@WebMvcTest(RoleController.class)
@ExtendWith(MockitoExtension.class)
public class RoleControllerTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ModelMapper modelMapper;

    @MockBean
    private RoleService roleService; // Injecter le service à tester, pas le mocker

    @Autowired
    private MockMvc mockMvc;

//    @Test
    void shouldReturn500WhenInvalidRoleEnumIsProvided() throws Exception {
        RoleDto invalidRole = new RoleDto();
        invalidRole.setId(1L);
        invalidRole.setLibelle("Test"); // "Test" n'existe pas dans RoleList

        mockMvc.perform(post("/roles") // remplace par ton vrai endpoint
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(invalidRole)))
                .andExpect(status().isInternalServerError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException))
                .andExpect(result -> assertEquals("500 INTERNAL_SERVER_ERROR \"No enum constant com.prediction_crime.models.enums.RoleList.Test\"",
                        result.getResolvedException().getMessage()));

    }
}
