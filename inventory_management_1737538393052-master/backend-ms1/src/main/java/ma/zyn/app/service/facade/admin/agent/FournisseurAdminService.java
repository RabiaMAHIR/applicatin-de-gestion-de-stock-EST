package ma.zyn.app.service.facade.admin.agent;

import java.util.List;
import ma.zyn.app.bean.core.agent.Fournisseur;
import ma.zyn.app.dao.criteria.core.agent.FournisseurCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface FournisseurAdminService {







	Fournisseur create(Fournisseur t);

    Fournisseur update(Fournisseur t);

    List<Fournisseur> update(List<Fournisseur> ts,boolean createIfNotExist);

    Fournisseur findById(Long id);

    Fournisseur findOrSave(Fournisseur t);

    Fournisseur findByReferenceEntity(Fournisseur t);

    Fournisseur findWithAssociatedLists(Long id);

    List<Fournisseur> findAllOptimized();

    List<Fournisseur> findAll();

    List<Fournisseur> findByCriteria(FournisseurCriteria criteria);

    List<Fournisseur> findPaginatedByCriteria(FournisseurCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(FournisseurCriteria criteria);

    List<Fournisseur> delete(List<Fournisseur> ts);

    boolean deleteById(Long id);

    List<List<Fournisseur>> getToBeSavedAndToBeDeleted(List<Fournisseur> oldList, List<Fournisseur> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;


   public long countFournisseurs();
}
