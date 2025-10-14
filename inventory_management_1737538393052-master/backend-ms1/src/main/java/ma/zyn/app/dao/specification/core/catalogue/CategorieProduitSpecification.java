package  ma.zyn.app.dao.specification.core.catalogue;

import ma.zyn.app.dao.criteria.core.catalogue.CategorieProduitCriteria;
import ma.zyn.app.bean.core.catalogue.CategorieProduit;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class CategorieProduitSpecification extends  AbstractSpecification<CategorieProduitCriteria, CategorieProduit>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
    }

    public CategorieProduitSpecification(CategorieProduitCriteria criteria) {
        super(criteria);
    }

    public CategorieProduitSpecification(CategorieProduitCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
