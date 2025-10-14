package ma.zyn.app.unit.ws.facade.admin.input;

import ma.zyn.app.bean.core.input.EntreeStockDetail;
import ma.zyn.app.service.impl.admin.input.EntreeStockDetailAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.input.EntreeStockDetailRestAdmin;
import ma.zyn.app.ws.converter.input.EntreeStockDetailConverter;
import ma.zyn.app.ws.dto.input.EntreeStockDetailDto;
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
public class EntreeStockDetailRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private EntreeStockDetailAdminServiceImpl service;
    @Mock
    private EntreeStockDetailConverter converter;

    @InjectMocks
    private EntreeStockDetailRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllEntreeStockDetailTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<EntreeStockDetailDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<EntreeStockDetailDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveEntreeStockDetailTest() throws Exception {
        // Mock data
        EntreeStockDetailDto requestDto = new EntreeStockDetailDto();
        EntreeStockDetail entity = new EntreeStockDetail();
        EntreeStockDetail saved = new EntreeStockDetail();
        EntreeStockDetailDto savedDto = new EntreeStockDetailDto();

        // Mock the converter to return the entreeStockDetail object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved entreeStockDetail DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<EntreeStockDetailDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        EntreeStockDetailDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved entreeStockDetail DTO
        assertEquals(savedDto.getPrix(), responseBody.getPrix());
        assertEquals(savedDto.getQuantite(), responseBody.getQuantite());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
