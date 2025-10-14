package ma.zyn.app.dao.facade.core.store;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.store.Magasin;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.store.Magasin;
import java.util.List;


@Repository
public interface MagasinDao extends AbstractRepository<Magasin,Long>  {
    Magasin findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Magasin(item.id,item.label) FROM Magasin item")
    List<Magasin> findAllOptimized();



    //------------------------------------------------------------------------------------
    @Query("SELECT COUNT(item) FROM Magasin item")
    long countMagasins();
//------------------------------------------------------------------------------------


}
