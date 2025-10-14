package ma.zyn.app.unit.dao.facade.core.agent;

import ma.zyn.app.bean.core.agent.Fournisseur;
import ma.zyn.app.dao.facade.core.agent.FournisseurDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class FournisseurDaoTest {

@Autowired
    private FournisseurDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        Fournisseur entity = new Fournisseur();
        entity.setCode(code);
        underTest.save(entity);
        Fournisseur loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        Fournisseur loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Fournisseur entity = new Fournisseur();
        entity.setId(id);
        underTest.save(entity);
        Fournisseur loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Fournisseur entity = new Fournisseur();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Fournisseur loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Fournisseur> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Fournisseur> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Fournisseur given = constructSample(1);
        Fournisseur saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
