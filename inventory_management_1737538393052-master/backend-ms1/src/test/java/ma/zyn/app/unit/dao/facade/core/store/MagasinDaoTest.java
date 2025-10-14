package ma.zyn.app.unit.dao.facade.core.store;

import ma.zyn.app.bean.core.store.Magasin;
import ma.zyn.app.dao.facade.core.store.MagasinDao;

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
public class MagasinDaoTest {

@Autowired
    private MagasinDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        Magasin entity = new Magasin();
        entity.setCode(code);
        underTest.save(entity);
        Magasin loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        Magasin loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Magasin entity = new Magasin();
        entity.setId(id);
        underTest.save(entity);
        Magasin loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Magasin entity = new Magasin();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Magasin loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Magasin> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Magasin> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Magasin given = constructSample(1);
        Magasin saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
