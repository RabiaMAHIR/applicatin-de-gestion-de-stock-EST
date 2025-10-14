package ma.zyn.app.unit.dao.facade.core.output;

import ma.zyn.app.bean.core.output.SortieStockDetail;
import ma.zyn.app.dao.facade.core.output.SortieStockDetailDao;

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

import ma.zyn.app.bean.core.output.SortieStock ;
import ma.zyn.app.bean.core.store.Magasin ;
import ma.zyn.app.bean.core.catalogue.Produit ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class SortieStockDetailDaoTest {

@Autowired
    private SortieStockDetailDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        SortieStockDetail entity = new SortieStockDetail();
        entity.setId(id);
        underTest.save(entity);
        SortieStockDetail loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        SortieStockDetail entity = new SortieStockDetail();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        SortieStockDetail loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<SortieStockDetail> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<SortieStockDetail> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        SortieStockDetail given = constructSample(1);
        SortieStockDetail saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
