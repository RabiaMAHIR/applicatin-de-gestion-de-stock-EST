package ma.zyn.app.dao.facade.core.agent;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.agent.Fournisseur;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.agent.Fournisseur;
import java.util.List;


@Repository
public interface FournisseurDao extends AbstractRepository<Fournisseur,Long>  {
    Fournisseur findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Fournisseur(item.id,item.label) FROM Fournisseur item")
    List<Fournisseur> findAllOptimized();
//------------------------------------------------------------------------------------
    @Query("SELECT COUNT(item) FROM Fournisseur item")
    long countFournisseurs();
//------------------------------------------------------------------------------------


}
