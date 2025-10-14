package ma.zyn.app.service.impl.admin.store;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.store.Stock;
import ma.zyn.app.dao.criteria.core.store.StockCriteria;
import ma.zyn.app.dao.facade.core.store.StockDao;
import ma.zyn.app.dao.specification.core.store.StockSpecification;
import ma.zyn.app.service.facade.admin.store.StockAdminService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;
import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zyn.app.service.facade.admin.store.MagasinAdminService ;
import ma.zyn.app.bean.core.store.Magasin ;
import ma.zyn.app.service.facade.admin.catalogue.ProduitAdminService ;
import ma.zyn.app.bean.core.catalogue.Produit ;

import java.util.List;

@Service
public class StockAdminServiceImpl implements StockAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Stock update(Stock t) {
        Stock loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Stock.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Stock findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Stock findOrSave(Stock t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Stock result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Stock> findAll() {
        return dao.findAll();
    }

    public List<Stock> findByCriteria(StockCriteria criteria) {
        List<Stock> content = null;
        if (criteria != null) {
            StockSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private StockSpecification constructSpecification(StockCriteria criteria) {
        StockSpecification mySpecification =  (StockSpecification) RefelexivityUtil.constructObjectUsingOneParam(StockSpecification.class, criteria);
        return mySpecification;
    }

    public List<Stock> findPaginatedByCriteria(StockCriteria criteria, int page, int pageSize, String order, String sortField) {
        StockSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(StockCriteria criteria) {
        StockSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }



    // <-------------------------------------------------------------->

    @Override
    public List<Stock> findStocksByMagazinId(String labelMagazin) {
        return dao.findStocksByMagazinId(labelMagazin);
    }


    @Override
    public List<Map<String, BigDecimal>> findTotalQuantitiesForAllProduits() {
        List<Object[]> results = dao.findTotalQuantitiesForAllProduits();
        List<Map<String, BigDecimal>> totalQuantities = new ArrayList<>();

        for (Object[] result : results) {
            String produitName = (String) result[0];
            BigDecimal totalQuantity = (BigDecimal) result[1];
            Map<String, BigDecimal> quantityMap = new HashMap<>();
            quantityMap.put(produitName, totalQuantity);
            totalQuantities.add(quantityMap);
        }

        return totalQuantities;
    }
    // <-------------------------------------------------------------->




    public List<Stock> findByProduitId(Long id){
        return dao.findByProduitId(id);
    }
    public int deleteByProduitId(Long id){
        return dao.deleteByProduitId(id);
    }
    public long countByProduitCode(String code){
        return dao.countByProduitCode(code);
    }
    public List<Stock> findByMagazinId(Long id){
        return dao.findByMagazinId(id);
    }
    public int deleteByMagazinId(Long id){
        return dao.deleteByMagazinId(id);
    }
    public long countByMagazinCode(String code){
        return dao.countByMagazinCode(code);
    }
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Stock> delete(List<Stock> list) {
		List<Stock> result = new ArrayList();
        if (list != null) {
            for (Stock t : list) {
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
    public Stock create(Stock t) {
        Stock loaded = findByReferenceEntity(t);
        Stock saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Stock findWithAssociatedLists(Long id){
        Stock result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Stock> update(List<Stock> ts, boolean createIfNotExist) {
        List<Stock> result = new ArrayList<>();
        if (ts != null) {
            for (Stock t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Stock loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Stock t, Stock loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Stock findByReferenceEntity(Stock t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Stock t){
        if( t != null) {
            t.setProduit(produitService.findOrSave(t.getProduit()));
            t.setMagazin(magasinService.findOrSave(t.getMagazin()));
        }
    }



    public List<Stock> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Stock>> getToBeSavedAndToBeDeleted(List<Stock> oldList, List<Stock> newList) {
        List<List<Stock>> result = new ArrayList<>();
        List<Stock> resultDelete = new ArrayList<>();
        List<Stock> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Stock> oldList, List<Stock> newList, List<Stock> resultUpdateOrSave, List<Stock> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Stock myOld = oldList.get(i);
                Stock t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Stock myNew = newList.get(i);
                Stock t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }

    @Override
    public Stock findByMagazinIdAndProduitId(Long idMagazin, Long idProduit) {
        return dao.findByMagazinIdAndProduitId(idMagazin,idProduit);
    }






    @Autowired
    private MagasinAdminService magasinService ;
    @Autowired
    private ProduitAdminService produitService ;

    public StockAdminServiceImpl(StockDao dao) {
        this.dao = dao;
    }

    private StockDao dao;
}
