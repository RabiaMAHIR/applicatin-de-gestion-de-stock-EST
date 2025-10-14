package ma.zyn.app.service.impl.admin.agent;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.agent.Fournisseur;
import ma.zyn.app.dao.criteria.core.agent.FournisseurCriteria;
import ma.zyn.app.dao.facade.core.agent.FournisseurDao;
import ma.zyn.app.dao.specification.core.agent.FournisseurSpecification;
import ma.zyn.app.service.facade.admin.agent.FournisseurAdminService;
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
public class FournisseurAdminServiceImpl implements FournisseurAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Fournisseur update(Fournisseur t) {
        Fournisseur loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Fournisseur.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Fournisseur findById(Long id) {
        return dao.findById(id).orElse(null);
    }

    // <-------------------------------------------------------------->
    @Override
    public long countFournisseurs() {
        return dao.count();
    }

    // <-------------------------------------------------------------->




    public Fournisseur findOrSave(Fournisseur t) {
        if (t != null) {
            Fournisseur result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Fournisseur> findAll() {
        return dao.findAll();
    }

    public List<Fournisseur> findByCriteria(FournisseurCriteria criteria) {
        List<Fournisseur> content = null;
        if (criteria != null) {
            FournisseurSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private FournisseurSpecification constructSpecification(FournisseurCriteria criteria) {
        FournisseurSpecification mySpecification =  (FournisseurSpecification) RefelexivityUtil.constructObjectUsingOneParam(FournisseurSpecification.class, criteria);
        return mySpecification;
    }

    public List<Fournisseur> findPaginatedByCriteria(FournisseurCriteria criteria, int page, int pageSize, String order, String sortField) {
        FournisseurSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(FournisseurCriteria criteria) {
        FournisseurSpecification mySpecification = constructSpecification(criteria);
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
    public List<Fournisseur> delete(List<Fournisseur> list) {
		List<Fournisseur> result = new ArrayList();
        if (list != null) {
            for (Fournisseur t : list) {
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
    public Fournisseur create(Fournisseur t) {
        Fournisseur loaded = findByReferenceEntity(t);
        Fournisseur saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Fournisseur findWithAssociatedLists(Long id){
        Fournisseur result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Fournisseur> update(List<Fournisseur> ts, boolean createIfNotExist) {
        List<Fournisseur> result = new ArrayList<>();
        if (ts != null) {
            for (Fournisseur t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Fournisseur loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Fournisseur t, Fournisseur loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Fournisseur findByReferenceEntity(Fournisseur t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Fournisseur> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Fournisseur>> getToBeSavedAndToBeDeleted(List<Fournisseur> oldList, List<Fournisseur> newList) {
        List<List<Fournisseur>> result = new ArrayList<>();
        List<Fournisseur> resultDelete = new ArrayList<>();
        List<Fournisseur> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Fournisseur> oldList, List<Fournisseur> newList, List<Fournisseur> resultUpdateOrSave, List<Fournisseur> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Fournisseur myOld = oldList.get(i);
                Fournisseur t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Fournisseur myNew = newList.get(i);
                Fournisseur t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public FournisseurAdminServiceImpl(FournisseurDao dao) {
        this.dao = dao;
    }

    private FournisseurDao dao;
}
