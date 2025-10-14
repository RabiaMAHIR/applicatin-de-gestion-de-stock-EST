package ma.zyn.app.dao.facade.core.catalogue;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.catalogue.Produit;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.catalogue.Produit;
import java.util.List;


@Repository
public interface ProduitDao extends AbstractRepository<Produit,Long>  {
    Produit findByCode(String code);
    int deleteByCode(String code);

    List<Produit> findByCategorieProduitId(Long id);
    int deleteByCategorieProduitId(Long id);
    long countByCategorieProduitCode(String code);

    @Query("SELECT NEW Produit(item.id,item.label) FROM Produit item")
    List<Produit> findAllOptimized();

}
