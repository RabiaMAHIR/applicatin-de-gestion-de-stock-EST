package ma.zyn.app.service.impl.admin.store;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.store.Magasin;
import ma.zyn.app.dao.criteria.core.store.MagasinCriteria;
import ma.zyn.app.dao.facade.core.store.MagasinDao;
import ma.zyn.app.dao.specification.core.store.MagasinSpecification;
import ma.zyn.app.service.facade.admin.store.MagasinAdminService;
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
public class MagasinAdminServiceImpl implements MagasinAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Magasin update(Magasin t) {
        Magasin loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Magasin.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Magasin findById(Long id) {
        return dao.findById(id).orElse(null);
    }



    // <-------------------------------------------------------------->
    @Override
    public long countMagasins() {
        return dao.count();
    }

    // <-------------------------------------------------------------->



    public Magasin findOrSave(Magasin t) {
        if (t != null) {
            Magasin result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Magasin> findAll() {
        return dao.findAll();
    }

    public List<Magasin> findByCriteria(MagasinCriteria criteria) {
        List<Magasin> content = null;
        if (criteria != null) {
            MagasinSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private MagasinSpecification constructSpecification(MagasinCriteria criteria) {
        MagasinSpecification mySpecification =  (MagasinSpecification) RefelexivityUtil.constructObjectUsingOneParam(MagasinSpecification.class, criteria);
        return mySpecification;
    }

    public List<Magasin> findPaginatedByCriteria(MagasinCriteria criteria, int page, int pageSize, String order, String sortField) {
        MagasinSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(MagasinCriteria criteria) {
        MagasinSpecification mySpecification = constructSpecification(criteria);
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
    public List<Magasin> delete(List<Magasin> list) {
		List<Magasin> result = new ArrayList();
        if (list != null) {
            for (Magasin t : list) {
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
    public Magasin create(Magasin t) {
        Magasin loaded = findByReferenceEntity(t);
        Magasin saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Magasin findWithAssociatedLists(Long id){
        Magasin result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Magasin> update(List<Magasin> ts, boolean createIfNotExist) {
        List<Magasin> result = new ArrayList<>();
        if (ts != null) {
            for (Magasin t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Magasin loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Magasin t, Magasin loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Magasin findByReferenceEntity(Magasin t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Magasin> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Magasin>> getToBeSavedAndToBeDeleted(List<Magasin> oldList, List<Magasin> newList) {
        List<List<Magasin>> result = new ArrayList<>();
        List<Magasin> resultDelete = new ArrayList<>();
        List<Magasin> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Magasin> oldList, List<Magasin> newList, List<Magasin> resultUpdateOrSave, List<Magasin> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Magasin myOld = oldList.get(i);
                Magasin t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Magasin myNew = newList.get(i);
                Magasin t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public MagasinAdminServiceImpl(MagasinDao dao) {
        this.dao = dao;
    }

    private MagasinDao dao;
}
