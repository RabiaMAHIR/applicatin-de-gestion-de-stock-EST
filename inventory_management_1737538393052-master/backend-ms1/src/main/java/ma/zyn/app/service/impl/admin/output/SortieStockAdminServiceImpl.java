package ma.zyn.app.service.impl.admin.output;


import ma.zyn.app.bean.core.store.Stock;
import ma.zyn.app.service.facade.admin.store.StockAdminService;
import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.output.SortieStock;
import ma.zyn.app.dao.criteria.core.output.SortieStockCriteria;
import ma.zyn.app.dao.facade.core.output.SortieStockDao;
import ma.zyn.app.dao.specification.core.output.SortieStockSpecification;
import ma.zyn.app.service.facade.admin.output.SortieStockAdminService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;
import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zyn.app.service.facade.admin.output.SortieStockDetailAdminService ;
import ma.zyn.app.bean.core.output.SortieStockDetail ;

import java.util.List;
@Service
public class SortieStockAdminServiceImpl implements SortieStockAdminService {


    @Autowired
    private StockAdminService stockService;



    public SortieStock findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public SortieStock findOrSave(SortieStock t) {
        if (t != null) {
            SortieStock result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<SortieStock> findAll() {
        return dao.findAll();
    }

    public List<SortieStock> findByCriteria(SortieStockCriteria criteria) {
        List<SortieStock> content = null;
        if (criteria != null) {
            SortieStockSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private SortieStockSpecification constructSpecification(SortieStockCriteria criteria) {
        SortieStockSpecification mySpecification =  (SortieStockSpecification) RefelexivityUtil.constructObjectUsingOneParam(SortieStockSpecification.class, criteria);
        return mySpecification;
    }

    public List<SortieStock> findPaginatedByCriteria(SortieStockCriteria criteria, int page, int pageSize, String order, String sortField) {
        SortieStockSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(SortieStockCriteria criteria) {
        SortieStockSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public void deleteAssociatedLists(Long id) {
        sortieStockDetailService.deleteBySortieStockId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<SortieStock> delete(List<SortieStock> list) {
		List<SortieStock> result = new ArrayList();
        if (list != null) {
            for (SortieStock t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}else{
                    dao.deleteById(t.getId());
                }
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public SortieStock update(SortieStock t) {
        SortieStock loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{SortieStock.class.getSimpleName(), t.getId().toString()});
        } else {
            t.getSortieStockDetails().forEach(element -> {
                SortieStockDetail loadedDetail = loadedItem.getSortieStockDetails().stream()
                        .filter(detail -> detail.getProduit().getId().equals(element.getProduit().getId()) &&
                                detail.getMagazin().getId().equals(element.getMagazin().getId()))
                        .findFirst()
                        .orElse(null);

                BigDecimal oldQuantity = loadedDetail != null ? loadedDetail.getQuantite() : BigDecimal.ZERO;


                BigDecimal quantityDifference = element.getQuantite().subtract(oldQuantity);

                Stock loadedStock = stockService.findByMagazinIdAndProduitId(element.getMagazin().getId(), element.getProduit().getId());
                if (loadedStock != null) {
                    loadedStock.setQuantite(loadedStock.getQuantite().subtract(quantityDifference));
                    if (loadedStock.getQuantite().compareTo(BigDecimal.ZERO) < 0) {
                        loadedStock.setQuantite(BigDecimal.ZERO);
                    }
                    stockService.update(loadedStock);
                }

                element.setSortieStock(loadedItem);
                sortieStockDetailService.update(element);
            });

            dao.save(t);
            return loadedItem;
        }
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public SortieStock create(SortieStock t) {
        SortieStock loaded = findByReferenceEntity(t);
        SortieStock saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getSortieStockDetails() != null) {
                t.getSortieStockDetails().forEach(element-> {
                    Stock loadedStock = stockService.findByMagazinIdAndProduitId(element.getMagazin().getId(),element.getProduit().getId());
                    System.out.println("loadedStock = " + loadedStock);
                    System.out.println("element = " + element);
                    //&& loadedStock.getQuantite().compareTo(element.getQuantite()) > 0
                    if (loadedStock != null ) {

                        BigDecimal quantite = loadedStock.getQuantite().subtract( element.getQuantite());
                        System.out.println("quantite = " + quantite);

                        loadedStock.setQuantite(quantite);
                        stockService.update(loadedStock);

                    }

                    element.setSortieStock(saved);
                    sortieStockDetailService.create(element);

                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public SortieStock findWithAssociatedLists(Long id){
        SortieStock result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setSortieStockDetails(sortieStockDetailService.findBySortieStockId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<SortieStock> update(List<SortieStock> ts, boolean createIfNotExist) {
        List<SortieStock> result = new ArrayList<>();
        if (ts != null) {
            for (SortieStock t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    SortieStock loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, SortieStock t, SortieStock loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(SortieStock sortieStock){
    if(sortieStock !=null && sortieStock.getId() != null){
        List<List<SortieStockDetail>> resultSortieStockDetails= sortieStockDetailService.getToBeSavedAndToBeDeleted(sortieStockDetailService.findBySortieStockId(sortieStock.getId()),sortieStock.getSortieStockDetails());
            sortieStockDetailService.delete(resultSortieStockDetails.get(1));
        emptyIfNull(resultSortieStockDetails.get(0)).forEach(e -> e.setSortieStock(sortieStock));
        sortieStockDetailService.update(resultSortieStockDetails.get(0),true);
        }
    }








    public SortieStock findByReferenceEntity(SortieStock t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<SortieStock> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<SortieStock>> getToBeSavedAndToBeDeleted(List<SortieStock> oldList, List<SortieStock> newList) {
        List<List<SortieStock>> result = new ArrayList<>();
        List<SortieStock> resultDelete = new ArrayList<>();
        List<SortieStock> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<SortieStock> oldList, List<SortieStock> newList, List<SortieStock> resultUpdateOrSave, List<SortieStock> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                SortieStock myOld = oldList.get(i);
                SortieStock t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                SortieStock myNew = newList.get(i);
                SortieStock t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }





    // <-------------------------------------------------------------->


    @Override
    public BigDecimal getTotalSortForYearAndMonth(int year, int month) {
        return dao.calculateTotalSortByYearAndMonth(year, month);
    }


    public BigDecimal getTotalSortForYear(int year) {
        BigDecimal totalIncome = BigDecimal.ZERO;

        for (int month = 1; month <= 12; month++) {

            BigDecimal monthlyTotal = dao.calculateTotalSortByYearAndMonth(year, month);

            totalIncome = totalIncome.add(monthlyTotal != null ? monthlyTotal : BigDecimal.ZERO);
        }

        return totalIncome;
    }


    @Override
    public BigDecimal calculateSortQuantityByYearAndMonth(int year, int month) {
        return dao.calculateSortQuantityByYearAndMonth(year, month);
    }

    @Override
    public BigDecimal getChangeTotalForYear(int year) {
        BigDecimal currentYearTotal = getTotalSortForYear(year);
        BigDecimal previousYearTotal = getTotalSortForYear(year - 1);

        if (previousYearTotal.compareTo(BigDecimal.ZERO) == 0) {
            return currentYearTotal.compareTo(BigDecimal.ZERO) > 0 ? BigDecimal.valueOf(100) : BigDecimal.valueOf(-100);
        }

        return currentYearTotal.subtract(previousYearTotal)
                .divide(previousYearTotal, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
    }
    // <-------------------------------------------------------------->






    @Autowired
    private SortieStockDetailAdminService sortieStockDetailService ;

    public SortieStockAdminServiceImpl(SortieStockDao dao) {
        this.dao = dao;
    }

    private SortieStockDao dao;
}
