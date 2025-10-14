package ma.zyn.app.unit.service.impl.admin.output;

import ma.zyn.app.bean.core.output.SortieStock;
import ma.zyn.app.dao.facade.core.output.SortieStockDao;
import ma.zyn.app.service.impl.admin.output.SortieStockAdminServiceImpl;

import ma.zyn.app.bean.core.output.SortieStock ;
import ma.zyn.app.bean.core.store.Magasin ;
import ma.zyn.app.bean.core.output.SortieStockDetail ;
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
class SortieStockAdminServiceImplTest {

    @Mock
    private SortieStockDao repository;
    private AutoCloseable autoCloseable;
    private SortieStockAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new SortieStockAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllSortieStock() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveSortieStock() {
        // Given
        SortieStock toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteSortieStock() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetSortieStockById() {
        // Given
        Long idToRetrieve = 1L; // Example SortieStock ID to retrieve
        SortieStock expected = new SortieStock(); // You need to replace SortieStock with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        SortieStock result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private SortieStock constructSample(int i) {
		SortieStock given = new SortieStock();
        given.setDateSortie(LocalDateTime.now());
        given.setDescription("description-"+i);
        given.setCode("code-"+i);
        List<SortieStockDetail> sortieStockDetails = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                SortieStockDetail element = new SortieStockDetail();
                                                element.setId((long)id);
                                                element.setSortieStock(new SortieStock(Long.valueOf(1)));
                                                element.setProduit(new Produit(Long.valueOf(2)));
                                                element.setPrix(new BigDecimal(3*10));
                                                element.setQuantite(new BigDecimal(4*10));
                                                element.setMagazin(new Magasin(Long.valueOf(5)));
                                                element.setDescription("description"+id);
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setSortieStockDetails(sortieStockDetails);
        return given;
    }

}
