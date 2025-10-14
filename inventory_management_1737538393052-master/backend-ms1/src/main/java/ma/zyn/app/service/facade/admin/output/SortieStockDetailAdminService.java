package ma.zyn.app.service.facade.admin.output;

import java.util.List;

import ma.zyn.app.bean.core.input.EntreeStockDetail;
import ma.zyn.app.bean.core.output.SortieStockDetail;
import ma.zyn.app.dao.criteria.core.output.SortieStockDetailCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface SortieStockDetailAdminService {



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




    SortieStockDetail create(SortieStockDetail t);

    SortieStockDetail update(SortieStockDetail t);

    List<SortieStockDetail> update(List<SortieStockDetail> ts,boolean createIfNotExist);

    SortieStockDetail findById(Long id);

    SortieStockDetail findOrSave(SortieStockDetail t);

    SortieStockDetail findByReferenceEntity(SortieStockDetail t);

    SortieStockDetail findWithAssociatedLists(Long id);

    List<SortieStockDetail> findAllOptimized();

    List<SortieStockDetail> findAll();

    List<SortieStockDetail> findByCriteria(SortieStockDetailCriteria criteria);

    List<SortieStockDetail> findPaginatedByCriteria(SortieStockDetailCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(SortieStockDetailCriteria criteria);

    List<SortieStockDetail> delete(List<SortieStockDetail> ts);

    boolean deleteById(Long id);

    List<List<SortieStockDetail>> getToBeSavedAndToBeDeleted(List<SortieStockDetail> oldList, List<SortieStockDetail> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
