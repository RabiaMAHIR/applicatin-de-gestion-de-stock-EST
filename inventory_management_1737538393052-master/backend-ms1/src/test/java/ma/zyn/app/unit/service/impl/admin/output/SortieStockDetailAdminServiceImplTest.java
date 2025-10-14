package ma.zyn.app.unit.service.impl.admin.output;

import ma.zyn.app.bean.core.output.SortieStockDetail;
import ma.zyn.app.dao.facade.core.output.SortieStockDetailDao;
import ma.zyn.app.service.impl.admin.output.SortieStockDetailAdminServiceImpl;

import ma.zyn.app.bean.core.output.SortieStock ;
import ma.zyn.app.bean.core.store.Magasin ;
import ma.zyn.app.bean.core.catalogue.Produit ;
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
class SortieStockDetailAdminServiceImplTest {

    @Mock
    private SortieStockDetailDao repository;
    private AutoCloseable autoCloseable;
    private SortieStockDetailAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new SortieStockDetailAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllSortieStockDetail() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveSortieStockDetail() {
        // Given
        SortieStockDetail toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteSortieStockDetail() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetSortieStockDetailById() {
        // Given
        Long idToRetrieve = 1L; // Example SortieStockDetail ID to retrieve
        SortieStockDetail expected = new SortieStockDetail(); // You need to replace SortieStockDetail with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        SortieStockDetail result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private SortieStockDetail constructSample(int i) {
		SortieStockDetail given = new SortieStockDetail();
        given.setSortieStock(new SortieStock(1L));
        given.setProduit(new Produit(1L));
        given.setPrix(BigDecimal.TEN);
        given.setQuantite(BigDecimal.TEN);
        given.setMagazin(new Magasin(1L));
        given.setDescription("description-"+i);
        return given;
    }

}
