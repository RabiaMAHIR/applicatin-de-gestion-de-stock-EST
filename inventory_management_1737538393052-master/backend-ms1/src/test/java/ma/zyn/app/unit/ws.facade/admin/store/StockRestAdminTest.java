package ma.zyn.app.unit.ws.facade.admin.store;

import ma.zyn.app.bean.core.store.Stock;
import ma.zyn.app.service.impl.admin.store.StockAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.store.StockRestAdmin;
import ma.zyn.app.ws.converter.store.StockConverter;
import ma.zyn.app.ws.dto.store.StockDto;
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
public class StockRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private StockAdminServiceImpl service;
    @Mock
    private StockConverter converter;

    @InjectMocks
    private StockRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllStockTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<StockDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<StockDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveStockTest() throws Exception {
        // Mock data
        StockDto requestDto = new StockDto();
        Stock entity = new Stock();
        Stock saved = new Stock();
        StockDto savedDto = new StockDto();

        // Mock the converter to return the stock object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved stock DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<StockDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        StockDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved stock DTO
        assertEquals(savedDto.getQuantite(), responseBody.getQuantite());
        assertEquals(savedDto.getQuantiteDeffecteuse(), responseBody.getQuantiteDeffecteuse());
    }

}
