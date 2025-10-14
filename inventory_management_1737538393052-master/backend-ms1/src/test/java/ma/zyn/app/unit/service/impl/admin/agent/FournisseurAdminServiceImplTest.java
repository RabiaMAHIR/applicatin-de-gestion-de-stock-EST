package ma.zyn.app.unit.service.impl.admin.agent;

import ma.zyn.app.bean.core.agent.Fournisseur;
import ma.zyn.app.dao.facade.core.agent.FournisseurDao;
import ma.zyn.app.service.impl.admin.agent.FournisseurAdminServiceImpl;

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
class FournisseurAdminServiceImplTest {

    @Mock
    private FournisseurDao repository;
    private AutoCloseable autoCloseable;
    private FournisseurAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new FournisseurAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllFournisseur() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveFournisseur() {
        // Given
        Fournisseur toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteFournisseur() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetFournisseurById() {
        // Given
        Long idToRetrieve = 1L; // Example Fournisseur ID to retrieve
        Fournisseur expected = new Fournisseur(); // You need to replace Fournisseur with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Fournisseur result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Fournisseur constructSample(int i) {
		Fournisseur given = new Fournisseur();
        given.setCode("code-"+i);
        given.setLabel("label-"+i);
        given.setAdresse("adresse-"+i);
        given.setEmail("email-"+i);
        given.setDescription("description-"+i);
        return given;
    }

}
