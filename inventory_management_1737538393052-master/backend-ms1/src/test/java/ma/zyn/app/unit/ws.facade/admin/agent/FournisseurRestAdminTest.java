package ma.zyn.app.unit.ws.facade.admin.agent;

import ma.zyn.app.bean.core.agent.Fournisseur;
import ma.zyn.app.service.impl.admin.agent.FournisseurAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.agent.FournisseurRestAdmin;
import ma.zyn.app.ws.converter.agent.FournisseurConverter;
import ma.zyn.app.ws.dto.agent.FournisseurDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FournisseurRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private FournisseurAdminServiceImpl service;
    @Mock
    private FournisseurConverter converter;

    @InjectMocks
    private FournisseurRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllFournisseurTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<FournisseurDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<FournisseurDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveFournisseurTest() throws Exception {
        // Mock data
        FournisseurDto requestDto = new FournisseurDto();
        Fournisseur entity = new Fournisseur();
        Fournisseur saved = new Fournisseur();
        FournisseurDto savedDto = new FournisseurDto();

        // Mock the converter to return the fournisseur object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved fournisseur DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<FournisseurDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        FournisseurDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved fournisseur DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLabel(), responseBody.getLabel());
        assertEquals(savedDto.getAdresse(), responseBody.getAdresse());
        assertEquals(savedDto.getEmail(), responseBody.getEmail());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
