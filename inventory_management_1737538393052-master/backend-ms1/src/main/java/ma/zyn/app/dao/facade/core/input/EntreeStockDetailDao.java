package ma.zyn.app.dao.facade.core.input;

import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.input.EntreeStockDetail;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface EntreeStockDetailDao extends AbstractRepository<EntreeStockDetail,Long>  {

    List<EntreeStockDetail> findByEntreeStockId(Long id);
    int deleteByEntreeStockId(Long id);
    long countByEntreeStockCode(String code);
    List<EntreeStockDetail> findByProduitId(Long id);
    int deleteByProduitId(Long id);
    long countByProduitCode(String code);
    List<EntreeStockDetail> findByMagazinId(Long id);
    int deleteByMagazinId(Long id);
    long countByMagazinCode(String code);
    List<EntreeStockDetail> findByMagazinIdAndProduitId(Long magasinId, Long produitId);

}
