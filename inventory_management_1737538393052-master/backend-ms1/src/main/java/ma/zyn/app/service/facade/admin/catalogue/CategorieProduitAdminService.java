package ma.zyn.app.service.facade.admin.catalogue;

import java.util.List;
import ma.zyn.app.bean.core.catalogue.CategorieProduit;
import ma.zyn.app.dao.criteria.core.catalogue.CategorieProduitCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface CategorieProduitAdminService {







	CategorieProduit create(CategorieProduit t);

    CategorieProduit update(CategorieProduit t);

    List<CategorieProduit> update(List<CategorieProduit> ts,boolean createIfNotExist);

    CategorieProduit findById(Long id);

    CategorieProduit findOrSave(CategorieProduit t);

    CategorieProduit findByReferenceEntity(CategorieProduit t);

    CategorieProduit findWithAssociatedLists(Long id);

    List<CategorieProduit> findAllOptimized();

    List<CategorieProduit> findAll();

    List<CategorieProduit> findByCriteria(CategorieProduitCriteria criteria);

    List<CategorieProduit> findPaginatedByCriteria(CategorieProduitCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CategorieProduitCriteria criteria);

    List<CategorieProduit> delete(List<CategorieProduit> ts);

    boolean deleteById(Long id);

    List<List<CategorieProduit>> getToBeSavedAndToBeDeleted(List<CategorieProduit> oldList, List<CategorieProduit> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
