package ma.zyn.app.unit.service.impl.admin.input;

import ma.zyn.app.bean.core.input.EntreeStockDetail;
import ma.zyn.app.dao.facade.core.input.EntreeStockDetailDao;
import ma.zyn.app.service.impl.admin.input.EntreeStockDetailAdminServiceImpl;

import ma.zyn.app.bean.core.store.Magasin ;
import ma.zyn.app.bean.core.catalogue.Produit ;
import ma.zyn.app.bean.core.input.EntreeStock ;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class EntreeStockDetailAdminServiceImplTest {

    @Mock
    private EntreeStockDetailDao repository;
    private AutoCloseable autoCloseable;
    private EntreeStockDetailAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new EntreeStockDetailAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllEntreeStockDetail() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveEntreeStockDetail() {
        // Given
        EntreeStockDetail toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteEntreeStockDetail() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetEntreeStockDetailById() {
        // Given
        Long idToRetrieve = 1L; // Example EntreeStockDetail ID to retrieve
        EntreeStockDetail expected = new EntreeStockDetail(); // You need to replace EntreeStockDetail with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        EntreeStockDetail result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private EntreeStockDetail constructSample(int i) {
		EntreeStockDetail given = new EntreeStockDetail();
        given.setEntreeStock(new EntreeStock(1L));
        given.setProduit(new Produit(1L));
        given.setPrix(BigDecimal.TEN);
        given.setQuantite(BigDecimal.TEN);
        given.setMagazin(new Magasin(1L));
        given.setDescription("description-"+i);
        return given;
    }

}
