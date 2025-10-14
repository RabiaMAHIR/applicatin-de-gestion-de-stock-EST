package ma.zyn.app.unit.dao.facade.core.input;

import ma.zyn.app.bean.core.input.EntreeStock;
import ma.zyn.app.dao.facade.core.input.EntreeStockDao;

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

import ma.zyn.app.bean.core.agent.Fournisseur ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class EntreeStockDaoTest {

@Autowired
    private EntreeStockDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        EntreeStock entity = new EntreeStock();
        entity.setCode(code);
        underTest.save(entity);
        EntreeStock loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        EntreeStock loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        EntreeStock entity = new EntreeStock();
        entity.setId(id);
        underTest.save(entity);
        EntreeStock loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        EntreeStock entity = new EntreeStock();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        EntreeStock loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<EntreeStock> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<EntreeStock> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        EntreeStock given = constructSample(1);
        EntreeStock saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private EntreeStock constructSample(int i) {
		EntreeStock given = new EntreeStock();
        given.setCode("code-"+i);
        given.setDateEntree(LocalDateTime.now());
        given.setDescription("description-"+i);
        given.setFournisseur(new Fournisseur(1L));
        return given;
    }

}
