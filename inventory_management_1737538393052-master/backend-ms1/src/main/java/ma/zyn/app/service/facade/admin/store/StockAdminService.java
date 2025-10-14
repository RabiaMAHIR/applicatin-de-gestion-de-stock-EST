package ma.zyn.app.service.facade.admin.store;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import ma.zyn.app.bean.core.store.Magasin;
import ma.zyn.app.bean.core.store.Stock;
import ma.zyn.app.dao.criteria.core.store.StockCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface StockAdminService {



    List<Stock> findByProduitId(Long id);
    int deleteByProduitId(Long id);
    long countByProduitCode(String code);
    List<Stock> findByMagazinId(Long id);
    int deleteByMagazinId(Long id);
    long countByMagazinCode(String code);




	Stock create(Stock t);

    Stock update(Stock t);

    List<Stock> update(List<Stock> ts,boolean createIfNotExist);

    Stock findById(Long id);

    Stock findOrSave(Stock t);

    Stock findByReferenceEntity(Stock t);

    Stock findWithAssociatedLists(Long id);

    List<Stock> findAllOptimized();

    List<Stock> findAll();

    List<Stock> findByCriteria(StockCriteria criteria);

    List<Stock> findPaginatedByCriteria(StockCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(StockCriteria criteria);

    List<Stock> delete(List<Stock> ts);

    boolean deleteById(Long id);

    List<List<Stock>> getToBeSavedAndToBeDeleted(List<Stock> oldList, List<Stock> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;
    Stock findByMagazinIdAndProduitId(Long idMagazin, Long idProduit);
    List<Stock> findStocksByMagazinId(String labelMagazin);
    List<Map<String, BigDecimal>> findTotalQuantitiesForAllProduits();
}
