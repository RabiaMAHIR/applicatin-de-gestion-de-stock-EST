package ma.zyn.app.service.impl.admin.output;


import ma.zyn.app.bean.core.input.EntreeStockDetail;
import ma.zyn.app.bean.core.store.Stock;
import ma.zyn.app.service.facade.admin.store.StockAdminService;
import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.output.SortieStockDetail;
import ma.zyn.app.dao.criteria.core.output.SortieStockDetailCriteria;
import ma.zyn.app.dao.facade.core.output.SortieStockDetailDao;
import ma.zyn.app.dao.specification.core.output.SortieStockDetailSpecification;
import ma.zyn.app.service.facade.admin.output.SortieStockDetailAdminService;
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

import ma.zyn.app.service.facade.admin.output.SortieStockAdminService ;
import ma.zyn.app.bean.core.output.SortieStock ;
import ma.zyn.app.service.facade.admin.store.MagasinAdminService ;
import ma.zyn.app.bean.core.store.Magasin ;
import ma.zyn.app.service.facade.admin.catalogue.ProduitAdminService ;
import ma.zyn.app.bean.core.catalogue.Produit ;

import java.util.List;
@Service
public class SortieStockDetailAdminServiceImpl implements SortieStockDetailAdminService {

    @Autowired
    private StockAdminService stockService;


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public SortieStockDetail update(SortieStockDetail t) {
        SortieStockDetail loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{SortieStockDetail.class.getSimpleName(), t.getId().toString()});
        } else {
            BigDecimal oldQuantity = loadedItem.getQuantite();

            System.out.println("Old Quantity: " + oldQuantity);
            System.out.println("New Quantity: " + t.getQuantite());

            // احسب الفرق
            BigDecimal quantityDifference = t.getQuantite().subtract(oldQuantity);

            System.out.println("Quantity Difference: " + quantityDifference);

            // تحديث الكمية في المخزون بناءً على الفرق
            Stock loadedStock = stockService.findByMagazinIdAndProduitId(t.getMagazin().getId(), t.getProduit().getId());
            if (loadedStock != null) {
                // تحديث الكمية في المخزون
                loadedStock.setQuantite(loadedStock.getQuantite().subtract(quantityDifference));
                // تأكد من أن الكمية لا تصبح سالبة
                if (loadedStock.getQuantite().compareTo(BigDecimal.ZERO) < 0) {
                    loadedStock.setQuantite(BigDecimal.ZERO);
                }
                stockService.update(loadedStock);
                System.out.println("Updated Stock Quantity: " + loadedStock.getQuantite());
            }

            // تحديث تفاصيل SortieStock
            t.setSortieStock(loadedItem.getSortieStock());
            dao.save(t); // حفظ الكائن المحدث
            return loadedItem;
        }
    }
    public SortieStockDetail findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public SortieStockDetail findOrSave(SortieStockDetail t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            SortieStockDetail result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<SortieStockDetail> findAll() {
        return dao.findAll();
    }

    public List<SortieStockDetail> findByCriteria(SortieStockDetailCriteria criteria) {
        List<SortieStockDetail> content = null;
        if (criteria != null) {
            SortieStockDetailSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private SortieStockDetailSpecification constructSpecification(SortieStockDetailCriteria criteria) {
        SortieStockDetailSpecification mySpecification =  (SortieStockDetailSpecification) RefelexivityUtil.constructObjectUsingOneParam(SortieStockDetailSpecification.class, criteria);
        return mySpecification;
    }

    public List<SortieStockDetail> findPaginatedByCriteria(SortieStockDetailCriteria criteria, int page, int pageSize, String order, String sortField) {
        SortieStockDetailSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(SortieStockDetailCriteria criteria) {
        SortieStockDetailSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<SortieStockDetail> findBySortieStockId(Long id){
        return dao.findBySortieStockId(id);
    }
    public int deleteBySortieStockId(Long id){
        return dao.deleteBySortieStockId(id);
    }
    public long countBySortieStockCode(String code){
        return dao.countBySortieStockCode(code);
    }
    public List<SortieStockDetail> findByProduitId(Long id){
        return dao.findByProduitId(id);
    }
    public int deleteByProduitId(Long id){
        return dao.deleteByProduitId(id);
    }
    public long countByProduitCode(String code){
        return dao.countByProduitCode(code);
    }
    public List<SortieStockDetail> findByMagazinId(Long id){
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

    public List<SortieStockDetail> findByMagazinIdAndProduitId(Long magasinId, Long produitId){
        return dao.findByMagazinIdAndProduitId(magasinId, produitId);
    }



    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<SortieStockDetail> delete(List<SortieStockDetail> list) {
		List<SortieStockDetail> result = new ArrayList();
        if (list != null) {
            for (SortieStockDetail t : list) {
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
    public SortieStockDetail create(SortieStockDetail t) {
        SortieStockDetail loaded = findByReferenceEntity(t);
        SortieStockDetail saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public SortieStockDetail findWithAssociatedLists(Long id){
        SortieStockDetail result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<SortieStockDetail> update(List<SortieStockDetail> ts, boolean createIfNotExist) {
        List<SortieStockDetail> result = new ArrayList<>();
        if (ts != null) {
            for (SortieStockDetail t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    SortieStockDetail loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, SortieStockDetail t, SortieStockDetail loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public SortieStockDetail findByReferenceEntity(SortieStockDetail t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(SortieStockDetail t){
        if( t != null) {
            t.setSortieStock(sortieStockService.findOrSave(t.getSortieStock()));
            t.setProduit(produitService.findOrSave(t.getProduit()));
            t.setMagazin(magasinService.findOrSave(t.getMagazin()));
        }
    }



    public List<SortieStockDetail> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<SortieStockDetail>> getToBeSavedAndToBeDeleted(List<SortieStockDetail> oldList, List<SortieStockDetail> newList) {
        List<List<SortieStockDetail>> result = new ArrayList<>();
        List<SortieStockDetail> resultDelete = new ArrayList<>();
        List<SortieStockDetail> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<SortieStockDetail> oldList, List<SortieStockDetail> newList, List<SortieStockDetail> resultUpdateOrSave, List<SortieStockDetail> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                SortieStockDetail myOld = oldList.get(i);
                SortieStockDetail t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                SortieStockDetail myNew = newList.get(i);
                SortieStockDetail t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private SortieStockAdminService sortieStockService ;
    @Autowired
    private MagasinAdminService magasinService ;
    @Autowired
    private ProduitAdminService produitService ;

    public SortieStockDetailAdminServiceImpl(SortieStockDetailDao dao) {
        this.dao = dao;
    }

    private SortieStockDetailDao dao;
}
