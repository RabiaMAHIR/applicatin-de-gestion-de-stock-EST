package ma.zyn.app.dao.facade.core.input;

import com.linecorp.armeria.server.annotation.Param;
import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.input.EntreeStock;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.input.EntreeStock;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface EntreeStockDao extends AbstractRepository<EntreeStock,Long> {
    EntreeStock findByCode(String code);

    int deleteByCode(String code);

    List<EntreeStock> findByFournisseurId(Long id);

    int deleteByFournisseurId(Long id);

    long countByFournisseurCode(String code);

    @Query("SELECT NEW EntreeStock(item.id,item.code) FROM EntreeStock item")
    List<EntreeStock> findAllOptimized();


    // <-------------------------------------------------------------------------------------------------------------------->
    @Query("SELECT SUM(detail.quantite * detail.prix) FROM EntreeStock stock JOIN stock.entreeStockDetails detail WHERE YEAR(stock.dateEntree) = :year AND MONTH(stock.dateEntree) = :month")
    BigDecimal calculateTotalByYearAndMonth(@Param("year") int year, @Param("month") int month);


    @Query("SELECT SUM(detail.quantite) FROM EntreeStock stock JOIN stock.entreeStockDetails detail WHERE YEAR(stock.dateEntree) = :year AND MONTH(stock.dateEntree) = :month")
    BigDecimal calculateQuantityByYearAndMonth(@Param("year") int year, @Param("month") int month);


}