package ma.zyn.app.service.facade.admin.input;

import java.util.List;
import ma.zyn.app.bean.core.input.EntreeStockDetail;
import ma.zyn.app.dao.criteria.core.input.EntreeStockDetailCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface EntreeStockDetailAdminService {



    List<EntreeStockDetail> findByEntreeStockId(Long id);
    int deleteByEntreeStockId(Long id);
    long countByEntreeStockCode(String code);

    int deleteByProduitId(Long id);
    int deleteByMagazinId(Long id);

    long countByProduitCode(String code);

    List<EntreeStockDetail> findByMagazinId(Long id);
    List<EntreeStockDetail> findByProduitId(Long id);

    long countByMagazinCode(String code);

    List<EntreeStockDetail> findByMagazinIdAndProduitId(Long magasinId, Long produitId);



	EntreeStockDetail create(EntreeStockDetail t);

    EntreeStockDetail update(EntreeStockDetail t);

    List<EntreeStockDetail> update(List<EntreeStockDetail> ts,boolean createIfNotExist);

    EntreeStockDetail findById(Long id);

    EntreeStockDetail findOrSave(EntreeStockDetail t);

    EntreeStockDetail findByReferenceEntity(EntreeStockDetail t);

    EntreeStockDetail findWithAssociatedLists(Long id);

    List<EntreeStockDetail> findAllOptimized();

    List<EntreeStockDetail> findAll();

    List<EntreeStockDetail> findByCriteria(EntreeStockDetailCriteria criteria);

    List<EntreeStockDetail> findPaginatedByCriteria(EntreeStockDetailCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EntreeStockDetailCriteria criteria);

    List<EntreeStockDetail> delete(List<EntreeStockDetail> ts);

    boolean deleteById(Long id);

    List<List<EntreeStockDetail>> getToBeSavedAndToBeDeleted(List<EntreeStockDetail> oldList, List<EntreeStockDetail> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
