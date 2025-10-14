package ma.zyn.app.dao.facade.core.store;

import com.linecorp.armeria.server.annotation.Param;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.store.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface StockDao extends AbstractRepository<Stock,Long>  {

    List<Stock> findByProduitId(Long id);
    int deleteByProduitId(Long id);
    long countByProduitCode(String code);
    List<Stock> findByMagazinId(Long id);
    int deleteByMagazinId(Long id);
    long countByMagazinCode(String code);
    Stock findByMagazinIdAndProduitId(Long idMagazin, Long idProduit);


    // <-------------------------------------------------------------------------------------------------------------------->

    @Query("SELECT s FROM Stock s JOIN FETCH s.produit WHERE s.magazin.label = :magazinlabel")
    List<Stock> findStocksByMagazinId(@Param("magazinlabel") String magazinlabel);

    @Query("SELECT s.produit.label, SUM(s.quantite) FROM Stock s GROUP BY s.produit.id")
    List<Object[]> findTotalQuantitiesForAllProduits();

}


