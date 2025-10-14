package ma.zyn.app.service.facade.admin.output;

import java.math.BigDecimal;
import java.util.List;
import ma.zyn.app.bean.core.output.SortieStock;
import ma.zyn.app.dao.criteria.core.output.SortieStockCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface SortieStockAdminService {







	SortieStock create(SortieStock t);

    SortieStock update(SortieStock t);

    List<SortieStock> update(List<SortieStock> ts,boolean createIfNotExist);

    SortieStock findById(Long id);

    SortieStock findOrSave(SortieStock t);

    SortieStock findByReferenceEntity(SortieStock t);

    SortieStock findWithAssociatedLists(Long id);

    List<SortieStock> findAllOptimized();

    List<SortieStock> findAll();

    List<SortieStock> findByCriteria(SortieStockCriteria criteria);

    List<SortieStock> findPaginatedByCriteria(SortieStockCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(SortieStockCriteria criteria);

    List<SortieStock> delete(List<SortieStock> ts);

    boolean deleteById(Long id);

    List<List<SortieStock>> getToBeSavedAndToBeDeleted(List<SortieStock> oldList, List<SortieStock> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;


    public BigDecimal getTotalSortForYearAndMonth(int year , int month);
    public BigDecimal getTotalSortForYear(int year);

   BigDecimal calculateSortQuantityByYearAndMonth(int year , int month);
    public BigDecimal getChangeTotalForYear(int year) ;

}
