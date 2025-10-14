package ma.zyn.app.unit.service.impl.admin.store;

import ma.zyn.app.bean.core.store.Magasin;
import ma.zyn.app.dao.facade.core.store.MagasinDao;
import ma.zyn.app.service.impl.admin.store.MagasinAdminServiceImpl;

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
class MagasinAdminServiceImplTest {

    @Mock
    private MagasinDao repository;
    private AutoCloseable autoCloseable;
    private MagasinAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new MagasinAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllMagasin() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveMagasin() {
        // Given
        Magasin toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteMagasin() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetMagasinById() {
        // Given
        Long idToRetrieve = 1L; // Example Magasin ID to retrieve
        Magasin expected = new Magasin(); // You need to replace Magasin with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Magasin result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Magasin constructSample(int i) {
		Magasin given = new Magasin();
        given.setAdresse("adresse-"+i);
        given.setCode("code-"+i);
        given.setLabel("label-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
