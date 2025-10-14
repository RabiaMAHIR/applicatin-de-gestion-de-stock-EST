package ma.zyn.app.unit.ws.facade.admin.store;

import ma.zyn.app.bean.core.store.Magasin;
import ma.zyn.app.service.impl.admin.store.MagasinAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.store.MagasinRestAdmin;
import ma.zyn.app.ws.converter.store.MagasinConverter;
import ma.zyn.app.ws.dto.store.MagasinDto;
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
public class MagasinRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private MagasinAdminServiceImpl service;
    @Mock
    private MagasinConverter converter;

    @InjectMocks
    private MagasinRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllMagasinTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<MagasinDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<MagasinDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveMagasinTest() throws Exception {
        // Mock data
        MagasinDto requestDto = new MagasinDto();
        Magasin entity = new Magasin();
        Magasin saved = new Magasin();
        MagasinDto savedDto = new MagasinDto();

        // Mock the converter to return the magasin object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved magasin DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<MagasinDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        MagasinDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved magasin DTO
        assertEquals(savedDto.getAdresse(), responseBody.getAdresse());
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLabel(), responseBody.getLabel());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
