package ma.zyn.app.service.impl.admin.input;


import ma.zyn.app.bean.core.store.Stock;
import ma.zyn.app.service.facade.admin.store.StockAdminService;
import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.input.EntreeStock;
import ma.zyn.app.dao.criteria.core.input.EntreeStockCriteria;
import ma.zyn.app.dao.facade.core.input.EntreeStockDao;
import ma.zyn.app.dao.specification.core.input.EntreeStockSpecification;
import ma.zyn.app.service.facade.admin.input.EntreeStockAdminService;
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

import ma.zyn.app.service.facade.admin.agent.FournisseurAdminService ;
import ma.zyn.app.bean.core.agent.Fournisseur ;
import ma.zyn.app.service.facade.admin.input.EntreeStockDetailAdminService ;
import ma.zyn.app.bean.core.input.EntreeStockDetail ;

import java.util.List;
@Service
public class EntreeStockAdminServiceImpl implements EntreeStockAdminService {

    @Autowired
    private StockAdminService stockService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EntreeStock update(EntreeStock t) {
        EntreeStock loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{EntreeStock.class.getSimpleName(), t.getId().toString()});
        } else {
            if (t.getEntreeStockDetails() != null) {
                t.getEntreeStockDetails().forEach(element -> {
                    BigDecimal oldQuantity = loadedItem.getEntreeStockDetails().stream()
                            .filter(detail -> detail.getProduit().getId().equals(element.getProduit().getId()) &&
                                    detail.getMagazin().getId().equals(element.getMagazin().getId()))
                            .map(EntreeStockDetail::getQuantite)
                            .findFirst()
                            .orElse(BigDecimal.ZERO);

                    BigDecimal quantityDifference = element.getQuantite().subtract(oldQuantity);

                    Stock loadedStock = stockService.findByMagazinIdAndProduitId(element.getMagazin().getId(), element.getProduit().getId());
                    if (loadedStock != null) {
                        loadedStock.setQuantite(loadedStock.getQuantite().add(quantityDifference));
                        stockService.update(loadedStock);
                        System.out.println("Updated Stock Quantity: " + loadedStock.getQuantite());
                    } else {
                        Stock stock = new Stock(element.getMagazin(), element.getProduit(), element.getQuantite());
                        stockService.create(stock);
                    }

                    element.setEntreeStock(loadedItem);
                    entreeStockDetailService.update(element);
                });
            }
            dao.save(t);
            return loadedItem;
        }
    }
    public EntreeStock findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public EntreeStock findOrSave(EntreeStock t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            EntreeStock result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<EntreeStock> findAll() {
        return dao.findAll();
    }

    public List<EntreeStock> findByCriteria(EntreeStockCriteria criteria) {
        List<EntreeStock> content = null;
        if (criteria != null) {
            EntreeStockSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private EntreeStockSpecification constructSpecification(EntreeStockCriteria criteria) {
        EntreeStockSpecification mySpecification =  (EntreeStockSpecification) RefelexivityUtil.constructObjectUsingOneParam(EntreeStockSpecification.class, criteria);
        return mySpecification;
    }

    public List<EntreeStock> findPaginatedByCriteria(EntreeStockCriteria criteria, int page, int pageSize, String order, String sortField) {
        EntreeStockSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EntreeStockCriteria criteria) {
        EntreeStockSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<EntreeStock> findByFournisseurId(Long id){
        return dao.findByFournisseurId(id);
    }
    public int deleteByFournisseurId(Long id){
        return dao.deleteByFournisseurId(id);
    }
    public long countByFournisseurCode(String code){
        return dao.countByFournisseurCode(code);
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
        entreeStockDetailService.deleteByEntreeStockId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EntreeStock> delete(List<EntreeStock> list) {
		List<EntreeStock> result = new ArrayList();
        if (list != null) {
            for (EntreeStock t : list) {
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
    public EntreeStock create(EntreeStock t) {
        EntreeStock loaded = findByReferenceEntity(t);
        EntreeStock saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getEntreeStockDetails() != null) {
                t.getEntreeStockDetails().forEach(element-> {
                    Stock loadedStock = stockService.findByMagazinIdAndProduitId(element.getMagazin().getId(),element.getProduit().getId());
                    if (loadedStock == null) {
                        Stock stock = new Stock(element.getMagazin(),element.getProduit(),element.getQuantite());
                        stockService.create(stock);
                    }else{
                        loadedStock.setQuantite( element.getQuantite().add(loadedStock.getQuantite()));
                        stockService.update(loadedStock);
                    }
                    element.setEntreeStock(saved);
                    entreeStockDetailService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public EntreeStock findWithAssociatedLists(Long id){
        EntreeStock result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setEntreeStockDetails(entreeStockDetailService.findByEntreeStockId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EntreeStock> update(List<EntreeStock> ts, boolean createIfNotExist) {
        List<EntreeStock> result = new ArrayList<>();
        if (ts != null) {
            for (EntreeStock t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    EntreeStock loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, EntreeStock t, EntreeStock loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(EntreeStock entreeStock){
    if(entreeStock !=null && entreeStock.getId() != null){
        List<List<EntreeStockDetail>> resultEntreeStockDetails= entreeStockDetailService.getToBeSavedAndToBeDeleted(entreeStockDetailService.findByEntreeStockId(entreeStock.getId()),entreeStock.getEntreeStockDetails());
            entreeStockDetailService.delete(resultEntreeStockDetails.get(1));
        emptyIfNull(resultEntreeStockDetails.get(0)).forEach(e -> e.setEntreeStock(entreeStock));
        entreeStockDetailService.update(resultEntreeStockDetails.get(0),true);
        }
    }








    public EntreeStock findByReferenceEntity(EntreeStock t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(EntreeStock t){
        if( t != null) {
            t.setFournisseur(fournisseurService.findOrSave(t.getFournisseur()));
        }
    }



    public List<EntreeStock> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<EntreeStock>> getToBeSavedAndToBeDeleted(List<EntreeStock> oldList, List<EntreeStock> newList) {
        List<List<EntreeStock>> result = new ArrayList<>();
        List<EntreeStock> resultDelete = new ArrayList<>();
        List<EntreeStock> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<EntreeStock> oldList, List<EntreeStock> newList, List<EntreeStock> resultUpdateOrSave, List<EntreeStock> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                EntreeStock myOld = oldList.get(i);
                EntreeStock t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                EntreeStock myNew = newList.get(i);
                EntreeStock t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    public BigDecimal getTotalForYearAndMonth(int year, int month) {
        return dao.calculateTotalByYearAndMonth(year, month);
    }
    public BigDecimal getTotalForYear(int year) {
        BigDecimal totalIncome = BigDecimal.ZERO;

        for (int month = 1; month <= 12; month++) {

            BigDecimal monthlyTotal = dao.calculateTotalByYearAndMonth(year, month);

            totalIncome = totalIncome.add(monthlyTotal != null ? monthlyTotal : BigDecimal.ZERO);
        }

        return totalIncome;
    }
    @Override
    public BigDecimal getChangeTotalForYear(int year) {
        BigDecimal currentYearTotal = getTotalForYear(year);
        BigDecimal previousYearTotal = getTotalForYear(year - 1);

        if (previousYearTotal.compareTo(BigDecimal.ZERO) == 0) {
            return currentYearTotal.compareTo(BigDecimal.ZERO) > 0 ? BigDecimal.valueOf(100) : BigDecimal.valueOf(-100);
        }

        return currentYearTotal.subtract(previousYearTotal)
                .divide(previousYearTotal, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
    }
    @Override
    public BigDecimal calculateQuantityByYearAndMonth(int year, int month) {
        return dao.calculateQuantityByYearAndMonth(year, month);
    }

    // <-------------------------------------------------------------->


    @Autowired
    private FournisseurAdminService fournisseurService ;
    @Autowired
    private EntreeStockDetailAdminService entreeStockDetailService ;

    public EntreeStockAdminServiceImpl(EntreeStockDao dao) {
        this.dao = dao;
    }

    private EntreeStockDao dao;
}
