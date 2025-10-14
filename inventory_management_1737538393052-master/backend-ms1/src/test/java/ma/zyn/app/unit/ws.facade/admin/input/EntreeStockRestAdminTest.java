package ma.zyn.app.unit.ws.facade.admin.input;

import ma.zyn.app.bean.core.input.EntreeStock;
import ma.zyn.app.service.impl.admin.input.EntreeStockAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.input.EntreeStockRestAdmin;
import ma.zyn.app.ws.converter.input.EntreeStockConverter;
import ma.zyn.app.ws.dto.input.EntreeStockDto;
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
public class EntreeStockRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private EntreeStockAdminServiceImpl service;
    @Mock
    private EntreeStockConverter converter;

    @InjectMocks
    private EntreeStockRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllEntreeStockTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<EntreeStockDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<EntreeStockDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveEntreeStockTest() throws Exception {
        // Mock data
        EntreeStockDto requestDto = new EntreeStockDto();
        EntreeStock entity = new EntreeStock();
        EntreeStock saved = new EntreeStock();
        EntreeStockDto savedDto = new EntreeStockDto();

        // Mock the converter to return the entreeStock object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved entreeStock DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<EntreeStockDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        EntreeStockDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved entreeStock DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getDateEntree(), responseBody.getDateEntree());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
