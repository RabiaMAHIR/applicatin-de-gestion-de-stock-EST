package ma.zyn.app.service.facade.admin.store;

import java.util.List;
import ma.zyn.app.bean.core.store.Magasin;
import ma.zyn.app.dao.criteria.core.store.MagasinCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface MagasinAdminService {







	Magasin create(Magasin t);

    Magasin update(Magasin t);

    List<Magasin> update(List<Magasin> ts,boolean createIfNotExist);

    Magasin findById(Long id);

    Magasin findOrSave(Magasin t);

    Magasin findByReferenceEntity(Magasin t);

    Magasin findWithAssociatedLists(Long id);

    List<Magasin> findAllOptimized();

    List<Magasin> findAll();

    List<Magasin> findByCriteria(MagasinCriteria criteria);

    List<Magasin> findPaginatedByCriteria(MagasinCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(MagasinCriteria criteria);

    List<Magasin> delete(List<Magasin> ts);

    boolean deleteById(Long id);

    List<List<Magasin>> getToBeSavedAndToBeDeleted(List<Magasin> oldList, List<Magasin> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    public long countMagasins();
}

