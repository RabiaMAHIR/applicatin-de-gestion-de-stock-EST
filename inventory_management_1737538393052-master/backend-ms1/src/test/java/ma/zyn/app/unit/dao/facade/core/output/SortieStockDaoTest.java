package ma.zyn.app.unit.dao.facade.core.output;

import ma.zyn.app.bean.core.output.SortieStock;
import ma.zyn.app.dao.facade.core.output.SortieStockDao;

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
public class SortieStockDaoTest {

@Autowired
    private SortieStockDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        SortieStock entity = new SortieStock();
        entity.setCode(code);
        underTest.save(entity);
        SortieStock loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        SortieStock loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        SortieStock entity = new SortieStock();
        entity.setId(id);
        underTest.save(entity);
        SortieStock loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        SortieStock entity = new SortieStock();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        SortieStock loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<SortieStock> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<SortieStock> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        SortieStock given = constructSample(1);
        SortieStock saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private SortieStock constructSample(int i) {
		SortieStock given = new SortieStock();
        given.setDateSortie(LocalDateTime.now());
        given.setDescription("description-"+i);
        given.setCode("code-"+i);
        return given;
    }

}
