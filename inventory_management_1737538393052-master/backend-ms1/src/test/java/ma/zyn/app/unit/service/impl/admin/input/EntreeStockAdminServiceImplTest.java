package ma.zyn.app.unit.service.impl.admin.input;

import ma.zyn.app.bean.core.input.EntreeStock;
import ma.zyn.app.dao.facade.core.input.EntreeStockDao;
import ma.zyn.app.service.impl.admin.input.EntreeStockAdminServiceImpl;

import ma.zyn.app.bean.core.agent.Fournisseur ;
import ma.zyn.app.bean.core.store.Magasin ;
import ma.zyn.app.bean.core.input.EntreeStockDetail ;
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
class EntreeStockAdminServiceImplTest {

    @Mock
    private EntreeStockDao repository;
    private AutoCloseable autoCloseable;
    private EntreeStockAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new EntreeStockAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllEntreeStock() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveEntreeStock() {
        // Given
        EntreeStock toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteEntreeStock() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetEntreeStockById() {
        // Given
        Long idToRetrieve = 1L; // Example EntreeStock ID to retrieve
        EntreeStock expected = new EntreeStock(); // You need to replace EntreeStock with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        EntreeStock result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private EntreeStock constructSample(int i) {
		EntreeStock given = new EntreeStock();
        given.setCode("code-"+i);
        given.setDateEntree(LocalDateTime.now());
        given.setDescription("description-"+i);
        given.setFournisseur(new Fournisseur(1L));
        List<EntreeStockDetail> entreeStockDetails = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                EntreeStockDetail element = new EntreeStockDetail();
                                                element.setId((long)id);
                                                element.setEntreeStock(new EntreeStock(Long.valueOf(1)));
                                                element.setProduit(new Produit(Long.valueOf(2)));
                                                element.setPrix(new BigDecimal(3*10));
                                                element.setQuantite(new BigDecimal(4*10));
                                                element.setMagazin(new Magasin(Long.valueOf(5)));
                                                element.setDescription("description"+id);
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setEntreeStockDetails(entreeStockDetails);
        return given;
    }

}
