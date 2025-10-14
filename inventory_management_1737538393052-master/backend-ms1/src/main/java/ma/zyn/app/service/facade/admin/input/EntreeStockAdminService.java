package ma.zyn.app.service.facade.admin.input;

import java.math.BigDecimal;
import java.util.List;
import ma.zyn.app.bean.core.input.EntreeStock;
import ma.zyn.app.dao.criteria.core.input.EntreeStockCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface EntreeStockAdminService {



    List<EntreeStock> findByFournisseurId(Long id);
    int deleteByFournisseurId(Long id);
    long countByFournisseurCode(String code);




	EntreeStock create(EntreeStock t);

    EntreeStock update(EntreeStock t);

    List<EntreeStock> update(List<EntreeStock> ts,boolean createIfNotExist);

    EntreeStock findById(Long id);

    EntreeStock findOrSave(EntreeStock t);

    EntreeStock findByReferenceEntity(EntreeStock t);

    EntreeStock findWithAssociatedLists(Long id);

    List<EntreeStock> findAllOptimized();

    List<EntreeStock> findAll();

    List<EntreeStock> findByCriteria(EntreeStockCriteria criteria);

    List<EntreeStock> findPaginatedByCriteria(EntreeStockCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EntreeStockCriteria criteria);

    List<EntreeStock> delete(List<EntreeStock> ts);

    boolean deleteById(Long id);

    List<List<EntreeStock>> getToBeSavedAndToBeDeleted(List<EntreeStock> oldList, List<EntreeStock> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    public BigDecimal getTotalForYearAndMonth(int year , int month);
    public BigDecimal getTotalForYear(int year);
    public  BigDecimal calculateQuantityByYearAndMonth(int year , int month);
    public BigDecimal getChangeTotalForYear(int year) ;

    }
