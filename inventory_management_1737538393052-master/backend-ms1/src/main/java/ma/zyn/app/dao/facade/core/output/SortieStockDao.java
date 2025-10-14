package ma.zyn.app.dao.facade.core.output;

import com.linecorp.armeria.server.annotation.Param;
import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.output.SortieStock;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.output.SortieStock;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface SortieStockDao extends AbstractRepository<SortieStock,Long>  {
    SortieStock findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW SortieStock(item.id,item.code) FROM SortieStock item")
    List<SortieStock> findAllOptimized();


    // <-------------------------------------------------------------------------------------------------------------------->


    @Query("SELECT SUM(detail.quantite * detail.prix) FROM SortieStock stock JOIN stock.sortieStockDetails detail WHERE YEAR(stock.dateSortie) = :year AND MONTH(stock.dateSortie) = :month")
    BigDecimal calculateTotalSortByYearAndMonth(@Param("year") int year, @Param("month") int month);

    @Query("SELECT SUM(detail.quantite) FROM SortieStock  stock JOIN stock.sortieStockDetails detail WHERE YEAR(stock.dateSortie) = :year AND MONTH(stock.dateSortie) = :month")
    BigDecimal calculateSortQuantityByYearAndMonth(@Param("year") int year, @Param("month") int month);



}
