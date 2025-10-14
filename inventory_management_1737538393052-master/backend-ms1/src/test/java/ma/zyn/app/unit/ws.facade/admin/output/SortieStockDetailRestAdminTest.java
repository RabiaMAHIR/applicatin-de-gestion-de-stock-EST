package ma.zyn.app.unit.ws.facade.admin.output;

import ma.zyn.app.bean.core.output.SortieStockDetail;
import ma.zyn.app.service.impl.admin.output.SortieStockDetailAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.output.SortieStockDetailRestAdmin;
import ma.zyn.app.ws.converter.output.SortieStockDetailConverter;
import ma.zyn.app.ws.dto.output.SortieStockDetailDto;
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
public class SortieStockDetailRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private SortieStockDetailAdminServiceImpl service;
    @Mock
    private SortieStockDetailConverter converter;

    @InjectMocks
    private SortieStockDetailRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllSortieStockDetailTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<SortieStockDetailDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<SortieStockDetailDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveSortieStockDetailTest() throws Exception {
        // Mock data
        SortieStockDetailDto requestDto = new SortieStockDetailDto();
        SortieStockDetail entity = new SortieStockDetail();
        SortieStockDetail saved = new SortieStockDetail();
        SortieStockDetailDto savedDto = new SortieStockDetailDto();

        // Mock the converter to return the sortieStockDetail object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved sortieStockDetail DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<SortieStockDetailDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        SortieStockDetailDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved sortieStockDetail DTO
        assertEquals(savedDto.getPrix(), responseBody.getPrix());
        assertEquals(savedDto.getQuantite(), responseBody.getQuantite());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
