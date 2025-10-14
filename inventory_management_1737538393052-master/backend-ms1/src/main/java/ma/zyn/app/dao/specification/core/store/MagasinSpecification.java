package  ma.zyn.app.dao.specification.core.store;

import ma.zyn.app.dao.criteria.core.store.MagasinCriteria;
import ma.zyn.app.bean.core.store.Magasin;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class MagasinSpecification extends  AbstractSpecification<MagasinCriteria, Magasin>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("label", criteria.getLabel(),criteria.getLabelLike());
    }

    public MagasinSpecification(MagasinCriteria criteria) {
        super(criteria);
    }

    public MagasinSpecification(MagasinCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
