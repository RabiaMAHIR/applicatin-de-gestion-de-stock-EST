package ma.zyn.app.dao.facade.core.output;

import ma.zyn.app.bean.core.input.EntreeStockDetail;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.output.SortieStockDetail;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface SortieStockDetailDao extends AbstractRepository<SortieStockDetail,Long>  {

    List<SortieStockDetail> findBySortieStockId(Long id);
    int deleteBySortieStockId(Long id);
    long countBySortieStockCode(String code);
    List<SortieStockDetail> findByProduitId(Long id);
    int deleteByProduitId(Long id);
    long countByProduitCode(String code);
    List<SortieStockDetail> findByMagazinId(Long id);
    int deleteByMagazinId(Long id);
    long countByMagazinCode(String code);
    List<SortieStockDetail> findByMagazinIdAndProduitId(Long magasinId, Long produitId);


}
