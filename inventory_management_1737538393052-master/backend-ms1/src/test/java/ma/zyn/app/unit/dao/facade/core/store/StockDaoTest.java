package ma.zyn.app.unit.dao.facade.core.store;

import ma.zyn.app.bean.core.store.Stock;
import ma.zyn.app.dao.facade.core.store.StockDao;

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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class StockDaoTest {

@Autowired
    private StockDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        Stock entity = new Stock();
        entity.setId(id);
        underTest.save(entity);
        Stock loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Stock entity = new Stock();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Stock loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Stock> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Stock> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Stock given = constructSample(1);
        Stock saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Stock constructSample(int i) {
		Stock given = new Stock();
        given.setProduit(new Produit(1L));
        given.setMagazin(new Magasin(1L));
        given.setQuantite(BigDecimal.TEN);
        given.setQuantiteDeffecteuse(BigDecimal.TEN);
        return given;
    }

}
