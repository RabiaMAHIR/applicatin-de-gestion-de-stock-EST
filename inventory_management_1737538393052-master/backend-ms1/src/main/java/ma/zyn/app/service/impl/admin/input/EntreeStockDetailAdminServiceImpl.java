package ma.zyn.app.service.impl.admin.input;


import ma.zyn.app.bean.core.store.Stock;
import ma.zyn.app.service.facade.admin.store.StockAdminService;
import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.input.EntreeStockDetail;
import ma.zyn.app.dao.criteria.core.input.EntreeStockDetailCriteria;
import ma.zyn.app.dao.facade.core.input.EntreeStockDetailDao;
import ma.zyn.app.dao.specification.core.input.EntreeStockDetailSpecification;
import ma.zyn.app.service.facade.admin.input.EntreeStockDetailAdminService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;
import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
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
import ma.zyn.app.service.facade.admin.input.EntreeStockAdminService ;
import ma.zyn.app.bean.core.input.EntreeStock ;

import java.util.List;
@Service
public class EntreeStockDetailAdminServiceImpl implements EntreeStockDetailAdminService {


    @Autowired
    private StockAdminService stockService;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EntreeStockDetail update(EntreeStockDetail t) {
        EntreeStockDetail loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{EntreeStockDetail.class.getSimpleName(), t.getId().toString()});
        } else {
            BigDecimal oldQuantity = loadedItem.getQuantite();



            BigDecimal quantityDifference = t.getQuantite().subtract(oldQuantity);

            Stock loadedStock = stockService.findByMagazinIdAndProduitId(t.getMagazin().getId(), t.getProduit().getId());
            if (loadedStock != null) {
                loadedStock.setQuantite(loadedStock.getQuantite().add(quantityDifference));
                stockService.update(loadedStock);
            }

            t.setEntreeStock(loadedItem.getEntreeStock());
            dao.save(t);
            return loadedItem;
        }
    }
    public EntreeStockDetail findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public EntreeStockDetail findOrSave(EntreeStockDetail t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            EntreeStockDetail result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<EntreeStockDetail> findAll() {
        return dao.findAll();
    }

    public List<EntreeStockDetail> findByCriteria(EntreeStockDetailCriteria criteria) {
        List<EntreeStockDetail> content = null;
        if (criteria != null) {
            EntreeStockDetailSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private EntreeStockDetailSpecification constructSpecification(EntreeStockDetailCriteria criteria) {
        EntreeStockDetailSpecification mySpecification =  (EntreeStockDetailSpecification) RefelexivityUtil.constructObjectUsingOneParam(EntreeStockDetailSpecification.class, criteria);
        return mySpecification;
    }

    public List<EntreeStockDetail> findPaginatedByCriteria(EntreeStockDetailCriteria criteria, int page, int pageSize, String order, String sortField) {
        EntreeStockDetailSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EntreeStockDetailCriteria criteria) {
        EntreeStockDetailSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<EntreeStockDetail> findByEntreeStockId(Long id){
        return dao.findByEntreeStockId(id);
    }
    public int deleteByEntreeStockId(Long id){
        return dao.deleteByEntreeStockId(id);
    }
    public long countByEntreeStockCode(String code){
        return dao.countByEntreeStockCode(code);
    }
    public List<EntreeStockDetail> findByProduitId(Long id){
        return dao.findByProduitId(id);
    }
    public int deleteByProduitId(Long id){
        return dao.deleteByProduitId(id);
    }
    public long countByProduitCode(String code){
        return dao.countByProduitCode(code);
    }
    public List<EntreeStockDetail> findByMagazinId(Long id){
        return dao.findByMagazinId(id);
    }

    public List<EntreeStockDetail> findByMagazinIdAndProduitId(Long magasinId, Long produitId){
        return dao.findByMagazinIdAndProduitId(magasinId, produitId);
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
    public List<EntreeStockDetail> delete(List<EntreeStockDetail> list) {
		List<EntreeStockDetail> result = new ArrayList();
        if (list != null) {
            for (EntreeStockDetail t : list) {
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
    public EntreeStockDetail create(EntreeStockDetail t) {
        EntreeStockDetail loaded = findByReferenceEntity(t);
        EntreeStockDetail saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public EntreeStockDetail findWithAssociatedLists(Long id){
        EntreeStockDetail result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EntreeStockDetail> update(List<EntreeStockDetail> ts, boolean createIfNotExist) {
        List<EntreeStockDetail> result = new ArrayList<>();
        if (ts != null) {
            for (EntreeStockDetail t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    EntreeStockDetail loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, EntreeStockDetail t, EntreeStockDetail loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public EntreeStockDetail findByReferenceEntity(EntreeStockDetail t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(EntreeStockDetail t){
        if( t != null) {
            t.setEntreeStock(entreeStockService.findOrSave(t.getEntreeStock()));
            t.setProduit(produitService.findOrSave(t.getProduit()));
            t.setMagazin(magasinService.findOrSave(t.getMagazin()));
        }
    }



    public List<EntreeStockDetail> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<EntreeStockDetail>> getToBeSavedAndToBeDeleted(List<EntreeStockDetail> oldList, List<EntreeStockDetail> newList) {
        List<List<EntreeStockDetail>> result = new ArrayList<>();
        List<EntreeStockDetail> resultDelete = new ArrayList<>();
        List<EntreeStockDetail> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<EntreeStockDetail> oldList, List<EntreeStockDetail> newList, List<EntreeStockDetail> resultUpdateOrSave, List<EntreeStockDetail> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                EntreeStockDetail myOld = oldList.get(i);
                EntreeStockDetail t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                EntreeStockDetail myNew = newList.get(i);
                EntreeStockDetail t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }







    @Autowired
    private MagasinAdminService magasinService ;
    @Autowired
    private ProduitAdminService produitService ;
    @Autowired
    private EntreeStockAdminService entreeStockService ;

    public EntreeStockDetailAdminServiceImpl(EntreeStockDetailDao dao) {
        this.dao = dao;
    }

    private EntreeStockDetailDao dao;
}
