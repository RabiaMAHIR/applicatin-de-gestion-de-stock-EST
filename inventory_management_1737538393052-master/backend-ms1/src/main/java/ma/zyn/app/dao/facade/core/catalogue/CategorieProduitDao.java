package ma.zyn.app.dao.facade.core.catalogue;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.catalogue.CategorieProduit;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.catalogue.CategorieProduit;
import java.util.List;


@Repository
public interface CategorieProduitDao extends AbstractRepository<CategorieProduit,Long>  {
    CategorieProduit findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW CategorieProduit(item.id,item.label) FROM CategorieProduit item")
    List<CategorieProduit> findAllOptimized();

}
