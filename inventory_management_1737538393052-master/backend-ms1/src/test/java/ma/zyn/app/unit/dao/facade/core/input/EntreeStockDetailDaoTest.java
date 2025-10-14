package ma.zyn.app.unit.dao.facade.core.input;

import ma.zyn.app.bean.core.input.EntreeStockDetail;
import ma.zyn.app.dao.facade.core.input.EntreeStockDetailDao;

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

import ma.zyn.app.bean.core.store.Magasin ;
import ma.zyn.app.bean.core.catalogue.Produit ;
import ma.zyn.app.bean.core.input.EntreeStock ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class EntreeStockDetailDaoTest {

@Autowired
    private EntreeStockDetailDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        EntreeStockDetail entity = new EntreeStockDetail();
        entity.setId(id);
        underTest.save(entity);
        EntreeStockDetail loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        EntreeStockDetail entity = new EntreeStockDetail();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        EntreeStockDetail loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<EntreeStockDetail> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<EntreeStockDetail> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        EntreeStockDetail given = constructSample(1);
        EntreeStockDetail saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private EntreeStockDetail constructSample(int i) {
		EntreeStockDetail given = new EntreeStockDetail();
        given.setEntreeStock(new EntreeStock(1L));
        given.setProduit(new Produit(1L));
        given.setPrix(BigDecimal.TEN);
        given.setQuantite(BigDecimal.TEN);
        given.setMagazin(new Magasin(1L));
        given.setDescription("description-"+i);
        return given;
    }

}
