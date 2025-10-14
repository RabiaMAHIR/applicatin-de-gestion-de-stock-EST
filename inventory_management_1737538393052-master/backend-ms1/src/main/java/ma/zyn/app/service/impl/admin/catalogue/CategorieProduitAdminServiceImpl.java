package ma.zyn.app.service.impl.admin.catalogue;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.catalogue.CategorieProduit;
import ma.zyn.app.dao.criteria.core.catalogue.CategorieProduitCriteria;
import ma.zyn.app.dao.facade.core.catalogue.CategorieProduitDao;
import ma.zyn.app.dao.specification.core.catalogue.CategorieProduitSpecification;
import ma.zyn.app.service.facade.admin.catalogue.CategorieProduitAdminService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;
import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Service
public class CategorieProduitAdminServiceImpl implements CategorieProduitAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public CategorieProduit update(CategorieProduit t) {
        CategorieProduit loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{CategorieProduit.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public CategorieProduit findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public CategorieProduit findOrSave(CategorieProduit t) {
        if (t != null) {
            CategorieProduit result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<CategorieProduit> findAll() {
        return dao.findAll();
    }

    public List<CategorieProduit> findByCriteria(CategorieProduitCriteria criteria) {
        List<CategorieProduit> content = null;
        if (criteria != null) {
            CategorieProduitSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private CategorieProduitSpecification constructSpecification(CategorieProduitCriteria criteria) {
        CategorieProduitSpecification mySpecification =  (CategorieProduitSpecification) RefelexivityUtil.constructObjectUsingOneParam(CategorieProduitSpecification.class, criteria);
        return mySpecification;
    }

    public List<CategorieProduit> findPaginatedByCriteria(CategorieProduitCriteria criteria, int page, int pageSize, String order, String sortField) {
        CategorieProduitSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CategorieProduitCriteria criteria) {
        CategorieProduitSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
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
    public List<CategorieProduit> delete(List<CategorieProduit> list) {
		List<CategorieProduit> result = new ArrayList();
        if (list != null) {
            for (CategorieProduit t : list) {
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
    public CategorieProduit create(CategorieProduit t) {
        CategorieProduit loaded = findByReferenceEntity(t);
        CategorieProduit saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public CategorieProduit findWithAssociatedLists(Long id){
        CategorieProduit result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<CategorieProduit> update(List<CategorieProduit> ts, boolean createIfNotExist) {
        List<CategorieProduit> result = new ArrayList<>();
        if (ts != null) {
            for (CategorieProduit t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    CategorieProduit loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, CategorieProduit t, CategorieProduit loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public CategorieProduit findByReferenceEntity(CategorieProduit t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<CategorieProduit> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<CategorieProduit>> getToBeSavedAndToBeDeleted(List<CategorieProduit> oldList, List<CategorieProduit> newList) {
        List<List<CategorieProduit>> result = new ArrayList<>();
        List<CategorieProduit> resultDelete = new ArrayList<>();
        List<CategorieProduit> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<CategorieProduit> oldList, List<CategorieProduit> newList, List<CategorieProduit> resultUpdateOrSave, List<CategorieProduit> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                CategorieProduit myOld = oldList.get(i);
                CategorieProduit t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                CategorieProduit myNew = newList.get(i);
                CategorieProduit t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public CategorieProduitAdminServiceImpl(CategorieProduitDao dao) {
        this.dao = dao;
    }

    private CategorieProduitDao dao;
}
